/*
package Extras;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors



public class FilingSystem {

	public static final int FILE_USER	 	= 1;
	public static final int FILE_KAKURO		= 2;
	public static final int FILE_PARTIDA	= 3;

	public static final String FILE_PARTIDA_FIN	= "F";
	public static final String FILE_PARTIDA_NO_FIN	= "J";

	public static final int SEPARATOR_CHAR	= '-';

	public static final int MAX_N_SAME_KAKURO_PER_USER	= 100;

	public static int SYSTEM_N_USER = 0;
	public static int SYSTEM_N_PARTIDA = 0;
	public static int SYSTEM_N_KAKURO = 0;

	/**
	* Obtiene el path absoluto a un fichero
	* @param FileType Formato del archivo a crear.
	* @param FileName Nombre del archivo a crear
	* @return Path absoluto al Fichero
	*/
/*
	String AbsolutePath(int FileType, String FileName){
		String Path = System.getProperty("user.dir");
		switch(FileType){
			case FILE_USER:
				Path = Path + '/' + "Usuario" + '/';
			break;

			case FILE_KAKURO:
				Path = Path + '/' + "Kakuro" + '/';
			break;


			case FILE_PARTIDA:
				Path = Path + '/' + "Partida" + '/';
			break;
			default:
		}
		return Path + FileName;
	}

	/**
	* Crear un fichero del formato definido
	- Puede ser:
			- User
			- Kakuro
			- Partida
	* @param FileType Formato del archivo a crear.
		
	* @param FileName Nombre del archivo a crear
	*/
/*
	public static void CreateFile(int FileType, String FileName) {
		File newFile = File(AbsolutePath(FileType, FileName));
	}

	/**
	* Buscaa si un archivo existe en el directorio de su tipo
	* @param FileType Formato del archivo a buscar.
		
	* @param FileName Nombre del archivo a buscar
	* @return Devuelve si el fichero existe
	*/
/*
	public boolean ExistsFile(int FileType, String FileName){
		String Path = System.getProperty("user.dir") + '/' + "Partida" + ;
		switch(FileType){
			case FILE_USER:
				Path = Path + '/' + "Usuario" + '/';
			break;
			case FILE_KAKURO:
				Path = Path + '/' + "Kakuro" + '/';
			break;
			case FILE_PARTIDA:
				Path = Path + '/' + "Partida" + '/' + FILE_PARTIDA_NO_FIN;
			break;
			default:
				//Error
		}
		Path = Path + FileName;
		return isFile(Path);
	}

	/**
	* Devuelve el numero de Partidas No Finalitzadas de un usuario de un Tablero Concreto
	* @param Username Formato del archivo a crear.
	* @param KakuroID Nombre del archivo a crear.
	* @return Number  Numero de Partidas No Finalitzadas
	*/
/*
	public int NumberOfPartidasSinAcabarDeUserPorKakuro(String Username, String KakuroID){
		int Number=0;
		boolean ReachNoPartida = false;
		for (int i=0;i < MAX_N_SAME_KAKURO_PER_USER || i < SYSTEM_N_PARTIDA || !ReachNoPartida; i++) {
			String FileToSearch = FILE_PARTIDA_NO_FIN + SEPARATOR_CHAR + Username + KakuroID + SEPARATOR_CHAR + Integer.toString(i);
			if(ExistsFile(FILE_PARTIDA,FileToSearch) ) Number++;
			else{
				String aux = FileToSearch = FILE_PARTIDA_FIN + SEPARATOR_CHAR + Username + KakuroID + SEPARATOR_CHAR + Integer.toString(i);
				if(ExistsFile(FILE_PARTIDA,aux){
					ReachNoPartida=true;
				}
			}
		}
		Path = Path + FileName;
		return Number;
	}
	/**
	* Devuelve el numero de Partidas Finalitzadas de un usuario de un Tablero Concreto
	* @param Username Formato del archivo a crear.
	* @param KakuroID Nombre del archivo a crear.
	* @return Number  Numero de Partidas Finalitzadas
	*/
/*
	public int NumberOfPartidasFinalitzadasDeUserPorKakuro(String Username, String KakuroID){
		int Number=0;
		boolean ReachNoPartida = false;
		for (int i=0;i < MAX_N_SAME_KAKURO_PER_USER || i < SYSTEM_N_PARTIDA || !ReachNoPartida; i++) {
			String FileToSearch = FILE_PARTIDA_FIN + SEPARATOR_CHAR + Username + KakuroID + SEPARATOR_CHAR + Integer.toString(i);
			if(ExistsFile(FILE_PARTIDA,FileToSearch) ) Number++;
			else{
				String aux = FileToSearch = FILE_PARTIDA_NO_FIN + SEPARATOR_CHAR + Username + KakuroID + SEPARATOR_CHAR + Integer.toString(i);
				if(ExistsFile(FILE_PARTIDA,aux){
					ReachNoPartida=true;
				}
			}
		}
		Path = Path + FileName;
		return Number;
	}

	/**
	* Devuelve el si existe este usuario
	* @param Username Nombre de usuario
	* @return Devuelve si un usuario con es username existe en el sistema
	*/
/*
	public boolean UserExists(String Username){
		return ExistsFile(FILE_USER, Username);
	}
	/**
	* Devuelve el si existe este Kakuro con este ID
	* @param KakuroID ID del Kakuro
	* @return Devuelve si un Kakuro con esta ID existe en el sistema
	*/
