/*package Extras;

import java.util.ArrayList;

public class ReturningValues {
	public final boolean change;
    public final String value;
    public final int type;

    public ReturningValues(boolean change, String value, int type) {
        this.change = change;
        this.value = value;
        this.type = type;
    }

    public boolean getchange(){
    	return this.change;
    }
     
    public boolean getvalue(){
     	return this.value;
    }
  
    public boolean getype(){
      return this.type;
    }


    public needsChange(){
    	return getchange();
    }

    public ReturningValues ReturningValuesNADA() {
        return ReturningValues(False, ".", 0);
    }
}

class Tree {

	public static final int NODE_NO_INFO	 	= -1;
	public static final int NODE_STARTING	 	= 1;

	public String Current_Username;
	public String Current_Kakuro;
	public String Current_Partida;

	private Int CurrentNode = NO_INFO_NODE;
	private ArrayList<DecisionNode> PossibleNodes;
	

	private ArrayList<ArrayList<int>> Arbol;

	Tree CreateDecisionTree(){
		
	}

	void ShowPossibleCommands(){
		for (int i = 0; i < Arbol[CurrentNode].size(); i++) {
			System.out.println( Integer.toString(i) +". " +  GetNodeFromCurrentNode(i).printMyCommandText());
		}
	}

	Node GetNodeFromCurrentNode(int NodeToGo){
		return PossibleNodes.get(Arbol.get(CurrentNode).get(NodeToGo) );
	}

	Node GetNodeFromCurrentTree(int NodeA, int NodeB){
		return PossibleNodesget(Arbolget(NodeA)get(NodeB));
	}

	Node CurrentNode(){
		return PossibleNodes.get(CurrentNode);
	}

	void ChangeParameters(ReturningValues){
		if(rv.needsChange()){
			switch(rv.getype){
				case USERNAME:
					Current_Username = rv.getvalue();
				break;

				case KAKURO:
					Current_Kakuro = rv.getvalue();
				break;

				case PARTIDA:
					Current_Partida = rv.getvalue();
				break;

			}
		}
	}

	void ActivateFunction(){
		ReturningValues rv = CurrentNode().EjecutarFuncion();
		if(rv.needsChange()) ChangeParameters(rv);
		NavigateTree();
	}


	void NavigateTree(int Command){
		ShowPossibleCommands();
		//Capture int
		int capture = CaptureCommand();

		ChangeCurrentNode(capture);
		ActivateFunction();
	}

}
class DecisionNode {


	private String CommandText;


	public void setCommandText(String Text){
		this.CommandText = Text;
	}
	public String getCommandText(){
		return this.CommandText;
	}


	public void printMyCommandText(){
		System.out.println(CommandText);
	}

	public abstract ReturningValues EjecutarFuncion();
}

/* 8 types of nodes
	0- Do Nothing
	1- Exit
	2- Login
	3- Sign Up
	4- Elegir Kakuro
	5- Elegir Partida
	6- Elegir Parametros
	7- Crear Kakuro
	*/
/*
class DoNothing extends DecisionNode{

	public ReturningValues EjecutarFuncion(){
		return ReturningValuesNADA();
	};
}

class Exit extends DecisionNode{

	public ReturningValues EjecutarFuncion(){
		 System.exit(0);
		 return ReturningValuesNADA();
	};
}

class Login extends DecisionNode{

	public ReturningValues EjecutarFuncion(){
		 String Username = Login();
		 return ReturningValues(true, Username, USERNAME);
	};

	public String Login(){

	}
}

class Sign_Up extends DecisionNode{
	public ReturningValues EjecutarFuncion(){
		 String Username = Login();
		 return ReturningValues(true, Username, USERNAME);
	};

	public String Sign_Up(){

	}
}

class Elegir_Kakuro extends DecisionNode{

	public ReturningValues EjecutarFuncion(){
		 String Kakuro = Select_Kakuro();
		 return ReturningValues(true, Kakuro, KAKURO);
	};

	public String Select_Kakuro(){

	}
}

class Elegir_Parametros_y_Crear extends DecisionNode{

	public ReturningValues EjecutarFuncion(){
		 String Kakuro = Select_Kakuro();
		 return ReturningValues(true, Kakuro, KAKURO);
	};

	public String Crear_Kakuro_Parametros(){

	}
}

class Elegir_Partida extends DecisionNode{

	public ReturningValues EjecutarFuncion(){
		 String Partida = Select_Partida();
		 return ReturningValues(true, Partida, PARTIDA);
	};

	public String Select_Partida(){

	}
} 

class Jugar_Kakuro extends DecisionNode{

	public ReturningValues EjecutarFuncion(){
		 Jugar_Kakuro();
		 return ReturningValuesNADA();
	};

}

 
/* 8 types of nodes
	0- Do Nothing
	1- Exit
	2- Login
	3- Sign Up
	4- Elegir Kakuro
	5- Elegir Partida
	6- Elegir Parametros
	7- Jugar Kakuro

	*/
