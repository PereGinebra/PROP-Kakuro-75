
package src.CapaDomini.Drivers;
import java.util.Scanner;
import src.CapaDomini.Classes.Taulell;
import src.CapaDomini.Controladors.KakuroSolver;
import src.Exceptions.ExceptionTaulellSenseSolucio;

/** @class DriverKakuroSolver
 *  @brief El driver per a la clase KakuroSolver
 *  @author alvaro
 */
public class DriverKakuroSolver {
    private KakuroSolver solver;

    public DriverKakuroSolver(){
        
    }
    
    private void testConstructorSolver(Taulell t) {
        System.out.println("Mètode Constructor amb parametres");
        solver = new KakuroSolver(t);
        System.out.println("S'ha creat el kakuroSolver amb valors d'input");
    }
    
    private void testSolver(boolean check) {
        //try {
            System.out.println("Mètode per solucionar el kakuro");
            System.out.println("Es vol validar? " + check);
            solver.solucio(check);
            System.out.println("S'ha solucionat el kakuro");
        //}
        /*
        catch (ExceptionTaulellSenseSolucio e) {
                System.out.println(e.getMessage());
        }
*/
    }
    
    public static void main (String[] args) {
        DriverKakuroSolver ks= new DriverKakuroSolver();
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
        
        Taulell t = new Taulell(taulell, false);
        ks.testConstructorSolver(t);
        
        
        System.out.println("1- nou Taulell i Solver");
        System.out.println("2- solucionar i validar");
        System.out.println("3- solucionar sense validar");
        System.out.println("0- Finalitzar testing");
        
        int seleccio;
        while ((seleccio = Integer.parseInt(reader.nextLine())) != 0){
            if (seleccio == 1) {
                    System.out.println("Insereix el numero de files i columnes del taulell");
                    reader = new Scanner(System.in);
                    linia = reader.nextLine();
                    valors = linia.split(",");
                    files = Integer.parseInt(valors[0]);
                    columnes = Integer.parseInt(valors[1]);

                    System.out.println("Escriu el taulell"); 
                    taulell = new String[files][columnes];

                    for(int i=0; i<files; ++i) {
                        linia = reader.nextLine();
                        valors = linia.split(",");
                        for (int j=0; j<columnes; ++j) {
                            taulell[i][j] = valors[j];
                        }
                    }
                    t = new Taulell(taulell, false);
                    ks.testConstructorSolver(t);
            }
                    
            else if (seleccio == 2) ks.testSolver(true);
            else if (seleccio == 3) ks.testSolver(false);
                    
            System.out.println("Introdueix un altre numero per continuar el testing");    
            }
        
        System.out.println("Fi");
    }
}
