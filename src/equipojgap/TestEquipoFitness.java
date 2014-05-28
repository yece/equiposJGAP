/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipojgap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import modelo.Bd;
import modelo.Equipo;
import equipojgap.algGenetico;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;

/**
 * @author Ceci
 */
public class TestEquipoFitness {
    
    private Configuration conf;
    private SwappingMutationOperator swapper;
    private EquipoFitnessFunction fitnessFunction = null;
    public double solution1;
    public ArrayList<Equipo> p;
    
    public ArrayList<Equipo> equipos = Bd.buscarEquipo();
     public TestEquipoFitness() {
    }
    public TestEquipoFitness(ArrayList<Equipo> equipos) {
    }    

    private static final int MAX_ALLOWED_EVOLUTIONS = 50;
    private Chromosome movieChromosome = null;
    
    public void initialize() throws Exception {
        conf = new DefaultConfiguration();
        Configuration.resetProperty(Configuration.PROPERTY_FITEVAL_INST);
        conf.setFitnessEvaluator(new DefaultFitnessEvaluator());
        conf.getGeneticOperators().clear();
        //Operador de intercambio de mutación
        swapper = new SwappingMutationOperator(conf);
        conf.addGeneticOperator(swapper);
        //preservar la función de aptitud inicial
        conf.setPreservFittestIndividual(true);
        //Tamaño de la población
        conf.setPopulationSize(4000);
        //Mantiene el tamaño de la población
        conf.setKeepPopulationSizeConstant(false);
        
        fitnessFunction = new EquipoFitnessFunction(equipos);
        //se fija la función fitness a la configuración
        conf.setFitnessFunction(fitnessFunction);

        Gene[] equipoGenes = new Gene[4];
        equipoGenes[0] = new IntegerGene(conf, 0, equipos.size() - 1);
        equipoGenes[1] = new IntegerGene(conf, 0, equipos.size() - 1);
        equipoGenes[2] = new IntegerGene(conf, 0, equipos.size() - 1);
        equipoGenes[3] = new IntegerGene(conf, 0, equipos.size() - 1);
        
        movieChromosome = new Chromosome(conf, equipoGenes);
        equipoGenes[0].setAllele(0);
        equipoGenes[1].setAllele(1);
        equipoGenes[2].setAllele(2);
        equipoGenes[3].setAllele(3);

        conf.setSampleChromosome(movieChromosome);
        Configuration.reset();
    }

    public String ms="";
    // Esta función se encarga de crear el genotype
    // y se realiza la evolución de la población
    //para encontrar la mejor solución
    public ArrayList<Equipo> testSelectFittestEquipos() throws Exception {
        Genotype population = Genotype.randomInitialGenotype(conf);
        IChromosome bestSolutionSoFar = movieChromosome;
        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            population.evolve();
            IChromosome candidateBestSolution = population.getFittestChromosome();
            if (candidateBestSolution.getFitnessValue() > bestSolutionSoFar.getFitnessValue()) {
                bestSolutionSoFar = candidateBestSolution;   
            }
        }
        solution1=bestSolutionSoFar.getFitnessValue();
        ms=bestSolutionSoFar.toString();
        return printSolutionTabla(bestSolutionSoFar, equipos);
    }
   
    public ArrayList<Equipo> printSolutionTabla(IChromosome solution, ArrayList<Equipo> equipo) {
        ArrayList<Equipo> ganadores= new ArrayList<Equipo>();
        for (int i = 0; i < solution.size(); i++) {
            int index = (Integer) solution.getGene(i).getAllele();
            Equipo eqGanadores = (Equipo) equipo.get(index);
            ganadores.add(eqGanadores);
        }
        return ganadores;
    }
    
       
    public void presentarEquiposGanadores(JTable tabla) throws Exception{
        this.initialize();
        ArrayList<Equipo> rs2=this.testSelectFittestEquipos();
        
        int numfilas=rs2.size();
        Object[] columns = new String [] {"Equipo","Ranking","Grupo","# Copa","Partidos"};
        Object [][] datos = new String [numfilas][columns.length];
        for(int i = 0; i< rs2.size();i++){
                datos[i][0] = rs2.get(i).getNombre();
                datos[i][1] = String.valueOf(rs2.get(i).getrFifa());
                datos[i][2] = String.valueOf(rs2.get(i).getGrupo());
                datos[i][3] = String.valueOf(rs2.get(i).getnCopas());
                datos[i][4] = String.valueOf("["+rs2.get(i).getPartidoA()+"] ["+rs2.get(i).getPartidoB()+"] ["+rs2.get(i).getPartidoC()+"]");
        }
        javax.swing.table.TableModel dataModel = new javax.swing.table.DefaultTableModel(datos,columns); 
        tabla.setModel(dataModel);
    }
    
//    public void printSolution(IChromosome solution, ArrayList<Equipo> equipo) {
//        System.out.println("#################################################################################################################");
//        System.out.println("Valor del Fitness: " + solution.getFitnessValue());
//
//        for (int i = 0; i < solution.size(); i++) {
//            int index = (Integer) solution.getGene(i).getAllele();
//            Equipo movie = (Equipo) equipo.get(index);
//            System.out.println(movie.toString());
//        }
//        System.out.println("#################################################################################################################");
//    }
    public Equipo buscarEquipo(String nombre){
        Equipo equipo = new Equipo();
        for(Equipo e: equipos){
            if(e.getNombre().equalsIgnoreCase(nombre)){
                equipo=e;
            }
        }
        return equipo;
    }
 
//    public boolean ModificarEquipo(int index, Equipo e){
//        this.loadMovies().set(index, e);
//        return true;
//    }
    
    public int buscarIndexEquipo1(String nombre){
        int index=-1;
        for(int i=0;i<Bd.buscarEquipo().size();i++){
            if(Bd.buscarEquipo().get(i).getNombre().equals(nombre)){
                index=i;
                break;
            }
        }
        //System.out.println("----- "+index);
        return index;
    }
    
    public int buscarIndexEquipo(String nombre){
//        Equipo equipo = new Equipo();
        System.out.println(nombre);
        int index = 0;
        
        Bd.buscarEquipo().indexOf(nombre);
        System.out.println(index +"  22222222222  "+Bd.buscarEquipo().indexOf(nombre));
        return index;
    }
    
//    public void presentarEquipos(JTable tabla){
//        ArrayList<Equipo> rs2=Bd.buscarEquipo();
//        int numfilas=rs2.size();
//        Object[] columns = new String [] {"Equipo"};
//        Object [][] datos = new String [numfilas][columns.length];
//        for(int i = 0; i< rs2.size();i++){
//            datos[i][0] = rs2.get(i).getNombre();
//        }
//        javax.swing.table.TableModel dataModel = new javax.swing.table.DefaultTableModel(datos,columns); 
//        tabla.setModel(dataModel);
//    }

}