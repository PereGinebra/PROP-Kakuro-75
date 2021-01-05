/** 
 * @file CtrlPresentacio.java
 */

package src.CapaPresentacio;
import src.CapaDomini.Utils.PairII;
import src.CapaDomini.Controladors.CtrlDomain;
import src.Exceptions.ExceptionTaulellSenseSolucio;

/** @class CtrlPresentacio
 *  @brief Controlador de la presentacio
 *  @author alvaro
 */
public class CtrlPresentacio {
    private CtrlDomain cd;
    
    public CtrlPresentacio (){
        cd = new CtrlDomain();
    }
    
    public void run() {
        PantallaPrincipal pp = new PantallaPrincipal(this);
        pp.setVisible(true);
    }
    
    /** @brief Consulta el nom de l'usuari.
        @pre Existeix un usuari inicialitzat.
        @post retorna un string amb el nom de l'usuari.
    */
    public String getNomUser(){
        return cd.getNomUser();
    }
    
    /** @brief Consulta si l'usuari i la password coincideixen.
        @pre l'usuari user existeix a les dades.
        @param user es el nom d'usuari.
        @param pass es la constrasenya a comprovar.
        @post retorna true en cas que la password sigui la de l'usuari .
    */
    public boolean comprovaUsuari(String user, String password) {
        return cd.logIn(user, password);
    }
    
    /** @brief Consulta si un cert usuari esta registrat al sistema.
        @pre Cert.
        @param user es el nom de l'usuari.
        @post Retorna un bool que indica si l'usuari esta registrat o no.
    */ 
    public boolean existeixUsuari(String user) {
        return cd.existeixUsuari(user);
    }
    
    /** @brief Registra un nou usuari al sistema.
        @pre No existeix cap usuari ja registrat amb el parametre "user"
        * com a nom d'usuari.
        @param user es el nom d'usuari amb el que l'usuari es vol registrar.
        @param pass es la constrasenya que vol tenir l'usuari.
        @post S'ha registrat un nou usuari amb el nom i la contrasenya indicats.
    */ 
    public void signUp(String user, String pass) {
        cd.signUp(user, pass);
    }
    
    /** @brief Consulta la puntuacio global de l'usuari.
        @pre l'usuari existeix.
        @param user es el nom de l'usuari.
        @post Retorna un enter amb la puntuacio global de l'usuari.
    */
    public int getPuntuacioGlobal(String user) {
        return cd.getPuntuacioGlobal(user);
    }
    
    /** @brief Consulta la puntuacio global de l'usuari.
        @pre l'usuari i el taulell existeixen.
        @param user es el nom de l'usuari.
        @param id es l'identificador del taulell.
        @post Retorna un string amb el temps de l'usuari en aquell taulell, si no te temps retorna ---.
    */
    public String getPuntuacioTaulell(String user, String id) {
        return cd.getPuntuacioTaulell(user, id); //To change body of generated methods, choose Tools | Templates.
    }
    
    /** @brief Consulta el ranking del 5 millors usuaris globals.
        @pre cert.
        @post Retorna una matriu string amb nom i puntuacio dels 5 millors usuaris ordenats per puntuacio.
    */
    public String[][] rankingUsuaris() {
        return cd.rankingUsuaris();
    }
    
    public String[][] getRankingTaulell(String id){
        return cd.getRankingTaulell(id);
    }
    
    /** @brief funcio per obtenir els valors del taulell.
        @pre el taulell existeix.
        @post Retorna una matriu string amb els valors del taulell.
    */
    public String[][] getValorsTaulell() {
        return cd.getValorsTaulell();
    }
    
    /** @brief Consulta si la casella amb coordenades [i][j] es una 
        * casella blanca.
        @pre El taulell/kakuro ja ha estat inicialitzat i carregat.
        @param i es la fila on es troba la casella. 0<=i<files del kakuro.
        @param j es la columna on es troba la casella. 0<=j<columnes del kakuro.
        @post Retorna un bool que indica si la casella en questio es blanca.
    */ 
    public boolean casellaBlanca(int i, int j){
        return cd.casellaBlanca(i, j);
    }
    
    /** @brief Consulta si la casella amb coordenades [i][j] es una 
        * casella de suma.
        @pre El taulell/kakuro ja ha estat inicialitzat i carregat.
        @param i es la fila on es troba la casella. 0<=i<files del kakuro.
        @param j es la columna on es troba la casella. 0<=j<columnes del kakuro.
        @post Retorna un bool que indica si la casella en questio es de suma.
    */ 
    public boolean casellaSuma(int i, int j){
        return cd.casellaSuma(i, j);
    }
    
    /** @brief Consulta si la casella amb coordenades [i][j] es una 
        * casella negra.
        @pre El taulell/kakuro ja ha estat inicialitzat i carregat.
        @param i es la fila on es troba la casella. 0<=i<files del kakuro.
        @param j es la columna on es troba la casella. 0<=j<columnes del kakuro.
        @post Retorna un bool que indica si la casella en questio es negra.
    */ 
    public boolean casellaNegra(int i, int j){
        return cd.casellaNegra(i, j);
    }
    
