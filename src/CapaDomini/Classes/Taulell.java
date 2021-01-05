/**@file Taulell.java 
 * 
 */

package src.CapaDomini.Classes;

import src.Exceptions.*;
import src.CapaDomini.Utils.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/** @class  Taulell
 *  @brief  Representa un taulell, s'hi guarden totes les dades referents al taulell d'un kakuro.
 *  @author alvaro.andres
 */

public class Taulell {
    /**@brief Nombre de files del taulell*/
    private int files;
    /**@brief Nombre de columnes del taulell*/
    private int columnes;
    /**@brief Nombre de caselles blanques del taulell*/
    private int casellesBlanques;
    /**@brief Difucultat del taulell*/
    private String dificultat;
    /**@brief Taulell creat per un usuari*/
    private boolean manual;
    /**@brief Taulell amb els valors inicials*/
    private Casella[][] taulell;
    /**@brief Taulell amb la solució*/
    private Casella[][] taulellSolucio;

    //Constructora
    /** @brief Constructora per defecte.
        @pre Cert.
        @post S'ha creat Taulell amb valors nuls.
    */
    public Taulell() {
         
    }
    
    /** @brief Constructora per amb paràmetres d'entrada.
        @pre t és una matriu amb valors vàlid per crear un taulell
        @param t matriu amb els valors del taulell
        @param man indica si el taulell s'ha creat de forma manual
        @post S'ha creat Taulell i s'han inicialitzat els valors privats.
    */
    public Taulell(String[][] t, boolean man) {
        manual = man;
        setTaulell(t);
    }
     
   //Consultores
    /** @brief Obtenir el nombre de files del taulell.
        @pre Cert.
        @post Retorna un enter amb el nombre de files del taulell.
    */
    public int getFiles() {
        return files;
    }
    /** @brief Obtenir el nombre de caselles blanques.
     *  @pre Cert.
     *  @return Retorna el nombre de caselles blanques
     */
    public int getNumeroCasellesBlanques(){
        return casellesBlanques;
    }
    /** @brief Obtenir el nombre de columnes del taulell.
        @pre Cert.
        @post Retorna un enter amb el nombre de columnes del taulell.
    */
    public int getColumnes() {
        return columnes;
    }
    
    
    /** @brief Obtenir la dificultat del taulell.
        @pre Cert.
        @post Retorna un string amb la dificultat del taulell (facil, mitja, dificil).
    */
    public String getDificultat() {
        return dificultat;
    }
    
    /** @brief Obtenir si el taulell s'ha creat de forma manual.
        @pre Cert.
        @post Retorna cert si el taulell s'ha creat de forma manual.
    */
    public boolean getManual() {
        return manual;
    }
    
    /** @brief Valor correcte de la casella en una posició determinada del taulell.
        @pre s'ha solucionat el taulell amb el solver
        @param f és la posició de la fila
        @param col és la posició de la columna
        @post Retorna un string amb el valor correcte que hi ha al taulell en la posició f,col
        */
    public String getValorCorrecteCasella(int f, int col) {
        return taulellSolucio[f][col].getValor();
        
    }
    
    /** @brief Funció per obtenir el valor de la casella.
        @pre s'ha creat el taulell
        @param f és la posició de la fila
        @param col és la posició de la columna
        @post Retorna un string amb el valor de la casella.
    */
    public String getValorCasella(int f, int col) {
        return taulell[f][col].getValor();
    }
    
    /** @brief Funcio per obtenir la matriu de Strings que representa el taulell
     *  @pre existeix el taulell
     *  @return Retorna una matriu de strings amb els valors de les caselles del taulell
     */
    public String[][] getValorsTaulell() {
        String[][] sol = new String[files][columnes];
        for(int i = 0; i < files; i++) {
            for(int j = 0; j < columnes; j++) {
                sol[i][j] = taulell[i][j].getValor();
            }
        }
        return sol;
    }
    
    /** @brief Funció per obtenir el valor de la suma de la fila.
        @pre s'ha creat el taulell i la casella és de suma
        @param f és la posició de la fila
        @param col és la posició de la columna
        @post Retorna un string amb el valor de la suma de la fila.
    */
    public int getSumaFila(int f, int col) {
        return taulell[f][col].getSumaFila();
    }
    
