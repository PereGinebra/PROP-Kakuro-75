/**@file CapaPersistencia.java 
 * 
 */

package src.CapaPersistencia;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.File;
import java.nio.charset.Charset;
import src.CapaDomini.Utils.*;
import src.CapaDomini.Utils.PairII;
import src.CapaDomini.Utils.PairSB;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.CapaDomini.Classes.Taulell;
import src.CapaDomini.Controladors.KakuroSolver;
import src.Exceptions.ExceptionTaulellSenseSolucio;

/**
 * @class  CtrlPersistencia
 * @brief  Controlador de la capa de persistencia.
 * @author guillermo lopez
 * @image html DiagramaFitxer.png

 */
public class CtrlPersistencia {
    /** @brief Parametre per conveniencia*/
    FileSystem fs;
    /** @brief Path a la carpeta on s'executa el codi*/
    String userDirectory;
    /**
     * @brief Constructora per defecte
     * @pre cert
     * @post Crea un CtrlPersistencia i l'entorn edecuat per treballar
     *       Cambia els noms necessaris perque funcioni pels Kakuros
     */
    public CtrlPersistencia() {
        this.fs = FileSystems.getDefault();
        this.userDirectory = new File("").getAbsolutePath();
        SetUp();
    }
    
    /**
     * @brief Crear l'entorn de data i posa els permisos corresponent
     * @pre userDirectory es correcte
     * @post Las carpetas data, kakuros, usuarios, Solucions i partidas existeixen i tenen els permisos adequats
     */
    private void SetUp(){
        System.out.println("Guardant Data a "+userDirectory);
        //data
        // |-Kakuros
        // |-Partidas
        // |-Usuarios
        String[] dircreate = new String[5];
        dircreate[0]= userDirectory + java.io.File.separator + FinalVariables.dataCarpeta;
        dircreate[1]= dircreate[0] + java.io.File.separator + FinalVariables.taulellCarpeta;
        dircreate[2]= dircreate[0] + java.io.File.separator + FinalVariables.partidaCarpeta;
        dircreate[3]= dircreate[0] + java.io.File.separator + FinalVariables.usuarisCarpeta;
        dircreate[4]= dircreate[1] + java.io.File.separator + FinalVariables.taulellSolucioCarpeta;
        for (String dircreate1 : dircreate) {
            File Carpeta = new File(dircreate1);
            if(!Carpeta.exists()){//Crear Carpetas
                Carpeta.mkdir();
            }
            //Actualizar Permisos
            Carpeta.setWritable(true,false);
            Carpeta.setReadable(true,false);
        }

        //Si existeix algun taulell que no sigue las normas cambia el titulo
        //changeNames();
    }
    
    /** 
     * @brief Detecta quins fichers no cumpleixen el format de nomenclatura dels Kakuros
     * @pre cert
     * @post cert
     * @return Array de fitxer del fitxer Kakuro que no segueixen la nomeclatura adient
    */
    private File[] FicherosACambiar(){
        int numKak = llistatTaulells().length;
        File f = new File(getPathKakuroDir().toString());
        File[] ficheros = f.listFiles((File dir, String name) -> {
            boolean result = true;
            if(name.equals(FinalVariables.taulellSolucioCarpeta)) return false;
            //System.out.println("checking: " + name);
            
            String tmp[] = name.split(FinalVariables.fileSeperator);
            
            result = tmp.length==2;
            //System.out.println(tmp.length + " num of diferent: "+String.valueOf(result));
            if(result){
                result= tmp[0].length() >= 7;
                //System.out.println(tmp[0].length() + " length 1st: "+String.valueOf(result));
            }
            
            if(result){
                String sb = tmp[0].substring(6);
                int num = FinalVariables.isNumeric(sb);
                result = num>0 && num<numKak;
                //System.out.println(num + " is a number last 1st: "+String.valueOf(result));
            }
            
            
            if(result){
                result = tmp[1].equals(FinalVariables.DificultatDificil);
                result = result || tmp[1].equals(FinalVariables.DificultatMig);
                result = result || tmp[1].equals(FinalVariables.DificultatFacil);
                //System.out.println(tmp[1] + " dif: "+String.valueOf(result));
            }
            
            
            //System.out.println("result: "+String.valueOf(result));
            return !result;
        } //apply a filter
        );
        System.out.println("Change ficheros name: "+ ficheros.length);
        for (File fichero : ficheros) {
            System.out.println(fichero.getName());
        }
        
        return ficheros;
    }

    /**@brief cambia nombres incorrectos a nombre correctos
    * @pre cert
    * @post cambia los nombres de los ficheros de Kakuro a noms funcionals
    *       p.e KakuroNoValid a Kakuro7_ facil
    
    private void changeNames(){
        File[] ficheros = FicherosACambiar();
        for (File fichero : ficheros) {
            String oldName=fichero.getName();
            String dif = calculateDificultatKakuro(oldName);
            String newName = nomnouKakuro(dif);
            // Rename file
            try{

            Files.move(fichero.toPath(), getPathKakuro(newName));

            } catch (IOException e) {
            }
        }
    }
    */
    
    /**
     * @brief Retorna un nom no utilitzat per un Kakuro nou
     * @pre Tots els noms dels kakuros s'han creat ha partir d'aquesta funcio
     * @param Dificultat afageix "_Dificultat" al nom per fer cerques rapides despres
     * @return Una string que conte un nom no utilitzat per enregistrar kakuros
     * @post cert
     */
    private String nomnouKakuro(String Dificultat){
        int n = llistatTaulells().length;
        String s =  "Kakuro" + String.valueOf(n) + FinalVariables.fileSeperator + Dificultat;
        File file2 = new File(s);
        while (file2.exists()){
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 6;
            Random random = new Random();

            String generatedString;
            generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            s = generatedString + String.valueOf(n+1) + FinalVariables.fileSeperator + Dificultat;
            file2 = new File(s);
        }
        return s;
    }
    
