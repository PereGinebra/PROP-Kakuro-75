package tests;

import java.util.*;
import org.junit.*;
import org.junit.runners.Suite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;


import src.CapaDomini.Classes.Casella;
import src.CapaDomini.Classes.CasellaSuma;
import src.CapaDomini.Classes.CasellaNegra;
import src.CapaDomini.Classes.CasellaBlanca;


/**
 *
 * @author pere.ginebra
 */
@RunWith(Parameterized.class)
public class CasellaTest {
    
    private String t;
    private String s;
  
    public CasellaTest(String tipus,String str){
        this.t = tipus;
        this.s = str; 
    }
    
    @Parameters
    public static Collection<String[]> getTestParameters () {
        return Arrays.asList( new String[][] {
            { "1", "10"},
            { "2", "*"},
            { "3","F20"} });
    }
    
    //tipus: 1-blanca, 2-negra, 3-suma
    @Test
    public void setValor(){
        int i = Integer.parseInt(t);
        
        if (i==1){
            CasellaBlanca c = new CasellaBlanca();
            c.setValor(s);
            assertEquals(c.getValor(),s);   
        }
        else if (i==2){
           CasellaNegra c = new CasellaNegra(); 
           c.setValor(s);
           assertEquals("*",c.getValor());
        }
        else {
           CasellaSuma c = new CasellaSuma(); 
           c.setValor(s);
           assertEquals(c.getValor(),s);
        }
        
    }
    
    @Test
    public void esBlanca(){
        int i = Integer.parseInt(t);
        if (i==1){
            CasellaBlanca c = new CasellaBlanca();
            assertTrue(c.esBlanca()); 
        }
        else if (i==2){
            CasellaNegra c = new CasellaNegra();
            assertFalse(c.esBlanca());
        }
        else {
            CasellaSuma c = new CasellaSuma();
            assertFalse(c.esBlanca());
        }
    }
    
    @Test
    public void esNegra(){
        int i = Integer.parseInt(t);
        
        if (i==1){
            CasellaBlanca c = new CasellaBlanca();
            assertFalse(c.esNegra()); 
        }
        else if (i==2){
            CasellaNegra c = new CasellaNegra();
            assertTrue(c.esNegra());
        }
        else {
            CasellaSuma c = new CasellaSuma();
            assertFalse(c.esNegra());
        }
    }
    
    @Test
    public void esSuma(){
        int i = Integer.parseInt(t);
        if (i==1){
            CasellaBlanca c = new CasellaBlanca();
            assertFalse(c.esSuma()); 
        }
        else if (i==2){
            CasellaNegra c = new CasellaNegra();
            assertFalse(c.esSuma());
        }
        else {
            CasellaSuma c = new CasellaSuma();
            assertTrue(c.esSuma());
        }
    }
    
    @Test
    public void getValor(){
        int i = Integer.parseInt(t);
        
        if (i==1){
            CasellaBlanca c = new CasellaBlanca(s);
            assertEquals(c.getValor(),s); 
        }
        else if (i==2){
            CasellaNegra c = new CasellaNegra();
            assertEquals(c.getValor(),"*"); 
        }
        else {
            CasellaSuma c = new CasellaSuma(s);
            assertEquals(c.getValor(),s); 
        }  
    }
    
    @Test
    public void getSumaFila(){
        int i = Integer.parseInt(t);
        if (i==1){
            CasellaBlanca c = new CasellaBlanca(s);
            assertEquals(c.getSumaFila(),-1);
        }
        else if (i==2){
            CasellaNegra c = new CasellaNegra();
            assertEquals(c.getSumaFila(),-1); 
        }
        else {
            CasellaSuma c = new CasellaSuma(s);
            assertNotEquals(c.getSumaFila(),-1); 
        }
    }
    
    @Test
    public void getSumaColumna(){
        int i = Integer.parseInt(t);
        if (i==1){
            CasellaBlanca c = new CasellaBlanca(s);
            assertEquals(c.getSumaFila(),-1);
        }
        else if (i==2){
            CasellaNegra c = new CasellaNegra();
            assertEquals(c.getSumaFila(),-1); 
        }
        else {
            CasellaSuma c = new CasellaSuma(s);
            assertNotEquals(c.getSumaFila(),-1); 
        }
    }
}
