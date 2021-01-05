/** @file RandomKakuro.java
 * 
 */
package src.CapaDomini.Controladors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import src.Exceptions.*;
import src.CapaDomini.Classes.Taulell;
import src.CapaDomini.Utils.*;
import src.Exceptions.*;

/** @class RandomKakuro
 *  @brief Controlador per poder crear un kakuro aleatori
 */
public class RandomKakuro {
    
    /**@brief Nombre de files del taulell */
    private int files;
    /**@brief Nombre de columnes del taulell */
    private int columnes;
    /**@brief Dificultat del taulell creat*/
    private String dificultat;
    /**@brief Taulell associat al random per guardar la forma del kakuro*/
    private Taulell KakuroGenerat;
    /**@brief Boolea si ha trobat un Kakuro Random*/
    private boolean FoundKakuroRandom;
    
    
    
    
    
    
    //Constructores
    /** @brief Constructora per defecte.
        @pre Cert.
        @post S'ha creat RandomKakuro amb valors nulls.
    */
    public RandomKakuro() {
        
    }
    
    /** @brief Constructora per defecte.
     * @param nfiles  files del kakuro a generar
     * @param ncolumnes columnes del kakuro a generar
     * @param tdificultat dificultat del kakuro a generar pot ser dificil mig facil
        @pre files > 2 and columnes > 2
        @post S'ha creat un KakuroRandom, s'associa el taulell i es dona valor a les files i les columnes.
    */
    public RandomKakuro(int nfiles, int ncolumnes, String tdificultat) {
        
      try {
        if(!(FinalVariables.DificultatDificil.equals(tdificultat) || FinalVariables.DificultatMig.equals(tdificultat) || FinalVariables.DificultatFacil.equals(tdificultat)))
            throw(new ExceptionDificultatErronea());
        
        if(nfiles>2 && ncolumnes>2){
            KakuroGenerat = new Taulell();
            files = nfiles;
            columnes = ncolumnes;
            dificultat = tdificultat;
            CrearTaulellRandom();
            
            while( !(LazyPopulate()) ) CrearTaulellRandom();
            
            ColocarSumaCasellas();
        }
        else throw(new ExceptionDimensionsTaulellErrones());
      }
      catch (ExceptionDimensionsTaulellErrones e) {
            System.out.println(e.getMessage() + " arg files-columnes:" + nfiles + "," + ncolumnes);
      }
      catch (ExceptionDificultatErronea e) {
            System.out.println(e.getMessage() + " arg:" + dificultat);
      }
    }
    
    //Consultores
    
