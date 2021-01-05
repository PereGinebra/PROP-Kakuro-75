/** @file CtrlDomain.java
 * 
 */
package src.CapaDomini.Controladors;

import java.util.InputMismatchException;
import src.Exceptions.*;
import src.CapaPersistencia.CtrlPersistencia;
import src.CapaDomini.Classes.Taulell;
import src.CapaDomini.Classes.Partida;
import java.util.Scanner;

/** @class CtrlDomain
 *  @brief Controlador del domini
 *
 *  @author alvaro.andres
 */
public class CtrlDomain {
    /**@brief Taulell per jugar la partida */
    private Taulell t;
    /**@brief Controlador de persistència */
    private CtrlPersistencia cp;
    /**@brief Controlador de partida */
    private CtrlPartida partida;
    /**@brief Controlador per resoldre el taulell */
    private KakuroSolver solver;
    /**@brief Controlador per resoldre el taulell */
    private RandomKakuro randomizer;
    
    //Constructora
    /** @brief Constructora del controlador del domini.
        @pre Cert.
        @post S'ha creat el controlador de domini amb valors null.
    */ 
    public CtrlDomain() {
        t = new Taulell();
        cp = new CtrlPersistencia();
    }
    
    //Modificadora
    /** @brief Llegir un taulell kakuro desde consola.
        @pre Cert.
        @post Dona el valors del kakuro al taulell, el valida i treu la solució.
    */ 
    private void llegirKakuroNou() throws ExceptionTaulellSenseSolucio {
        String[][] taulell = new String[0][0];
        
        System.out.println("Escriu nombre de files i columnes: ex. 3,3");

        Scanner reader = new Scanner(System.in);
        String linia = reader.nextLine();
        String[] valors = linia.split(",");

        int files = Integer.parseInt(valors[0]);
        int columnes = Integer.parseInt(valors[1]);
        
        try{
            if(files < 3 || columnes < 3){
                throw new ExceptionDimensionsTaulellErrones();
            }
        }
        catch (ExceptionDimensionsTaulellErrones e) {
                e.getMessage();
        }
        
        taulell = new String[files][columnes];
        
        for(int i=0; i<files; ++i) {
            try{
                System.out.println("Escriu els valors de la fila separats per coma:");
                linia = reader.nextLine();
                valors = linia.split(",");
                for (int j=0; j<columnes; ++j) {
                    if(valors[j].isEmpty()){
                        
                        throw new ExceptionStringBuidaInput();
                    }
                    
                    if((i==0 && "?".equals(valors[j])) ||(j==0 && "?".equals(valors[j]))) {
                        throw new CustomException("No hi pot haver caselles blanques a la primera fila o columna");    
                    }
                    
                    if(!IsValidInput(valors[j])) {
                        
                        throw new ExceptionValorNoValid();
                     }
                     taulell[i][j] = valors[j];
                     
                 }
            }
            catch (ExceptionValorNoValid e) {
                --i;
                System.out.println(e.getMessage());
            }
            catch (CustomException e) {
                --i;
                System.out.println(e.getMessage());
            }
            catch (ExceptionStringBuidaInput e2){
                --i;
                System.out.println(e2.getMessage());
            }
        }
        
        t = new Taulell(taulell, true);
        solver = new KakuroSolver(t);
        solver.solucio(true);
    }
    
    /** @brief Llegir un taulell kakuro existent.
        @pre Cert.
        @post Dona el valors del kakuro al taulell i treu la solució.
    */
    private void carregarKakuro() throws ExceptionTaulellSenseSolucio{
        System.out.println("Selecciona kakuro:");
        System.out.println("1- kakuro fàcil");
        System.out.println("2- kakuro mig");
        System.out.println("3- kakuro difícil");

        Scanner reader = new Scanner(System.in);
        int select = reader.nextInt();
        String path = new String();

        if (select == 1) path = "kakuroFacil.txt";
        else if (select == 2) path = "kakuroMig.txt";
        else if (select == 3) path = "kakuroDificil.txt";
        else System.out.println("seleccio incorrecte");

        String[][] taulell = cp.carregarKakuro(path);
        
        t = new Taulell(taulell, false);
        solver = new KakuroSolver(t);
        solver.solucio(false);
       
    }
    
