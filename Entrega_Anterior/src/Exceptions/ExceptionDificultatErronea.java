package src.Exceptions;
/**
 *
 * @author quill
 */

public class ExceptionDificultatErronea extends Exception{
    
    @Override 
    public String getMessage() {
        return "La dificultat del taulell no es dificil, ni mig ni tampoc facil";
    }
}
