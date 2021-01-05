package src.Exceptions;
/**
 *
 * @author quill
 */

public class ExceptionDimensionsTaulellErrones extends Exception{
    
    @Override 
    public String getMessage() {
        return "Les dimensions del taulell son errones";
    }
}
