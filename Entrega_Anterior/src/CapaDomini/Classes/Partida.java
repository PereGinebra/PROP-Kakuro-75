/**@file Partida.java 
 * 
 */

package src.CapaDomini.Classes;

import java.util.*;
import src.CapaDomini.Utils.PairII;
import src.CapaDomini.Utils.PairSB;
import src.CapaDomini.Utils.FinalVariables;

/**@class Partida 
 * @brief Representa una partida
 *  
 *  @author pere.ginebra
*/
public class Partida{
    /**@brief Temps empreat en la partida */
    private int temps;
    /**@brief Identificador de la partida */
    private int IDpartida;
    /**@brief Valors actuals del kakuro */
    private PairSB  matriuValorsActuals[][];
    
    
    /**@brief Llista de coordenades de les caselles blanques no resoltes */
    private List< PairII > coordNoConfirmades;
    /**@brief Nombre d'ajudes utilitzades per resoldre la partida */
    private int ajudesUtilitzades;
    /**@brief Taulell associat a la partida */
    private Taulell mapa;
    /**@brief Indica si la partida s'ha finalitzat */
    private boolean acabada;


    //Creadora
    /** @brief Creadora per defecte.
        @pre Cert.
        @post Crea una partida amb tots els valors nuls.
    */ 
    public Partida() {

    }
    
    //Creadora
    /** @brief Creadora de partida.
        @param t és un taulell valid
        @pre Cert.
        @post Crea una partida i s'assigna el taulell a aquesta.
    */
    public Partida(Taulell t){
        temps = 0;
        mapa = t;
        
        int n = t.getFiles();
        int m = t.getColumnes();
        
        matriuValorsActuals= new PairSB[n][m];

        for (int i = 0 ; i<n; ++i){
            for (int j = 0; j<m; ++j){
                matriuValorsActuals[i][j] = new PairSB("?", true);
            }
        }
        
        acabada = false;
        ajudesUtilitzades = 0;
        
        coordNoConfirmades = new ArrayList < PairII > ();
        omplirCoordNoConfirmades();
    }

    //Consultores
    /** @brief Retorna la quantitat de temps que s'ha jugat la partida.
        @pre Cert.
        @post Retorna la quantitat de temps que s'ha jugat la partida.
    */ 
    
    public int getTemps(){
        return temps;
    }
    
    /** @brief Retorna la dificultat de la partida.
        @pre El kakuro de la partida ha sigut creat automàticament.
        @post Retorna la dificultat de la partida.
    */ 
    public String getDificultat() {
        return mapa.getDificultat();
    }
    
    /** @brief Retorna el nombre d'ajudes utilitzades.
        @pre Cert.
        @post Retorna el nombre d'ajudes utilitzades.
    */
    public int nombreAjudesDemanades() {
        return ajudesUtilitzades;
    }
    
    /** @brief Retorna el nombre de files del kakuro.
        @pre Cert.
        @post Retorna el nombre de files del kakuro.
    */ 
    public int getFiles(){
        return mapa.getFiles();
    }
    
    /** @brief Retorna el nombre de columnes del kakuro.
        @pre Cert.
        @post Retorna el nombre de columnes del kakuro.
    */ 
    public int getColumnes(){
        return mapa.getColumnes();
    }

    
    /** @brief Retorna el ID de la partida.
        @pre Cert.
        @post Retorna el ID de la partida.
    */ 
    public int getIdPartida(){
        return IDpartida;
    }
    
