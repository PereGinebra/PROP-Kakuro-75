/** @file CtrlDomain.java
 * 
 */
package src.CapaDomini.Controladors;
import src.CapaDomini.Utils.PairII;
import src.CapaDomini.Utils.PairSB;
import java.util.AbstractMap;
import java.util.ArrayList;
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
    /**@brief Controlador d'usuari */
    private CtrlUsuari cu;
    
    private CtrlRankingGlobal cg;
    
    
    //Constructora
    /** @brief Constructora del controlador del domini.
        @pre Cert.
        @post S'ha creat el controlador de domini amb valors null.
    */ 
    public CtrlDomain() {
        t = new Taulell();
        cp = new CtrlPersistencia();
        cu = new CtrlUsuari();
        cg = new CtrlRankingGlobal();
    }
    
    
//--------- METODES USUARI -----------------------------------------------------
    
    /** @brief Funcio per logejar un usuari.
        @pre cert.
        @param user es el nom de l'usuari.
        @param pass es contrassenya de l'usuari.
        @post retorna cert en cas que l'usuari existeixi i tingui la contrassenya pass.
    */
    public boolean logIn(String user, String pass) {
        return (cu.existsUsuari(user) && cu.passwordCorrecte(user, pass));
    }
    
    /** @brief Funcio per registrar un usuari.
        @pre cert.
        @param user es el nom de l'usuari.
        @param pass es contrassenya de l'usuari.
        @post es crea l'usuari user amb la contrassenya pass.
    */
    public void signUp(String user, String pass) {

       cu.crearUsuari(user, pass);
    }
    
    /** @brief Funcio saber si existeix l'usuari.
        @pre cert.
        @param user es el nom de l'usuari.
        @post retorna cert si existeix l'usuari user.
    */
    public boolean existeixUsuari(String user) {
        return cu.existsUsuari(user);
    }
    
    /** @brief Funcio saber la puntuació global de l'usuari.
        @pre l'usuari existeix.
        @param user es el nom de l'usuari.
        @post retorna la puntuació global de l'usari.
    */
    public int getPuntuacioGlobal(String user) {
        return cp.getPuntuacioGlobal(user);
    }
    
    /** @brief Funcio saber la puntuació global de l'usuari.
        @pre l'usuari existeix.
        @param user es el nom de l'usuari.
        @param id es l'identificador del taulell.
        @post retorna unn string amb la puntuació del taulell de l'usari.
    */
    public String getPuntuacioTaulell(String user, String id) {
        return cp.getPuntuacioTaulell(user, id);
    }
    
    /** @brief Funcio saber el nom de l'usuari.
        @pre l'usuari s'ha inicialitzat.
        @param user es el nom de l'usuari.
        @post retorna un string amb el nom de l'usuari.
    */
    public String getNomUser() {
        return cu.getNomUser();
    }
//------------------------------------------------------------------------------ 
 
