/**
 * @file RankingTaulell.java
 */
package src.CapaDomini.Classes;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/** @class RankingTaulell 
 *  @brief Ranking d'un taulell de kakuro en concret
 *
 *  @author Pere Ginebra
 */
public class RankingTaulell {
    //ranking, ordenat per temps
    private ArrayList<AbstractMap.SimpleEntry<String, Long>> rank = new ArrayList();
    
    //CONSTRUCTORES
    RankingTaulell() {   
    }
    
    RankingTaulell(ArrayList<AbstractMap.SimpleEntry<String, Long>> r) {
        this.rank = r;
        this.sortRankingByTime(); //no es necessaria si sempre legim el ranking ordenat per temps
    }
    
    //MODIFICADORES
    
    //nose si es necessaria, ja insertem ordenadament per temps
    /**
     *  @brief  Ordena el ranking per temps
     *  @pre    Cert.
     *  @post   S'ha ordenat el ranking de manera creixent en relació al temps
     */
    private void sortRankingByTime() {
        CompEntryByTime comp = new CompEntryByTime();
        Collections.sort(rank, comp);
    }
    
    /**
     * @brief inserta una puntuació al rànking ordenat per temps
     * @pre Cert.
     * @post S'ha insertat la puntuació a la posició adient del rànking ordenat per temps
     * @param usuari: el username de l'usuari que ha finalitzat el taulell
     * @param temps: el temps que ha tardat l'usuari en completar el taulell
     */
    public void insertEntry(String usuari, Long temps) {
        AbstractMap.SimpleEntry<String, Long> entry = new AbstractMap.SimpleEntry<String, Long>(usuari, temps);
        int n = rank.size();
        int m = n/2;
        recInsert(entry, 0, n);
    }
    
    /**
     * @brief funció recursiva per insertar una puntuació al ranking de forma ordenada
     */
    private void recInsert(AbstractMap.SimpleEntry<String, Long> entry, int l, int r) {
        CompEntryByTime comp = new CompEntryByTime();
        int n = (r - l);
        int m = l + (n / 2);
        int res = comp.compare(entry, rank.get(m));
        if(res == 0) {          //tenen els mateixos punts
            rank.add(m, entry);
        }
        else if(n == 1) {
            if(res == -1) rank.add(m, entry);
            else if(res == 1) {
                if(m+1<rank.size())rank.add(m+1, entry);
                else rank.add(entry);
            }
        }
        else if(res == -1) {    //esta a l'esquerra
            recInsert(entry, l, m);
        }
        else if(res == 1) {     //esta a la dreta
            recInsert(entry, m, r);
        }
    }  
    
    //CONSULTORES
    
    /**
     * @brief Consultar el ranking ordenat per Temps
     * @pre Cert.
     * @return retorna el rànking com a arraylist de SimpleEntry (pairs)
     */
    public ArrayList<AbstractMap.SimpleEntry<String, Long>> getRankingTemps() {
        return rank;
    }
    
    /**
     * @brief Consultar el ranking ordenat per Nom
     * @pre Cert.
     * @return retorna el rànking com a arraylist de SimpleEntry (pairs)
     */
    public ArrayList<AbstractMap.SimpleEntry<String, Long>> getRankingNom() {
        ArrayList<AbstractMap.SimpleEntry<String, Long>> rankNom = new ArrayList();
        CompEntryByTime comp = new CompEntryByTime();
        Collections.sort(rankNom, comp);
        return rank;
    }
}

/**
 * @class CompEntryByTime
 * @brief Comparador per ordenar el ranking per temps
 * 
 * @author Pere Ginebra
 */
class CompEntryByTime implements Comparator<AbstractMap.SimpleEntry<String, Long>> {
    public void CompEntry(){
    }

    @Override
    public int compare(AbstractMap.SimpleEntry<String, Long> a, AbstractMap.SimpleEntry<String, Long> b) {
        return a.getValue().compareTo(b.getValue());
    }
}

/**
 * @class CompEntryByName
 * @brief Comparador per ordenar el ranking per nom d'usuari
 * 
 * @author Pere Ginebra
 */
class CompEntryByName implements Comparator<AbstractMap.SimpleEntry<String, Long>> {
    public void CompEntry(){
    }

    @Override
    public int compare(AbstractMap.SimpleEntry<String, Long> a, AbstractMap.SimpleEntry<String, Long> b) {
        return a.getKey().compareTo(b.getKey());
    }
}