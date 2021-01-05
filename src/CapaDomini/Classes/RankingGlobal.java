/**
 * @file RankingGlobal.java
 */

package src.CapaDomini.Classes;
import java.util.*;

/** @class RankingGlobal
 *  @brief Ranking de puntuacio per usuari
 *
 * @author ignasi
 */
public class RankingGlobal {
    
    /** @brief Llista dels usuaris del sistema i les seves puntuacions
     * ordenada per ordre alfabètic decreixent de nom d'usuari.
     * Cada nom d'usuari és únic.*/
    private ArrayList<AbstractMap.SimpleEntry<String, Integer>> ranking;

    

    
    //Constructora
    public RankingGlobal(){
        ranking = new ArrayList<AbstractMap.SimpleEntry<String, Integer>>();
    }
    
    //Consultores
   
    /** @brief Consulta tot el ranking global en ordre alfabètic.
        @pre Cert.
        @post S'ha retornat tot el ranking global en ordre alfabètic.
    */ 
    public ArrayList<AbstractMap.SimpleEntry<String, Integer>> getRankingPerOrdreAlfabetic(){
        return ranking;
    }
    
    
    /** @brief Consulta el nombre d'usuaris registrats al sistema.
        @pre Cert.
        @post Retorna la quantitat d'usuaris registrats al sistema.
    */    
    public int getMida(){
        return ranking.size();          
    }
    
    
    /** @brief Consulta la puntuació de l'usuari "i" en ordre alfabètic.
        @pre Hi ha almenys i+1 usuaris registrats al sistema.
        @post S'ha retornat la puntuació de l'usuari "i" en ordre alfabètic.
    */    
    public int getPuntuacioUsuariOrdreAlfabetic(int i){
        return ranking.get(i).getValue();
               
    }
    
    
    
    /** @brief Retorna la puntuació global de l'usuari amb nom "nomU".
        @pre L'usuari amb nom "nomU" existeix.
        @post Retorna la puntuació global de l'usuari amb nom "nomU".
    */ 
    public int getPuntuacioTotalUsuariPerNom(String nomU){
        int index = getIndexPerUsuariExistent(nomU);
        return ranking.get(index).getValue();

    }
    
    
    
    
    /** @brief Retorna la posició de la llista on es troba l'usuari amb nom "nomU".
        @pre L'usuari amb nom "nomU" sí que està en el ranking.
        @post Retorna la posició de la llista on es troba l'usuari amb nom "nomU".
    */ 
    private int getIndexPerUsuariExistent(String nomU){
        return getPosUsuariExistent(nomU,0,ranking.size()-1);
    }
    
    
    /** @brief Retorna la posició de l'inteval [left,right] de la llista
     *  on es troba l'usuari amb nom "nomU". 
        @pre L'usuari amb nom "nomU" sí que està en el ranking.
        @post Retorna la posició de l'inteval [left,right] de la llista
     *  on es troba l'usuari amb nom "nomU".
    */ 
    private int getPosUsuariExistent(String nomABuscar, int left, int right){
    
        int mig = (left+right)/2;
        String nomMig = ranking.get(mig).getKey();
        int comparacio = nomABuscar.compareTo(nomMig);

        if (comparacio<0){
            //Miro cap a l'esquerra
            return getPosUsuariExistent(nomABuscar,left,mig-1);
        }
        if (comparacio>0){
            //Miro cap a  la dreta
            return getPosUsuariExistent(nomABuscar,mig+1,right);
        }
        
        return mig;   
    }
    
    
    /** @brief Retorna la posició de la llista on s'ha de col·locar l'usuari amb nom "nomU".
        @pre L'usuari amb nom "nomU" no està en el ranking.
        @post Retorna la posició de la llista on s'ha de col·locar l'usuari amb nom "nomU".
    */ 
    private int getIndexPerUsuariNou(String nomU){
        if (ranking.size()==0){
            return 0;
        }
        return getPosUsuariNou(nomU,0,ranking.size()-1);
    }
    
    
    /** @brief Retorna la posició de l'inteval [left,right] de la llista
     *  on s'ha de col·locar l'usuari amb nom "nomU". 
        @pre L'usuari amb nom "nomU" no està en el ranking.
        @post Retorna la posició de l'inteval [left,right] de la llista
     *  on s'ha de col·locar l'usuari amb nom "nomU".
    */ 
    private int getPosUsuariNou(String nomU,int left,int right){
        

        int mig = (left+right)/2;
        int midaPartLlista = left-right+1;
        String nomMig = ranking.get(mig).getKey();
        int comparacio = nomU.compareTo(nomMig);
        
        
        if (midaPartLlista==1){

            if (comparacio<0){
                return mig;
            } 
            return mig+1;
        }
        
        if (comparacio<0){
            //Miro cap a l'esquerra
            return getPosUsuariNou(nomU,left,mig-1);
        }
        if (comparacio>0){
            //Miro cap a  la dreta
            return getPosUsuariNou(nomU,mig+1,right);
        }
        
        return mig+1;
   
    }
    


    //Modificadores
    
    /** @brief Es col·loca al ranking un nom d'usuari "nomU" i els seus punts "n".
        @pre L'usuari amb nom "nomU" no està en el ranking.
        @post S'ha col·locat al ranking un nom d'usuari "nomU" i els seus punts "n".
    */ 
    public void afegirNouUsuari(String nomU,int n){
        ranking.ensureCapacity(ranking.size()+20);
        int index = getIndexPerUsuariNou(nomU);
        ranking.add(index,new AbstractMap.SimpleEntry(nomU,n));

    }
    
    /** @brief Es treu del ranking l'entrada amb nom d'usuari "nomU".
        @pre L'usuari amb nom "nomU" està en el ranking.
        @post S'ha col·locat al ranking l'entrada amb nom d'usuari "nomU".
    */ 
    public void treureUsuari(String nomU){
        int index = getIndexPerUsuariExistent(nomU);
        ranking.remove(index);
    }
    
    /** @brief Se li suma a l'usuari amb nom "nomU" n punts.
        @pre L'usuari amb nom "nomU" està en el ranking.
        @post Se li ha sumat a l'usuari amb nom "nomU" n punts.
    */ 
    public void sumarPuntuacioAUsuari(String nomU,int n){
        int index = getIndexPerUsuariExistent(nomU);
        int valorActual = ranking.get(index).getValue();
        ranking.get(index).setValue(n+valorActual);
    }
      
}