    /** @brief Consulta si la casella de coordenades [i][j] és una casella on hi ha d'haver un nombre posat per l'usuari.
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @param 0<=i<nombre de files del kakuro.
        @param 0<=j<nombre de columnes del kakuro.
        @post Retorna true si i només hi hauria d'haver un nombre en la casella amb coordenades [i][j].
    */
    private boolean casellaEsBlanca (int i, int j){
        return mapa.casellaBlanca(i,j);
    }
    
    
    /** @brief Mira si hi ha algun nombre igual que el que es vol posar en la mateixa fila i/o columna.
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @param i és la posició de la fila
        @param j és la posició de la columna
        @param s és un string amb el valor a comprovar
        @post Retorna true si i només si en hi ha algun nombre que sigui igual que el nombre de la casella de coordenades [i][j].
    */
    public boolean hiHaRepetits (int i, int j, String s){
        int n = getFiles();
        int m = getColumnes();
        
        
        //Cap a la dreta.
        int ii = i;
        int jj = j+1;
        while ((jj<m) && (casellaEsBlanca(ii,jj))){
            
            String vActual = matriuValorsActuals[ii][jj].getLeft();
            
            
            if (vActual.compareTo(s) == 0){
                return true;
            }
            ++jj;
        }
        
        // Cap a baix.
        ii = i+1;
        jj = j;
        while ((ii<n) && (casellaEsBlanca(ii,jj))){
               
            String vActual = matriuValorsActuals[ii][jj].getLeft();
            if (vActual.compareTo(s) == 0){
                return true;
            }
            ++ii;
        }
        
        //Cap a l'esquerra.
        ii = i;
        jj = j-1;
        while ((0<=jj) && (casellaEsBlanca(ii,jj))){
        
            String vActual = matriuValorsActuals[ii][jj].getLeft();
            if (vActual.compareTo(s) == 0){
                return true;
            }
            --jj;
        }
        
        //Cap a dalt.
        ii = i-1;
        jj = j;
        while ((0<=ii) && (casellaEsBlanca(ii,jj))){
        
            String vActual = matriuValorsActuals[ii][jj].getLeft();
            if (vActual.compareTo(s) == 0){
                return true;
            }
            --ii;
        }
        
        return false;
    
    }
    
    /** @brief Retorna el valor de la casella [i][j].
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @param i és la posició de la fila
        @param j és la posició de la columna
        @post Retorna el valor actual de la casella de coordenades[i][j].
    */ 
    private String getValorActual(int i, int j){
        return matriuValorsActuals[i][j].getLeft();
    }
    
    
    
    /** @brief Retorna el valor correcte de la casella [i][j].
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @param i és la posició de la fila
        @param j és la posició de la columna
        @post Retorna el valor correcte de la casella [i][j].
    */ 
    private String getValorCorrecte(int i, int j){
        return mapa.getValorCorrecteCasella(i,j);
    }
    
    /** @brief Consulta si es pot escriure un nombre en la casella amb coordenades [i][j].
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @param i és la posició de la fila
        @param j és la posició de la columna
        @post Retorna true si i només si es pot escriure un nombre en la casella amb coordenades [i][j].
    */
    public boolean casellaEsEscribible(int i, int j){
        return (casellaEsBlanca(i,j) && matriuValorsActuals[i][j].getRight());
    }
    
