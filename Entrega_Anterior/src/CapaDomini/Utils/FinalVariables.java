/* @file FinalVariables.java
 */
package src.CapaDomini.Utils;

import java.util.Comparator;

/** @class FinalVariables
 *  @brief Per referenciar diferents final variables del projecte
 * 
 */
public class FinalVariables {
    /** @brief % de celes blanques que una kakuro dificl ha de tenir aproximadament
     */
    public static final double WhiteCellPercentDificil  = 0.7;
    /** @brief % de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final double WhiteCellPercentMig 	   = 0.6;
    /** @brief % de celes blanques que una kakuro facil ha de tenir aproximadament
     */
    public static final double WhiteCellPercentFacil    = 0.55;
    
    /** @brief Utilitzada per referenciar una cel·la blanca
     */
    public static final int CapACellaBlanca     = 10;
    /** @brief Utilitzada per referenciar una cel·la negra
     */
    public static final int CapACellaNegra 	= 20;
    /** @brief Utilitzada per referenciar una cel·la de suma
     */
    public static final int CapACellaSuma       = 30;
    
    /** @brief Permutabilitat de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final double PermutabilitatDificil  = 2.0;
    /** @brief PermutabilitatDificil de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final double PermutabilitatMig 	   = 2.5;
    /** @brief PermutabilitatDificil de celes blanques que una kakuro facil ha de tenir aproximadament
     */
    public static final double PermutabilitatFacil  = 2.0;
    
    /** @brief Permutabilitat de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final String DificultatDificil  = "dificil";
    /** @brief PermutabilitatDificil de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final String DificultatMig 	   = "mig";
    /** @brief PermutabilitatDificil de celes blanques que una kakuro facil ha de tenir aproximadament
     */
    public static final String DificultatFacil  = "facil";
    
    /** @brief Permutabilitat de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final double DificultatScoreDificil  = 8.5;
    /** @brief PermutabilitatDificil de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final double DificultatScoreMig 	   = 5.75;
    /** @brief PermutabilitatDificil de celes blanques que una kakuro facil ha de tenir aproximadament
     */
    public static final double DificultatScoreFacil  = 0.0;
    
    
    /**
     * @brief centra una string mitjançant espais
     * @pre String s ha de ser menys de 7 caracters
     * @param s String a centrar
     * @return String de 7 caracter on el centra esta la substring s
     */
    public static String NormalitzarEspai(String s){
        while(s.length() < 7){
          String s2 = " ";
          if(s.length()%2==0) s = s.concat(s2);
          else{
            s = s2.concat(s);
          }
        }
        return s;
    }
}
