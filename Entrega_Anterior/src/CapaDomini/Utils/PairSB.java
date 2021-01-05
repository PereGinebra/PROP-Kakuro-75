
package src.CapaDomini.Utils;

/** @class PairSB
 *  @brief Pair de string i boolean
 *  @author ignasi
 */
public class PairSB {
    
    private String left;
    private boolean right;
    
    //Creadores
    public PairSB(){
        
    }
    
    public PairSB (String s, boolean b){
        this.left = s;
        this.right = b;
    }
    
    public static PairSB of(final String left, final boolean right) {
        return new PairSB(left, right);
    }
    
    //Consultores
    public String getLeft(){
        return this.left;
    }
    
    public boolean getRight(){
        return this.right;
    }
    
    //Modificadores
    
    public void setLeft(String s){
        this.left = s;
    }
    
    public void setRight(boolean b){
        this.right = b;
    }
};
