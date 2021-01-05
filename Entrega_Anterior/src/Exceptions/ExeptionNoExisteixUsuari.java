package src.Exceptions;
/**
 *
 * @author pere.ginebra
 */

public class ExeptionNoExisteixUsuari extends Exception{
    
    @Override
    public String getMessage() {
        return "L'usuari no existeix";
    }
}
