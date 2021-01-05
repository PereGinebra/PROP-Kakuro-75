package src.Exceptions;
/**
 *
 * @author pere.ginebra
 */

public class ExceptionPosicioNoValida extends Exception {
    
    @Override 
    public String getMessage() {
        return "La posició es troba fora del rang permès, no és vàlida";
    }
}
