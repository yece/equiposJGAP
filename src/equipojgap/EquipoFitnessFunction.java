package equipojgap;


import java.util.ArrayList;
import java.util.List;
import modelo.Equipo;
import org.jgap.Chromosome;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import org.jgap.impl.IntegerGene;

public class EquipoFitnessFunction extends FitnessFunction {

    ArrayList<Equipo> equipo = new ArrayList();
    public EquipoFitnessFunction(ArrayList<Equipo> equipo) {
        this.equipo = equipo;
    }
    
    public static final ArrayList<String> ch= new ArrayList();
    @Override
    //Método de evaluación de los cromosomas de cada equipo 
       protected double evaluate(IChromosome chromosome) {
        double score = 0;
        List dups = new ArrayList();
        int badSolution = 1;
        for (int i = 0; i < chromosome.size(); i++) {
            IntegerGene agene = (IntegerGene) chromosome.getGene(i);
            int index = (Integer) chromosome.getGene(i).getAllele();
            if (dups.contains(index)) {
                badSolution = 0;
            } else {
                dups.add(index);
            }
            Equipo equipo_ = (Equipo) this.equipo.get(index);
            double genreScore = getGenreScore(equipo_);
//            if (genreScore == 0) {
//                badSolution = 0;
//            };
            score = (score) + (genreScore);
        }
        ch.add(chromosome.toString());
        return (score * badSolution);
    }
    
 int cont=0;
 //Método para evaluar las características de cada equipo  
 private double getGenreScore(Equipo equipo) {
        double genreScore = 0;
        genreScore = (genreScore+(equipo.getnCopas()+3)+
                ((1/equipo.getrFifa()+2))+
                ((equipo.getPartidoA()+equipo.getPartidoB()+equipo.getPartidoC())*0.5));
        return genreScore;
//             if(equipo.getnCopas()>0){
//                    genreScore = genreScore+(equipo.getnCopas()+5);
//             }           
//            if (equipo.getrFifa()>0 && (equipo.getrFifa()<=10)){
//                genreScore = genreScore+((1/equipo.getrFifa())+4);
//            }else if (equipo.getrFifa()>10 && (equipo.getrFifa()<=20)){
//                genreScore = genreScore+((1/equipo.getrFifa())+3);
//            }else if (equipo.getrFifa()>20 && (equipo.getrFifa()<=30)){
//                genreScore = genreScore+((1/equipo.getrFifa())+2);
//            }else if (equipo.getrFifa()>30 && (equipo.getrFifa()<=40)){
//                genreScore = genreScore+((1/equipo.getrFifa())+1);
//            }else if (equipo.getrFifa()>40 && (equipo.getrFifa()<=50)){
//                genreScore = genreScore+((1/equipo.getrFifa()));
//            }
//            if ((equipo.getPartidoA()==-1)||(equipo.getPartidoB()==-1)||(equipo.getPartidoB()==-1)){
//                genreScore = genreScore;
//            }else if ((equipo.getPartidoA()==1)||(equipo.getPartidoB()==1)||(equipo.getPartidoB()==1)){
//                genreScore = genreScore + 0.5;
//            }if ((equipo.getPartidoA()==0)||(equipo.getPartidoB()==0)||(equipo.getPartidoB()==0)){
//                genreScore = genreScore + 1;
//            }
//            
                    
    }
}
