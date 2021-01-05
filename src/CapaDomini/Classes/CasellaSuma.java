/**@file CasellaSuma.java 
 * 
 */
package src.CapaDomini.Classes;
import src.Exceptions.CustomException;

/** @class CasellaSuma 
 * @brief Representa una casella de suma del taulell.
 *  
 * @author pere.ginebra
*/
public class CasellaSuma extends Casella {
    /**@brief Valor de la suma de la fila */
    private int sumaFila = 0;
    /**@brief Valor de la suma de la columna */
    private int sumaCol = 0;  
    
    
    //constructores
    
    /** @brief  Constructora per defecte.
     *  @pre    Cert.
     *  @post   Crea una CasellaSuma.
    */
    public CasellaSuma(){
    }
    
    /** @brief  Constructora amb paràmetres.
     *  @pre    val és un valor correcte per una casella de suma.
     *  @param  val és el valor de la casella a crear.
     *  @post   Crea una CasellaSuma i la seva Casella pare amb l'atribut val
    */
    public CasellaSuma(String val){
        super();
        setValor(val);
    }
    
    
    //Modificadores
    /** @brief  Modifica el valor de la casella 
     *  @pre    val es un valor correcte per la casella de suma.
     *  @param  val és el valor a modificar de la casella.
     *  @post   El valor de la casella s'ha modificat pel valor de l'string val
    */
    @Override
    public void setValor(String val) {
        try {
            if(val.charAt(0) == 'F' || val.charAt(0) == 'C') {
                setValorsSuma(val);
                super.setValor(val);
            }
            else throw new CustomException("Valor no correcte per una casella de suma");
        }
        catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    //Consultores
    
    /** @brief  Consultar el valor que ha de sumar la fila de la casella
     *  @pre    Cert.
     *  @post   Retorna el valor que ha de sumar la fila de la casella
    */
    @Override
    public int getSumaFila() {
        return sumaFila;
    }
    
    /** @brief  Consultar el valor que ha de sumar la columna de la casella 
     *  @pre    Cert.
     *  @post   Retorna el valor que ha de sumar la columna de la casella
    */
    @Override
    public int getSumaColumna() {
        return sumaCol;
    }
    
    /** @brief  Consultar si la casella és de suma  
     *  @pre    Cert.
     *  @post   Retorna true
    */
    @Override
    public boolean esSuma() {
        return true;
    }
    
    
    //privades
    /** @brief  Funcio per obtenir la suma de la fila i la columna de la casella
     *  @pre    Cert.
     *  @param val és el valor de la casella
     *  @post   A la casella se li assigna el valor de la suma de la fila i la columna
    */
    private void setValorsSuma(String val) {
        char[] v = val.toCharArray();
        int size = val.length();
        try {
            if (val.charAt(0) == 'C') {
                //si només té C i un digit o el 3r valor es una F, la sumaCol és de 1 digit
                if(size <= 1) throw new CustomException("Valor no valid per una casella de suma"); 
                else if (size == 2 || v[2] == 'F'){
                    sumaCol = Character.getNumericValue(v[1]);
                    //si també té suma fila
                    if(size > 2 && v[2] == 'F') {
                        //si te mida 4, la fila es de un digit
                        if(size == 4) sumaFila = Character.getNumericValue(v[3]);
                        //sino, es de dos
                        else {
                            sumaFila = Character.getNumericValue(v[3]) * 10;
                            sumaFila += Character.getNumericValue(v[4]); 
                        }
                    }
                }
                //si és mes gran que 2 i el 3r valor no és una F, sumaCol es de 2 digits
                else if (v[2] != 'F'){
                    sumaCol = Character.getNumericValue(v[1]) * 10;
                    sumaCol += Character.getNumericValue(v[2]);
                    //si té més de 3 chars, també té sumaFila 
                    if(size > 3) {
                        //si té 5 chars, sumaFila és d'un digit
                        if(size == 5) sumaFila = Character.getNumericValue(v[4]);
                        //si en té més, sumaFila és de dos digits
                        else {
                            sumaFila = Character.getNumericValue(v[4]) * 10;
                            sumaFila += Character.getNumericValue(v[5]);                           
                        }
                    }
                }
            }
            else if (v[0] == 'F') {
                if(size == 2) sumaFila = Character.getNumericValue(v[1]);
                else {
                    sumaFila = Character.getNumericValue(v[1]) * 10;
                    sumaFila += Character.getNumericValue(v[2]);
                }
            }
            else {
                throw new CustomException("Valor no valid per una casella de suma");
            }
        }
        catch (CustomException e){
            System.out.println(e.getMessage());
        }
    }
}
