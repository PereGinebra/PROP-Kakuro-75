package src;   
import src.CapaDomini.Controladors.CtrlDomain;

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
            
        CtrlDomain CD = new CtrlDomain();
        CD.jugar();
    }
}

