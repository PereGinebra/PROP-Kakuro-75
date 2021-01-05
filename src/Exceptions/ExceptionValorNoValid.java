package src.Exceptions;
/**
 *
 * @author pere.ginebra
 */

public class ExceptionValorNoValid extends Exception {
    
    @Override
    public String getMessage() {
        return "El valor no és vàlid";
    }
}
