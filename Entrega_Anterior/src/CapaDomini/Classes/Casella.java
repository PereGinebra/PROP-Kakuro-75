/**@file Casella.java 
 * 
 */
package src.CapaDomini.Classes;

/** @class Casella 
 * @brief Representa una casella del taulell.
 *  
 * @author pere.ginebra
*/
public class Casella {
    /**@brief Valor de la casella */
    private String valor;
    
    
    //Constructores 
    /** @brief  Constructora per defecte.
     *  @pre    Cert.
     *  @post   Crea una casella amb els atributs sense inicialitzar.
    */
    public Casella(){
    }
    
    /** @brief  Constructora amb el valor de la casella.
     *  @pre    val és un valor correcte per una casella.
     *  @param  val és el valor de la casella a crear.
     *  @post   Crea una casella amb només el valor d'input val.
    */
    public Casella(String val) {
        valor = val;  
    }
    
    
    //MODIFICADORA
    /** @brief  Modifica el valor de la casella 
     *  @pre    Casella declarada. s és un valor correcte per la casella.
     *  @param  s és el valor a modificar a la casella
     *  @post   El valor de la casella s'ha modificat pel valor de l'string s
    */
    public void setValor(String s) {  
        valor=s;
    }
    
    //Consultores
    /** @brief  Consultar si la casella és blanca  
     *  @pre    Cert.
     *  @post   Retorna true si la casella és blanca, pel contrari retorna false
    */
    public boolean esBlanca() {
        return false;
    }
    
    /** @brief Consultar si la casella és negra  
     *  @pre Cert.
     *  @post Retorna true si la casella és negra, pel contrari retorna false
    */
    public boolean esNegra() {
        return false;
    }
    
    /** @brief  Consultar si la casella és de suma  
     *  @pre    Cert.
     *  @post   Retorna true si la casella és de suma, pel contrari retorna false
    */
    public boolean esSuma() {
        return false;
    }
    
    /** @brief  Consultar el codi (valor de la casella amb el format de l'input)
     *  @pre    Cert.
     *  @post   Retorna el valor de codi
    */
    public String getValor() {
        return valor;
    }
    
    /** @brief  Consultar el valor que ha de sumar la fila de la casella
     *  @pre    Cert.
     *  @post   Retorna el valor que ha de sumar la fila de la casella
    */
    public int getSumaFila(){
        return -1;
    }
    
    /** @brief  Consultar el valor que ha de sumar la columna de la casella 
     *  @pre    Cert.
     *  @post   Retorna el valor que ha de sumar la columna de la casella
    */
    public int getSumaColumna() {
        return -1;
    }

}