    /** @brief Funció per obtenir el valor de la suma de la columna.
        @pre s'ha creat el taulell i la casella és de suma
        @param f és la posició de la fila
        @param col és la posició de la columna
        @post Retorna un string amb el valor de la suma de la columna.
    */
    public int getSumaColumna(int f, int col) {
        return taulell[f][col].getSumaColumna();
    }
    
    /** @brief Funció per saber si la casella es blanca.
        @pre s'ha creat el taulell
        @param f és la posició de la fila
        @param col és la posició de la columna
        @post Si la casella es blanca retorna true, sino false.
    */
    public boolean casellaBlanca(int f, int col) {
        return taulell[f][col].esBlanca();
    }
    /** @brief Funció per saber si la casella es negra.
        @pre s'ha creat el taulell
        @param f és la posició de la fila
        @param col és la posició de la columna
        @post Si la casella es negra retorna true, sino false.
    */
    public boolean casellaNegra(int f, int col) {
        return taulell[f][col].esNegra();
    }
    /** @brief Funció per saber si la casella es de suma.
        @pre s'ha creat el taulell
        @param f és la posició de la fila
        @param col és la posició de la columna
        @post Si la casella es de suma retorna true, sino false.
    */
    public boolean casellaSuma(int f, int col) {
        return taulell[f][col].esSuma();
    }
    
