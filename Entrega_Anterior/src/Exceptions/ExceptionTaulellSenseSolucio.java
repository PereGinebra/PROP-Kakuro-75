package src.Exceptions;
/**
 *
 * @author pere.ginebra
 */

public class ExceptionTaulellSenseSolucio extends Exception{
    
    @Override 
    public String getMessage() {
        return "El taulell donat no té solució única";
    }
}
