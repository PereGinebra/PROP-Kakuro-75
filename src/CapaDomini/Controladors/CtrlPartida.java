/**@file CtrlPartida.java 
 * 
 */
package src.CapaDomini.Controladors;
import src.CapaDomini.Utils.PairII;
import src.Exceptions.*;
import java.util.Scanner;
import java.util.*;
import src.CapaDomini.Classes.Taulell;

import src.CapaDomini.Classes.Partida;
import src.CapaDomini.Utils.PairSB;

/** @class CtrlPartida
 *  @brief Controlador per poder jugar una partida
 *
 * @author alvaro.andres
 */
public class CtrlPartida {
    /**@brief Partida associada */
    Partida p;
    /**@brief Identificador del kakuro que s'esta jugant en aquest moment */
    String IdKakuro;
    
    //Constructores
    /** @brief Constructora per defecte.
        @pre Cert.
        @post S'ha creat el controlador partida amb valors nuls.
    */
    public CtrlPartida() {
        
    }
    
    /** @brief Constructora per defecte amb atribut partida.
        @pre x es una partida existent.
        @param x es una partida.
        @post S'ha creat el controlador partida i se 
        * li assigna x a la partida p.
    */
    public CtrlPartida(Partida x){
        this.p = x;
    }
    
        
    /** @brief Constructora per defecte amb atribut partida i l'identificador
     * del kakuro que es vol jugar.
        @pre x es una partida existent.
        @param x es una partida.
        @param IDK es l'identificador d'un kakuro existent.
        @post S'ha creat el controlador partida i se li assigna x a la 
        * partida p i se li assigna el string IDK a l'identificador del kakuro
        * que es vol jugar.
    */
    public CtrlPartida(Partida x,String IDK){
        this.p = x;
        this.IdKakuro = IDK;
    }
    
    

    
    //Consultores
    
    /** @brief Consulta el id del taulell que s'esta jugant.
        @pre La partida s'ha inicialitzat i l'identificador del kakuro també.
        @post Retorna el id del taulell de la partida.
    */
    public String getIdKakuro(){
        return IdKakuro;
    }
    
    
    /** @brief Consultora per obtenir el nombre de files del 
        * taulell de la partida.
        @pre Cert.
        @post Retorna el nombre de files del taulell de la partida.
    */
    private int getFiles(){
        return p.getFiles();
    }
    
    /** @brief Consultora per obtenir el nombre de columnes del
        * taulell de la partida.
        @pre Cert.
        @post Retorna el nombre de columnes del taulell de la partida.
    */
    private int getColumnes(){
        return p.getColumnes();
    }
    
    /** @brief Consulta el valor posat per l'usuari en una casella.
        @pre La partida ha sigut inicialitzada i encara no s'ha acabat.
        @pre La casella de coordenades [i][j] es una casella blanca.
        @param i es la fila de la casella.
        @param j es la columna de la casella.
        @post Retorna el valor colocat per l'usuari a la casella 
        * de coordenades [i][j].
    */
    public String getValorActual(int i, int j) {
        return p.getValorActual(i, j);
    }
    
    /** @brief Consultora per obtenir els valors que 
        * l'usuari ha posat a la partida.
        @pre La partida ha sigut inicialitzada i encara no s'ha acabat.
        @post Retorna els valors que l'usuari ha posat a la partida.
    */
    public PairSB[][] getValorsActuals(){
        return p.getValorsActuals();
    }
    
    /** @brief Retorna les coordenades de les caselles de 
        * la partida que no han sigut solucionades.
        @pre La partida ha sigut inicialitzada i encara no s'ha acabat.
        @post S'ha retornat les coordenades de les caselles 
        * de la partida que no han sigut solucionades.
    */
    public ArrayList< PairII > getcoordNoConfirmades(){
        return p.getcoordNoConfirmades();
    }
    
       
    /** @brief Consultora per obtenir la dificultat de la partida.
        @pre La partida ha sigut inicialitzada.
        @post Retorna la dificultat de la partida.
    */
    public String getDificultat(){
        return p.getDificultat();
    }
    
    /** @brief Consultora per obtenir la quantitat de temps que s'ha jugat la partida.
        @pre La partida ha sigut inicialitzada i encara no s'ha acabat.
        @post Retorna la quantitat de temps que s'ha jugat la partida en el 
        * format següent: hh:mm:ss.
    */
    public String getTemps(){
        return p.getTemps();
    }
    

    
    public Taulell getTaulell() {
        return p.getTaulell();
    }
    
    /** @brief Consultora per saber si la casella es pot modificar.
        @pre Hi ha una partida inicialitzada.
        @param fila es la posicio de la fila
        @param columna es la posicio de la columna
        @post Retorna true si la casella es pot modificar.
    */
    public boolean casellaEsEscribible(int fila,int columna){
        return p.casellaEsEscribible(fila,columna);
    }
    
    /** @brief Mira si hi ha algun nombre igual que el que es vol posar en la mateixa fila i/o columna.
        @pre Hi ha una partida inicialitzada.
        @param i es la posicio de la fila
        @param j es la posicio de la columna
        @param s es el valor a comprovar
        @post Retorna true si i nomes si en hi ha algun nombre que sigui igual que el nombre de la casella de coordenades [i][j].
    */
    private boolean hiHaRepetits(int i, int j, String s){
        return p.hiHaRepetits(i, j, s);
    }
    
    /** @brief Consulta si la partida ha sigut acabada.
        @pre Cert.
        @post Retorna un bool que indica si s'ha acabat la partida.
    */
    public boolean partidaAcabada() {
        return p.partidaAcabada();
    }
    
    //Modificadores
    /** @brief Modificadora d'una casella del tauler.
        @pre Hi ha una partida inicialitzada i string valor es valid.
        @param i es la posicio de la fila
        @param j es la posicio de la columna
        @param valor es el valor de casellas
        @post S'ha solucionat el tauler de la partida.
    */
    public void modificarCasella(int fila, int columna, String valor){
        p.modificarCasella(fila,columna,valor);
    }
    
    /** @brief Modificadora d'una casella del tauler mitjançant ajuda.
        @pre Cert.
        @post S'ha modificat una casella aleatoria del taulell.
    */
    public PairII demanarAjuda(){
        return p.demanarAjuda();
    }
    
    /** @brief Resol tot el kakuro.
        @pre Cert.
        @post Resol tot el kakuro.
    */ 
    public String[][] solucionar(){
        return p.solucionar();
    }
    

    

    /** @brief Assigna els atributs necessaris per tal de poder jugar la partida.
        @pre Tots els atributs pertanyen a la mateixa partida.
        @post S'ha assignat els atributs necessaris per tal de poder jugar la partida.
    */ 
    public void assignarAtributs(String time, String IDPartida,PairSB  mat[][],ArrayList< PairII > llista,Taulell t){
        p.assignarAtributs(time,IDPartida,mat,llista,t);
    }
    
        /** @brief Modifica el temps que s'ha jugat la partida.
        @pre La partida ha sigut inicialitzada i encara no s'ha acabat.
        @param t és un string que representa el temps en el format següent:
        *  hh:mm:ss.
        @post S'ha mofificat la quantitat de temps que s'ha jugat la partida.
    */
    public void setTemps(String t) {
        p.assignarTemps(t);
    }
   
}
