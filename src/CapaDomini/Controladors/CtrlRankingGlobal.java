/**
 * @file CtrlRankingGlobal.java
 */
package src.CapaDomini.Controladors;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import src.CapaDomini.Classes.RankingGlobal;
import src.CapaPersistencia.CtrlPersistencia;
/**
 *  @class CtrlRankingGlobal
 *  @brief Controlador per la clase RankingGlobal
 *  @author ignasi
 */
public class CtrlRankingGlobal {
    
    private RankingGlobal RG;
    private CtrlPersistencia cp;
    
    //Creadora
    
    public CtrlRankingGlobal(){
        RG = new RankingGlobal();
        cp = new CtrlPersistencia();
    }
    
    
    //Consultores
    
    /** @brief Consulta el nom i la puntuació de tots els usuaris del sistema
     * per ordre alfabètic decreixent de nom d'usuari.
        @pre Hi ha almenys un usuari registrat al sistema.
        @post S'ha retornat una llista amb el nom i la puntuació de tots els usuaris del sistema en ordre alfabètic.
    */   
    public ArrayList<AbstractMap.SimpleEntry<String, Integer>> getRankingPerOrdreAlfabetic(){
        return RG.getRankingPerOrdreAlfabetic();
    }


    
    /** @brief Consulta el nom i la puntuació dels 5 usuaris que tenen més punts.
        @pre Hi ha almenys un usuari registrat al sistema.
        @post S'ha retornat una llista amb el nom i la puntuació dels 5 usuaris que tenen més punts.
    */   
    public String[][] getMillorsUsuarisGlobal(){
        return cp.getRankingGlobal();
    }
       
       
       
    //Modificadores
    
    
    /** @brief Afegeix un usuari amb nom "nomU" al sistema amb zero punts.
        @pre L'usuari amb nom "nom" encara no està registrat en el ranking.
        @post Afegeix un usuari amb nom "nomU" al sistema amb zero punts.
    */ 
    public void afegirUsuariNou(String nom){
        RG.afegirNouUsuari(nom,0);
    }
    
    
    /** @brief Borra l'usuari amb nom "nomU" del sistema.
        @pre L'usuari amb nom "nom" està registrat en el ranking.
        @post Borra l'usuari amb nom "nomU" del ranking global.
    */ 
    public void treureUsuari(String nom){
        RG.treureUsuari(nom);
    }
    
    
    /** @brief Suma a l'usuari amb nom "nomU" n punts.
        @pre L'usuari amb nom "nom" està registrat en el ranking.
        @post S'ha sumat n punts a l'usuari amb nom "nom".
    */ 
    public void sumarPuntuacioAUsuari(String nom,int n){
        RG.sumarPuntuacioAUsuari(nom,n);
    }
}

/**
 *  @class ComparadorPerTempsDecreixent
 *  @brief Comparador per ordenar un ranking de manera decreixent pel temps
 *  @author Pere Ginebra
 */
class ComparadorPerTempsDecreixent implements Comparator<AbstractMap.SimpleEntry<String, Integer>> {
        
    public void CompEntry(){
    }

    @Override
    public int compare(AbstractMap.SimpleEntry<String, Integer> a, AbstractMap.SimpleEntry<String, Integer> b) {
        return b.getValue().compareTo(a.getValue());
    }
}
