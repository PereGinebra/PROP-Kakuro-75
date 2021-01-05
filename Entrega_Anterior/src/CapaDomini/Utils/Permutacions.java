/**@file Permutacions.java 
 * 
 */


package src.CapaDomini.Utils;
import java.util.HashMap;
import java.util.*;


/** @class Permutacions
 *  @brief Classe per obtenir els valors valids segons la suma i el nombre de caselles
 */


public class Permutacions {
    

    
    /**
     * @brief Mapa que donat un enter (nombre de caselles) i un altre enter (suma) retorna els nombres que podrien pertànyer a la solució d'aquesta suma.
     */ 
    private HashMap< String,  boolean[] > valorsPossibles; 
    
    
    /**
     * @brief Creadora per defecte
     * @pre cert
     * @post Es crea el valor de totes les permutacions
     */ 
    public Permutacions(){
        valorsPossibles = new HashMap< String ,boolean[] >(120);
        omplirValors();
    }
    
    //Consultores
    
    /** @brief Donat un nombre de caselles, una suma i un enter n, retorna true si i només si n es pot utilitzar per resoldre la fila o columna on pertany la casella..
        @pre nombreCaselles és el nombre de caselles de la fila o columa, suma és el nombre de suma que conté la casella i 1<=n<=9.
        @post Retorna true si i només si n es pot utilitzar per resoldre la fila o columna on pertany la casella.
    */ 
    public boolean pertany(int nombreCaselles, int suma, int n){
        String p = String.valueOf(nombreCaselles);
        String s = String.valueOf(suma);
        
        boolean[] v = new boolean [9];
        v = valorsPossibles.get(p+" "+s);
        
        if(v != null && 1<=n && 9>=n){
            return v[n-1];
        }
        return false; 
    }
    
    /** @brief Donat un nombre de caselles i una suma, retorna els valors que podrien ser utilitzats per resoldre-les.
        @pre Cert.
        @post Retorna els valors que podrien ser utilitzats en la fila o columna.
    */ 
    
    public List<Integer> valorsPossiblesDeUnaCasella(int nombreCaselles, int suma){
        List<Integer> conjunt = new ArrayList<Integer> ();
        
        String p = String.valueOf(nombreCaselles);
        String s = String.valueOf(suma);
        boolean v [] = new boolean [9];
        v = valorsPossibles.get(p+" "+s);
        
        if (v==null){
            return conjunt;
        }
        
        for (int i = 0; i<9; ++i){
            if (v[i]){
                boolean b = conjunt.add(i+1);
            }
        }
        return conjunt;
    }
    
    /** @brief Donat dos parells (nCaselles,suma) retorna els valors que podria haver-hi en l'intersecció de les caselles.
        @pre Un parell correspon a una columna i l'altre corespont a una fila.
        @pre Aquesta fila i aquesta columna es creuen en una casella. 
        @post Retorna un conjunt de valors possibles per aquesta intersecció.
    */ 
    
    public List<Integer> interseccio (int nombreCasellesFila, int sumaFila, int nombreCasellesColumna ,int sumaColumna){
        List<Integer> conjunt = new ArrayList<Integer>();
        
        String pC = String.valueOf(nombreCasellesColumna);
        String sC = String.valueOf(sumaColumna);
        
        String pF = String.valueOf(nombreCasellesFila);
        String sF = String.valueOf(sumaFila);
        
        boolean[] vFila = new boolean [9];
        boolean[] vColumna = new boolean [9]; 
        vFila = valorsPossibles.get(pF+" "+ sF);
        vColumna = valorsPossibles.get(pC+" "+sC);
        
        if (vFila==null || vColumna == null){
            return conjunt;
        }
        
        for (int i = 0; i<9; ++i){
            if (vFila[i] && vColumna[i]){
                boolean b = conjunt.add(i+1);
            }
        }
        return conjunt;
    }
    
    /** @brief Donat dos parells (nCaselles,suma) retorna el nombre de valors que podria haver-hi en l'intersecció de les caselles.
        @pre Un parell correspon a una columna i l'altre corespont a una fila.
        @pre Aquesta fila i aquesta columna es creuen en una casella. 
        @post El nombre de valors possibles per aquesta intersecció.
    */ 
    
