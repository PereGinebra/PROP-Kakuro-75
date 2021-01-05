package src.Exceptions;
/**
 *
 * @author pere.ginebra
 */

public class ExceptionTaulellNoValid extends Exception{
    
    @Override 
    public String getMessage() {
        return "El taulell donat no és vàlid";
    }
}
