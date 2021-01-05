/**@file KakuroSolver.java
 * 
 */
package src.CapaDomini.Controladors;

import src.Exceptions.ExceptionTaulellSenseSolucio;
import src.CapaDomini.Classes.Taulell;

/** @class KakuroSolver
 *  @brief Controlador per poder solucionar i validar un taulell kakuro
 *
 * @author alvaro.andres
 */
public class KakuroSolver {
    /**@brief Nombre de files del taulell */
    private int files;
    /**@brief Nombre de columnes del taulell */
    private int columnes;
    /**@brief Taulell associat al solver */
    private Taulell taulell;

    /**@brief Nombre de solucions del kakuro */
    private class Solucions {
        int num;
    }
    
    //Constructores
    /** @brief Constructora per defecte.
        @pre Cert.
        @post S'ha creat kakuroSolver amb valors nulls.
    */
    public KakuroSolver() {
        
    }
    
    /** @brief Constructora per defecte.
        @pre S'introdueix un taulell amb un kakuro.
        @param t es un taulell
        @post S'ha creat kakuroSolver, s'associa el taulell i es dona valor a les files i les columnes.
    */
    public KakuroSolver(Taulell t) {
        taulell = t;
        files = t.getFiles();
        columnes = t.getColumnes();
    }
    
    /** @brief Solucionador i validador del taulell.
        @pre Hi ha un taulell inicialitzat.
        @param check boolea per saber si s'ha de comprovar que la solució és única
        @post Si check es false, no es valida el taulell, si es true si es valida, en ambdos casos s'escriu la solució al taulell.
    */
    public void solucio(boolean check) throws ExceptionTaulellSenseSolucio {
        
        Solucions sol = new Solucions();
        sol.num=0;
        int c = 0;
        int f = 0;
        boolean trobat = false;
        
        if(!taulell.CheckTaulellValidEstructuralment()) throw new ExceptionTaulellSenseSolucio();
        
        while(f < files && !trobat) {
            c=0;
            
            while (c < columnes && !trobat) {
                if (taulell.casellaBlanca(f,c)) trobat= true;
                else ++c;
            }
            
            if (!trobat) ++f;
        }
        
        if(trobat) solver(f, c, sol, check);
        if (sol.num == 1){
            if (check) System.out.println("Solucio Unica");
        }
        
        else if (sol.num != 1) throw new ExceptionTaulellSenseSolucio();

    }
    
    /** @brief Algoritme solucionador i validador.
        @pre Hi ha un taulell inicialitzat.
        @param fila és la posició fila
        @param columna és la posició columna
        @param numSol és el nombre de solucions trobades.
        @param check boolea per saber si s'ha de comprovar que la solució és única
        @post Si troba una solució l'escriu a la solucio del taulell i numSol tindrà el nombre de solucions que s'han trobat (màxim 2).
    */
    private void solver(int fila, int columna, Solucions numSol, boolean check) {
        if (!check && numSol.num == 1) return;
        
        if (numSol.num > 1) {
            return;
        }
        
        if (fila == files) {
            setTaulellSolucio();
            ++numSol.num;
            return;
        }

        if (taulell.casellaBlanca(fila,columna)) {
            for (int i = 1; i <10; ++i) {
  
                if (valorPossible(String.valueOf(i), fila, columna)) {
                    taulell.setValorCasella(fila,columna,String.valueOf(i));
                    int a = fila;
                    int b = columna + 1;
                    if (b == columnes) {a = a+1; b=0;} 
                    solver(a, b, numSol, check);
                }
            }
            
            if ("?".equals(taulell.getValorCasella(fila,columna))) return; //no hi ha cap valor possible
        }
        
        else {
            int a = fila;
            int b = columna + 1;
            if (b == columnes) {a = a+1; b=0;} 
            solver(a, b, numSol, check);
        }

    }
    
    /** @brief Funció per escriure la solució del taulell.
      @pre Cert.
      @post Se li escriu al taulell associat la solució del taulell.
    */
    private void setTaulellSolucio() {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (taulell.casellaBlanca(i,j)) taulell.setValorSolucio(i,j, taulell.getValorCasella(i, j));
            }  
        }
    }
    
    /** @brief Funció per saber si es pot col·locar el valor a la casella.
        @pre Cert.
        @param possible és el valor a comprovar
        @param fila és la posició de la fila
        @param columna és la posició de la columna
        @post Retorna true en cas que .
    */
    private boolean valorPossible(String possible, int fila, int columna) {
        int suma = 0;
        int f = fila-1;
        while (f>=0 && taulell.casellaBlanca(f,columna)) { 
            //es comproven valors repetits i la suma vertical
            if (taulell.getValorCasella(f,columna).equals(possible)) return false; //valor repetit
            
            suma += Integer.parseInt(taulell.getValorCasella(f,columna));
            --f;
        }
        
        if (suma + Integer.parseInt(possible) > taulell.getSumaColumna(f,columna)) return false; //exedeix la suma
        if (files-1 == fila || !taulell.casellaBlanca(fila+1,columna)) {
            if (sumaVerticalIncorrecte(possible, fila, columna)) return false;  
        }

        suma = 0;
        int col = columna-1;

        while (col>=0 && taulell.casellaBlanca(fila,col)) { 
            //es comproven valors repetits i la suma horitzontal
            
            if (taulell.getValorCasella(fila,col).equals(possible)) return false; //valor repetit
            suma += Integer.parseInt(taulell.getValorCasella(fila,col));
            --col;
        }
        
        if (suma + Integer.parseInt(possible) > taulell.getSumaFila(fila,col)) return false; //exedeix la suma
        if (columnes-1 == columna || !taulell.casellaBlanca(fila, columna+1)) {
            if (sumaHoritzontalIncorrecte(possible, fila, columna)) return false;  
        }
        
        return true;
    }

    
    /** @brief Funció per saber si la suma horitzontal s'ha superat.
        @pre Cert.
        @param s és el valor a comprovar
        @param fila és la posició de la fila
        @param columna és la posició de la columna
        @post Retorna true en cas que la suma horitzontal sigui superada.
    */
    private boolean sumaHoritzontalIncorrecte(String s, int fila, int columna) {

        int suma = Integer.parseInt(s);
        int c = columna-1;
        //suma columna
        while (taulell.casellaBlanca(fila,c)) { 
            suma += Integer.parseInt(taulell.getValorCasella(fila,c));
            --c;
        }

        return suma != taulell.getSumaFila(fila,c);
    }
    
    /** @brief Funció per saber si la suma vertical s'ha superat.
        @pre Cert.
        @param s és el valor a comprovar
        @param fila és la posició de la fila
        @param columna és la posició de la columna
        @post Retorna true en cas que la suma sigui vertical sigui superada.
    */
    private boolean sumaVerticalIncorrecte(String s, int fila, int columna) {

        int suma = Integer.parseInt(s);
        int f = fila-1;
        //suma columna
        while (taulell.casellaBlanca(f,columna)) { 
            suma += Integer.parseInt(taulell.getValorCasella(f,columna));
            --f;
        }

        return suma != taulell.getSumaColumna(f,columna);
    }
}
