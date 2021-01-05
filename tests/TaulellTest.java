/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import src.CapaDomini.Classes.Taulell;
import src.CapaDomini.Utils.FinalVariables;

/**
 *
 * @author pere.ginebra
 */
public class TaulellTest {
    
    String [][] taula = new String[][]{{"*","C3","C16","C4","*","*","*","*","*"},
    {"F11","?","?","?","C38","C22","*","C13","C5"},
    {"F23","?","?","?","?","?","C13F4","?","?"},
    {"*","*","*","F15","?","?","?","?","?"},
    {"*","*","C7","C9F27","?","?","?","?","*"},
    {"*","C11F11","?","?","?","?","*","*","*"},
    {"F34","?","?","?","?","?","C15","C17","C13"},
    {"F4","?","?","F34","?","?","?","?","?"},
    {"*","*","*","*","*","F23","?","?","?"}};;

    String[][] taulaFacil = new String[][]{{"*","C4","C12"},
                            {"F9","?","?"},
                            {"F7","?","?"}}; 

    


    
    public TaulellTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        TaulellTest tt = new TaulellTest();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFiles method, of class Taulell.
     */
    @Test
    public void testGetFiles() {
        Taulell instance = new Taulell(taula,true);
        int expResult = taula.length;
        int result = instance.getFiles();
        assertEquals(expResult, result);
        
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = taulaFacil.length;
        result = instance2.getFiles();
        assertEquals(expResult, result);   
    }
    
    
    /**
     * Test of getNumeroCasellesBlanques method, of class Taulell.
     */
    @Test
    public void testGetNumeroCasellesBlanques() {
        Taulell instance = new Taulell(taula,true);
        int expResult = 38;
        int result = instance.getNumeroCasellesBlanques();
        assertEquals(expResult, result);
        
 
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = 4;
        result = instance2.getNumeroCasellesBlanques();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getColumnes method, of class Taulell.
     */
    @Test
    public void testGetColumnes() {
        Taulell instance = new Taulell(taula,true);
        int expResult = taula[0].length;
        int result = instance.getColumnes();
        assertEquals(expResult, result);
        
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = taulaFacil[0].length;
        result = instance2.getColumnes();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getDificultat method, of class Taulell.
     */
    @Test
    public void testGetDificultat() {
        Taulell instance = new Taulell(taula,true);
        

        String expResult = FinalVariables.DificultatMig;
        String result = instance.CalcularDificultatKakuro();
        assertEquals(expResult, result);
        
        Taulell instance2 = new Taulell(taulaFacil,true);
       
        expResult = FinalVariables.DificultatFacil;
        result = instance2.CalcularDificultatKakuro();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getManual method, of class Taulell.
     */
    @Test
    public void testGetManual() {
        Taulell instance = new Taulell(taula,true);
        boolean expResult = true;
        boolean result = instance.getManual();
        assertEquals(expResult, result);
        
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = true;
        result = instance2.getManual();
        assertEquals(expResult, result);
        
        
    }


    /**
     * Test of getValorCasella method, of class Taulell.
     */
    @Test
    public void testGetValorCasella() {
        int f = 1;
        int col = 7;
        Taulell instance = new Taulell(taula,true);
        String expResult = "C13";
        String result = instance.getValorCasella(f, col);
        assertEquals(expResult, result);
        
        f = 1;
        col = 1;
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = "?";
        result = instance2.getValorCasella(f, col);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getSumaFila method, of class Taulell.
     */
    @Test
    public void testGetSumaFila() {
        int f = 4;
        int col = 3;
        Taulell instance = new Taulell(taula,true);
        int expResult = 27;
        int result = instance.getSumaFila(f, col);
        assertEquals(expResult, result);
        
        f = 1;
        col = 0;
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = 9;
        result = instance2.getSumaFila(f, col);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getSumaColumna method, of class Taulell.
     */
    @Test
    public void testGetSumaColumna() {
        int f = 4;
        int col = 3;
        Taulell instance = new Taulell(taula,true);
        int expResult = 9;
        int result = instance.getSumaColumna(f, col);
        assertEquals(expResult, result);
        
        f = 1;
        col = 1;
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = -1;
        result = instance2.getSumaColumna(f, col);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of casellaBlanca method, of class Taulell.
     */
    @Test
    public void testCasellaBlanca() {
        int f = 0;
        int col = 0;
        Taulell instance = new Taulell(taula,true);
        boolean expResult = false;
        boolean result = instance.casellaBlanca(f, col);
        assertEquals(expResult, result);
        
        f = 1;
        col = 1;
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = true;
        result = instance2.casellaBlanca(f, col);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of casellaNegra method, of class Taulell.
     */
    @Test
    public void testCasellaNegra() {
        int f = 0;
        int col = 0;
        Taulell instance = new Taulell(taula,true);
        boolean expResult = true;
        boolean result = instance.casellaNegra(f, col);
        assertEquals(expResult, result);
        
        f = 1;
        col = 1;
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = false;
        result = instance2.casellaNegra(f, col);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of casellaSuma method, of class Taulell.
     */
    @Test
    public void testCasellaSuma() {
        int f = 0;
        int col = 1;
        Taulell instance = new Taulell(taula,true);
        boolean expResult = true;
        boolean result = instance.casellaSuma(f, col);
        assertEquals(expResult, result);
        
        f = 1;
        col = 1;
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = false;
        result = instance2.casellaSuma(f, col);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of setValorCasella method, of class Taulell.
     */
    @Test
    public void testSetValorCasella() {
        int f = 1;
        int col = 1;
        String valor = "5";
        Taulell instance = new Taulell(taula,true);
        instance.setValorCasella(f, col, valor);
        String s = instance.getValorCasella(f, col);
        assertEquals(s, valor);
        
        f = 1;
        col = 1;
        valor = "6";
        Taulell instance2 = new Taulell(taulaFacil,true);
        instance2.setValorCasella(f, col, valor);
        s = instance2.getValorCasella(f, col);
        assertEquals(s, valor);
        
        
    }

    /**
     * Test of CheckConnectivityWhiteCells method, of class Taulell.
     */
    @Test
    public void testCheckConnectivityWhiteCells() {
        Taulell instance = new Taulell(taula,true);
        boolean expResult = true;
        boolean result = instance.CheckConnectivityWhiteCells();
        assertEquals(expResult, result);
        
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = true;
        result = instance2.CheckConnectivityWhiteCells();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of CheckOneCellArragement method, of class Taulell.
     */
    @Test
    public void testCheckOneCellArragement() {
        Taulell instance = new Taulell(taula,true);
        boolean expResult = true;
        boolean result = instance.CheckOneCellArragement();
        assertEquals(expResult, result);
        
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = true;
        result = instance2.CheckOneCellArragement();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of TransformCellToOtherType method, of class Taulell.
     */
    @Test
    public void testTransformCellToOtherType() {
        int fila = 0;
        int col = 0;
        int ChangeTo = FinalVariables.CapACellaBlanca;
        String CellValue = "?";
        Taulell instance = new Taulell(taula,true);
        instance.TransformCellToOtherType(fila, col, ChangeTo, "?");
        assertEquals(instance.getValorCasella(fila, col),"?");
        ChangeTo = FinalVariables.CapACellaNegra;    
        instance.TransformCellToOtherType(fila, col, ChangeTo, "*");
        assertEquals(instance.getValorCasella(fila, col),"*");
        
        
        fila = 0;
        col = 0;
        ChangeTo = FinalVariables.CapACellaBlanca;
        CellValue = "?";
        Taulell instance2 = new Taulell(taulaFacil,true);
        instance2.TransformCellToOtherType(fila, col, ChangeTo, "?");
        assertEquals(instance2.getValorCasella(fila, col),"?");
        ChangeTo = FinalVariables.CapACellaNegra;    
        instance2.TransformCellToOtherType(fila, col, ChangeTo, "*");
        assertEquals(instance2.getValorCasella(fila, col),"*");
        
        
        
    }

    /**
     * Test of CheckTaulellValidEstructuralment method, of class Taulell.
     */
    @Test
    public void testCheckTaulellValidEstructuralment() {
        Taulell instance = new Taulell(taula,true);
        boolean expResult = true;
        boolean result = instance.CheckTaulellValidEstructuralment();
        assertEquals(expResult, result);
        
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = true;
        result = instance2.CheckTaulellValidEstructuralment();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of GenerateDefaultBoard method, of class Taulell.
     */
    @Test
    public void testGenerateDefaultBoard() {
        int nfiles = taula.length;
        int ncolumnes = taula[0].length;
        Taulell instance = new Taulell();
        instance.GenerateDefaultBoard(nfiles, ncolumnes);

        for (int col = 0; col<ncolumnes; ++col){
            assertEquals(instance.getValorCasella(0, col), "*");
        }
        for (int fila = 1; fila<nfiles; ++fila){
            assertEquals(instance.getValorCasella(fila, 0), "*");
        }
        
        nfiles = taulaFacil.length;
        ncolumnes = taulaFacil[0].length;
        Taulell instance2 = new Taulell();
        instance2.GenerateDefaultBoard(nfiles, ncolumnes);

        for (int col = 0; col<ncolumnes; ++col){
            assertEquals(instance2.getValorCasella(0, col), "*");
        }
        for (int fila = 1; fila<nfiles; ++fila){
            assertEquals(instance2.getValorCasella(fila, 0), "*");
        }
        

        
        
    }

    /**
     * Test of nombreCasellesBlanquesSpecific method, of class Taulell.
     */
    @Test
    public void testNombreCasellesBlanquesSpecific() {
        int fila = 0;
        int columna = 1;
        boolean vertical = true;
        Taulell instance = new Taulell(taula,true);
        int expResult = 2;
        int result = instance.nombreCasellesBlanquesSpecific(fila, columna, vertical);
        assertEquals(expResult, result);
        
        fila = 2;
        columna = 0;
        vertical = false;
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = 2;
        result = instance2.nombreCasellesBlanquesSpecific(fila, columna, vertical);
        assertEquals(expResult, result);
    }

    /**
     * Test of CalcularDificultatKakuro method, of class Taulell.
     */
    @Test
    public void testCalcularDificultatKakuro() {
        Taulell instance = new Taulell(taula,true);
        String expResult = FinalVariables.DificultatMig;
        String result = instance.CalcularDificultatKakuro();
        assertEquals(expResult, result);
        
        
        Taulell instance2 = new Taulell(taulaFacil,true);
        expResult = FinalVariables.DificultatFacil;
        result = instance2.CalcularDificultatKakuro();
        assertEquals(expResult, result);
    }
    
}
