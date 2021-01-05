package src.CapaDomini.Drivers;

import src.CapaDomini.Classes.Casella;
import src.CapaDomini.Classes.CasellaBlanca;
import src.CapaDomini.Classes.CasellaSuma;
import src.CapaDomini.Classes.CasellaNegra;

import java.io.Reader;
import java.util.Scanner;
        
/** @class DriverCasella
 *  @brief El driver per a la clase Casella
 *  @author pere.ginebra
 */
public class DriverCasella {
    private static Casella c;
    private static CasellaBlanca cb;
    
    private void testConstructora(String value) {
        System.out.println("Mètode constructor amb paràmetres");
        c = new Casella(value);
        System.out.println("S'ha creat la casella amb el valor d'input: " + value);
    }
    
    private void test_setValor(String s){
        System.out.println("\nMètode setValor amb input: " + s);
        c.setValor(s);
    }
    
    private void test_esBlanca() {
        System.out.println("\nMètode esBlanca");
        System.out.println("c es blanca? " + c.esBlanca());
    }
    
    private void test_esNegra() {
        System.out.println("\nMètode esNegra");
        System.out.println("c es negra? " + c.esNegra());
    }
    
    private void test_esSuma() {
        System.out.println("\nMètode esSuma");
        System.out.println("c es suma? " + c.esSuma());
    }
    
    private void test_getValor() {
        System.out.println("\nMètode getValorInput");
        System.out.println("Valor de casella = " + c.getValor());
    }
    
    private void test_getSumaFila() {
        System.out.println("\nMètode getSumaFila");
        System.out.println("El valor de la suma de fila es: "+c.getSumaFila());
    }
    
    private void test_getSumaColumna() {
        System.out.println("\nMètode getSumaColumna");
        System.out.println("El valor de la suma de columna es: "+c.getSumaColumna());
    }
    
    public static void main(String args[]) {
        
        Scanner reader = new Scanner(System.in);
        DriverCasella dc = new DriverCasella();
        
        int select;
        String value;
        
        System.out.println("1- casella negra");
        System.out.println("2- casella blanca");
        System.out.println("3- casella suma");
        System.out.println("0- acabar test");
        
        while((select = Integer.parseInt(reader.nextLine())) != 0) {
            
            if(select == 1) {
                System.out.println("Creadora casella negra");
                c = new CasellaNegra();
            }
            else {
                System.out.println("Escriu el valor de la casella: ");
                value = reader.nextLine();
                if(select == 2){
                    System.out.println("Creadora casella blanca amb valor: " + value);
                    c = new CasellaBlanca(value);
                }
                else if(select == 3) {
                    System.out.println("Creadora casella suma amb valor: " + value);
                    c = new CasellaSuma(value);
                }
            }
            dc.test_getValor();
            
            dc.test_setValor("*");
            dc.test_getValor();
            dc.test_setValor("?");
            dc.test_getValor();
            dc.test_setValor("14");
            dc.test_getValor();
            dc.test_setValor("F5");
            dc.test_getValor();
            dc.test_setValor("C10F9");
            dc.test_getValor();
            
            dc.test_esBlanca();
            dc.test_esNegra();
            dc.test_esSuma();
            
            dc.test_getSumaFila();
            dc.test_getSumaColumna();
            
            System.out.println("\n-------------------------------------------\n");
            System.out.println("1- casella negra");
            System.out.println("2- casella blanca");
            System.out.println("3- casella suma");
            System.out.println("0- acabar test");
        }
    }    
}
