package src.Exceptions;
/**
 *
 * @author pere.ginebra
 */

public class ExceptionNoExisteixPartida extends Exception{
    
    @Override 
    public String getMessage(){
        return "La partida seleccionada no existeix";
    }
}
