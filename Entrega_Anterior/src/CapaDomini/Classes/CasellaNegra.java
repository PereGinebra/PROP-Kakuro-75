/**@file CasellaNegra.java 
 * 
 */
package src.CapaDomini.Classes;
import src.Exceptions.CustomException;

/** @class CasellaNegra 
 * @brief Representa una casella negra del taulell.
 *  
 * @author pere.ginebra
*/

public class CasellaNegra extends Casella {
    
    //Constructores
    
    /** @brief  Constructora per defecte.
     *  @pre    Cert.
     *  @post   Crea una CasellaNegra i la seva casella pare.
    */
    public CasellaNegra() {
        super("*");
    }
    
    //Modificadora
    
    /** @brief  Modifica el valor de la casella 
     *  @pre    Casella declarada, val valor correcte per la casella negra.
     *  @param  val és el valor a modificar a la casella.
     *  @post   El valor de la casella s'ha modificat pel valor de l'string val
    */
    @Override 
    public void setValor(String val) {
        try {
            if(val.equals("*")) super.setValor("*");
            else throw new CustomException("No es pot canviar el valor d'una casella negra");
        }
        catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }   
    
    //Consultores
    
    /** @brief  Consultar si la casella és negra  
     *  @pre    Cert.
     *  @post   Retorna true
    */
    @Override 
    public boolean esNegra() {
        return true;
    }
      
}
