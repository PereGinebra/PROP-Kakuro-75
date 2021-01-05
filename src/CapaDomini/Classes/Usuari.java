/**@file Usuari.java 
 * 
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.CapaDomini.Classes;

/** @class  Usuari
 *  @brief  Representa un usuari, s'hi guarden totes les dades referents al taulell d'un kakuro.
 * @author alvaro
 */
public class Usuari {
    private String user;
    private String password;
    
    public Usuari(){
        
    }
    
    public Usuari(String u, String p) {
        user = u;
        password = p;
    }
    
    /* @brief Funcio per saber el nom d'usuari
       @pre usuari existeix
       @post retorna el nom de l'usuari
    */
    public String getUser() {
        return user;
    }
    
    /* @brief Funcio per saber el password d'usuari
       @pre usuari existeix
       @post retorna el password de l'usuari
    */
    public String getPassword(){
        return password;
    }

    
}
