/**@file CtrlPartida.java 
 * 
 */
package src.CapaDomini.Controladors;

import src.Exceptions.*;
import java.util.Scanner;
import java.util.*;

import src.CapaDomini.Classes.Partida;

/** @class CtrlPartida
 *  @brief Controlador per poder jugar una partida
 *
 * @author alvaro.andres
 */
public class CtrlPartida {
    /**@brief Partida associada */
    Partida p;
    
    //Constructores
    /** @brief Constructora per defecte.
        @pre Cert.
        @post S'ha creat el controlador partida amb valors nulls.
    */
    public CtrlPartida() {
        
    }
    
    /** @brief Constructora per defecte amb atribut partida.
        @pre x es una partida existent.
        @param x és una partida.
        @post S'ha creat el controlador partida i s'assigna x a partida p
    */
    public CtrlPartida(Partida x){
        this.p = x;
    }
    
    
    /** @brief Modificadora per jugar la partida.
        @pre Hi ha un taulell associat al controlador.
        @post S'ha solucionat el tauler de la partida.
    */
    public void jugar() {
        
        
        
        Scanner reader = new Scanner(System.in);
        boolean resolt = false;
                
        while(!resolt) {
            try {
                System.out.println("");
                p.escriureTaulellPartida();
                System.out.println("");

                System.out.println("Introdueix fila a modificar: ");
                System.out.println("Fila = -1 resoldre una casella aleatoria");
                System.out.println("Fila = -2 solucionar tot el taulell i acabar partida");
                System.out.println("Fila = -3 acaba d'executar el programa");

                boolean revisar = false;
                int fila = reader.nextInt();
                
                switch (fila) {
                    case -1:
                        demanarAjuda();
                        revisar = true;
                        break;

                    case -2:
                        solucionar();
                        revisar = true;
                        break;
                        
                    case -3:
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Introdueix columna a modificar: ");
                        int columna = reader.nextInt();
                        int n = getFiles();
                        int m = getColumnes();
                        if ((0>fila) || (fila>=n) || (0>columna) || (columna>=m)){
                            throw new ExceptionPosicioNoValida();
                        }

                        else if (p.casellaEsEscribible(fila,columna)==false){
                            throw new ExceptionCasellaNoModificable();
                        }


                        else {
                            reader = new Scanner(System.in);
                            String valor = reader.nextLine();
                            if (Integer.parseInt(valor)>9 || Integer.parseInt(valor)<= 0) {
                                throw new ExceptionValorForaRang();
                            }
                            
                            else if (hiHaRepetits(fila, columna, valor)){
                                throw new ExceptionValorNoValid();
                            }

                            else{

                                modificarCasella(fila,columna,valor);
                                revisar = true;
                            } 
                        }break;
                }

                if (revisar && p.partidaAcabada()) {
                    System.out.println("Enhorabona, has solucionat el kakuro correctament");
                    System.out.println("");
                    p.escriureTaulellPartida();
                    System.out.println("");
                    resolt = true;
                } 
            }
       
            catch (ExceptionPosicioNoValida e) {
                System.out.println(e.getMessage());
            }

            catch (ExceptionCasellaNoModificable e) {
                System.out.println(e.getMessage());
            }

            catch (ExceptionValorForaRang e) {
                System.out.println(e.getMessage());
            }

            catch (ExceptionValorNoValid e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    //Consultores
    /** @brief Consultora per obtenir el nombre de files del taulell de la partida.
        @pre Cert.
        @post Retorna el nombre de files del taulell de la partida.
    */
    private int getFiles(){
        return p.getFiles();
    }
    
    /** @brief Consultora per obtenir el nombre de columnes del taulell de la partida.
        @pre Cert.
        @post Retorna el nombre de columnes del taulell de la partida.
    */
    private int getColumnes(){
        return p.getColumnes();
    }
    
    /** @brief Consultora per saber si la casella es pot modificar.
        @pre Hi ha una partida inicialitzada.
        @param fila és la posició de la fila
        @param columna és la posició de la columna
        @post Retorna true si la casella es pot modificar.
    */
    private boolean casellaEsEscribible(int fila,int columna){
        return p.casellaEsEscribible(fila,columna);
    }
    
    /** @brief Mira si hi ha algun nombre igual que el que es vol posar en la mateixa fila i/o columna.
        @pre Hi ha una partida inicialitzada.
        @param i és la posició de la fila
        @param j és la posició de la columna
        @param s és el valor a comprovar
        @post Retorna true si i només si en hi ha algun nombre que sigui igual que el nombre de la casella de coordenades [i][j].
    */
    private boolean hiHaRepetits(int i, int j, String s){
        return p.hiHaRepetits(i, j, s);
    }
    
    //Modificadores
    /** @brief Modificadora d'una casella del tauler.
        @pre Hi ha una partida inicialitzada i string valor és vàlid.
        @param i és la posició de la fila
        @param j és la posició de la columna
        @param valor és el valor de casellas
        @post S'ha solucionat el tauler de la partida.
    */
    private void modificarCasella(int fila, int columna, String valor){
        p.modificarCasella(fila,columna,valor);
    }
    
    /** @brief Modificadora d'una casella del tauler mitjançant ajuda.
        @pre Cert.
        @post S'ha modificat una casella aleatoria del taulell.
    */
    private void demanarAjuda(){
        p.demanarAjuda();
    }
    
    /** @brief Resol tot el kakuro.
        @pre Cert.
        @post Resol tot el kakuro.
    */ 
    private void solucionar(){
        p.solucionar();
    }
   
}