    /** @brief Funció per donar els valors del taulell.
        @pre La matriu és un taulell inicial vàlid.
        @param t matriu de string amb els valors del taulell inicial.
        @post el taulell serà de caselles amb els valors de t.
    */
    private void setTaulell(String[][] t) {
        
        files = t.length;
        columnes = t[0].length;
        taulell = new Casella[files][columnes];
        taulellSolucio = new Casella[files][columnes];
        casellesBlanques = 0;
        int blanca = 0;
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                try {
                    if(t[i][j].charAt(0) == '*') {
                        taulell[i][j] = new CasellaNegra();
                    }

                    else if(t[i][j].charAt(0) == '?') {
                        ++casellesBlanques;
                        taulell[i][j] = new CasellaBlanca("?");
                    }

                    else if(t[i][j].charAt(0) == 'C' || t[i][j].charAt(0) == 'F'){
                        taulell[i][j] = new CasellaSuma(t[i][j]);
                    }
                    else throw new CustomException("Valor no valid per una casella");
                }
                catch (CustomException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        
    }
    
    /** @brief Funció per donar els valors al taulell solucio.
        @pre La matriu és un taulell vàlid i de la mateixa mida que el taulell amb el que s'ha inicialitzat la classe
        *    el param t correspon al mateix taulell que el inicial
        @param t matriu de string amb els valors del taulell aamb numeros possibles valids.
        @post el taulell dolucio està omplert de nombres vàlids pel taulell
    */
    public void setTaulellSolucio(String[][] t) {
        
        files = t.length;
        columnes = t[0].length;
        int blanca = 0;
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if(t[i][j].charAt(0) == '*') {
                    taulellSolucio[i][j] = new CasellaNegra();
                }

                else if(t[i][j].charAt(0) == 'C' || t[i][j].charAt(0) == 'F') {
                   taulellSolucio[i][j] = new CasellaSuma(t[i][j]);
                }

                else{
                    taulellSolucio[i][j] = new CasellaBlanca(t[i][j]);
                }
            }
            
        }
    }
    
    /** @brief Funció per obtenir el valor de la casella.
        @pre La casella s'ha inicialitzat.
        @param f és la posició de la fila
        @param col és la posició de la columna
        @param valor és el valor que se li vol donar a la casella.
        @post Li dona el valor valor a la casella del taulell de la fila f columna col.
    */
    public void setValorCasella(int f, int col, String valor) {
        taulell[f][col].setValor(valor);
    }
    
    /** @brief Funció per obtenir el valor de la casella.
        @pre Cert.
        @param f és la posició de la fila
        @param col és la posició de la columna
        @param valor és el valor correcte de la casella.
        @post Crea una nova casella blanca a la posicio f de la fila i col de la columna al taulellSolucio i li dona el valor valor.
    */
    public void setValorSolucio(int f, int col, String valor) {

        if(valor.charAt(0) == '*') {
            taulellSolucio[f][col] = new CasellaNegra();
        }

        else if(valor.charAt(0) == 'C' || valor.charAt(0) == 'F') {
            taulellSolucio[f][col] = new CasellaSuma(valor);
        }

        else{
            taulellSolucio[f][col] = new CasellaBlanca(valor);
        }
    }

    /**
    	@brief Funció per dir si totes les caselles blanques del Taulell estan connectades
        @pre Taulell esta creat i te celes blanques i negres
        @return Retorna si totes les celes blanques estan connectades entre si
    */
    public boolean CheckConnectivityWhiteCells() {
	    int fil, col = 0;
            
	    int ConnectedCells=0;
            int totalCasellesBlanques = casellesBlanques;
            ArrayList<PairII> backtrackingList = new ArrayList<>();
            Integer idx = 0, jdx = 0;
            //Trobo la primera cel·la blanca
	    for(fil=0; fil < files; ++fil) {
	       for (col = 0; col < columnes; ++col) {
	            if(taulell[fil][col].esBlanca()) {
                    idx=fil;
                    jdx=col;
                    col=columnes;
                    fil=files;
                    }
                }
            }
            
            boolean Visited[][] = new boolean[files][columnes];
            Visited[idx][jdx] = true;
            ConnectedCells++;
            backtrackingList.add(PairII.of(idx,jdx));
            while(!backtrackingList.isEmpty()){
                idx = backtrackingList.get(0).getLeft();
                jdx = backtrackingList.get(0).getRight();
                //right
                if(jdx<columnes-1 && taulell[idx][jdx+1].esBlanca() && !Visited[idx][jdx+1]){
                            ConnectedCells++;
                            Visited[idx][jdx+1]=true;
                            jdx=jdx+1;
                            backtrackingList.add(PairII.of(idx,jdx));
                  }
                //left
                else if(jdx>0 && taulell[idx][jdx-1].esBlanca() && !Visited[idx][jdx-1]){
                        ConnectedCells++;
                        Visited[idx][jdx-1]=true;
                        jdx=jdx-1;
                        backtrackingList.add(PairII.of(idx,jdx));
                    }

                //up
                else if(idx>0 &&  taulell[idx-1][jdx].esBlanca() && !Visited[idx-1][jdx]){
                        ConnectedCells++;
                        Visited[idx-1][jdx]=true;
                        idx=idx-1;
                        backtrackingList.add(PairII.of(idx,jdx));
                    }
                //down
                else if(idx<files-1 && taulell[idx+1][jdx].esBlanca() && !Visited[idx+1][jdx]){
                            ConnectedCells++;
                            Visited[idx+1][jdx]=true;
                            idx=idx+1;
                            backtrackingList.add(PairII.of(idx,jdx));
                }
                else{//pop
                    backtrackingList.remove(0);             
                    
                }
            
            }
	    return totalCasellesBlanques == ConnectedCells;
	}

    /**
    @brief Funció per dir si existeix alguna fila o columna amb una unica cel·la en el taulell
    @pre Taulell esta creat i te celes blanques
    @return Retorna TRUE si totes les columnes i files tenen com a minim dues celes
    */
    public boolean CheckOneCellArragement() {
	    boolean Notfound=true;
	    int fil, col;
            fil=col=0;
	    for(fil = 0; fil < files && Notfound; ++fil) {
	       for (col = 0; col < columnes && Notfound; ++col) {
	            if(taulell[fil][col].esNegra() )
                       if( nombreCasellesBlanquesSpecific(fil,col,true)  == 1 || nombreCasellesBlanquesSpecific(fil,col,false) == 1 )
                            return false;
	        }
	     }
            return true;
    }
    
    
    /**
    * @brief Donada una posicio i el seu valor futur, a una
    * @post si retorna FALSE no s'ha fet ningun canvi, si retorna TRUE s'ha transformat al tipus indicat
    * @pre Taulell existeix
    * @param fila posicio vertical de la cel·la a cambiar
    * @param col posicio horitzontal de la cel·la a cambiar 
    * @param ChangeTo tipus de cell que volem transformar en
    * @param CellValue si es un cambi a CasellaSuma sera la informacio de la cela transformada
    * @post si la posicio es una posicio del taulell es cambia el valor de la cela en aquesta posicio i el taulell no te perque ser estructuralment correcte
    */
    public void TransformCellToOtherType(int fila, int col, int ChangeTo, String CellValue){
        if(fila>=0 && fila<files && col>=0 && col<columnes) {
            if(taulell[fila][col].esBlanca()) casellesBlanques--;
            switch(ChangeTo){
                case FinalVariables.CapACellaBlanca:
                    casellesBlanques++;
                    taulell[fila][col] = new CasellaBlanca("?");
                break;

                case FinalVariables.CapACellaSuma:
                    taulell[fila][col] = new CasellaSuma(CellValue);
                break;

                case FinalVariables.CapACellaNegra: 
                    taulell[fila][col] = new CasellaNegra();
                break;

                default://ErrorHandling
            }
        }
    }
    
    
    
    
    
    /**
     * @brief Comprova si un kakuro no te seccions blanques de menys de 2 o mas de 9
     * @return Devuelve si no hay secciones de blancas de menos de 2 fichas o mas de 9
     */
    private boolean CheckWhiteCellArragement(){
        for(int idx=0;idx<files;idx++){
            for(int jdx=0;jdx<columnes;jdx++){
                if(taulell[idx][jdx].esNegra()){
                    if(     jdx+1<columnes &&
                            taulell[idx][jdx+1].esBlanca() &&
                            (nombreCasellesBlanquesSpecific(idx,jdx,false)>9 ||
                             nombreCasellesBlanquesSpecific(idx,jdx,false)<2
                            )
                    ){
                        return false;
                    }
                    if( idx+1<files &&
                        taulell[idx+1][jdx].esBlanca() &&
                            (nombreCasellesBlanquesSpecific(idx,jdx,true)>9 ||
                             nombreCasellesBlanquesSpecific(idx,jdx,true)<2
                            )
                    ){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * @brief Un kakuro es estructuralmente valido si las celdas blancas estan conectadas i no hay filas o columas de blancas de mas de 9 o menos de 2
     * @return Devuelve si un kakuro es estructuralmente valido
     */
    public boolean CheckTaulellValidEstructuralment(){
        if(files<3 || columnes < 3){
            return false;
        }
        if(!CheckConnectivityWhiteCells()){
            return false;
        }
        if(!CheckWhiteCellArragement()){
            return false;
        }
        return true;
    }
    
    /**
     * @brief Genera un Taulell amb totes les celes blanques excepte la primera fila i la primera columna
     * @param nfiles
     * @param ncolumnes 
     * @post el taulell no te perque ser estructuralment correcte
     */
    public void GenerateDefaultBoard(int nfiles, int ncolumnes){
        files = nfiles;
        columnes = ncolumnes;
        taulell = new Casella[nfiles][ncolumnes];
        for(int i=0;i<nfiles;i++){
            for(int j=0;j<ncolumnes;j++){
                if(i==0 || j==0)   taulell[i][j] = new CasellaNegra();
                else taulell[i][j] = new CasellaBlanca("?");
            }
        }
        casellesBlanques = (nfiles-1)*(ncolumnes-1);
    }
    
    
    /**
     * @pre El taulell es estructuralment correcte
     * @return Retorna la suma de totes les permutacions de les cel·les blanques del taulell
     */
    private double TotalWhiteCellPermutation(){
        int TotalPermutation=0;
        Permutacions p = new Permutacions();
        int sumaFila = 0, sumaColumna = 0, nombreCasellesFila = 0, nombreCasellesColumna = 0;
        for(int fila=0;fila<files;fila++){
            for(int col=0;col<columnes;col++){
                if(taulell[fila][col].esBlanca()){
                    boolean trobat=false;
                    int ii,jj;
                    //trobar casella suma Vertical
                    for(ii=fila; ii>=0 && !trobat; ii--){
                        if(taulell[ii][col].esSuma() ) {
                            trobat = true;
                            sumaColumna = taulell[ii][col].getSumaColumna();
                            nombreCasellesColumna = nombreCasellesBlanquesSpecific(ii,col,true);
                        }
                    }//trobar casella suma Horitzontal
                    trobat = false;
                    for(jj=col; jj>=0 && !trobat; jj--){
                        if(taulell[fila][jj].esSuma() ){
                            trobat = true;
                            sumaFila = taulell[fila][jj].getSumaFila();
                            nombreCasellesFila = nombreCasellesBlanquesSpecific(fila,jj,false);

                        }
                    }
                    TotalPermutation += p.interseccio(nombreCasellesFila, sumaFila, nombreCasellesColumna ,sumaColumna).size();
                }                      
            }
        }
        return TotalPermutation;
    }
    
       
    /**
     * @brief Funció per saber la mida del conjunt de caselles blanques del que forma una posicio al taulell.
     * @param fila Posicio vertical de la casella
     * @param columna Posicio horitzontal de la casella
     * @param vertical Si el conteig es fara vertical TRUE o horizontal False
        @pre S'introdueix dos enterns fila i columna que indiquen la casella de la matriu on estas (No te perque ha de ser de tipus suma)
        * i un bool h.
        @post Si vertical es true, retorna el nombre de caselles blanques que fan la suma en vertical, si es fals retorna les que ho fan en horitzontal.
    */
    public int nombreCasellesBlanquesSpecific (int fila, int columna, boolean vertical) {
        int caselles = 0;
        boolean negra = false;  
        
        if (vertical) {
            //nombre de caselles verticals
            if(taulell[fila][columna].esSuma()){
                for (int i = fila+1; (i < files) && !negra; i++) {
                   if (taulell[i][columna].esBlanca()) ++caselles;
                   else negra = true;
               }
            }
            else if(taulell[fila][columna].esBlanca()){
                for (int i = 1; fila+i < files && !negra; i++) {
                   if (taulell[fila+i][columna].esBlanca()) ++caselles;
                   else negra = true;
                }
                for (int i = fila; i > 0 && !negra; i--) {
                   if (taulell[i][columna].esBlanca()) ++caselles;
                   else negra = true;
                }
            }
            else if(taulell[fila][columna].esNegra()){
                for (int i = 1; fila+i < files && !negra; i++) {
                   if (taulell[fila+i][columna].esBlanca()) ++caselles;
                   else negra = true;
                }
            }
        }
         
         else {
            //nombre de caselles horitzontals
            if(taulell[fila][columna].esSuma()){
                for (int i = columna+1; i < columnes && !negra; i++) {
                   if (taulell[fila][i].esBlanca()) ++caselles;
                   else negra = true;
               }
            }
            else if(taulell[fila][columna].esBlanca()){
                for (int i = 1; i+columna < columnes && !negra; i++) {
                   if (taulell[fila][columna+i].esBlanca()) ++caselles;
                   else negra = true;
                }
                for (int i = columna; i > 0 && !negra; i--) {
                   if (taulell[fila][i].esBlanca()) ++caselles;
                   else negra = true;
                }
            }
            else if(taulell[fila][columna].esNegra()){
                for (int i = columna+1; (i < columnes) && !negra; i++) {
                   if (taulell[fila][i].esBlanca()) ++caselles;
                   else negra = true;
                }
            }
         }
        return caselles;
    }
    
    /**
     * @brief Calcula la Dificultat de un taulell donat un taulell valid
     * La dificultat del taulell es calcula a partir de la permutabilitat de cada casella, donada la seva sumaFila i la seva sumaColumna
     * i el tant per cent de celes blanques en relaciò a les caselles no blanques.
     * @return Retorna la dificultat del kakuro
     * @post el taulell te asignat la dificultat que li correspon
     */
    public String CalcularDificultatKakuro(){
        Permutacions p = new Permutacions();
        
        double permutabilitat;
        int nCasellesblanques, CasellesNegres;
        permutabilitat = TotalWhiteCellPermutation()/casellesBlanques;
        
        double percentatgeBlanques = (double)casellesBlanques/(files*columnes);
        double dificultyscore =  permutabilitat/ (1-percentatgeBlanques);
        
        String CalculatedDificulty;
        if(dificultyscore >=  FinalVariables.DificultatScoreDificil) CalculatedDificulty = FinalVariables.DificultatDificil;
        else if(dificultyscore >= FinalVariables.DificultatScoreMig) CalculatedDificulty = FinalVariables.DificultatMig;
             else CalculatedDificulty = FinalVariables.DificultatFacil;
        
        dificultat = CalculatedDificulty;
        
        return getDificultat();
    }
    
    /**
     * @brief Funció posar la dificultat del taulell.
     * @param dif Dificultat del taulell
        @pre Existeix el taulell i la seva solució.
        @post S'haura inicialitzat la variable dificultat amb el valor dif.
    */
    public void setDificultat(String dif) {
        this.dificultat = dif;
    }
    
    /**
     * @brief Retorna la solució del taulell.
        @pre Existeix el taulell i la seva solució.
        @post Es retorna una matriu string amb els valors de la solucio.
    */
    public String[][] getTaulellSolucio(){
       
        String s [][] = new String [files][columnes];
        
        for (int i = 0; i<files; ++i){
            for (int j = 0; j<columnes; ++j){
                s[i][j] = taulellSolucio[i][j].getValor();
            }
        }
        return s;
    }
}