//--------- METODES PARTIDA ----------------------------------------------------
    
    /** @brief Consulta si la casella amb coordenades [i][j] és una 
        * casella blanca que encara es pot modificar.
        @pre El taulell/kakuro ja ha estat inicialitzat i carregat.
        @param i és la fila on es troba la casella. 0<=i<files del kakuro.
        @param j és la columna on es troba la casella. 0<=j<columnes del kakuro.
        @post Retorna un bool que indica si la casella en qüestió és blanca i
        * encara es pot modificar.
    */ 
    public boolean casellaEsModificable(int i, int j) {
        return partida.casellaEsEscribible(i, j);
    }
    
    /** @brief Soluciona completament la partida que s'esta jugant.
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Soluciona del tot la partida en qüestió i retorna una matriu de
        * strings que conté el taulell amb les solucions de totes les caselles
        * incloses.
    */ 
    public String[][] solucionar(){
        return partida.solucionar();
    }
    
    /** @brief Soluciona una casella blanca aleatòria de 
        * la partida que s'esta jugant i la bloqueja.
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Soluciona una casella aleatòria de la partida en qüestió i 
        * retorna un parell d'enters que conté les coordenades de la casella 
        * que ha sigut resolta.
    */
    public PairII demanarAjuda() {
        return partida.demanarAjuda();
    }
    
    /** @brief Consulta el valor que ha posat l'usuari 
        * en una certa casella blanca. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @pre La casella que es consulta és una casella blanca.
        @param i és la fila on es troba la casella. 0<=i<files del kakuro.
        @param j és la columna on es troba la casella. 0<=j<columnes del kakuro.
        @post Retorna un string amb el valor col·locat per l'usuari en la 
        * casella blanca en qüestió.
    */
    public String getValorActual (int i, int j) {
        return partida.getValorActual(i, j);
    }
    
    /** @brief Modifica el valor col·locat per l'usuari 
        * d'una casella blanca de la partida. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @pre La casella que es modifica és una casella blanca.
        @param i és la fila on es troba la casella. 0<=i<files del kakuro.
        @param j és la columna on es troba la casella. 0<=j<columnes del kakuro.
        @param val és un nombre positiu d'una xifra que 
        * es vol posar a la casella.
        @post S'ha modificat el valor de la casella blanca en qüestió.
    */
    public void modificarCasella (int i, int j, String val) {
        partida.modificarCasella(i, j, val);
    }
    
    /** @brief Consulta si l'usuari ha resolt completament la partida. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Retorna un bool que indica si la partida en qüestio ja ha estat
        * completament resolta.
    */
    public boolean partidaAcabada() {
        return partida.partidaAcabada();
    }
    
    /** @brief Consulta la quantitat de temps que s'ha jugat la partida. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Retorna el temps que s'ha jugat la partida amb el següent format:
        * hh:mm:ss.
    */
    public String getTempsPartida() {
        return partida.getTemps();
    }
    
    /** @brief Consulta la dificultat del taulell. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Retorna un string que indica la dificultat del taulell.
    */
    public String getDificultatPartida() {
        return partida.getDificultat();
    }
  
    
    /** @brief Consulta els valors col·locats per l'usuari en la partida. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Retorna una matriu de parells de strings i bools que indiquen
        * el valor introduit per l'usuari i si es pot canviar o no.
    */
    public PairSB[][] getValorsActuals(){
        return partida.getValorsActuals();
    }
    
    /** @brief Retorna les coordenades de les caselles de la partida que no han sigut solucionades.
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post S'ha retornat les coordenades de les caselles de la partida que no han sigut solucionades.
    */
    public ArrayList< PairII > getcoordNoConfirmades(){
        return partida.getcoordNoConfirmades();
    }
    
    /** @brief Retorna les coordenades de les caselles de la partida que no han sigut solucionades.
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post S'ha retornat les coordenades de les caselles de la partida que no han sigut solucionades.
    */
    public void setTemps(String t) {
        partida.setTemps(t);
    }
    
    public void afegirPartidaFinalitzada(String user, String idPartida, String time) {
        cp.afegirPartidaAcabada(user, idPartida, time);
    }
    
    public String getIdKakuro(){
        return partida.getIdKakuro();
    }
    
//--------- METODES TAULELL ----------------------------------------------------
    public String[][] getValorsTaulell() {
        return t.getValorsTaulell();
    }
    
    public boolean casellaBlanca(int i, int j) {
        return t.casellaBlanca(i, j);
    }
    
    public boolean casellaSuma(int i, int j) {
        return t.casellaSuma(i, j);
    }
    
    public boolean casellaNegra(int i, int j) {
        return t.casellaNegra(i, j);
    }
    
    public int getSumaFila (int i, int j) {
        return t.getSumaFila(i, j);
    }
    
    public int getSumaCol(int i, int j) {
        return t.getSumaColumna(i, j);
    }
//------------------------------------------------------------------------------
    
    
//--------- METODES RANKING ----------------------------------------------------
    
    public String[][] rankingUsuaris() {
        return cg.getMillorsUsuarisGlobal();
    }
    
    public String[][] getRankingTaulell(String id){
        return cp.getRankingTaulell(id);
    }
    
    
//------------------------------------------------------------------------------
    
