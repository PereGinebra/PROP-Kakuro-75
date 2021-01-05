
package src.CapaDomini.Utils;

/** @class PairII
 *  @brief Pair de enters
 *  @author ignasi
 */
public class PairII {
    
    private int left;
    private int right;
    
    PairII (){}
    
    //Creadores
    public PairII (int s, int b){
        this.left = s;
        this.right = b;
    }
    
    public static PairII of(final int s, final int b) {
        return new PairII(s, b);
    }
    
    //Consultores
    
    public int getLeft(){
        return this.left;
    }
    
    public int getRight(){
        return this.right;
    }
    
    //Modificadores
    
    public void setLeft(int s){
        this.left = s;
    }
    
    public void setRight(int b){
        this.right = b;
    }
};