    /**
     * @brief Retorna el nom de tots els taulells que te enregistrat el sistema
     * @pre El sistema està enregistrant els kakuros a la carpeta adequada
     * @return Una vector de noms de tots els taulells enregistrats al sistema
     * @post cert
     */
    public String[] llistatTaulells(){
        String strpath = getPathKakuroDir().toString();
        File f = new File(strpath);
        File[] ficheros = f.listFiles();
        String[] llistat = new String[ficheros.length];
        for (int x=0;x<ficheros.length;x++){
          llistat[x]=ficheros[x].getName();
        }
        return llistat;
    }
    
    /**
     * @brief Retorna el nom de tots els taulells que te enregistrat el sistema que tinguin la dificultat especificada
     * @pre Dificultat es Facil,Mig o Dificil i el sisyema està utilitzant noms vàlids
     * @param Dificultat dels kakuros ha buscar
     * @return Una vector de noms de tots els taulells d'aquesta dificultat enregistrats al sistema
     * @post cert
     */
    public String[] llistatTaulellsDificultat(String Dificultat){
        String strpath = getPathKakuroDir().toString();
        File f = new File(strpath);
        File[] ficheros = f.listFiles(new FilenameFilter() {
                
                //apply a filter
                @Override
                public boolean accept(File dir, String name) {
                    boolean result;
                    String tmp[] = name.split(FinalVariables.fileSeperator);
                    result = tmp.length>1 && tmp[1].equals(Dificultat);
                    return result;
                }
            });

        String noms[] = new String[ficheros.length];
        for (int i=0;i<ficheros.length;i++){
            noms[i] = ficheros[i].getName();
        }

        return noms;
    }

    /**
     * @brief Retorna el nom de tots els usuaris que te enregistrat el sistema
     * @pre El sistema ha estat enregistrant correctament els usuaris
     * @return Una vector de Strings amb el nom d'usuari tots els usuaris enregistrats al sistema
     * @post cert
     */
    private String[] llistatUsuarios(){
        String strpath = getPathUsuarisDir().toString();
        File f = new File(strpath);
        File[] ficheros = f.listFiles();
        String[] llistat = new String[ficheros.length];
        for (int x=0;x<ficheros.length;x++){
          llistat[x]=ficheros[x].getName();
        }
        return llistat;
    }

    /**
     * @brief Retorna el Path a la carpeta on s'enregistrant Usuaris
     * @pre cert
     * @return Path a la carpeta on s'enregistran Usuaris
     * @post cert
     */
    private Path getPathUsuarisDir(){
        return fs.getPath(  userDirectory,
                            FinalVariables.dataCarpeta,
                            FinalVariables.usuarisCarpeta
                );
    }

    /**
     * @brief Retorna el Path a la carpeta on s'enregistran kakuros
     * @pre cert
     * @return Path a la carpeta on s'enregistran kakuros
     * @post cert
     */
    private Path getPathKakuroDir(){
        return fs.getPath(  userDirectory,
                            FinalVariables.dataCarpeta,
                            FinalVariables.taulellCarpeta
                );
    }

    /**
     * @brief Retorna el Path al fitxer d'informacio d'un usuari en concret
     * @pre cert
     * @param Username nom d'Usuari a cercar
     * @return el Path al fitxer d'informacio d'un usuari
     * @post cert
     */
    private Path getPathUsuari(String Username){
        return fs.getPath(  userDirectory,
                            FinalVariables.dataCarpeta ,
                            FinalVariables.usuarisCarpeta,
                            Username
                );
    }

    /**
     * @brief Retorna el Path al fitxer d'informacio d'un taulell en concret
     * @pre cert
     * @param KakuroID identificador del taulell a buscar
     * @return el Path al fitxer d'informacio d'un taulell
     * @post cert
     */
    private Path getPathKakuro(String KakuroID){
        return fs.getPath(  userDirectory,
                            FinalVariables.dataCarpeta ,
                            FinalVariables.taulellCarpeta,
                            KakuroID
                );
    }
    
    /**
     * @brief Retorna el Path en format String al fitxer d'informacio d'un taulell
     * @pre cert
     * @param KakuroID identificador del taulell a buscar
     * @return String Path al fitxer d'informacio d'un taulell
     * @post cert
     */
    private String getPathStringKakuro(String KakuroID){
        return fs.getPath(  userDirectory,
                            FinalVariables.dataCarpeta ,
                            FinalVariables.taulellCarpeta,
                            KakuroID
                ).toString();
    }

    /**
     * @brief Retorna el Path en format String  a la partida determinada
     * @pre cert
     * @param username nom de l'usuari del qual volem saber la partida 
     * @param partida nom de la partida 
     * @return Path al fitxer partida desitjat
     * @post cert
     */
    private String getPathStringPartida(String username, String partida){
        return fs.getPath(  userDirectory,
                            FinalVariables.dataCarpeta ,
                            FinalVariables.partidaCarpeta,
                            username,
                            partida
                ).toString();
    }
    
    /**
     * @brief Retorna el Path en format String  a la partida determinada
     * @pre cert
     * @param username nom de l'usuari del qual volem saber la partida 
     * @param partida nom de la partida 
     * @return Path al fitxer partida desitjat
     * @post cert
     */
    private Path getPathPartida(String username, String partida){
        return fs.getPath(  userDirectory,
                            FinalVariables.dataCarpeta ,
                            FinalVariables.partidaCarpeta,
                            username,
                            partida
                );
    }
    
    /**
     * @brief Retorna el Path a la partida determinada
     * @pre cert
     * @param username nom de l'usuari del qual volem saber la partida
     * @return Path al fitxer partida desitjat
     * @post cert
     */
    private String getPathStringPartidas(String username){
        return fs.getPath(  userDirectory,
                            FinalVariables.dataCarpeta ,
                            FinalVariables.partidaCarpeta,
                            username
                ).toString();
    }

