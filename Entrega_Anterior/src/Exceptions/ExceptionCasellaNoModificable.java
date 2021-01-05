package src.Exceptions;
/**
 *
 * @author pere.ginebra
 */

public class ExceptionCasellaNoModificable extends Exception{
    
    @Override 
    public String getMessage() {
        return "La casella seleccionada no és modificable";
    }
}
