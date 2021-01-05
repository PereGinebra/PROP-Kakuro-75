
package src.CapaDomini.Drivers;
import src.CapaDomini.Controladors.CtrlDomain;
/**
 * @class DriverCtrlDomain
 * @brief El driver per a la clase CtrlDomain
 * @author Pere Ginebra
 */
public class DriverCtrlDomain {
    CtrlDomain CD;
    
    public DriverCtrlDomain(){
        CD = new CtrlDomain();
    }
    
    
    public void jugar(){
        //CD.jugar();
    }
    
    
    public static void main (String [] args){
        DriverCtrlDomain DCD = new DriverCtrlDomain();
        DCD.jugar();
    }
    
    
    
    
    
    
}
