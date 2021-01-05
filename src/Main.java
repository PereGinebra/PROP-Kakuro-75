package src;   

import src.CapaPresentacio.CtrlPresentacio;
import javax.swing.UIManager;

/**
 * @author Álvaro Andrés
 * @author Ignasi de José
 * @author Pere Ginebra
 * @author Guillermo López
 * 
 * Aplicació per poder jugar a Kakuro.
 * 
 * Kakuro es una clase de enigma lógico que a menudo es referido como una transcripción matemática del crucigrama.
 * Básicamente, los enigmas Kakuro son problemas de programación lineal, y se pueden resolver utilizando las técnicas de matriz matemática,
 * aunque sean resueltos típicamente a mano. Los enigmas de Kakuro son regulares en la mayoría, si no todas, de las publicaciones de matemáticas y 
 * de enigmas lógicos. 
 * 
 * 
 * 
 */

public class Main {
        public static void main (String[] args)  {
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); //per el que utilitzavem during development a linux
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //per que quedi to fresco a windows
        }
        catch (javax.swing.UnsupportedLookAndFeelException e) {
           // handle exception
        }
        catch (ClassNotFoundException e) {
           // handle exception
        }
        catch (InstantiationException e) {
           // handle exception
        }
        catch (IllegalAccessException e) {
           // handle exception
        }
        CtrlPresentacio cp = new CtrlPresentacio();
        cp.run();
    }
}

