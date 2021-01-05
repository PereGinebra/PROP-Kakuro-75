
package src.CapaDomini.Drivers;
import java.util.*;
import  src.CapaDomini.Classes.Partida;
import  src.CapaDomini.Classes.Taulell;

/** @class DriverPartida
 *  @brief El driver per a la clase Partida
 *  @author ignasi
 */

public class DriverPartida {
    
    private Partida p;
    
    public DriverPartida(Taulell t){
        testConstructor(t);
    }
    
    
    public void testConstructor(Taulell t){
        System.out.println("Crida a mètode constructor.");
        p = new Partida(t);
        System.out.println("S'ha creat la partida.");
    }
    
    /*
    public long testGetTemps(){
        System.out.println("Crida a mètode getTemps.");
        long n = p.getTemps();
        System.out.println("Ha retornat el temps: " + Long.toString(n));
        return n;
    }
    */
    
    public int testNombreAjudesDemanades(){
        System.out.println("Crida a mètode nombreAjudesDemanades.");
        //int n = p.nombreAjudesDemanades();
        int n = 0;
        System.out.println("Ha retornat el nombre d'ajudes demanades: "+ Integer.toString(n));
        return n;
    }
    
    public int testGetFiles(){
        System.out.println("Crida a mètode getFiles.");
        int n = p.getFiles();
        System.out.println("Ha retornat el nombre de files del kakuro:" + Integer.toString(n));
        return n;
    }

    
    public int testGetColumnes(){
        System.out.println("Crida a mètode getColumnnes.");
        int n = p.getColumnes();
        System.out.println("Ha retornat el nombre de columnes del kakuro: "+ Integer.toString(n));
        return n;
    }
    
    
 

    





    public static void main (String [] args){
        
        
        System.out.println("Escriu nFiles i nColumnes");
        Scanner scan = new Scanner(System.in);
        String linia = scan.nextLine();
        String[] valors = linia.split(",");
        int files = Integer.parseInt(valors[0]);
        int columnes = Integer.parseInt(valors[1]);
        
        System.out.println("Escriu taulell"); 
        String[][] mat;
        mat = new String[files][columnes];
        
        for(int i=0; i<files; ++i) {
            linia = scan.nextLine();
            valors = linia.split(",");
            for (int j=0; j<columnes; ++j) {
                mat[i][j] = valors[j];
            }
        }
        
        
        
        
        
        
        
        Taulell t = new Taulell(mat,true);
        DriverPartida DP = new DriverPartida(t);
        int select;
        
        System.out.println("-1: Sortir");
        System.out.println("0: Temps");
        System.out.println("1: NombreAjudesDemanades");
        System.out.println("2: Files");
        System.out.println("3: Columnes");
               
        
        while((select = Integer.parseInt(scan.nextLine())) != -1){
            
            boolean b;
            
            if (select == 0){
                //long n = DP.testGetTemps();
            }
            else if (select == 1){
               int n = DP.testNombreAjudesDemanades(); 
            }
            
            else if (select == 2){
               int n = DP.testGetFiles();
            }
            
            else if (select == 3){
               int n = DP.testGetColumnes(); 
            }        
        }
        System.out.println("Fi de la prova.");
    }

    
}

