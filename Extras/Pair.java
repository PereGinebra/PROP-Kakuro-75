/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extras;



class Pair<U, V>
{
    public U first;       // first field of a Pair
    public V second;      // second field of a Pair
 
    // Constructs a new Pair with specified values
    public Pair(U first, V second)
    {
        this.first = first;
        this.second = second;
    }
    
        
    public U getKey(){
        return first;
    }
    
     
    public V getValue(){
        return second;
    }
    
    public void modificarKey(U x){
        first = x;
    }
    
    public void modificarValue(V x){
        second = x;
    }
 
 
    @Override
    // Checks specified object is "equal to" current object or not
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Pair<?, ?> pair = (Pair<?, ?>) o;
 
        // call equals() method of the underlying objects
        if (!first.equals(pair.first))
            return false;
        return second.equals(pair.second);
    }
 
    @Override
    // Computes hash code for an object to support hash tables
    public int hashCode()
    {
        // use hash codes of the underlying objects
        return 31 * first.hashCode() + second.hashCode();
    }
 
    @Override
    public String toString()
    {
        return "(" + first + ", " + second + ")";
    }
 
    // Factory method for creating a Typed Pair immutable instance
    public static <U, V> Pair <U, V> of(U a, V b)
    {
        // calls private constructor
        return new Pair<>(a, b);
    }
}