    /** @brief Consulta si la casella amb coordenades [i][j] es una 
        * casella blanca que encara es pot modificar.
        @pre El taulell/kakuro ja ha estat inicialitzat i carregat.
        @param i es la fila on es troba la casella. 0<=i<files del kakuro.
        @param j es la columna on es troba la casella. 0<=j<columnes del kakuro.
        @post Retorna un bool que indica si la casella en questio es blanca i
        * encara es pot modificar.
    */ 
    public boolean casellaEsModificable(int i, int j) {
        return cd.casellaEsModificable(i, j);
    }
    
    /** @brief Soluciona completament la partida que s'esta jugant.
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Soluciona del tot la partida en questio i retorna una matriu de
        * strings que conte el taulell amb les solucions de totes les caselles
        * incloses.
    */ 
    public String[][] solucionar() {
        return cd.solucionar();
    }
    
    /** @brief Soluciona una casella blanca aleatoria de 
        * la partida que s'esta jugant i la bloqueja.
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Soluciona una casella aleatoria de la partida en questio i 
        * retorna un parell d'enters que conte les coordenades de la casella 
        * que ha sigut resolta.
    */
    public PairII demanarAjuda() {
        return cd.demanarAjuda();
    }
    
    /** @brief Consulta el valor que ha posat l'usuari 
        * en una certa casella blanca. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @pre La casella que es consulta es una casella blanca.
        @param i es la fila on es troba la casella. 0<=i<files del kakuro.
        @param j es la columna on es troba la casella. 0<=j<columnes del kakuro.
        @post Retorna un string amb el valor col·locat per l'usuari en la 
        * casella blanca en questio.
    */
    public String getValorActual(int i, int j) {
        return cd.getValorActual(i, j);
    }
    
    /** @brief Modifica el valor col·locat per l'usuari 
        * d'una casella blanca de la partida. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @pre La casella que es modifica es una casella blanca.
        @param i es la fila on es troba la casella. 0<=i<files del kakuro.
        @param j es la columna on es troba la casella. 0<=j<columnes del kakuro.
        @param val es un nombre positiu d'una xifra que 
        * es vol posar a la casella.
        @post S'ha modificat el valor de la casella blanca en questio.
    */
    public void modificarCasella(int i, int j, String val) {
        cd.modificarCasella(i, j, val);
    }
    
    /** @brief Consulta si l'usuari ha resolt completament la partida. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Retorna un bool que indica si la partida en questio ja ha estat
        * completament resolta.
    */
    public boolean partidaAcabada() {
        return cd.partidaAcabada();
    }
    
    /** @brief Consulta la quantitat de temps que s'ha jugat la partida. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Retorna el temps que s'ha jugat la partida amb el seguent format:
        * hh:mm:ss.
    */
    public String getTempsPartida() {
        return cd.getTempsPartida();
    }
    
    /** @brief Consulta la dificultat del taulell. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @post Retorna un string que indica la dificultat del taulell.
    */
    public String getDificultatPartida() {
        return cd.getDificultatPartida();
    }
    
    /** @brief Consulta la suma de fila d'una casella de suma. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @param i es la fila on es troba la casella. 0<=i<files del kakuro.
        @param j es la columna on es troba la casella. 0<=j<columnes del kakuro.
        @post Retorna el valor de la suma de fila si la casella en questio es 
        * de suma i te suma de fila. Retorna -1 altrament.
    */
    public int getSumaFila(int i, int j) {
        return cd.getSumaFila(i, j);
    }
    
    /** @brief Consulta la suma de columna d'una casella de suma. 
        @pre El taulell/kakuro i la partida 
        * ja han estat inicialitzats i carregats.
        @param i es la fila on es troba la casella. 0<=i<files del kakuro.
        @param j es la columna on es troba la casella. 0<=j<columnes del kakuro.
        @post Retorna el valor de la suma de columna si la casella en questio es 
        * de suma i te suma de columna. Retorna -1 altrament.
    */
    public int getSumaCol(int i, int j) {
        return cd.getSumaCol(i, j);
    }
    
