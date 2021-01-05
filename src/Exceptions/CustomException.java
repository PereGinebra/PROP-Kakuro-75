package src.Exceptions;

/** @class CustomException
 *  @brief Per llençar excepcions amb missatges personalitzats
 *  @author pere.ginebra
 */
public class CustomException extends Exception{
    
    public CustomException(){
        super();
    }
    
    public CustomException(String s) {
        super(s);
    }
}