//------METODES NOMS KAKURO-----------------------------------------------------
    
    /** @brief Funcio per obtenir el nom de tots els kakuros facils.
        @pre Cert.
        @post Retorna un vector de strings amb tots els noms dels kakuros facils.
    */ 
    public String[] getKakuroFacilTots() {
        return cp.llistatTaulellsDificultat("facil");
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros mitjans.
        @pre Cert.
        @post Retorna un vector de strings amb tots els noms dels kakuros mitjans.
    */ 
    public String[] getKakuroMigTots() {
        return cp.llistatTaulellsDificultat("mig");
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros dificils.
        @pre Cert.
        @post Retorna un vector de strings amb tots els noms dels kakuros dificils.
    */
    public String[] getKakuroDificilTots() {
        return cp.llistatTaulellsDificultat("dificil");
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros facils guardats per l'usuari.
        @pre Cert.
        @param String user es el nom de l'usuari
        @post Retorna un vector de strings amb tots els noms dels kakuros facils guardats per l'usuari.
    */
    public String[] getKakuroFacilUser(String user) {
        return cp.llistatPartidasEnCursDificultat(user,"facil");
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros mitjans guardats per l'usuari.
        @pre Cert.
        @param String user es el nom de l'usuari
        @post Retorna un vector de strings amb tots els noms dels kakuros mitjans guardats per l'usuari.
    */
    public String[] getKakuroMigUser(String user) {
        return cp.llistatPartidasEnCursDificultat(user,"mig");
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros dificils guardats per l'usuari.
        @pre Cert.
        @param String user es el nom de l'usuari
        @post Retorna un vector de strings amb tots els noms dels kakuros dificils guardats per l'usuari.
    */
    public String[] getKakuroDificilUser(String user) {
        return cp.llistatPartidasEnCursDificultat(user,"dificil");
    }
//------------------------------------------------------------------------------
    //Modificadora
    /** @brief Llegir un taulell kakuro desde consola.
        @pre Cert.
        @post Dona el valors del kakuro al taulell, el valida i treu la solució.
    */ 
    public boolean llegirKakuroNou(String taulell, int files, int columnes) {

        String[] valorsFila = taulell.split("\n");
        String[][] nouSenseSolucio = new String[files][columnes];
        
        for(int i=0; i<files; ++i) {

            String[] valors = valorsFila[i].split(",");
            for (int j=0; j<columnes; ++j) {
                if(valors[j].isEmpty()){
                    return false;
                }

                if((i==0 && "?".equals(valors[j])) ||(j==0 && "?".equals(valors[j]))) {
                    return false;    
                }

                if(!IsValidInput(valors[j])) {

                    return false;
                 }
                
                 nouSenseSolucio[i][j] = valors[j];

             }
        }
        
        t = new Taulell(nouSenseSolucio, true);
        solver = new KakuroSolver(t);
        
        boolean b = solver.solucio(true);
        //La funcio de sobre li afegeix la solucio al taulell.
        
        
        String dif = t.CalcularDificultatKakuro();
        String [][] TaulellCompletAmbSolucio = t.getTaulellSolucio();
        String IdKakuro = cp.AfegirNewKakuro(dif,files,columnes,nouSenseSolucio);
        cp.afegirSolucioKakuro(files, columnes, TaulellCompletAmbSolucio, IdKakuro);
        jugar(IdKakuro);
        return b;
            
        
    }
    
    /** @brief Llegir un taulell kakuro existent.
        @pre Cert.
        @post Dona el valors del kakuro al taulell i treu la solució.
    */
    public void carregarKakuro(String id) {
         
        String[][] taulell = cp.carregarKakuro(id);
        String[][] taulellSolucio = cp.carregarKakuroSolucio(id);
        String dif = cp.getDificultatTitulo(id);
        t = new Taulell(taulell, false);
        t.setTaulellSolucio(taulellSolucio);
        t.setDificultat(dif);
        jugar(id);
       
    }
    
    /** @brief Carregar una partida començada per jugar.
        @pre el idPartida existeix.
        @param idPartida string amb l'identificador de la partida guardada a jugar
        @post Carrega la partida amb idPartida per jugar.
    */
    public void carregarPartida(String idPartida) {
        String user = cu.getNomUser(); 
        String idKakuro = cp.getPartidaIdTaulell(user,idPartida);
        
        String[][] taulell = cp.carregarKakuro(idKakuro);
        t = new Taulell(taulell, false);
        String[][] taulellSolucio = cp.carregarKakuroSolucio(idKakuro);
        t.setTaulellSolucio(taulellSolucio);
        String dif = cp.getDificultatPartida(idPartida);
        t.setDificultat(dif);
        
        String temps = cp.getPartidaTemps(user,idPartida);
        PairSB[][] valors = cp.getPartidaMatriuValorsActuals(user, idPartida);
        ArrayList<PairII> coord = cp.getPartidaCoordNoConfirmades(user, idPartida);
        
        Partida actual = new Partida(t);
        actual.assignarAtributs(temps, idPartida, valors, coord, t);
        partida = new CtrlPartida(actual,idKakuro);
   
    }
    

    
   /** @brief Generar un Kakuro segons la dificultat i el guarda.
        @pre Cert.
        @post S'ha generat un kakuro aleatori i s'ha guardat.
    */
    public void generarIGuardarRandomKakuro(String dificultat, int nFiles, int nColumnes){
        
                 
        RandomKakuro randomizer = new RandomKakuro(nFiles,nColumnes,dificultat);
        
        String[][] taulellNoSolucio = randomizer.MatriuString();
        
        String[][] taulellAmbSolucio =  randomizer.MatriuStringNum();
        //ES SEGUR QUE AQUEST TAULELL TE SOLUCIO PERQUÈ S'HA GENERAT RANDOM
        // DE MANERA RANDOM ABANS
        t = new Taulell(taulellNoSolucio, true);
        
        t.setTaulellSolucio(taulellAmbSolucio);
        t.setDificultat(dificultat);
        String IDK = cp.AfegirNewKakuro(dificultat, nFiles, nColumnes, taulellNoSolucio);
        cp.afegirSolucioKakuro(nFiles, nColumnes, taulellAmbSolucio, IDK);
        jugar(IDK);
        
    }
    
    
    /** @brief Jugar una nova partida.
        @pre La partida és nova.
        @post S'ha jugat la partida del kakuro.
    */ 
    public void jugar(String IDKakuro) {
        Partida actual = new Partida(t);
        partida = new CtrlPartida(actual,IDKakuro);
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /** @brief Valida que el kakuro tingui una solució única
        @pre "mapa" és una matriu de strings que està escrita en el format correcte.
        @post Retorna true si el kakuro té solució única.
    */
    public boolean validarKakuro(String[][] mapa) throws ExceptionTaulellSenseSolucio{
        Taulell t = new Taulell(mapa,true);
        KakuroSolver KS = new KakuroSolver(t);
        KS.solucio(true);
        return (KS.numSolucions()==1);
    }
    
    
    /** @brief Calcula la dificultat del taulell "mapa".
        @pre "mapa" és una matriu de strings que està escrita en el format correcte.
        @post Retorna una string que indica la dificultat del kakuro.
    */
    public String calcularDificultatKakuro(String [][] mapa){
        Taulell t = new Taulell (mapa,true);
        return t.CalcularDificultatKakuro();
    }
    
     /**
     * @brief Enregistra un Kakuro nou al sistema
     * @pre cert
     * @param Dificultat Dificultat del kakuro ha enregistrat
     * @param files files del kakuro ha enregistrat
     * @param columnes columnes del kakuro ha enregistrat
     * @param taulell Matriu que enregistra els valors de cada casella
     * @post Ha quedat enregistrat un kakuro amb els valors de taulell i dificultat
     */
    public void AfegirNewKakuro(String Dificultat, int files, int columnes, String[][] taulell){
        cp.AfegirNewKakuro(Dificultat, files, columnes, taulell);
    }
    
    
    /**
     * @brief Enregistra un partida nova al sistema
     * @pre L'entorn ha sigut creat
     * @param temps és el temps que la partida porta jugada.
     * @post Ha quedat enregistrat una partida amb els valors donats.
     * @return Retorna el nom del fitxer creat
     */
    public String guardarPartidaAMitges(String temps){
        
        String username = getNomUser();
        PairSB [][] actuals = partida.getValorsActuals();
        ArrayList<PairII> noConfirmades = partida.getcoordNoConfirmades();
        String idTaulell = partida.getIdKakuro();
     
        return cp.afegirPartida(username,idTaulell,temps,actuals, noConfirmades);
        
        
    }
}