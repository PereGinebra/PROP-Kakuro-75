/**@file CasellaBlanca.java 
 * 
 */
package src.CapaDomini.Classes;

/** @class CasellaBlanca 
 *  @brief Representa una casella blanca del taulell.
 *  
 *  @author pere.ginebra
*/
public class CasellaBlanca extends Casella {
    
    //Constructores
    /** @brief  Constructora per defecte.
     *  @pre    Cert.
     *  @post   Crea una CasellaBlanca.
    */
    public CasellaBlanca() {
    }
    
    /** @brief  Constructora amb paràmetres.
     *  @pre    val és un valor correcte per a casella blanca.
     *  @param  val és el valor de la casella a crear.
     *  @post   Crea una CasellaBlanca i la seva Casella pare amb l'atribut val
    */
    public CasellaBlanca (String val) {
        super();
        setValor(val);
    }
    
    
    //Modificadora
    
    /** @brief  Modifica el valor de la casella 
     *  @pre    Casella declarada. val valor correcte per la casella.
     *  @param  val és el valor a modificar a la casella.
     *  @post   El valor de la casella s'ha modificat pel valor de l'string val
    */
    @Override
    public void setValor(String val) {
        try {
            if(val.equals("?") || Integer.parseInt(val) > 0) super.setValor(val);
        }
        catch (NumberFormatException e) {
            System.out.println("el valor no es correcte per a una casella blanca");
        }
    }
    
    
    //Consultores 
    
    /** @brief  Consultar si la casella és blanca  
     *  @pre    Cert.
     *  @post   Retorna true
    */
    @Override
    public boolean esBlanca() {
        return true;
    }

}