    /** @brief Posar el temps de la partida. 
        @pre cert.
        @param t es un string amb el temps de la partida
        @post es posa el temps de la partida.
    */
    public void setTempsPartida(String t) {
        cd.setTemps(t);
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros facils.
        @pre Cert.
        @post Retorna un vector de strings amb tots els noms dels kakuros facils.
    */ 
    public String[] getKakuroFacilTots() {
        return cd.getKakuroFacilTots();
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros mitjans.
        @pre Cert.
        @post Retorna un vector de strings amb tots els noms dels kakuros mitjans.
    */ 
    public String[] getKakuroMigTots() {
        return cd.getKakuroMigTots();
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros dificils.
        @pre Cert.
        @post Retorna un vector de strings amb tots els noms dels kakuros dificils.
    */
    public String[] getKakuroDificilTots() {
        return cd.getKakuroDificilTots();
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros facils guardats per l'usuari.
        @pre Cert.
        @param String user es el nom de l'usuari
        @post Retorna un vector de strings amb tots els noms dels kakuros facils guardats per l'usuari.
    */
    public String[] getKakuroFacilUser(String user) {
        return cd.getKakuroFacilUser(user);
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros mitjans guardats per l'usuari.
        @pre Cert.
        @param String user es el nom de l'usuari
        @post Retorna un vector de strings amb tots els noms dels kakuros mitjans guardats per l'usuari.
    */
    public String[] getKakuroMigUser(String user) {
        return cd.getKakuroMigUser(user);
    }
    
    /** @brief Funcio per obtenir el nom de tots els kakuros dificils guardats per l'usuari.
        @pre Cert.
        @param String user es el nom de l'usuari
        @post Retorna un vector de strings amb tots els noms dels kakuros dificils guardats per l'usuari.
    */
    public String[] getKakuroDificilUser(String user) {
        return cd.getKakuroDificilUser(user);
    }
    
    
    /** @brief Valida que el kakuro tingui una solucio unica
        @pre "mapa" es una matriu de strings que esta escrita en el format correcte.
        @post Retorna true si el kakuro te solucio unica.
    */
    public boolean validarKakuro(String[][] mapa) throws ExceptionTaulellSenseSolucio{
        return cd.validarKakuro(mapa);
    }
    
    /** @brief Calcula la dificultat del taulell "mapa".
        @pre "mapa" es una matriu de strings que esta escrita en el format correcte.
        @post Retorna una string que indica la dificultat del kakuro.
    */
    public String calcularDificultatKakuro(String[][] mapa){
        return cd.calcularDificultatKakuro(mapa);
    }
    
    //CREC QUE AQUESTA FUNCIÓ LA PODEM BORRAR PERFECTAMENT
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
        cd.AfegirNewKakuro(Dificultat, files, columnes, taulell);
    }
    
    /** @brief Enregistra un kakuro nou al sistema.
        @pre Cert.
        @param files es el nombre de files del kakuro.
        @param columnes es el nombre de columnes del kakuro.
        @param taulell es un string que conte tot el taulell amb la 
        * solucio inclosa.
        @post S'ha generat un nou kakuro fent servir els parametres
        * i s'ha guardat al sistema. Retorna true si el kakuro s'ha pogut
        * generar sense cap problema.
    */
    public boolean newTaulell(String taulell, int files, int columnes) {
       return cd.llegirKakuroNou(taulell, files, columnes);
    }
    
   /** @brief Genera un Kakuro aleatori segons la dificultat i el guarda.
        @pre Cert.
        @param files es el nombre de files del kakuro.
        @param columnes es el nombre de columnes del kakuro.
        @param dificultat es la dificultat que es vol que tingui 
        * el kakuro aleatori.
        @post S'ha generat un kakuro aleatori i s'ha guardat.
    */
    public void generarIGuardarRandomKakuro(String dificultat, int nFiles, int nColumnes){
        cd.generarIGuardarRandomKakuro(dificultat,nFiles,nColumnes);
    }
    
    
    /** @brief Carrega un kakuro ja existent i es genera una nova partida amb 
        * aquest kakuro com a taulell.
        @pre Cert.
        @param id es l'identificador del kakuro.
        @post S'ha carregat el kakuro i s'ha creat 
        * una partida nova utilitzant el kakuro indicat 
        * pel parametre.
    */
    public void carregarKakuro(String id) {
        cd.carregarKakuro(id);
    }
    
    /** @brief Guarda l'estat de la partida que s'esta jugant per 
        * poder seguir jugant-la en una altre moment.
        @pre Cert.
        @param temps es el temps total que s'ha estat jugant la partida.
        @post S'ha guardat la partida per poder jugar-la despres i 
        * retorna l'identificador d'aquesta partida.
    */
    public String guardarPartidaAMitges(String temps){
        return cd.guardarPartidaAMitges(temps);
    }
    
    /** @brief Funcio per carregar una partida guardada.
        @pre Cert.
        @param idPartida es l'identificador de la partida guardada.
        @post s'ha inicialitzat una partida amb la partida guardada.
    */
    public void carregarPartida(String idPartida) {
        cd.carregarPartida(idPartida);
    }
    
    /** @brief Funcio per actualitzar el ranking.
        @pre Cert.
        @param user es l'identificador de l'usuari.
        @param idPartida es l'identificador del taulell.
        @param time es el temps de la partida.
        @post s'actulitza el ranking global i del taulell.
    */
    public void afegirPartidaFinalitzada(String user, String idTaulell, String time) {
        cd.afegirPartidaFinalitzada(user, idTaulell, time);
    }
    
    /** @brief Funcio per aobtenir l'identificador del taulell
        @pre el taulell existeix.
        @post es retorna l'identificador del taulell.
    */
    public String getIdKakuro(){
        return cd.getIdKakuro();
    }

}
