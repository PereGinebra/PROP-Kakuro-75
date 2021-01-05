package src.Exceptions;
/**
 *
 * @author pere.ginebra
 */

public class ExceptionMidaIncorrecta extends Exception {
    
    @Override 
    public String getMessage() {
        return "Aquesta mida no és vàlida";
    }
}
