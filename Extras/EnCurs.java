/* 
package Extras;
import java.util.*;

import src.CapaDomini.Classes.Partida;
import src.CapaDomini.Classes.Taulell;
import src.CapaDomini.Utils.PairII;
import src.CapaDomini.Utils.PairSB;




public class EnCurs extends Partida{
    
    public EnCurs() {

    }
    
    public EnCurs(Partida p) {
        p = this;
    }
    //Consultores
    /** @brief Consulta si el nombre que es vol posar està repetit en la fila o la columna.
        @pre 0=<i<nombre de files.
        @pre 0=<j<nombre de columnes i.
        @pre s és un nombre del 1 al 9.
        @post Retorna si i només si el el nombre que es vol posar repetit en la fila o la columna.
    */ /*
    public boolean hiHaRepetits (int i, int j, String s){
        int n = getFiles();
        int m = getColumnes();
        
        
        //Cap a la dreta.
        int ii = i;
        int jj = j+1;
        while ((jj<m) && (casellaEsBlanca(ii,jj))){
            
            String vActual = matriuValorsActuals[ii][jj].getLeft();
            if (vActual == s){
                return true;
            }
            ++jj;
        }
        
        // Cap a baix.
        ii = i+1;
        jj = j;
        while ((ii<n) && (casellaEsBlanca(ii,jj))){
            
            
            String vActual = matriuValorsActuals[ii][jj].getLeft();
            if (vActual == s){
                return true;
            }
            ++ii;
        }
        
        //Cap a l'esquerra.
        ii = i;
        jj = j-1;
        while ((0<=jj) && (casellaEsBlanca(ii,jj))){
        
            String vActual = matriuValorsActuals[ii][jj].getLeft();
            if (vActual == s){
                return true;
            }
            --jj;
        }
        
        //Cap a dalt.
        ii = i-1;
        jj = j;
        while ((0<=ii) && (casellaEsBlanca(ii,jj))){
        
            String vActual = matriuValorsActuals[ii][jj].getLeft();
            if (vActual == s){
                return true;
            }
            --ii;
        }
        
        
        return false;
    
    }
    
    
    /** @brief Retorna el valor correcte de la casella [i][j].
        @pre 0<=i<nombre de files del kakuro, i.
        @pre 0<=j<nombre de columnes.
        @pre Aquesta casella és una casella blanca.
        @post Retorna el valor correcte de la casella [i][j].
    */ /*
    public String getValorCorrecte(int i, int j){
        return mapa.getValorCorrecteCasella(i,j);
    }
    
    /** @brief Consulta si es pot escriure un nombre en la casella amb coordenades [i][j].
        @pre 0<=i<nombre de files.  
        @pre 0<=j<nombre de columnes.
        @post Retorna true si i només si es pot escriure un nombre en la casella amb coordenades [i][j].
    *//*
    public boolean casellaEsEscribible (int i, int j){
        return (casellaEsBlanca(i,j) && matriuValorsActuals[i][j].getRight());
    }
    
    
    
    public boolean valorMassaGran(int i, int j, String s){
    
        int ii = i;
        int jj = j;
        return true;
    }
    
    public boolean partidaAcabada(){
        int files = mapa.getFiles();
        int columnes = mapa.getColumnes();
        
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (mapa.casellaBlanca(i, j)) {
                    if (mapa.getValorCasella(i, j) != matriuValorsActuals[i][j].getLeft()) return false;
                }
            }
        }
        
        return true;  
    }

    //Modificadores
    /** @brief Resol una casella aleatòria.
        @pre Cert.
        @post Resol una casella aleatòria i no es permet que es torni a modificar.
    */ /*
    
    public void demanarAjuda(){
        
        ++ajudesUtilitzades;
         
        PairII p = coordNoConfirmades.remove(0);
        int i = p.getLeft();
        int j = p.getRight();
            
        matriuValorsActuals[i][j].setLeft(getValorCorrecte(i,j));
        matriuValorsActuals[i][j].setRight(false);  
    }
    
    /** @brief Resol tot el kakuro.
        @pre Cert.
        @post Resol tot el kakuro.
    */ 
  /*  
    public void solucionar(){
        
        ajudesUtilitzades = Integer.MAX_VALUE;
        int n = getFiles();
        int m = getColumnes();
        
        for (int i = 0; i<n; ++i){
            for (int j = 0; j<m; ++j){
                if (casellaEsEscribible(i,j)){
                    matriuValorsActuals[i][j].setLeft(getValorCorrecte(i,j));
                    matriuValorsActuals[i][j].setRight(false);
                }
                
            }
        }
    }
    
/*    
    public void jugarPartida() {
        Scanner reader = new Scanner(System.in);
        boolean resolt = false;
         
        System.out.println("Juguem");
        while(!resolt) {
            System.out.println("Introdueix la fila");
            boolean revisar = false;
            int fila = reader.nextInt();

            if (fila == -1){
                demanarAjuda();
                revisar = true;
            } 
            else if (fila == -2){
                solucionar();
                revisar = true;
            } 

            else {
                System.out.println("Introdueix la columna");
                
                int columna = reader.nextInt();
                System.out.println("Introdueix valor");
                
                int s = reader.nextInt();
                
                String valor = String.valueOf(s);
                
                System.out.println(valor);
                System.out.println("Arribo");
                
                int n = getFiles();
                int m = getColumnes();
                System.out.println("SALU2");
                if ((0>fila) || (fila>=n) || (0>columna) || (columna>=m)){
                    System.out.println("Coordenades incorrectes");
                }
                
                else if (!casellaEsEscribible(fila,columna)){
                    System.out.println("Aquesta casella no pot ser modificada");
                }
                
                else{
                    matriuValorsActuals[fila][columna].setLeft(valor);
                    revisar = true;
                }
            }
            
            if (revisar && partidaAcabada()) resolt = true;
        } 
        
    }
    
/*
    void escriureCasella(int i, int j, String s){
        int n = getFiles();
        int m = getColumnes();
        
        if ((0>i) || (i>=n) || (0>j) || (j>=m)){
            System.out.println("Coordenades incorrectes");
            return;
        }
        
        if (! (casellaEsBlanca(i,j))){
           System.out.println("En aquesta casella no es pot escriure cap nombre.");
           return;
        }
        if (! (matriuValorsActuals[i][j].getRight())){
           System.out.println("Aquesta casella ja no es pot modificar"); 
           return;
        }
        

        
        boolean reglaInfringida = false;
        boolean repetit = hiHaRepetits (i,j,s);
        boolean vMGran = valorMassaGran (i,j,s);
        
        reglaInfringida = repetit || vMGran;
        
        if (repetit){
            System.out.println("Aquest valor ja es troba en la fila o en la columna");
        }
            
        if (vMGran){
            System.out.println("Aquest valor és més gran que una de les sumes");
        }
            
        
        
        if (reglaInfringida == false) {
            String v = getValorCorrecte(i,j);
            
            if ((matriuValorsActuals[i][j].getLeft()==v) && (s!=v)){
               ++casellesRestantsPerResoldre;   
            }
            else if ((matriuValorsActuals[i][j].getLeft()!=v) && (s==v)){
                --casellesRestantsPerResoldre;
            }
            
            matriuValorsActuals[i][j].setLeft(s);
            System.out.println("Nombre escrit");
            
            //  AQUESTA COSA FUNCIONA
            
        }
    }*/
    /*
};*/