    public int midaInterseccio (int nombreCasellesFila, int sumaFila, int nombreCasellesColumna ,int sumaColumna){
        
        String pC = String.valueOf(nombreCasellesColumna);
        String sC = String.valueOf(sumaColumna);
        
        String pF = String.valueOf(nombreCasellesFila);
        String sF = String.valueOf(sumaFila);
        
        boolean[] vFila = new boolean [9];
        boolean[] vColumna = new boolean [9]; 
        vFila = valorsPossibles.get(pF+" "+ sF);
        vColumna = valorsPossibles.get(pC+" "+sC);
        int n = 0;
        
        if (vFila==null || vColumna == null){
            return n;
        }
        
        
        for (int i = 0; i<9; ++i){
            if (vFila[i] && vColumna[i]){
                ++n;
            }
        }
        return n;
    }
    
    
    //Modificadora
    
    /** @brief Omple la lLista de valors possibles per cada nombre de caselles i la seva suma corersponent.
        @pre La lLista de valors possibles està buida.
        @post S'omple la lLista de valors possibles per cada nombre de caselles i suma.
    */ 
    private void omplirValors(){
  
        boolean F = false;
        boolean T = true;

        valorsPossibles.put("2"+" "+"3", new boolean[]{T,T,F,F,F,F,F,F,F});
        valorsPossibles.put("2"+" "+"4", new boolean[]{T,F,T,F,F,F,F,F,F});
        valorsPossibles.put("2"+" "+"5", new boolean[]{T,T,T,T,F,F,F,F,F});
        valorsPossibles.put("2"+" "+"6", new boolean[]{T,T,F,T,T,F,F,F,F});
        valorsPossibles.put("2"+" "+"7", new boolean[]{T,T,T,T,T,T,F,F,F});
        valorsPossibles.put("2"+" "+"8", new boolean[]{T,T,T,F,T,T,T,F,F});
        valorsPossibles.put("2"+" "+"9", new boolean[]{T,T,T,T,T,T,T,T,F});
        valorsPossibles.put("2"+" "+"10", new boolean[]{T,T,T,T,F,T,T,T,T});
        valorsPossibles.put("2"+" "+"11", new boolean[]{F,T,T,T,T,T,T,T,T});
        valorsPossibles.put("2"+" "+"12", new boolean[]{F,F,T,T,T,F,T,T,T});
        valorsPossibles.put("2"+" "+"13", new boolean[]{F,F,F,T,T,T,T,T,T});
        valorsPossibles.put("2"+" "+"14", new boolean[]{F,F,F,F,T,T,F,T,T});
        valorsPossibles.put("2"+" "+"15", new boolean[]{F,F,F,F,F,T,T,T,T});
        valorsPossibles.put("2"+" "+"16", new boolean[]{F,F,F,F,F,F,T,F,T});
        valorsPossibles.put("2"+" "+"17", new boolean[]{F,F,F,F,F,F,F,T,T});
        
        
        valorsPossibles.put("3"+" "+"6", new boolean[]{T,T,T,F,F,F,F,F,F});
        valorsPossibles.put("3"+" "+"7", new boolean[]{T,T,F,T,F,F,F,F,F});
        valorsPossibles.put("3"+" "+"8", new boolean[]{T,T,T,T,T,F,F,F,F});
        valorsPossibles.put("3"+" "+"9", new boolean[]{T,T,T,T,T,T,F,F,F});
        valorsPossibles.put("3"+" "+"10", new boolean[]{T,T,T,T,T,T,T,F,F});
        valorsPossibles.put("3"+" "+"11", new boolean[]{T,T,T,T,T,T,T,T,F});
        valorsPossibles.put("3"+" "+"12", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"13", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"14", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"15", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"16", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"17", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"18", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"19", new boolean[]{F,T,T,T,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"20", new boolean[]{F,F,T,T,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"21", new boolean[]{F,F,F,T,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"22", new boolean[]{F,F,F,F,T,T,T,T,T});
        valorsPossibles.put("3"+" "+"23", new boolean[]{F,F,F,F,F,T,F,T,T});
        valorsPossibles.put("3"+" "+"24", new boolean[]{F,F,F,F,F,F,T,T,T});
        
        
        valorsPossibles.put("4"+" "+"10", new boolean[]{T,T,T,T,F,F,F,F,F});
        valorsPossibles.put("4"+" "+"11", new boolean[]{T,T,T,F,T,F,F,F,F});
        valorsPossibles.put("4"+" "+"12", new boolean[]{T,T,T,T,T,T,F,F,F});
        valorsPossibles.put("4"+" "+"13", new boolean[]{T,T,T,T,T,T,T,F,F});
        valorsPossibles.put("4"+" "+"14", new boolean[]{T,T,T,T,T,T,T,T,F});
        valorsPossibles.put("4"+" "+"15", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"16", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"17", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"18", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"19", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"20", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"21", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"22", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"23", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"24", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"25", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"26", new boolean[]{F,T,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"27", new boolean[]{F,F,T,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"28", new boolean[]{F,F,F,T,T,T,T,T,T});
        valorsPossibles.put("4"+" "+"29", new boolean[]{F,F,F,F,T,F,T,T,T});
        valorsPossibles.put("4"+" "+"30", new boolean[]{F,F,F,F,F,T,T,T,T});
       
        
        
    
        valorsPossibles.put("5"+" "+"15", new boolean[]{T,T,T,T,T,F,F,F,F});       
        valorsPossibles.put("5"+" "+"16", new boolean[]{T,T,T,T,F,T,F,F,F});
        valorsPossibles.put("5"+" "+"17", new boolean[]{T,T,T,T,T,T,T,F,F});
        valorsPossibles.put("5"+" "+"18", new boolean[]{T,T,T,T,T,T,T,T,F});
        valorsPossibles.put("5"+" "+"19", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"20", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"21", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"22", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"23", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"24", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"25", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"26", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"27", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"28", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"29", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"30", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"31", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"32", new boolean[]{F,T,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"33", new boolean[]{F,F,T,T,T,T,T,T,T});
        valorsPossibles.put("5"+" "+"34", new boolean[]{F,F,F,T,F,T,T,T,T});
        valorsPossibles.put("5"+" "+"35", new boolean[]{F,F,F,F,T,T,T,T,T});
        

        valorsPossibles.put("6"+" "+"21", new boolean[]{T,T,T,T,T,T,F,F,F});
        valorsPossibles.put("6"+" "+"22", new boolean[]{T,T,T,T,T,F,T,F,F});
        valorsPossibles.put("6"+" "+"23", new boolean[]{T,T,T,T,T,T,T,T,F});
        valorsPossibles.put("6"+" "+"24", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"25", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"26", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"27", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"28", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"29", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"30", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"31", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"32", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"33", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"34", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"35", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"36", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"37", new boolean[]{F,T,T,T,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"38", new boolean[]{F,F,T,F,T,T,T,T,T});
        valorsPossibles.put("6"+" "+"39", new boolean[]{F,F,F,T,T,T,T,T,T});
       
        

        valorsPossibles.put("7"+" "+"28", new boolean[]{T,T,T,T,T,T,T,F,F});
        valorsPossibles.put("7"+" "+"29", new boolean[]{T,T,T,T,T,T,F,T,F});
        valorsPossibles.put("7"+" "+"30", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"31", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"32", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"33", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"34", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"35", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"36", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"37", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"38", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"39", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"40", new boolean[]{T,T,T,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"41", new boolean[]{F,T,F,T,T,T,T,T,T});
        valorsPossibles.put("7"+" "+"42", new boolean[]{F,F,T,T,T,T,T,T,T});
        

        valorsPossibles.put("8"+" "+"36", new boolean[]{T,T,T,T,T,T,T,T,F});
        valorsPossibles.put("8"+" "+"37", new boolean[]{T,T,T,T,T,T,T,F,T});
        valorsPossibles.put("8"+" "+"38", new boolean[]{T,T,T,T,T,T,F,T,T});
        valorsPossibles.put("8"+" "+"39", new boolean[]{T,T,T,T,T,F,T,T,T});
        valorsPossibles.put("8"+" "+"40", new boolean[]{T,T,T,T,F,T,T,T,T});
        valorsPossibles.put("8"+" "+"41", new boolean[]{T,T,T,F,T,T,T,T,T});
        valorsPossibles.put("8"+" "+"42", new boolean[]{T,T,F,T,T,T,T,T,T});
        valorsPossibles.put("8"+" "+"43", new boolean[]{T,F,T,T,T,T,T,T,T});
        valorsPossibles.put("8"+" "+"44", new boolean[]{F,T,T,T,T,T,T,T,T});
        
        
        valorsPossibles.put("9"+" "+"45", new boolean[]{T,T,T,T,T,T,T,T,T});
        
       
    }
};
