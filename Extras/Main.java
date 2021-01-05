/*package Extras;

import static java.lang.System.*;
import java.util.Scanner;

/* Main Class*/
/*
public class Main {

public static final String ANSI_RESET		= "\u001B[0m";
public static final String ANSI_RED			= "\u001B[31m";

public static final int COMMAND_ATRAS				= 0;
public static final int STR_INSTRUCTION_COMMAND		= 0;

public static final int COMMAND_GOUSER		= 1;

public static final int COMMAND_NEWUSER	 	= 1;
public static final int COMMAND_LOGIN	 	= 2;

public static final int COMMAND_KAKURO		= 1;
public static final int COMMAND_RANKING	 	= 2;

public static final int COMMAND_RANKING_GLOBAL		= 1;
public static final int COMMAND_RANKING_INDIVIUAL	= 2;

public static final int COMMAND_KAKURO_CREAR		= 1;
public static final int COMMAND_KAKURO_NEWGAME		= 2;
public static final int COMMAND_KAKURO_LOADGAME		= 3;

public static final int COMMAND_KAKURO_NEWGAME_MANUAL		= 1;
public static final int COMMAND_KAKURO_NEWGAME_AUTO			= 2;

public static final int COMMAND_KAKURO_NEWGAME_AUTO_SIMPLE	= 1;
public static final int COMMAND_KAKURO_NEWGAME_AUTO_MIG		= 2;
public static final int COMMAND_KAKURO_NEWGAME_AUTO_DIFICIL	= 3;

public static final int USER_FLAGS_COUNTITY					= 8;

public static final int USER_COMMANDDEFAULT					= 0;
public static final int USER_START							= 1;
public static final int USER_NEWUSER_LOGIN					= 2;
public static final int USER_KAKURO_RANKING					= 3;
public static final int USER_RANKING_GLOBAL_INDI			= 4;
public static final int USER_KAKURO_CHOICE					= 5;
public static final int USER_KAKURO_NEWGAME_TYPE			= 6;
public static final int USER_KAKURO_NEWGAME_AUTO_DIFICULTY	= 7;


public static final int ALL_FLAGS = 33;
/** \brief Variable de Flag de bits para regulaar la navegación por el menu
*	Current Flags:
*	USER_START
*		- USER_NEWUSER_LOGIN
*			- USER_KAKURO_RANKING
*				- USER_RANKING_GLOBAL_INDI
*				- USER_KAKURO_CHOICE
*			- USER_KAKURO_NEWGAME_TYPE
*				- USER_KAKURO_NEWGAME_AUTO_DIFICULTY
**
	public static int FLAG_USER_INPUT;
	/** \brief Variable de String para saber que opciones de comandos hay
	*	@see Flowchart
	


	/** \brief Variable del Nombre de las Flags
	*	@see Flowchart
	*	@see COMMAND_STR_OUTPUT
	* 	@see FLAG_USER_INPUT
	**

/*
    public static void main(String[] args) {
        
		int command;
		DeactivateFlag(ALL_FLAGS);

		while(CheckFlagNOTACTIVE(USER_START) ){
			PrintExpectedCommand(USER_START);
			DeactivateFlag(ALL_FLAGS);

			switch(command){
				case (COMMAND_ATRAS):
					ActivarFlag(USER_START);
					break;

				case (commandGoToUser):
					DeactivateFlag(ALL_FLAGS);
					while(CheckFlag(USER_NEWUSER_LOGIN) ){
					command = ExpectedCommand(USER_NEWUSER_LOGIN);
					bool Registrated = false;
					//Usuario Current_User;
					switch(command){
						case COMMAND_ATRAS:
							ActivarFlag(USER_NEWUSER_LOGIN);
						break;
						case COMMAND_NEWUSER:
							PrintMessage("Registra-te");
							Registrated = true;
						break;
						case COMMAND_LOGIN:
							PrintMessage("Log In");
							Registrated = true;
						break;
						default:
							PrintErrorMessage("Commando no reconocido");
					}
					if(Registrated){
						DeactivateFlag(ALL_FLAGS);

						while(CheckFlag(USER_KAKURO_RANKING) ){
							command = ExpectedCommand(USER_KAKURO_RANKING);
							switch(command){
								case COMMAND_ATRAS:
								ActivarFlag(USER_KAKURO_RANKING);
								break;
								case COMMAND_RANKING:
									DeactivateFlag(ALL_FLAGS);
									while(CheckFlag(USER_RANKING_GLOBAL_INDI)){
										command = ExpectedCommand(USER_RANKING_GLOBAL_INDI);
										switch(command){
											case COMMAND_ATRAS:
												ActivarFlag(USER_RANKING_GLOBAL_INDI);
											break;

											case COMMAND_RANKING_INDIVIUAL:
												//ShowGlobalRanking(Current_User);
												PrintMessage("ShowingGlobalRanking");
											break;

											case COMMAND_RANKING_GLOBAL:
												KAKURO = ChooseKAKURO();
												//ShowIndividualRanking(Current_User);
												PrintMessage("ShowIndividualRanking");
											break;

											default:
												PrintErrorMessage("Commando no reconocido");
										}
									}
								break;

								case COMMAND_KAKURO:
									DeactivateFlag(ALL_FLAGS);
									while(CheckFlag(USER_KAKURO_CHOICE)){
										command = ExpectedCommand(USER_KAKURO_CHOICE);
										switch(command){
											case COMMAND_ATRAS:
												ActivarFlag(USER_KAKURO_CHOICE);
											break;

											case COMMAND_KAKURO_CREAR:
												DeactivateFlag(ALL_FLAGS);
												while(CheckFlag(USER_KAKURO_NEWGAME_TYPE)){
													switch(USER_KAKURO_NEWGAME_TYPE){
														case COMMAND_ATRAS:
															ActivarFlag(USER_KAKURO_CHOICE);
														break;

														case COMMAND_KAKURO_NEWGAME_AUTO:
															PrintMessage("CrearKakuroAutomaticamente");
														break;

														case COMMAND_KAKURO_NEWGAME_MANUAL:
															DeactivateFlag(ALL_FLAGS);
															while(CheckFlag(USER_KAKURO_NEWGAME_AUTO_DIFICULTY)){
																switch(USER_KAKURO_NEWGAME_AUTO_DIFICULTY){
																	case COMMAND_ATRAS:
																		ActivarFlag(USER_KAKURO_CHOICE);
																	break;

																	case COMMAND_KAKURO_NEWGAME_AUTO_SIMPLE:
																		PrintMessage("CrearKakuroSimple");
																	break;

																	case COMMAND_KAKURO_NEWGAME_AUTO_MIG:
																		PrintMessage("CrearKakuroMig");
																	break;

																	case COMMAND_KAKURO_NEWGAME_AUTO_DIFICIL:
																		PrintMessage("CrearKakuroDificil");
																	break;
																	default:
																		PrintErrorMessage("Commando no reconocido");

																}
															}
													break;
													default:
														PrintErrorMessage("Commando no reconocido");
												}
											}
											case COMMAND_KAKURO_NEWGAME:

												PrintMessage("NEWGAME");
											break;

											case COMMAND_KAKURO_LOADGAME:
												PrintMessage("LoadGame");
											break;

											default:
												PrintErrorMessage("Commando no reconocido");
										}
									}									
								break;

								default:
								PrintErrorMessage("Commando no reconocido");
							}
						}

					}
					}
					break;
				
				default:
					PrintErrorMessage("Commando no reconocido");
			}
		}
		
		System.exit(0);
	}

	/**
	* Imprime por pantalla un mensaje de error en rojo.
	* @param str String de Error que quieres que se enseñe por consola.
	* @see PrintMessage()
	*
	*
	public static void PrintErrorMessage(String str){
		System.out.println(ANSI_RED + "[Error] " + str + ANSI_RESET);
	}

	/**
	* Imprime por pantalla un mensaje.
	* @param str String que quieres que se enseñe por consola.
	* @see PrintErrorMessage()
	*
	/*
	public static void PrintMessage(String str){
		System.out.println(str);
	}

	public static void PrintActivatedFlags(){
		for (int i=0;i<USER_FLAGS_COUNTITY;i++) {
			String str;
			if(CheckFlagNOTACTIVE(i)){
				PrintMessage("TRUE  = " +  FlagName[i]);
			}
			else{
				PrintMessage("FALSE = " + FlagName[i]);
			}
		}
	}
	/**
	* Imprime por pantalla un mensaje.
	* @param str String que quieres que se enseñe por consola.
	* @see PrintErrorMessage()
	*
	public static void PrintCommandInput(int COMMAND_CHOICE, int USER_COMMAND){
		System.out.println(COMMAND_STR_OUTPUT[USER_COMMAND][COMMAND_CHOICE]);
	}

	public static  void ActivarFlag(int USER_COMMAND){
		if(USER_COMMAND == ALL_FLAGS){
			FLAG_USER_INPUT = Integer.MAX_VALUE;
		}
		else{
			FLAG_USER_INPUT = FLAG_USER_INPUT | (1 << USER_COMMAND);
		}
		
	}

	public static  void DeactivateFlag(int USER_COMMAND){
		if(USER_COMMAND == ALL_FLAGS){
			FLAG_USER_INPUT = 0;
		}
		else{
			FLAG_USER_INPUT = FLAG_USER_INPUT & ~(1 << USER_COMMAND);
		}
	}

	public static  boolean CheckFlagNOTACTIVE(int USER_COMMAND){
		return ((FLAG_USER_INPUT>>USER_COMMAND) & 1) > 0;
	}

	public static int ExpectedCommand(int USER_CommandExpected){

		PrintCommandInput(STR_INSTRUCTION_COMMAND, USER_COMMANDDEFAULT);

		PrintCommandInput(COMMAND_ATRAS, USER_CommandExpected);

		switch(USER_CommandExpected){

			case USER_START:
				PrintCommandInput(COMMAND_GOUSER, USER_CommandExpected);
			break;

			case USER_NEWUSER_LOGIN:
				PrintCommandInput(COMMAND_NEWUSER, USER_CommandExpected);
				PrintCommandInput(COMMAND_LOGIN, USER_CommandExpected);
			break;

			case USER_KAKURO_RANKING:
				PrintCommandInput(COMMAND_RANKING, USER_CommandExpected);
				PrintCommandInput(COMMAND_KAKURO, USER_CommandExpected);
			break;

			case USER_RANKING_GLOBAL_INDI:
				PrintCommandInput(COMMAND_RANKING_INDIVIUAL, USER_CommandExpected);
				PrintCommandInput(COMMAND_RANKING_GLOBAL, USER_CommandExpected);
			break;

			case USER_KAKURO_CHOICE:
				PrintCommandInput(COMMAND_KAKURO_CREAR, USER_CommandExpected);
				PrintCommandInput(COMMAND_KAKURO_NEWGAME, USER_CommandExpected);
				PrintCommandInput(COMMAND_KAKURO_LOADGAME, USER_CommandExpected);
			break;

			case USER_KAKURO_NEWGAME_TYPE:
				PrintCommandInput(COMMAND_KAKURO_NEWGAME_MANUAL, USER_CommandExpected);
				PrintCommandInput(COMMAND_KAKURO_NEWGAME_AUTO, USER_CommandExpected);
			break;


			case USER_KAKURO_NEWGAME_AUTO_DIFICULTY:
				PrintCommandInput(COMMAND_KAKURO_NEWGAME_AUTO_SIMPLE, USER_CommandExpected);
				PrintCommandInput(COMMAND_KAKURO_NEWGAME_AUTO_MIG, USER_CommandExpected);
				PrintCommandInput(COMMAND_KAKURO_NEWGAME_AUTO_DIFICIL, USER_CommandExpected);
			break;


			default:
				PrintErrorMessage("Not an expected decision");
		}

		Scanner capt = new Scanner(System.in);
		int captura = capt.nextInt();

		return captura;
	}

}

*/