   /** @brief Generar un Kakurokakuro segons la dificultat
        @pre Cert.
        @post Dona el valors del kakuro al taulell i treu la solució.
    */
    private void GenerarRandomKakuro() throws ExceptionTaulellSenseSolucio{
        
        
        boolean seleccionat = false;
        String Dificultat;
        int nfiles,ncolumnes;
        String linia;
        String[] valors;
        Scanner reader = new Scanner(System.in);
        int select;
        
        Dificultat="nodif";
        nfiles = 10;
        ncolumnes = 10;
        
        while(!seleccionat){
            System.out.println("Selecciona dificultat:");
            System.out.println("1- kakuro fàcil");
            System.out.println("2- kakuro mig");
            System.out.println("3- kakuro difícil");
             try{
                select = reader.nextInt();
                String path = new String();
                switch(select){
                    case 1:
                        seleccionat = true;
                        Dificultat="dificil";
                        break;
                    case 2:
                        seleccionat = true;
                        Dificultat="mig";
                        break;
                    case 3:
                        seleccionat = true;
                        Dificultat="facil";
                        break;    
                    default:
                        throw new CustomException("Seleccio incorrecte");
                    }
                }
                catch(CustomException e) {
                    System.out.println(e.getMessage());
                    seleccionat=false;
                }
        }
        int n=0;
        while(n<2){
            try{
                if(n==0){
                    System.out.println("Numero de files:");
                    nfiles = reader.nextInt();
                    if(nfiles<3 || nfiles>16) throw new ExceptionDimensionsTaulellErrones();
                    else n++;
                }
                else{
                    System.out.println("Numero de columnes:");
                    ncolumnes = reader.nextInt();
                    if(ncolumnes<3 || ncolumnes>17) throw new ExceptionDimensionsTaulellErrones();
                    else n++;
                }
            }
            catch(InputMismatchException e){
                if(n==0) System.out.println("Files no es un numero");
                else System.out.println("Columnes no es un numero");
                n=0;nfiles=0;ncolumnes=0;
            }
            catch(ExceptionDimensionsTaulellErrones e){
                System.out.println("Dimensions no correctes");
                n=0;nfiles=0;ncolumnes=0;
            }
        }
        
        System.out.println("GENERADOR kakuro de " + nfiles + " X " + ncolumnes);
                
        RandomKakuro randomizer = new RandomKakuro(nfiles,ncolumnes,Dificultat);
        
        String[][] taulell = randomizer.MatriuString();
        
        String[][] taulellSol =  randomizer.MatriuStringNum();
        
        String lineTaulell="";
        System.out.println(nfiles + "," + ncolumnes);
        for(int i=0; i<nfiles;i++){
            for(int j=0; j<ncolumnes;j++){
                if(j==0) lineTaulell = taulell[i][j];
                else lineTaulell = lineTaulell + "," + taulell[i][j];
            }
            System.out.println(lineTaulell);
        }
        System.out.println();

        t = new Taulell(taulell, true);
        t.setTaulellSolucio(taulellSol);
        
    }
    
    
    /** @brief jugar al programa.
        @pre Cert.
        @post S'ha jugat la partida del kakuro.
    */ 
    public void jugar() {
        boolean acabar = false;
        while (!acabar) {
            try {
                
                System.out.println("1- Escriu Kakuro per consola");
                System.out.println("2- Carregar Kakuro existent");
                System.out.println("3- Generar kakuro Random");
                System.out.println("4- Acaba execució");
                Scanner reader = new Scanner(System.in);

                int seleccio = reader.nextInt();

                switch (seleccio) {
                    case 1:
                        llegirKakuroNou();
                        break;
                    case 2:
                        carregarKakuro();
                        break;
                    case 3:
                        GenerarRandomKakuro();
                        break;
                    case 4:
                        acabar = true;
                        break;
                    default:
                        throw new CustomException("Seleccio incorrecte");
                }
                if (!acabar) {
                    Partida actual = new Partida(t);
                    partida = new CtrlPartida(actual);
                    partida.jugar();
                }
            }

            catch(CustomException e) {
                System.out.println(e.getMessage());
            }
            catch(ExceptionTaulellSenseSolucio e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    /** @brief Validar input de caselles suma.
        @pre Cert.
        @param s és string amb el valor que es vol posar a una casella de suma
        @post Retorna true si el format es correcte.
    */
    private boolean EsCasellaSumaPossible(String s){

        if(s.length()<4){
            if(!(s.charAt(0)=='F' || s.charAt(0)=='C') ) return false;

            for(int i=1;i<s.length();i++){
                if(s.charAt(i)>='0' || s.charAt(0)<='9' ) {
                } else {
                    return false;
                }
            }
        }

        else{
            int i;
            if(!(s.charAt(0)=='C'))return false;
            for(i=1;i<s.length();i++){
                if(!(s.charAt(i)=='F')); 
                else if(s.charAt(i)>='0' || s.charAt(0)<='9' ) {
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    /** @brief Validar input de caselles.
        @pre Cert.
        @param StringToCheck és el valor a comprovar
        @param InputType és el tipus (number, interrogant, asterisk, CasellaSuma)
        @post Retorna true si el format es correcte.
    */
    private boolean IsValidInputByType(String StringToCheck, String InputType){
        switch(InputType){
            case "coma":
                if(",".equals(StringToCheck)) return true;
            break;

            case "asterisk":
                if("*".equals(StringToCheck)) return true;
            break;

            case "interrogant":
                if("?".equals(StringToCheck)) return true;

            break;

            case "number":
                boolean b = true;
                 try { 
                    Integer.parseInt(StringToCheck); 
                }catch(NumberFormatException e) {
                    b=false;
                }
                if(b) return true; 
            break;
            case "CasellaSuma":
                if(EsCasellaSumaPossible(StringToCheck)) return true;
            break;
            default:
        }
    return false;
    
    }
    
    /** @brief Validar input de caselles.
        @pre Cert.
        @param StringToCheck és el valor a comprovar
        @post Retorna true si el format es correcte.
    */
    private boolean IsValidInput(String StringToCheck){
        if((
             IsValidInputByType(StringToCheck,"number") ||
             IsValidInputByType(StringToCheck,"interrogant") ||
             IsValidInputByType(StringToCheck,"asterisk") ||
             IsValidInputByType(StringToCheck,"CasellaSuma")
               
        )) return true;
        
        return false;
    }
}