    /** 
     * @brief Retorna el Path en format String al fitxer que hauria d'encapsular la solucio d'un kakuro
     * @pre cert
     * @param KakuroID identificador del taulell a buscar
     * @return String Path al fitxer d'informacio d'un taulell que contendria, si el Kakuro identificat com a tituloKak
     *         no existeix el potser que el path no sigui vàlid
     * @post cert
    */
    private String getKakuroSolucioPathString(String tituloKak){
        return fs.getPath(  userDirectory,
                            FinalVariables.dataCarpeta ,
                            FinalVariables.taulellCarpeta,
                            FinalVariables.taulellSolucioCarpeta,
                            tituloKak
                ).toString();
    }
    
    /** 
     * @brief Retorna el Path al fitxer que hauria d'encapsular la solucio d'un kakuro
     * @pre cert
     * @param KakuroID identificador del taulell a buscar
     * @return Path al fitxer d'informacio d'un taulell que contendria, si el Kakuro identificat com a tituloKak
     *         no existeix el potser que el path no sigui vàlid
     * @post cert
    */
    private Path getPathKakuroSolucio(String tituloKak){
        return fs.getPath(  userDirectory,
                            FinalVariables.dataCarpeta ,
                            FinalVariables.taulellCarpeta,
                            FinalVariables.taulellSolucioCarpeta,
                            tituloKak
                );
    }

    /** 
     * @brief Retorna una string amb la dificultat donada al Kakuro al crear-lo
     * @param tituloKak identificador del Taulell del qual volem saber la dificultat
     * @pre El kakuro ha de haver se creat correctament
     * @post cert
     * @return String que conté quina difucultat tè un Kakuro
     */
    public String getDificultatTitulo(String tituloKak){
        return tituloKak.split(FinalVariables.fileSeperator)[1];
    }

    /** 
     * @brief Retorna una string amb la dificultat donada al Kakuro associat a una partida
     * @param tituloPartida identificador dela partida de la qual volem saber la dificultat del taulell associat
     * @pre El kakuro i la partida ha de haver se creat correctament
     * @post cert
     * @return String que conté quina difucultat tè un Kakuro
     */ 
    public String getDificultatPartida(String tituloPartida){
        return tituloPartida.split(FinalVariables.fileSeperator)[1];
    }

    
    /** 
     * @brief Metode de conveniencia que indica quin nom tindria el fitxer amb solució d'un Tauelll
     * @param kakuroID nom del Kakuro
     * @pre cert
     * @post cert
     * @return String que conté el nom del fitxer associat
     */
    private String nomnouSolucioKakuro(String kakuroID){
        return kakuroID;
    }

    /** 
     * @brief Calcula la dificultat que té un kakuro situat a un fitxer 
     * @param kakuroID nom del Kakuro
     * @pre kakuroID correspon a un fitxer real
     * @post cert
     * @return String amb la dificultat
     
    private String calculateDificultatKakuro(String kakuroID){
        String[][] tS = carregarKakuro(kakuroID);
        Taulell t = new Taulell(tS, false);
        
        String dif = t.CalcularDificultatKakuro();
        return dif;
    }
    */


    /** @brief
     * @param
     * @pre
     * @post
     * @return
    */
    private void deleteKakuro(String kakuroID){
        String ps = getPathKakuro(kakuroID).toString();
        File myObj = new File(ps);
        if(myObj.exists()) myObj.delete();
    }
    
    /**
     * @brief Funcio per esborrar una partida en Curs d'un usuari
     * @pre cert
     * @post si i nomes si idPartida identifica una partida En curs del user esborra el fitxer d'informació
     * @param user nom del usuari propietari de la partida
     * @param idPartida identificador de la partida
     * @return return TRUE si i només si ha esborrat el fitxer
     */
    public boolean deletePartidaEnCurs(String user, String idPartida){
        String ps = this.getPathStringPartida(user, idPartida);
        File f = new File(ps);
        if(f.exists() && !(this.getPartidaEstat(idPartida))){
            return f.delete();
        }
        return false;
    }


    /** @brief
     * @param
     * @pre
     * @post
     * @return
    */
    private File[] getPartidasKakuroUsuari(String kakuroID, String username){
        String path = getPathStringPartidas(username);
        File carpeta = new File(path);
        if(carpeta.isDirectory()){
            File[] Partidas = carpeta.listFiles(new FilenameFilter() {
                
                //apply a filter
                @Override
                public boolean accept(File dir, String name) {
                    boolean result;
                    if(name.startsWith(kakuroID+FinalVariables.fileSeperator)){
                        result=true;
                    }
                    else{
                        result=false;
                    }
                    return result;
                }
            });
            return Partidas;
        }/*
        else{
            carpeta.delete();
            
        }*/
        return new File[0];
    }

