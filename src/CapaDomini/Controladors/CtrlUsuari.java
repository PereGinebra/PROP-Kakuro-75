/**@file CtrlUsuari.java 
 * 
 */

package src.CapaDomini.Controladors;

import src.CapaDomini.Classes.Usuari;
import src.CapaPersistencia.CtrlPersistencia;

/** @class  CtrlUsuari
 *  @brief  Controlador de l'usuari, serveix per interaccionar amb les diferents capes i obtenir les dades de l'usuari.
 * @author alvaro
 */
public class CtrlUsuari {
    private Usuari user;
    private CtrlPersistencia cp;
    
    public CtrlUsuari() {
        cp = new CtrlPersistencia();
    }
    /* @brief Funcio per saber si la contrassenya de l'usuari es correcte
       @pre usuari existeix
       @param nom es l'user
       @param pass es la contrassenya a comprovar
       @post retorna true en cas que la constrassenya sigui la de l'usuari
    */
    public void crearUsuari(String u, String pass) {
        cp.afegirUsuari(u, pass, 0);
        user = new Usuari(u, pass);
    }
    
    /* @brief Funcio per saber si existeix l'usuari
        @pre cert
        @param u es el nom de l'usuari
        @post retorna true en cas que l'usuari existeixi
    */ 
    public boolean existsUsuari(String u) {
        return cp.existsUsuari(u);
    }
    

    /* @brief Funcio per saber si la contrassenya de l'usuari es correcte
       @pre usuari existeix
       @param nom es l'user
       @param pass es la contrassenya a comprovar
       @post retorna true en cas que la constrassenya sigui la de l'usuari
    */    
    public boolean passwordCorrecte(String nom, String pass) {
        user = new Usuari(nom, pass);
        return (cp.getPass(nom).equals(pass));
    }
    
    /* @brief Funcio per obtenir el nom de l'user
       @pre hi ha un usuari iniciallitzat
       @post retorna un string amb el nom de l'usuari
    */ 
    public String getNomUser() {
        return user.getUser();
    }
    
}
