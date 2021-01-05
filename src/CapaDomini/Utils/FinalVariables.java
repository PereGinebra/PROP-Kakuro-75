/* @file FinalVariables.java
 */
package src.CapaDomini.Utils;

import java.util.Comparator;

/** @class FinalVariables
 *  @brief Per referenciar diferents final variables del projecte i metodes estatics simples
 */
public class FinalVariables {
    /** 
     * @brief % de celes blanques que una kakuro dificl ha de tenir aproximadament
     */
    public static final double WhiteCellPercentDificil  = 0.7;
    /** 
     * @brief % de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final double WhiteCellPercentMig 	   = 0.6;
    /** 
     * @brief % de celes blanques que una kakuro facil ha de tenir aproximadament
     */
    public static final double WhiteCellPercentFacil    = 0.55;
    
    /** 
     * @brief Utilitzada per referenciar una cel·la blanca
     */
    public static final int CapACellaBlanca     = 10;
    /** 
     * @brief Utilitzada per referenciar una cel·la negra
     */
    public static final int CapACellaNegra 	= 20;
    /** 
     * @brief Utilitzada per referenciar una cel·la de suma
     */
    public static final int CapACellaSuma       = 30;
    
    /** 
     * @brief Permutabilitat de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final double PermutabilitatDificil  = 2.0;
    /** 
     * @brief PermutabilitatDificil de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final double PermutabilitatMig 	   = 2.5;
    /** 
     * @brief PermutabilitatDificil de celes blanques que una kakuro facil ha de tenir aproximadament
     */
    public static final double PermutabilitatFacil  = 2.0;
    
    /** 
     * @brief Permutabilitat de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final String DificultatDificil  = "dificil";
    /** 
     * @brief PermutabilitatDificil de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final String DificultatMig 	   = "mig";
    /** 
     * @brief PermutabilitatDificil de celes blanques que una kakuro facil ha de tenir aproximadament
     */
    public static final String DificultatFacil  = "facil";
    
    /** 
     * @brief Permutabilitat de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final double DificultatScoreDificil  = 8.5;
    /**
     * @brief PermutabilitatDificil de celes blanques que una kakuro Mig ha de tenir aproximadament
     */
    public static final double DificultatScoreMig 	   = 5.75;
    /** 
     * @brief PermutabilitatDificil de celes blanques que una kakuro facil ha de tenir aproximadament
     */
    public static final double DificultatScoreFacil  = 0.0;
    /** 
     * @brief Nom de la carpeta que conte tots els fitxers que es guarden
     */
    public static final String dataCarpeta  = "data";
    /** 
     * @brief Nom de la carpeta que conte tots els Taulells i la carpeta de solucions
     */
    public static final String taulellCarpeta  = "Kakuros";
    /** 
     * @brief Nom de la carpeta que conte tots els usuaris i la seva informacio
     */
    public static final String usuarisCarpeta  = "Usuarios";
    /** 
     * @brief Nom de la carpeta que conte tots les Partides siguin EnCurs o Finalitzades i la seva informacio
     */
    public static final String partidaCarpeta  = "Partidas";
    /** 
     * @brief Nom de la carpeta que conte tots les solucions dels Kakuros
     */
    public static final String taulellSolucioCarpeta  = "Solucions";
    /** 
     * @brief String que sepera les diferents troços dels titulos dels fitxers guardats
     */
    public static final String fileSeperator  = "_";
    /** 
     * @brief String que correspon quan un usuari no te puntuacio en un taulell
     */
    public static final String tempsDefaultRanking = "  -  -  ";
    /** 
     * @brief Posicio que ocupa en el titol la dificultat d'un Kakuro enregistrat (Per Taulells, solucions, partides)
     */
    public static final int posicioDificultatCP  = 1;
    /** 
     * @brief Linia dintre d'un fitxer de partida que ocupa el temps
     */
    public static final int posicioTempsCP = 0;
    
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

    /**
     * @brief extreu el nombre de la string i retorna -1 si no es nombre
     * @pre cert
     * @post cert
     * @param str String a extreure el nombre
     * @return int amb el valor del nombre o -1 si no hi ha cap nombre
     */

    public static int isNumeric(String str) { 
      try {  
        return Integer.parseInt(str);
      } catch(NumberFormatException e){  
        return -1;  
      }  
    }
}
