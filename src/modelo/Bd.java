package modelo;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Bd {
    public static Connection cn;
    
    public Bd(){}
    
      public static Connection getCn() {
        return cn;
    }

    public static void setCn(Connection cn) {
        Bd.cn = cn;
    }
    
    public static void cargarDriver(){
        try{
             Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error no se puede cargar los drivers: "+e.getMessage());
        }
    }
    public static void conexionBaseDatos(){
        // Establecer la conexion con la base de datos
        try{
            String url="jdbc:mysql://localhost:3306/mundialjgap";
            String user="root";
            String password="";
            cn=(Connection) DriverManager.getConnection(url,user,password);
//            System.out.println("holallllllllllllllllllll");
        }catch(SQLException e){            
            JOptionPane.showMessageDialog(null,"Error no se puede establecer la conexion: "+e.getMessage());
        }
    }
    
    public static ArrayList<Equipo> buscarEquipo(){
        Bd.conexionBaseDatos();
        ArrayList<Equipo> equipo = new ArrayList<Equipo>();
        try{
            String sql = "select * from equipo";
            PreparedStatement ps= (PreparedStatement) cn.prepareStatement(sql);
//            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Equipo eq = new Equipo();
//                eq.setNombre(rs.getInt(1));
                eq.setNombre(rs.getString(2));
                eq.setrFifa(rs.getInt(3));
                eq.setGrupo(rs.getString(4));
                eq.setnCopas(rs.getInt(5));
                eq.setPartidoA(rs.getInt(6));
                eq.setPartidoB(rs.getInt(7));
                eq.setPartidoC(rs.getInt(8));
                equipo.add(eq);
            }        
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return equipo;
    }
    
    public static void presentarEquipos(JTable tabla){
        Bd.conexionBaseDatos();
        try{
            String sql = "Select * From equipo ";
            Statement ps = (Statement) cn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            
            String sql2 = "Select count(*) From equipo ";
            Statement ps2= (Statement) cn.createStatement();
            ResultSet rs2=ps2.executeQuery(sql2);
     
            int numfilas=0;
            if (rs2.next()){
                numfilas = rs2.getInt(1);
            }
                    
        Object[] columns = new String [] {
                "nombre"
            };
        
        Object [][] datos = new String [numfilas][columns.length];
        
                  
                    
          int cont = 0;  
          
          while(rs.next()){
                  datos[cont][0] = rs.getString("nombre");
                  cont ++;
         }
           
        javax.swing.table.TableModel dataModel = new javax.swing.table.DefaultTableModel(datos,columns); 
        
        tabla.setModel(dataModel);
 
        } catch(SQLException e) {
                System.out.println("Error en SQL "+e.getMessage()+e.getStackTrace());
       }
 
    }
    
    public static boolean modificarEquipo(String nombre, Equipo e){
        String sql;
        PreparedStatement ps;
        try {
            sql="update equipo set nombre=?, rFifa=?, grupo=?, nCopas=?, partidoA=?, partidoB=?, partidoC=? where nombre='"+nombre+"'";
            ps=(PreparedStatement) cn.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getrFifa());
            ps.setString(3, e.getGrupo());
            ps.setInt(4, e.getnCopas());
            ps.setInt(5, e.getPartidoA());
            ps.setInt(6, e.getPartidoB());
            ps.setInt(7, e.getPartidoC());
            ps.executeUpdate();
            return true;
        }catch(SQLException ex){            
            return false;
        }
    }
   
}
   
    