/*
	public boolean KakuroExists(String KakuroID){
		return ExistsFile(FILE_KAKURO, KakuroID);
	}
	/**
	* Devuelve el si existe este Kakuro con busca profunda en todos los kakuros. Pending Implementation
	* @param KakuroID ID del Kakuro
	* @return Devuelve si existe un kakuro igual en este sistema
	*/
/*
	public boolean KakuroExistsDeepDive(String KakuroID){}

	/**
	* Devuelve el Username de una Partida
	* @param FilePartida Nombre del fichero Partida
	* @return Devuelve una string con el valor username de la partida
	*/
/*
	public String UsernameDeUnaPartida(String FilePartida){
		int endUsername  = FilePartida.indexOf(SEPARATOR_CHAR);
		return FilePartida.substring(0, endUsername-1);
	}

	/**
	* Devuelve el ID del Kakuro de una Partida
	* @param FilePartida Nombre del fichero Partida
	* @return Devuelve una string con el valor de la ID del Kakuro de una Partida
	*/
/*
	public String KakuroDeUnaPartida(String FilePartida){
		int beginningID = FilePartida.indexOf(SEPARATOR_CHAR);
		int endID  = FilePartida.lastIndexOf(SEPARATOR_CHAR);
		return FilePartida.substring(beginningID+1, endID-1);
	}

	/**
	* Devuelve el Numero de Instancia de una Partida
	* @param FilePartida Nombre del fichero Partida
	* @return Devuelve una int con la instancia de Partida
	*/
/*
	public Int InstanciaDeUnaPartida(String FilePartida){
		int beginningInstancia  = FilePartida.lastIndexOf(SEPARATOR_CHAR);
		return FilePartida.substring(beginningInstancia+1);
	}

	/**
	* Devuelve el numero de Partidas que tiene este Username con este kakuro
	* @param Username Username del Usuario.
	* @param KakuroID ID del Kakuro.
	* @return Number  Numero de instancias que tiene este usuario con este kakuro
	*/
/*
	public Int LastInstanciaDeUSERconKakuro(String KakuroID, String Username){
		return NumberOfPartidasFinalitzadasDeUserPorKakuro + NumberOfPartidasFinalitzadasDeUserPorKakuro;
	}



	/**
	* Devuelve la contraseña del usuario si existe, si no existe devuelve NULL.
	* @param Username Username del Usuario.
	* @return Si el usuario existe devuelve su contrasenya de lo contrario devuelve NULL.
	*/
/*
	public String Password_De_User(String Username){
		if(UserExists(Username)){
			String FileName = ConvertToFileName_User(Username); 
			return Files.readString(AbsolutePath(FILE_USER, FileName), StandardCharsets.US_ASCII);
		}
		else return NULL;
	}

	/**
	* Sobreescribe la contraseña de un usuario
	* Creara el fichero si no existe
	* @param Username Username del Usuario.
	* @param NewPassword Nueva contraeña que desea colocar
	*/
/*
	public void New_Password_De_User(String Username, String NewPassword){
		String Path = ConvertToFileName_User(Username);
		Path = AbsolutePath(FILE_USER, Path);
		Files.write(Path,NewPassword);
	}


	/**
	* Devuelve el nombree de fichero de una Partida
	* @param Username Username del Usuario.
	* @param KakuroID ID del Kakuro
	* @param instanciaPartida Numero de la instancia de la partida a crear (en int)
	* @param Finalitzada Si la partida esta finalitzada o no
	* @return Devuelve el nombre del fichero que se crearia segun los parametros dados
	*/
/*
	public static void ConvertToFileName_Partida(String Username, String KakuroID, Int instanciaPartida, boolean Finalitzada){
		if (Finalitzada) {
			return FILE_PARTIDA_FIN + SEPARATOR_CHAR + Username + SEPARATOR_CHAR + KakuroID + SEPARATOR_CHAR + Integer.toString(instanciaPartida);
		}
		else return FILE_PARTIDA_NO_FIN + SEPARATOR_CHAR + Username + SEPARATOR_CHAR + KakuroID + SEPARATOR_CHAR + Integer.toString(instanciaPartida);
	}

	/**
	* Devuelve el nombree de fichero de una Partida
	* @param Username Username del Usuario.
	* @param KakuroID ID del Kakuro
	* @param instanciaPartida Numero de la instancia de la partida a crear (en una String)
	* @param Finalitzada Si la partida esta finalitzada o no
	* @return Devuelve el nombre del fichero que se crearia segun los parametros dados
	*/
/*
	public static void ConvertToFileName_Partida(String Username, String KakuroID, String instanciaPartida, boolean Finalitzada){
		if (Finalitzada) {
			return FILE_PARTIDA_FIN + SEPARATOR_CHAR + Username + SEPARATOR_CHAR + KakuroID + SEPARATOR_CHAR + instanciaPartida;
		}
		else return FILE_PARTIDA_NO_FIN + SEPARATOR_CHAR + Username + SEPARATOR_CHAR + KakuroID + SEPARATOR_CHAR + instanciaPartida;
	}

	/**
	* Devuelve el nombre de fichero de una User
	* @param Username Username del Usuario.
	* @return Devuelve el nombre del fichero que se crearia segun los parametros dados
	*/
/*
	public static void ConvertToFileName_User(String Username){
		return Username;
	}

	/**
	* Devuelve el nombre de fichero de una Tablero
	* @param KakuroID ID del Kakuro
	* @return Devuelve el nombre del fichero que se crearia segun los parametros dados
	*/
/*
	public static void ConvertToFileName_Kakuro(String KakuroID){
		return KakuroID;
	}
}
*/



