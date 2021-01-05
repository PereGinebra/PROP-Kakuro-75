/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.CapaDomini.Drivers;
import java.util.Scanner;
import  src.CapaDomini.Classes.Taulell;

/** @clase DriverTaulell
 *  @brief El driver per a la clase Taulell
 *  @author alvaro
 */
public class DriverTaulell {
    private Taulell t;
    
    public DriverTaulell(){
        
    }
    
    public void testConstructor(String[][] taulell, boolean man) {
        System.out.println("Mètode Constructor amb parametres");
        t = new Taulell(taulell, man);
        System.out.println("S'ha creat el taulell amb valors d'input");
    }
    
    public void testGetFiles(){
        System.out.println("Mètode getFiles");
        int files = t.getFiles();
        System.out.println("Files= " + files);
    }
    
    public void testGetColumnes(){
        System.out.println("Mètode getColumnes");
        int columnes = t.getColumnes();
        System.out.println("Files= " + columnes);
    }
    
    public void testGetDificultat(){
        System.out.println("Mètode getDificultat");
        String s = t.getDificultat();
        System.out.println("Dificultat= " + s);
    }
    
    public void testGetManual() {
        System.out.println("Mètode getManual");
        boolean s = t.getManual();
        System.out.println("Manual= " + s);
    }
    
    public void testGetSumaFila(int fila, int columna) {
        System.out.println("Mètode getSumaFila amb parametres " + fila +" " + columna);
        int suma = t.getSumaFila(fila, columna);
        System.out.println("Suma= " + suma);
    }
    
    public void testGetSumaColumna(int fila, int columna) {
        System.out.println("Mètode getSumaColumna amb parametres " + fila +" " + columna);
        int suma = t.getSumaColumna(fila, columna);
        System.out.println("Suma= " + suma);
    }
    
    public void testCasellaBlanca(int fila, int columna) {
        System.out.println("Mètode casellaBlanca amb parametres " + fila +" " + columna);
        boolean s = t.casellaBlanca(fila, columna);
        System.out.println("Blanca= " + s);
    }
    
    
    public static void main (String[] args) {
        DriverTaulell t = new DriverTaulell();
        
        System.out.println("Insereix el numero de files i columnes del taulell");
        Scanner reader = new Scanner(System.in);
        String linia = reader.nextLine();
        String[] valors = linia.split(",");
        int files = Integer.parseInt(valors[0]);
        int columnes = Integer.parseInt(valors[1]);
        
        System.out.println("Escriu el taulell"); 
        String[][] taulell;
        taulell = new String[files][columnes];
        
        for(int i=0; i<files; ++i) {
            linia = reader.nextLine();
            valors = linia.split(",");
            for (int j=0; j<columnes; ++j) {
                taulell[i][j] = valors[j];
            }
        }
        t.testConstructor(taulell, true);
        
        System.out.println("1- getFiles");
        System.out.println("2- getColumnes");
        System.out.println("3- getDificultat");
        System.out.println("4- getManual");
        System.out.println("5- getSumaColumna");
        System.out.println("6- getSumaFila");
        System.out.println("7- getCasellaBlanca");
        System.out.println("0- Finalitzar test");
         
        int seleccio, fila, columna;
        while((seleccio = Integer.parseInt(reader.nextLine())) != 0) {
            if (seleccio == 1) t.testGetFiles();                    
            else if (seleccio == 2)t.testGetColumnes();
            else if (seleccio == 3)t.testGetDificultat();
            else if (seleccio == 4)t.testGetManual();
            else if (seleccio == 5) {
                    System.out.println("Escriu fila i columna a consultar: exemple 2,2");
                    linia = reader.nextLine();       
                    valors = linia.split(",");
                    fila = Integer.parseInt(valors[0]);
                    columna = Integer.parseInt(valors[1]);
                    t.testGetSumaColumna(fila, columna);
            }
                    
            else if (seleccio == 6){
                    System.out.println("Escriu fila i columna a consultar: exemple 2,2");
                    linia = reader.nextLine();        
                    valors = linia.split(",");
                    fila = Integer.parseInt(valors[0]);
                    columna = Integer.parseInt(valors[1]);
                    t.testGetSumaFila(fila, columna);                   
            }
                    
            else if (seleccio == 7){
                    System.out.println("Escriu fila i columna a consultar: exemple 2,2");
                    linia = reader.nextLine();       
                    valors = linia.split(",");
                    fila = Integer.parseInt(valors[0]);
                    columna = Integer.parseInt(valors[1]);
                    t.testCasellaBlanca(fila, columna);
            } 
            System.out.println("Introdueix un altre numero per continuar el testing");    
        }
        System.out.println("Fi");
    }
}
