/**@file CapaPersistencia.java 
 * 
 */

package src.CapaPersistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

/**
 *  @class  CtrlPersistencia
 *  @brief  Controlador de la capa de persistencia.
 *  @author alvaro.andres

 */
public class CtrlPersistencia {
    
    /**
     * @brief Constructora per defecte
     * @pre cert
     * @post cert
     */
    public CtrlPersistencia() {
        
    }
    /**
     * @brief Busca el contingut de un fitxer que conte un Kakuro
     * @param path nom del fitxer a cercar
     * @pre Existeix un fitxer anomenat path a la carpeta data/Kakuros
     * @post cert
     * @return es retorna una matriu de String amb els valors del fitxer especidicat a path
    */
    public String[][] carregarKakuro(String path) {
        String[][] taulell = new String[0][0];
     
        String IDKakuro = path;
        String userDirectory = new File("").getAbsolutePath();
        String directoryName = "Kakuros";
        FileSystem fs = FileSystems.getDefault();
        
        //si es canvia el nom de la carpeta subgrup-prop7-5, tamé s'ha de cambiar aqui
        String fitxerpath = fs.getPath(userDirectory, "data" , directoryName, IDKakuro).toString();
        File fitxer = new File(fitxerpath); 
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader(fitxer));
            String linia = new String();

            linia = bf.readLine();
            String[] valors = linia.split(",");
            int files = Integer.parseInt(valors[0]);
            int columnes = Integer.parseInt(valors[1]);
            
            taulell = new String[files][columnes];
            
            for (int i = 0; i < files; i++) {
                linia = bf.readLine();
                valors = linia.split(",");
                
                for (int j = 0; j < columnes; j++) {
                    taulell[i][j] = valors[j];
                }
            }
            
            bf.close();
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
  
        return taulell;
    }
}

