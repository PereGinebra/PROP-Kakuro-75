package src.Exceptions;
/**
 *
 * @author pere.ginebra
 */

public class ExceptionValorForaRang extends Exception {
    
    @Override
    public String getMessage() {
        return "El valor ha de ser un nombre entre 1 i 9";
    }
}