    /**
     * @brief Retorna el taulell generat amb string que segueixen el input format establit 
     * @pre Taulell no es nuk
     * @post cert
     * @return Retorna el taulell generat amb string que segueixen el input format establit 
     */
    public String[][] MatriuString(){
        String[][] Matriu = new String[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if(KakuroGenerat.casellaBlanca(i,j)) Matriu[i][j]="?";
                else Matriu[i][j] = KakuroGenerat.getValorCasella(i, j);
            }
        }
        return Matriu;
    }
    
    /**
     * @brief Retorna el taulell generat amb numeros valids string que segueixen el input format establit 
     * @pre Taulell no es null i s'han colocat nombres
     * @post cert
     * @return Retorna el taulell generat omplert de nombres valids amb string que segueixen el input format establit 
     */
    public String[][] MatriuStringNum(){
        String[][] Matriu = new String[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                Matriu[i][j] = KakuroGenerat.getValorCasella(i, j);
                System.out.print(Matriu[i][j]);
            }
        }
        return Matriu;
    }
    
    /**
     * @brief Retorna si ha trobat un taulell random possible
     * @pre se ha intentat crear com a minim un kakuro
     * @post cert
     * @return Retorna si ha trobat un taulell random possible
     */
    public boolean getFoundKakuro(){
        return FoundKakuroRandom;
    }
    
    /**
     * @brief crea un nou taulell jugable amb les dimensions pasades originalment
     * @pre el Ctrl te associat files i columnes i dificultat
     * @post es crea un taulell jugable amb les dimensions quan es va initzialitzar
     */
    public String[][] CreateNewBoard(){
        CrearTaulellRandom();
        while( !(LazyPopulate()) ) CrearTaulellRandom();
        ColocarSumaCasellas();
        return MatriuString();
    }
    /**
     * @brief Crea un Kakuro de Blanques i de Negres Estructuralment correcte segons la dificultat
     * @pre dimensions del kakuro mes grans que 3x3
     * @post es crea un taulell de blanques i negres segons la dificultat i mles dimensions
     */
    private void CrearTaulellRandom(){
        DefaultKakuro(files,columnes);

        int wc;
        while(!KakuroGenerat.CheckTaulellValidEstructuralment() ){
            EliminateImpossibleCellArragement();
            EliminateImpossibleCellArragement();
        };
        double temp = 0.0;
        if(FinalVariables.DificultatDificil.equals(dificultat)) temp = FinalVariables.WhiteCellPercentDificil;
        else if (FinalVariables.DificultatMig.equals(dificultat)) temp = FinalVariables.WhiteCellPercentMig;
                else if (FinalVariables.DificultatFacil.equals(dificultat)) temp = FinalVariables.WhiteCellPercentFacil;
        
        
        int wt = (int) ((files*columnes) * temp);
         while(wt < KakuroGenerat.getNumeroCasellesBlanques() ){
            GenerateBlackPieces();
            }
    }
        
    
    
    
  
    
    
    /** @brief Crea un taulell default de blanques i negres
     *  @pre S'han donat dimensions
     *  @post KakuroGenerat es un taulell de blanques amb la primera fila i la primera columna negres
     */
    private void DefaultKakuro(int nfiles,int ncolumnes){
        KakuroGenerat = new Taulell();
        files = nfiles;
        columnes = ncolumnes;
        KakuroGenerat.GenerateDefaultBoard(nfiles,ncolumnes);
    }
    
      
    
    /** @brief Imprimeix per pantalla el taulell associat
     * @param coloreado True si vols que imprimeixi el taulell associat amb colors
        @pre el taullell associat no és nul
        @post S'imprimeix per pantalla el taullell per pantalla, tindra colors si l'argument es tur
    */
    public void PrintTaulell(boolean coloreado) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_RED = "\u001B[31m";
        
        System.out.print(ANSI_BLUE);
        for(int a=-1;a<columnes;a++){
            System.out.print(src.CapaDomini.Utils.FinalVariables.NormalitzarEspai("c"+String.valueOf(a)));
            if(a!=columnes-1)System.out.print("|");
        }
        System.out.print("\n");
        for (int j=0;j<columnes+1;j++) {
            System.out.print("-");
            System.out.print("-------");
         }
        System.out.print("\n");
        System.out.print(ANSI_RESET);
        for(int i=0;i<files;i++){
          for (int j=0;j<columnes;j++) {
            if(j==0)System.out.print(ANSI_BLUE+src.CapaDomini.Utils.FinalVariables.NormalitzarEspai("f"+String.valueOf(i))+ANSI_RESET);  
            System.out.print("|");
            if(coloreado && KakuroGenerat.casellaBlanca(i,j))System.out.print(ANSI_YELLOW);
            else if(coloreado && KakuroGenerat.casellaNegra(i,j))System.out.print(ANSI_PURPLE);
            else if(coloreado && KakuroGenerat.casellaSuma(i,j))System.out.print(ANSI_RED);
            System.out.print(src.CapaDomini.Utils.FinalVariables.NormalitzarEspai(KakuroGenerat.getValorCasella(i, j)));
            System.out.print(ANSI_RESET);
          }
          System.out.print("\n");
          for (int j=0;j<columnes+1;j++) {
            if(j!=0) System.out.print("-");
            System.out.print("-------");
          }
          System.out.print("\n");
        }
    }
    
    /**
     * @brief Elimina Casellas que blanques que estiguin unicament elles en una fila
     * @pre cert
     * @post converteix a negres aquelles caselles que estan unicament elles en una agrupacio
     *       També elimina si s'han creat caselles solitaries en fer la primera pasada
     */
    private void EliminarSolitarias(){
         boolean Notfound=true;
         int n=0;
	    int fil, col;
            fil=col=0;
	    for(fil = 0; fil < files && Notfound; ++fil) {
	       for (col = 0; col < columnes && Notfound; ++col) {
	            if(KakuroGenerat.casellaNegra(fil,col)){
                       if( KakuroGenerat.nombreCasellesBlanquesSpecific(fil,col,true)  == 1 ){
                           KakuroGenerat.TransformCellToOtherType(fil+1, col,FinalVariables.CapACellaNegra ,"*");
                           n++;
                       }
                       if( KakuroGenerat.nombreCasellesBlanquesSpecific(fil,col,false)  == 1 ){
                           KakuroGenerat.TransformCellToOtherType(fil, col+1,FinalVariables.CapACellaNegra ,"*");
                           n++;
                        }
                    }
                           
	        }
            }
        if(n!=0) EliminarSolitarias();     
    }
    
    
    /**
     * @brief Retorna un valor aleatori ponderat d'entre 2 i 9
     * @pre cert
     * @post cert
     * @return Valor entre 2 i 9
     */
    private int GetRndCellLine(){
        Random ran = new Random();
        int rnd;
        int NCellLine=3;                
        rnd = ran.nextInt(100); 
        if(rnd >= 0  && rnd <=10)  NCellLine = 2;
        if(rnd >= 11 && rnd <=25)  NCellLine = 3;
        if(rnd >= 26 && rnd <=40)  NCellLine = 4;
        if(rnd >= 41 && rnd <=52)  NCellLine = 5;
        if(rnd >= 53 && rnd <=65)  NCellLine = 6;
        if(rnd >= 65 && rnd <=80)  NCellLine = 7;
        if(rnd >= 81 && rnd <=90)  NCellLine = 8;
        if(rnd >= 91 && rnd <=99)  NCellLine = 9;
        
        return NCellLine;
    }
    
    /** @brief Elimina agrupacions de celes blanques de 9 generant caselles negres
        @pre el kakuro taulell no te caselles solitaries
        @post modifica el taulell perque no hi hagi agrupacions de >9 celes blanques
    */
    private void EliminateImpossibleCellArragement(){
        int abort = 1000;
        Random ran = new Random();
    	int CellsTransformed=0;
        
        ArrayList<PairII> ListForChange = new ArrayList<>();
        ArrayList<PairII> ListForBackTracking = new ArrayList<>();
        for (int fil = 0; fil < files; fil++) {//pone en una lista las posiciones negras donde detecta errores verticals
            for (int col = 0; col < columnes; col++) {
                if(KakuroGenerat.casellaNegra(fil,col) && (KakuroGenerat.nombreCasellesBlanquesSpecific(fil,col,true)>9))
                    ListForChange.add(PairII.of(fil,col));
            }
        }      
        
        
        int NCellLine;
        int nCasellesBlanques;
        boolean CambioPorDelante;
        
        
        
    	while(!ListForChange.isEmpty()){
            if((abort--)<0) return;
            int f = ListForChange.get(0).getLeft();
            int c = ListForChange.get(0).getRight();
            if(KakuroGenerat.nombreCasellesBlanquesSpecific(f,c,true)<9){
                ListForChange.remove(0);
                
            }
            else{
                
                if(KakuroGenerat.nombreCasellesBlanquesSpecific(f,c,true)>9){//vertical
                    
                    nCasellesBlanques = KakuroGenerat.nombreCasellesBlanquesSpecific(f,c,false);
                    NCellLine = GetRndCellLine();

                    CambioPorDelante = ran.nextBoolean();//50% chance
                    int TransformFila,TransformColumna;
                    if(CambioPorDelante){
                        TransformFila= f + NCellLine;
                        TransformColumna = c;
                    }
                    else{
                        TransformFila= f + nCasellesBlanques - NCellLine;
                        TransformColumna = c;
                    }
                    KakuroGenerat.TransformCellToOtherType(TransformFila,TransformColumna,FinalVariables.CapACellaNegra,"");
                    //PrintTaulell(true);
                    if(KakuroGenerat.CheckConnectivityWhiteCells()){
                        ListForBackTracking.add(PairII.of(TransformFila,TransformColumna));
                    }
                    else KakuroGenerat.TransformCellToOtherType(TransformFila,TransformColumna,FinalVariables.CapACellaBlanca,"");
                    
                }
            }
        }
        
        ListForChange = new ArrayList<>();
        for (int fil = 0; fil < files; fil++) {//pone en una lista las posiciones negras donde detecta errores verticals
            for (int col = 0; col < columnes; col++) {
                if(KakuroGenerat.casellaNegra(fil,col) && (KakuroGenerat.nombreCasellesBlanquesSpecific(fil,col,false)>9))
                    ListForChange.add(PairII.of(fil,col));
            }
        }
        
        //Errors Horitzontals
        while(!ListForChange.isEmpty()){      
            if((abort--)<0) return;
            int IndexList = 0;
            int f = ListForChange.get(IndexList).getLeft();
            int c = ListForChange.get(IndexList).getRight();
            if(KakuroGenerat.nombreCasellesBlanquesSpecific(f,c,false)<9){
                //PrintTaulell(true);
                ListForChange.remove(IndexList);
                
            }
            else {//horizontal
                    
                    nCasellesBlanques = KakuroGenerat.nombreCasellesBlanquesSpecific(f,c,false);
                    NCellLine = GetRndCellLine();//2-9

                    CambioPorDelante = ran.nextBoolean();//50% chance
                    int TransformFila,TransformColumna;
                    if(CambioPorDelante){
                        TransformColumna= c + NCellLine;
                        TransformFila = f;
                    }
                    else{
                        TransformColumna= c + nCasellesBlanques - NCellLine;
                        TransformFila = f;
                    }
                    KakuroGenerat.TransformCellToOtherType(TransformFila,TransformColumna,FinalVariables.CapACellaNegra,"");
                    if(KakuroGenerat.CheckConnectivityWhiteCells()){
                        ListForBackTracking.add(PairII.of(TransformFila,TransformColumna));
                    }
                    else KakuroGenerat.TransformCellToOtherType(TransformFila,TransformColumna,FinalVariables.CapACellaBlanca,"");
            }
        }
        EliminarSolitarias();
        if(!KakuroGenerat.CheckTaulellValidEstructuralment()){
            DefaultKakuro(files,columnes);
            EliminateImpossibleCellArragement();
        }
        
        
    }
    
    /**
    * @brief Intenta Genera una casella negra en una cela blanca aleatoria
    * @pre taulell existeix
    * @pre el taulell es estructuralment correcte
    * @post el taulell es estructuralment correcte
    * + de les caselles blanques que es puguin eliminar perque mantigui la correctesa estructural s'a eliminat una al atzar
    */
    private void GenerateBlackPieces(){
        int Transformed=0;
        Random ran = new Random(); 
        ArrayList<PairII> Selector = new ArrayList<>();
        for(int idx=0;idx<files;idx++){
            for(int jdx=0;jdx<columnes;jdx++){
                if(KakuroGenerat.casellaBlanca(idx,jdx))
                   Selector.add(PairII.of(idx,jdx));
            }
        }
        int indexList;
        int PosicioFilaTransf,PosicioColumnaTransf;
        while(!Selector.isEmpty()){
            indexList = ran.nextInt(Selector.size());
            PosicioFilaTransf=Selector.get(indexList).getLeft();
            PosicioColumnaTransf=Selector.get(indexList).getRight();
            if(KakuroGenerat.casellaBlanca(PosicioFilaTransf,PosicioColumnaTransf)){
               KakuroGenerat.TransformCellToOtherType(PosicioFilaTransf, PosicioColumnaTransf, FinalVariables.CapACellaNegra, "*");
               if(!KakuroGenerat.CheckTaulellValidEstructuralment()){
                   KakuroGenerat.TransformCellToOtherType(PosicioFilaTransf, PosicioColumnaTransf, FinalVariables.CapACellaBlanca, "?");
                    Selector.remove(indexList);
               }
            }
            else {
                Selector.remove(indexList);
            }
            
        }
    } 
    
    
    
    /** @brief Funció per saber si es pot col·locar un valornumeric a una casella blanca
        @pre Kakurogenerat conte valors no nuls
        @param possible és el valor a comprovar
        @param fila és la posició de la fila
        @param columna és la posició de la columna
        @post Retorna true en cas que el valor númeric no estigui duplicat a la seva fila o columna
    */
    private boolean valorPossible(String possible, int fila, int columna) {
        int suma = 0;
        int f = fila-1;
        while (f>=0 && KakuroGenerat.casellaBlanca(f,columna)) { 
            //es comproven valors repetits i la suma vertical
            if (KakuroGenerat.getValorCasella(f,columna).equals(possible)) return false; //valor repetit            
            --f;
        }
        
        int col = columna-1;

        while (col>=0 && KakuroGenerat.casellaBlanca(fila,col)) { 
            //es comproven valors repetits i la suma horitzontal
            
            if (KakuroGenerat.getValorCasella(fila,col).equals(possible)) return false; //valor repetit
            
            --col;
        }
        
        
        return true;
    }
    
    /**
     * @brief Coloca Numeros aleatoris entre 1-9 a les posicions blanques del taulell
     * @param fila fila on intenta col.locar numero
     * @param columna colocar on intenta col.locar numero
     * @return True si s'ha pogut col·locar un numero en aquesta posició
     * @post Valor casella canvia a un numero si s'ha pogut col.locar un numero aleatori
     * @pre La funció es crida a la primera casella blanca trobada
     */
    private boolean ColocarNumeros(int fila, int columna){
        Random ran = new Random();
        if (fila == files) {
            return true;
        }
        
        ArrayList<String> numbers = new ArrayList<>();
        if (KakuroGenerat.casellaBlanca(fila,columna)) {
            for (int i = 1; i <10; ++i) {
                if (valorPossible(String.valueOf(i), fila, columna))
                    numbers.add(String.valueOf(i));
            }
            
            while(!numbers.isEmpty()){
                int rnd = ran.nextInt(numbers.size());
                KakuroGenerat.setValorCasella(fila,columna,numbers.get(rnd));
                numbers.remove(rnd);
                
                int a = fila;
                int b = columna + 1;
                if (b == columnes) {a = a+1; b=0;} 
                if(ColocarNumeros(a, b) ) return true;
                    
            }
            
            KakuroGenerat.setValorCasella(fila,columna,"?");
        }
            
        if ("?".equals(KakuroGenerat.getValorCasella(fila,columna))){
            return false;
        } //no hi ha cap valor possible
          
        
        else {
            int a = fila;
            int b = columna + 1;
            if (b == columnes) {a = a+1; b=0;} 
            ColocarNumeros(a, b);
        }
        return true;
    }
    
    /**
     * @brief Canvia les caselles negres a casella de suma amb els seus sumatoris corresponent
     * @pre les caselles blanque tenen nombres
     * @post Canvia les caselles negres a casella de suma amb els seus valors corresponent
     */
    private void ColocarSumaCasellas(){
        for(int i=0; i<files; i++){
            for (int j = 0; j < columnes; j++) {
                 if(KakuroGenerat.casellaNegra(i,j)){
                     //Te suma Vertical
                     int sumaFila = 0,sumaColumna = 0;
                     boolean esSuma=false;
                     if(KakuroGenerat.nombreCasellesBlanquesSpecific(i, j, true)>1){
                         sumaColumna = GetSumaPopulate(i,j,true);
                         esSuma=true;
                         
                     }
                     //Te suma horizontal
                     if(KakuroGenerat.nombreCasellesBlanquesSpecific(i, j, false)>1){
                          sumaFila = GetSumaPopulate(i,j,false);
                          esSuma=true;
                     }                     
                     if(esSuma){
                         String s = CrearCasellaSumaValor(sumaFila,sumaColumna);
                         KakuroGenerat.TransformCellToOtherType(i, j, FinalVariables.CapACellaSuma, s);
                     }
                 }
            }
        }
    
    }
    
    /**
     * @brief Coloca numeros aleatoris a les caselles blanques i coloca la seva suma corresponent
     * @post en el taulell es coloquen numeros i la seva suma corresponent
     * @return True si ha pogut col·locar nombres a totes les caselles
     * @pre KakuroGenerat es estructuralment correcte
     */
    private boolean LazyPopulate(){
        Random ran = new Random();
        ArrayList<PairII> ListPosition = new ArrayList<>();
        //Coloca nombres aleatoris entre 1 i 9 a les posicions blanques
        
        boolean trobat = false;
        int c,f;
        c = f = 0;
        while(f < files && !trobat) {
            c=0;
            
            while (c < columnes && !trobat) {
                if (KakuroGenerat.casellaBlanca(f,c)) trobat= true;
                else ++c;
            }
            
            if (!trobat) ++f;
        }
        return ColocarNumeros(f,c);
        
    }
    /**
     * Funcio per trobar la suma vertical o horitzontal que te una casella negra
     * @pre el taulell ha de tenir numeros en totes les posicions blanques
     * @param fila fila de la casella de Suma
     * @param columna columna de la casella de Suma
     * @param Vertical Si volem la suma en vertical TRUE o en horitzontal FALSE
     * @return retorna el sumatori de les caselles blanques que li son adjecents verticals o horitzontals
     * @post cert
     */
    private int GetSumaPopulate(int fila, int columna, boolean Vertical){
        
        int CellLine = KakuroGenerat.nombreCasellesBlanquesSpecific(fila, columna, Vertical);
        int Total = 0;
        for (int i = 1; i <= CellLine; i++) {
            if(Vertical) Total+= Integer.parseInt(KakuroGenerat.getValorCasella(fila+i,columna));
            else Total+= Integer.parseInt(KakuroGenerat.getValorCasella(fila,columna+i));
        }
        return Total;
    }
    /**
     * Crear el tipus de input SumaFila Corresponent als valors donats
     * @param SumaFila sumatori horitzontal
     * @param SumaColumna sumatori vertical
     * @pre Almenys un dels arguments es mes gran que 0
     * @return String amb el input corresponent de la casella suma
     * @post cert
     */
    private String CrearCasellaSumaValor(int SumaFila, int SumaColumna){
        
        String str1 = "notinit",str2;
        if(SumaColumna>0 && SumaFila>0){
            str1 ="C"+String.valueOf(SumaColumna);
            str2 ="F"+String.valueOf(SumaFila);
            str1 = str1.concat(str2);
        }
        else if(SumaFila>0){
            str1 ="F"+String.valueOf(SumaFila);
        }
        else if(SumaColumna>0){
            str1 ="C"+String.valueOf(SumaColumna);
        }
        return str1;
    }
    
    
    
}