    /** @brief Consulta si la casella [i][j] ha estat bloquejada perquè ja està solucionada.
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @param i és la posició de la fila
        @param j és la posició de la columna
        @post Retorna true si i la casella [i][j] és una casella blanca que ha estat bloquejada.
    */
    public boolean casellaEsBloquejada(int i, int j){
        return (casellaEsBlanca(i,j) && !matriuValorsActuals[i][j].getRight());
    }
    
    
    /** @brief Comprova si la partida ha sigut acabada.
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.  
        @post Retorna true si i només s'han omplert totes les caselles blanques amb el valor correcte.
    */
    public boolean partidaAcabada(){
        int files = getFiles();
        int columnes = getColumnes();
        
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                
                if (casellaEsBlanca(i, j)) {
                    
                    if (getValorActual(i,j).compareTo(getValorCorrecte(i,j)) != 0){
                        System.out.println("La partida no esta acabada correctament");
                        return false;
                    } 
                }
            }
        }
        acabada = true;
        return true;  
    }
    
    
    /** @brief Escriu el taulell amb els valors que s'ha posat fins ara.
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @post Escriu el taulell amb els valors que s'ha posat fins ara.
    */
    public void escriureTaulellPartida() {
        
        
        int files = getFiles();
        int columnes = getColumnes();
        
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        
        System.out.print(ANSI_BLUE);
        for(int a=-1;a<columnes;a++){
            System.out.print(src.CapaDomini.Utils.FinalVariables.NormalitzarEspai("c"+String.valueOf(a)));
            if(a!=columnes-1)System.out.print("|");
        }
        System.out.print("\n");
        for (int j=0;j<columnes+1;j++) {
            System.out.print("-");
            System.out.print("-------");
         }
        System.out.print("\n");
        System.out.print(ANSI_RESET);
        for(int i=0;i<files;i++){
          for (int j=0;j<columnes;j++) {
            if(j==0)System.out.print(ANSI_BLUE+src.CapaDomini.Utils.FinalVariables.NormalitzarEspai("f"+String.valueOf(i))+ANSI_RESET);  
            System.out.print("|");
            if (casellaEsBlanca(i, j)) {
                    System.out.print(FinalVariables.NormalitzarEspai(getValorActual(i,j)));
                }
                
            else {
                    System.out.print(FinalVariables.NormalitzarEspai(mapa.getValorCasella(i,j)));
                }
          }
          System.out.print("\n");
          for (int j=0;j<columnes+1;j++) {
            if(j!=0) System.out.print("-");
            System.out.print("-------");
          }
          System.out.print("\n");
        }
        
        
    }
    
    
    //Modificadores
    /** @brief Quan es crea una partida omple un conjunt amb les coordenades que pertanyen a caselles blanques.
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.  
        @post Les coordenades de caselles blanques han sigut guardades.
    */
    private void omplirCoordNoConfirmades(){
        int n = getFiles();
        int m = getColumnes();
        
        for (int i = 0; i<n; ++i){
            for (int j = 0; j<m; ++j){
                if (casellaEsBlanca(i,j)){
                    coordNoConfirmades.add(PairII.of(i,j));
                }
            }   
        }
    }

    /** @brief Resol una casella i la bloqueja per tal que no es pugui modificar.
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @pre La partida no està acabada.  
        @post Una casella blanca aleatòria ha estat resolta.
    */
    
    public void demanarAjuda(){
        
        ++ajudesUtilitzades;
        
        Random r = new Random();

        int mida = coordNoConfirmades.size();
        int nAleatori = r.nextInt(mida);
        PairII p = coordNoConfirmades.remove(nAleatori);
        int i = p.getLeft();
        int j = p.getRight();
        resoldreCasella(i,j);
    }
    
    /** @brief Resol tot el kakuro.
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @post Resol tot el kakuro.
    */ 
    
    public void solucionar(){
        
        ajudesUtilitzades = Integer.MAX_VALUE;
        int n = getFiles();
        int m = getColumnes();
        
        for (int i = 0; i<n; ++i){
            for (int j = 0; j<m; ++j){
                if (casellaEsEscribible(i,j)){
                    resoldreCasella(i,j);
                }
                
            }
        }
        acabada = true;
    }
    
    /** @brief Modifica la casella de coordenades [i][j] posant-li el valor "valor".
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @pre La casella és una casella blanca i no està bloquejada.
        @param i és la posició de la fila
        @param j és la posició de la columna
        @param valor és el valor que se li vol donar a la casella
        @post La casella ha estat modificada.
    */ 
    public void modificarCasella(int fila, int columna,String valor){
        if (casellaEsEscribible (fila, columna)){
            matriuValorsActuals[fila][columna].setLeft(valor);
        }
        
        else System.out.println("La casella no es pot modificar.");
    }
    
    /** @brief Resol la casella de coordenades [i][j].
        @pre El taulell ja havia estat inicialitzat i s'ha trobat la seva solució única.
        @pre La casella és una casella blanca.
        @param i és la posició de la fila
        @param j és la posició de la columna
        @post Resol una casella i no deixa que s'hi torni a escriure.
    */ 
    private void resoldreCasella(int i, int j){
        matriuValorsActuals[i][j].setLeft(getValorCorrecte(i,j));
        matriuValorsActuals[i][j].setRight(false);
    }
    
};
