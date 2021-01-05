package src.Exceptions;
/**
 *
 * @author pere.ginebra
 */

public class ExceptionNoExisteixTaulell extends Exception{
    
    @Override 
    public String getMessage() {
        return "No existeix aquest taulell";
    }
}