    /**
     * @brief Enregistra un Kakuro nou al sistema
     * @pre cert
     * @param Dificultat Dificultat del kakuro ha enregistrat
     * @param files files del kakuro ha enregistrat
     * @param columnes columnes del kakuro ha enregistrat
     * @param taulell Matriu que enregistra els valors de cada casella
     * @post Ha quedat enregistrat un kakuro amb els valors de taulell i dificultat
     */
    public String AfegirNewKakuro(String Dificultat, int files, int columnes, String[][] taulell){
        Path p = getPathKakuroDir();
        String newName = nomnouKakuro(Dificultat);
        String pathstring = p.toString()+ java.io.File.separator + newName;
        String newline="";
        File myObj;
        try {
          myObj = new File(pathstring);
          if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
          } else {
            System.out.println("File already exists.");
          }
        

        FileWriter fw = new FileWriter(myObj);

        fw.write(String.valueOf(files)+","+columnes);
        fw.write(System.lineSeparator());

        for (int i=0;i<files;i++) {
            for (int j=0;j<columnes;j++){
                String valor = taulell[i][j];
                if(FinalVariables.isNumeric(valor)> 0){
                    valor = "?";
                }                    
                if(j==0) newline = valor;
                else{
                    newline = newline + "," + valor;
                }
            }
            fw.write(newline);
            fw.write(System.lineSeparator());
        }
        
        fw.close();

        } catch (IOException e) {
          //System.out.println("An error occurred in afegirkakuro.");
        }
        return newName;
    }

    /**
     * @brief Enregistra un Usuari nou al sistema
     * @pre cert
     * @param username username de l'Usuari
     * @param password password de l'Usuari
     * @param puntuacio puntacioglobal de l'Usuari
     * @post Ha quedat enregistrat un Usuari amb els valors donats
     */
    public void afegirUsuari(String username, String password, int puntuacio){
        System.out.println("Registering " + username);
        try {
            Path p = getPathUsuari(username);
        
            File myObj = new File(p.toString());
            //System.out.println(myObj.getAbsolutePath());
            if(!myObj.createNewFile() ) System.out.println("error create " + username);

            FileWriter fw = new FileWriter(myObj);
            String puntString = String.valueOf(puntuacio);

            fw.write(username);
            fw.write(System.lineSeparator());
            fw.write(password);
            fw.write(System.lineSeparator());
            fw.write(puntString);
            fw.write(System.lineSeparator());
            fw.close();

            String ps = getPathStringPartidas(username);
            File partidasdir = new File(ps);
            if(partidasdir.exists()){
                if(partidasdir.isDirectory()){
                    File[] listf = partidasdir.listFiles();
                    for (File lf : listf) lf.delete();
                }
                partidasdir.delete();
            }
            partidasdir.mkdir();


        } catch (IOException e) {
          //System.out.println("An error occurred in creating user.");
        }
    }

    /**
     * @brief Genera un nom nou no en us per una partida
     * @pre cert
     * @param user user de la partida
     * @param taulell taulell associat a la partida
     * @param acabada si es true la partida esta acabada
     * @return Nom unic per una partida
     * @post cert
    */
    private String nomPartida(String user, String taulell, boolean acabada){
        String sEstat;
        File f = new File(getPathStringPartidas(user));
        int nPartidas = f.listFiles().length;
        if(acabada){
            sEstat="fin";
            return    taulell
                    + FinalVariables.fileSeperator
                    + sEstat
                    + FinalVariables.fileSeperator
                    + String.valueOf(nPartidas);
        }
        sEstat="enJoc";
        return    taulell
                + FinalVariables.fileSeperator
                + sEstat
                + FinalVariables.fileSeperator
                + String.valueOf(nPartidas);
                
    }

    /**
     * @brief Enregistra un partida nova al sistema
     * @pre L'entorn ha sigut creat
     * @param username username del usuari que juga la partida
     * @param idTaulell id del taulell on es juga
     * @param temps temps que la partida porta jugada en format hh:mm:ss
     * @param matriuValorsActuals matriu dels valors actuals
     * @param coordNoConfirmades coordeades no confirmades
     * @post Ha quedat enregistrat una partida amb els valors donats
     * @return Retorna el nom del fitxer creat
     */
    public String afegirPartida(
            String username,
            String idTaulell,
            String temps,
            PairSB  matriuValorsActuals[][],
            ArrayList< PairII > coordNoConfirmades
    ){
        String p = getPathStringPartidas(username);
        String newName = nomPartida(username,idTaulell, false);
        String pathstring = p + java.io.File.separator + newName;

        File myObj;
        try {
            myObj = new File(pathstring);
            if (!myObj.exists()) {
              myObj.createNewFile();
              System.out.println("File created: " + myObj.getName());
            } else {
            //System.out.println("File already exists.");
            }
        
            try (FileWriter fw = new FileWriter(myObj)) {
                //write matriu
                fw.write(temps);
                fw.write(System.lineSeparator());
                
                //write matriu
                int files = matriuValorsActuals.length;
                int columnes = matriuValorsActuals[0].length;
                fw.write(files+","+columnes);
                fw.write(System.lineSeparator());
                String newline="";
                String l;
                String r;
               
                for (int i=0;i<files;i++) {
                    for (int j=0;j<columnes;j++){
                        l=matriuValorsActuals[i][j].getLeft();
                        r=String.valueOf(matriuValorsActuals[i][j].getRight());
                        if(j==0) newline = l + "/" + r;
                        else{
                            newline = newline + "," + l + "/" + r;
                        }           
                    }
                    fw.write(newline);
                    fw.write(System.lineSeparator());

                }
                
                
                //wrtie list
                int listsize = coordNoConfirmades.size();
                fw.write(String.valueOf(listsize));
                fw.write(System.lineSeparator());
                for(int i=0;i<listsize;i++){
                    PairII pair = coordNoConfirmades.get(i);
                    fw.write(String.valueOf(pair.getLeft()) + "," +String.valueOf(pair.getRight()));
                    fw.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
          //System.out.println("An error occurred in afegirPartida.");
        }
        return newName;
    }
    
    /** 
     * @brief
     * @param user useuari creador de la partida
     * @param partida nom del fitxer on esta la partida
     * @pre user i partida existeixen. El kakuro associat a la partida existeix
     * @post cert
     * @return
    */
    public String getPartidaIdTaulell(String user, String partida){
        String tmp[] = partida.split(FinalVariables.fileSeperator);
        return tmp[0]+FinalVariables.fileSeperator+tmp[1];
    }
    
    /**
     * @brief metodo de conveniencia
     * @param user
     * @param idPartida
     * @return 
     */
    public String[][] getPartidaTaulell(String user, String idPartida){
        return carregarKakuro(getPartidaIdTaulell(user,idPartida));
    }
    
    /**
     * @brief metodo de conveniencia
     * @param user
     * @param idPartida
     * @return 
     */
    public String[][] getPartidaTaullellSolucio(String user, String idPartida){
        return carregarKakuroSolucio(getPartidaIdTaulell(user,idPartida));
    }

    /** 
     * @brief
     * @param user useuari creador de la partida
     * @param partida nom del fitxer on esta la partida
     * @pre user i partida existeixen
     * @return
    */
    public String getPartidaTemps(String user, String partida){
        String p = getPathStringPartida(user,partida);
        File f = new File(p);
        if(f.exists()){
            //System.out.println("finding");
            f.setReadable(true);
            try{
                List<String> content = Files.readAllLines(f.toPath());
                return content.get(0);
            } catch (IOException e) {
              //System.out.println("An error occurred in getPartidaTemps.");
            }
        }
        return FinalVariables.tempsDefaultRanking;
    }

    /** 
     * @brief Troba la Matriu de valors actuals d'una partida en curs. Per retornar l'estat de la partida.
     * @param user useuari creador de la partida
     * @param partida nom del fitxer on esta la partida
     * @pre user i partida existeixen. La partida es enJoc
     * @post cert
     * @return Matriu de valors actuals d'una partida en curs
    */
    public PairSB[][] getPartidaMatriuValorsActuals(String user, String partida){
        String p = getPathStringPartida(user,partida);
        File f = new File(p);
        PairSB[][] mat = new PairSB[0][0];
        if(f.exists()){
            f.setReadable(true);
            List<String> content = new ArrayList<>();
            try{
                content = Files.readAllLines(f.toPath());
            } catch (IOException e) {
              //System.out.println("An error occurred in getPartidaMatriuValorsActuals.");
            }
            String s = content.get(1);
            
            int files, columnes;
            String t[] = s.split(",");
            files = Integer.parseInt(t[0]);
            columnes = Integer.parseInt(t[1]);
            mat = new PairSB[files][columnes];
            PairSB temp;
            String line;
            int posicioPrimerValorMatriu = 2;
            for(int i=0;i<files;i++){
                line=content.get(i+posicioPrimerValorMatriu);
                
                t = line.split(",");
                for(int j=0;j<columnes;j++){
                    String[] sPair = t[j].split("/");
                    mat[i][j] = new PairSB(sPair[0], sPair[1].equals(String.valueOf(true)));
                }
            }
        }
        return mat;
    }

    /** 
     * @brief Retorna 
     * @param user useuari creador de la partida
     * @param partida nom del fitxer on esta la partida
     * @pre user i partida existeixen. La partida es enJoc
     * @post cert
     * @return
    */
    public ArrayList< PairII > getPartidaCoordNoConfirmades(String user, String partida){
        String p = getPathStringPartida(user,partida);
        File f = new File(p);
        ArrayList<PairII> listCoord = new ArrayList<>();
        if(f.exists()){
            f.setReadable(true);
            List<String> content = new ArrayList<>();
            try{
                content = Files.readAllLines(f.toPath());
            } catch (IOException e) {
              //System.out.println("An error occurred in getPartidaTemps.");
            }
            String s = content.get(1);
            int files;
            String[] t = s.split(",");
            files = Integer.parseInt(t[0]);
            int primerValorLiniaCoord = files + 2;
            int listsize =  Integer.parseInt(content.get(primerValorLiniaCoord));
            for(int i=0; i<listsize;i++){
                s = content.get(primerValorLiniaCoord+1+i);
                t = s.split(",");
                int a,b;
                a = Integer.parseInt(t[0]);
                b = Integer.parseInt(t[1]);
                PairII temp = new PairII(a,b);
                listCoord.add(temp);
            }

        }
        return listCoord;
    }

    /** 
     * @brief Troba l'estat d'una partida  
     * @param user useuari creador de la partida
     * @param partida nom del fitxer on esta la partida
     * @pre user i partida existeixen.
     * @post cert
     * @return L'estat d'una partida
    */
    public boolean getPartidaEstat(String user, String partida){
        String[] tmp = partida.split(FinalVariables.fileSeperator);
        return tmp[1].equals("Fin");
    };

    /** 
     * @brief Metode de Conveniencia per trobar l'estat d'una partida 
     * @param user useuari creador de la partida
     * @param partida nom del fitxer on esta la partida
     * @pre user i partida existeixen.
     * @post cert
     * @return L'estat d'una partida
    */
    public boolean getPartidaEstat(String partida){
        String[] tmp = partida.split(FinalVariables.fileSeperator);
        return tmp[1].equals("Fin");
    };
    
    /**
     * @brief Enregistra un partida Completa nova al sistema, i actualitza de no completa a nova
     * @pre L'entorn ha sigut creat
     * @param username username del usuari que juga la partida
     * @param idTaulell id del taulell on es juga
     * @param temps temps que la partida porta jugada en format hh:mm:ss
     * @return Retorna el nom del fitxer creat
     * @post Ha quedat enregistrat una partida completa  els valors donats i esborra una vella si es desitja
    */
    public String afegirPartidaAcabada(
        String username,
        String idTaulell,
        String temps
    ){
        String p = getPathStringPartidas(username);
        String newName = nomPartida(username, idTaulell, true);
        String pathstring = p+ java.io.File.separator + newName;
        File myObj;
        try {
          myObj = new File(pathstring);
          if (myObj.createNewFile()) {
            //System.out.println("File created: " + myObj.getName());
          } else {
           // System.out.println("File already exists.");
          }
        

            try (FileWriter fw = new FileWriter(myObj)) {
                fw.write(temps);
                fw.write(System.lineSeparator());
                fw.write(username);
                fw.write(System.lineSeparator());
                fw.write(idTaulell);
                
                
            }

            //Si existeix una partida en joc canvia a fin
            String vella = nomPartida(username, idTaulell, false);
            File fv = new File(vella);
            if(fv.exists()) fv.delete();

        } catch (IOException e) {
         // System.out.println("An error occurred in afegirPartida.");
        }

        int puntsGlob;
        String dif;
        dif = getDificultatPartida(idTaulell);
        if(dif.equals("dificil")) puntsGlob=7;
        else if(dif.equals("mig")) puntsGlob=4;
            else puntsGlob=2;
        addPuntsGlobals(username,puntsGlob);

        return newName;
    }
    
    /** 
     * @brief Afegeix punts a un usuari
     * @param username usuari en questio
     * @param puntsAdd punts a afegir
     * @pre Usuari amb username existeix, puntsAdd > 0
     * @post El usuari te enregistrat que te puntsAdd mes que abans
    */
    private void addPuntsGlobals(String username, int puntsAdd){
        int punts;
        String password;
        Path p;
        
        punts = getPuntuacioGlobal(username) + puntsAdd;
        password = getPass(username);
        p = getPathUsuari(username);
        
        File myObj = new File(p.toString());
        
        try (FileWriter fw = new FileWriter(myObj)) {
            String puntString;
            puntString = String.valueOf(punts);
            
            fw.write(username);
            fw.write(System.lineSeparator());
            fw.write(password);
            fw.write(System.lineSeparator());
            fw.write(puntString);
            fw.write(System.lineSeparator());
        } catch (IOException ex) {
            Logger.getLogger(CtrlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    /**
     * @brief Comprova si existeix un usuari amb aquest username
     * @pre cert
     * @param username username de l'Usuari a cercar
     * @return boolean que indica cert si existeix aquest usuari al sistema
     * @post cert
     */
    public boolean existsUsuari(String username){
        Path p = getPathUsuari(username);
        File myObj = new File(p.toString());
        return myObj.exists();
    }

    /**
     * @brief Comprova si existeix una solucio del kakuro
     * @pre existeix un taulell kakuroID 
     * @param kakuroID username de l'Usuari a cercar
     * @return boolean que indica cert si existeix aquesta solucio al Sistema
     * @post cert
     */
    private boolean existsSolucioKakuro(String kakuroID){
        Path p = getPathKakuroSolucio(kakuroID);
        File myObj = new File(p.toString());
        return myObj.exists();
    }
    
    /**
     * @brief Retorna la puntacio mes baixa que te un usari en un taulell
     * @pre Usuari existeix
     * @param user username de l'Usuari
     * @param id identificador del taulell
     * @return puntacio que te un usari en un taulell, 0 si no te cap partida enregistrda
     * @post cert
     */
    public String getPuntuacioTaulell(String user, String id){
        String kakid = id.split(FinalVariables.fileSeperator)[0];
        String punts = FinalVariables.tempsDefaultRanking;
        File f = new File(getPathStringPartidas(user));
        File[] ficheros;
        ficheros = f.listFiles((File dir, String name) -> {
            boolean result;
            String tmp[] = name.split(FinalVariables.fileSeperator);
            result = tmp[2].equals("fin") && tmp[0].equals(kakid);
            return result;
        } //apply a filter
        );
        if(ficheros.length!=0){
            for (File fichero : ficheros) {
                Path pathPartida = fichero.toPath();
                String puntsPartida=FinalVariables.tempsDefaultRanking;
                try{
                    puntsPartida = Files.readAllLines(pathPartida).get(FinalVariables.posicioTempsCP);
                } 
                catch(IOException e){
                   // System.out.println(e);
                }
                if(punts.equals(FinalVariables.tempsDefaultRanking)) punts = puntsPartida;
                else if(punts.compareTo(puntsPartida)>0) punts = puntsPartida;
            }
        }
        return punts;
    }
    
    /**
     * @brief  Retorna la puntacio global que te un usari, Partida dificil = 7 punts, mig=4, facil=2
     * @pre Usuari existeix
     * @param username username de l'Usuari a cercar
     * @return puntacio global  que te un usari
     * @post cert
     */
    public int getPuntuacioGlobal(String username){
        
        Path p = getPathUsuari(username);
        int n = 2; // The line number
        try{
            return Integer.parseInt(Files.readAllLines(Paths.get(p.toString())).get(n));
        } 
        catch(IOException e){
           // System.out.println(e);
        }
        return 0;
    }
    
    /**
     * @brief  Retorna la contrasenya que te un usari
     * @pre Usuari existeix
     * @param username username de l'Usuari a cercar
     * @return la contrasenya que te un usari
     * @post cert
     */
    public String getPass(String username){
        Path p = getPathUsuari(username);
        int n = 1; // The line number
        try{
            return Files.readAllLines(Paths.get(p.toString())).get(n);
        } 
        catch(IOException e){
            //System.out.println(e);
        }
        return "NULL";
    }
    
    /**
     * @brief Enregistra una solucio de un Kakuro existent al sistema
     * @pre existeix un kakuro amb el nom kakuroOriginal
     * @param files files del kakuro ha enregistrat
     * @param columnes columnes del kakuro ha enregistrat
     * @param taulell Matriu que enregistra els valors de cada casella
     * @param kakuroOriginal nom del fitxer on esta el kakuro Original
     * @post Ha quedat enregistrat una solucio del kakuro amb els valors de taulell i dificultat
     */
    public String afegirSolucioKakuro( int files,
                                        int columnes,
                                        String[][] taulellSolucio,
                                        String kakuroOriginal
                                    ){
     
        Path p = getPathKakuroDir();
        String newName = nomnouSolucioKakuro(kakuroOriginal);
        String pathstring = getPathKakuroSolucio(newName).toString();
        String newline="";
        File myObj;
        try {
          myObj = new File(pathstring);
          if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
          } else {
            //System.out.println("File already exists.");
          }
        

            try (FileWriter fw = new FileWriter(myObj)) {
                fw.write(String.valueOf(files)+","+columnes);
                fw.write(System.lineSeparator());
                for (int i=0;i<files;i++) {
                    for (int j=0;j<columnes;j++){
                        if(j==0) newline = taulellSolucio[i][j];
                        else{
                            newline = newline + "," + taulellSolucio[i][j];
                        }
                    }
                    fw.write(newline);
                    fw.write(System.lineSeparator());
                }   }

        } catch (IOException e) {
          //System.out.println("An error occurred in afegirkakuro.");
        }
        return newName;
    }

    /**
     * @param kakuroID nom del fitxer a cercar
     * @brief Busca el contingut de un fitxer que conte un Kakuro 
     * @pre Existeix un fitxer anomenat KakuroID a la carpeta data/Kakuros
     * @post cert
     * @return es retorna una matriu de String amb els valors del fitxer especificat a KakuroID
    */
    public String[][] carregarKakuro(String kakuroID) {
        System.out.println("Loading "+ kakuroID);
        String[][] taulell = new String[0][0];
        
        String fitxerpath = getPathKakuro(kakuroID).toString();
        File fitxer = new File(fitxerpath); 

        if(!fitxer.exists()){
            return taulell;
        }

        try {
            BufferedReader bf = new BufferedReader(new FileReader(fitxer));
            String linia;

            linia = bf.readLine();
            String[] valors = linia.split(",");
            int files = Integer.parseInt(valors[0]);
            int columnes = Integer.parseInt(valors[1]);
            taulell = new String[files][columnes];
            
            for (int i = 0; i < files; i++) {
                linia = bf.readLine();
                valors = linia.split(",");
                
                System.arraycopy(valors, 0, taulell[i], 0, columnes);
            }
            
            bf.close();
        }
        
        catch (IOException e) {
        }
        return taulell;
    }
    
    /**
     * @param pathKakuro path absolut del fitxer
     * @brief Busca el contingut de un fitxer que conte un Kakuro 
     * @pre Existeix el fitxer i es un kakuro viable
     * @post cert
     * @return es retorna una matriu de String amb els valors del fitxer especificat a KakuroID
    */
    public String[][] carregarKakuroAmbPath(String pathKakuro) {
        String[][] taulell;
        File fitxer;
        taulell = new String[0][0];
        fitxer = new File(pathKakuro); 

        if(!fitxer.exists()) return taulell;

        try {
            BufferedReader bf = new BufferedReader(new FileReader(fitxer));
            String linia;

            linia = bf.readLine();
            String[] valors = linia.split(",");
            int files = Integer.parseInt(valors[0]);
            int columnes = Integer.parseInt(valors[1]);
            
            taulell = new String[files][columnes];
            
            for (int i = 0; i < files; i++) {
                linia = bf.readLine();
                valors = linia.split(",");
                
                System.arraycopy(valors, 0, taulell[i], 0, columnes);
            }
            
            bf.close();
        }
        
        catch (IOException e) {
        }
  
        return taulell;
    }

    /** 
     * @brief Retorna la solucio d'un kakuro
     * @param kakuroID kakuro a buscar la solucio
     * @pre kakuroID existeix
     * @post Si no existia la solucio, la crea
     * @return Retorna en el format de matriu de strings el el kakuro solucio
    */
    public String[][] carregarKakuroSolucio(String kakuroID){
        return carregarKakuroAmbPath(getKakuroSolucioPathString(kakuroID));
    }

    /**
     * @brief Troba totes les partidas no finalitzades d'un usuari
     * @param user Username de l'usuari en questio
     * @pre user existeix
     * @post cert
     * @return Retorna el nom de les partidas que no estan finalitzades
    */
    public String[] getPartidasEnJoc(String user){
        String p = getPathStringPartidas(user);
        File carpeta = new File(p);
        File[] Partidas;
        Partidas = carpeta.listFiles((File dir, String name) -> {
            boolean result;
            String estat = name.split(FinalVariables.fileSeperator)[2];
            result = estat.equals("enJoc");
            return result;
        } //apply a filter
        );
        
        String[] Taulells = new String[Partidas.length];
        for (int i=0;i< Partidas.length; i++) {
            Taulells[i] =
                Partidas[i].getName();
        }
        return Taulells;
    }
    
    /** 
     * @brief Troba les 5 o menys millors partidas de tot el sistema
     * @pre Existeix un usuari com a minim
     * @post cert
     * @return Una matriu on en la primera columna, hi ha el username i en el segon la puntuacio total de l'usuari
     *          La matriu te una dimensio maxima de 5 files
    */
    public String[][] getRankingGlobal(){
        String[] lu = llistatUsuarios();
        String[][] rankingGlob;
        if(lu.length>=5){
            rankingGlob = new String[5][2];
        }
        else{
            rankingGlob = new String[lu.length][2];
        }
        for (int i=0;i<lu.length;i++) {
            boolean ordenar = false;
            int puntsCompare = getPuntuacioGlobal(lu[i]);//Punts de l'usari a comprovar
            if(i<rankingGlob.length){//añade valores hasta que la matriz este completa
                rankingGlob[i][0] = lu[i];
                rankingGlob[i][1] = String.valueOf(puntsCompare);
                ordenar = (i==rankingGlob.length-1);//ordena por primera vez, la matriz esta llena por primera vez
            }
            else{//la matriz esta ordenada,comprueba si este valor es mayor que el ultimo y ordena si cambia
                int puntsLastRanking = Integer.parseInt(rankingGlob[rankingGlob.length-1][1]);
                if(puntsCompare>puntsLastRanking){
                    ordenar = true;
                    rankingGlob[rankingGlob.length-1][0] = lu[i];
                    rankingGlob[rankingGlob.length-1][1] = String.valueOf(puntsCompare);
                }
            }
            if(ordenar){
                String tempU;
                String tempP;
                for(int j=0;j<rankingGlob.length;j++){
                    for(int k=j+1;k<rankingGlob.length;k++){
                        if(Integer.parseInt(rankingGlob[j][1])<Integer.parseInt(rankingGlob[k][1])){
                            //fem swap
                            tempU = rankingGlob[j][0];
                            tempP = rankingGlob[j][1];
                            rankingGlob[j][0] = rankingGlob[k][0];
                            rankingGlob[j][1] = rankingGlob[k][1];
                            rankingGlob[k][0] = tempU;
                            rankingGlob[k][1] = tempP;

                        }
                    }
                }
            }
        }
        return rankingGlob;
    }
    
    /** 
     * @brief Troba les 5 o menys millors partidas d'un Taulell
     * @param kakuroID ID del kakuro que volem obtenir el seu ranking
     * @pre cert
     * @post cert
     * @return Una matriu on en la primera columna, hi ha el username i en el segon la puntuacio que ha obtingut
    */
    public String[][] getRankingTaulell(String kakuroID){
        ArrayList<String> list =  getPathAllPartidasFinTaulell(kakuroID);
        String[][] rankingtaulell;

        if(list.size()>=5){
            rankingtaulell = new String[5][2];
        }
        else{
            rankingtaulell = new String[list.size()][2];
        }
        for (int i=0;i<list.size();i++) {
            File f = new File(list.get(i));
            String user = f.getParentFile().getName();
            boolean ordenar = false;
            String puntsCompare = getPuntacioPartida(list.get(i));//Punts de l'usari a comprovar
            if(i<rankingtaulell.length){//añade valores hasta que la matriz este completa
                rankingtaulell[i][0] = user;
                rankingtaulell[i][1] = puntsCompare;
                ordenar = (i==rankingtaulell.length-1);//ordena por primera vez, la matriz esta llena por primera vez
            }
            else{//la matriz esta ordenada,comprueba si este valor es mayor que el ultimo y ordena si cambia
                String puntsLastRanking = rankingtaulell[rankingtaulell.length][1];
                if(puntsCompare.compareTo(puntsLastRanking)>0){
                    ordenar = true;
                    rankingtaulell[rankingtaulell.length][0] = user;
                    rankingtaulell[rankingtaulell.length][1] = puntsCompare;
                }
            }
            if(ordenar){
                String tempU;
                String tempP;
                for(int j=0;j<rankingtaulell.length;j++){
                    for(int k=j+1;k<rankingtaulell.length;k++){
                        if((rankingtaulell[j][1].compareTo(rankingtaulell[k][1])>0)){
                            //fem swap
                            tempU = rankingtaulell[j][0];
                            tempP = rankingtaulell[j][1];
                            rankingtaulell[j][0] = rankingtaulell[k][0];
                            rankingtaulell[j][1] = rankingtaulell[k][1];
                            rankingtaulell[k][0] = tempU;
                            rankingtaulell[k][1] = tempP;

                        }
                    }
                }
            }
        }
        return rankingtaulell;
    }
    
    /**
     * @brief Troba la puntuacio d'una partida en concret
     * @param spath Path en format string de la partida que volem conseguir la puntacio
     * @pre cert
     * @post cert
     * @return temps de la partida, si la partida no existeix retorna FinalVariables.tempsDefaultRanking
    */
    private String getPuntacioPartida(String spath){

        Path path = Paths.get(spath);
        String puntsPartida=FinalVariables.tempsDefaultRanking;
        try{
            puntsPartida = Files.readAllLines(path).get(FinalVariables.posicioTempsCP);
        } 
        catch(IOException e){
            //System.out.println(e);
        }
        return puntsPartida;
    }

    /**
     * @brief Troba la llista de partidas d'un Kakuro concret
     * @param kakId nom del fitxer kakuro
     * @pre cert, si no hi ha cap partida la llista sera buida
     * @post cert
     * @return Llista amb el Path en format String de totes les partidas finalitzades del Kakuro desitjat
    */
    private ArrayList<String> getPathAllPartidasFinTaulell(String kakId){
        String[] lu = llistatUsuarios();
        String checkKakId = kakId.split(FinalVariables.fileSeperator)[0];
        ArrayList<String> list;
        list = new ArrayList<>();
        for (String lu1 : lu) {
            File f = new File(getPathStringPartidas(lu1));
            File[] ficheros = f.listFiles((File dir, String name) -> {
                boolean result = false;
                String tmp[] = name.split(FinalVariables.fileSeperator);
                if(tmp[0].equals(checkKakId) && tmp[2].equals("fin")){
                    result=true;
                }
                return result;
            } //apply a filter
            );
            for (File fichero : ficheros) {
                list.add(fichero.getPath());
            }
        }
        return list;
    }
    
    /**
     * @brief Troba els noms de les partidas d'una dificultat que te en curs un usuari
     * @pre user existeix
     * @post cert
     * @param dificultat dificultat escollida
     * @param user propietari de les partides
     * @return Matriu amb el titol de les partides
     */
    public String[] llistatPartidasEnCursDificultat(String user,String dificultat){
        String p = getPathStringPartidas(user);
        File carpeta = new File(p);
        File[] Partidas;
        Partidas = carpeta.listFiles((File dir, String name) -> {
            boolean result;
            String estat,dif;
            estat = name.split(FinalVariables.fileSeperator)[2];
            dif = name.split(FinalVariables.fileSeperator)[1];
            result = dif.equals(dificultat) && estat.equals("enJoc");
            return result;
        } //apply a filter
        );
        
        String[] Taulells = new String[Partidas.length];
        for (int i=0;i< Partidas.length; i++) {
            Taulells[i] =
                Partidas[i].getName();
        }
        return Taulells;
    }

}

