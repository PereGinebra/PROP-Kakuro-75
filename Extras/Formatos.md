# Formato Kakuro
Un kakuro tendrà el siguiente formato

+ El nombre del archivo correspondra al id del Kakuro.

+ La primera línea contendrà la string Simple, Mig, Dificil, DelUsuario

+ La segunda línia conté dos nombres n (número de files) i m (número de columnes)

+ Les següents n línies contenen m elements separats per ',' on cadascun defineix una cel.la

+ Valors de cada cel∙la:
  - 1. ? per indicar cel.la blanca a omplir
  - 2. nombre per indicar una cel.la ja emplenada
  - 3. * per indicar cel.la negra buida
  - 4. Fnombre per indicar cel.la negra amb suma de fila
  - 5. Cnombre per indicar cel.la negra amb suma de columna
  - 6. CnombreFnombre per indicar cel.la negra amb sumes de columna i fila

El valor del kakuro siguiente serà:

9,9

*,*,C19,C12,*,*,*,C7,C10

*,F14,?,?,C4,C11,C17F4,?,?

*,C7F36,?,?,?,?,?,?,?

F12,?,?,F10,?,?,?,C25,C14

F3,?,?,C20,C11F20,?,?,?,?

F17,?,?,?,?,C8,F6,?,?

*,C11,C7F13,?,?,?,C4F10,?,?

F28,?,?,?,?,?,?,?,*

F6,?,?,*,*,F8,?,?,*


![KakuroFormat](https://gitlab.fib.upc.edu/grau-prop/subgrup-prop7-5/raw/master/Kakuro/src/main/java/prop75/kakuro/Extras/Ejemplo_KakuroMarkdown.PNG)



# Formato Usuario

+ El nombre del archivo correspondra al id del Usuario.

+ Contendrà 1 string: la contrasenya

# Formato Partida

+ El nombre del archivo correspondra a la F|J id del Usuario '+' id del Kakuro '+' idPartida.
  - F i J serán por si la partida se esta jugando o esta Finalitzada
  - Por ejemplo:
    - El usuario jordi en el kakuro Ganimedes5 con la partida 9 creara el archivo:
      - J-jordi-Ganimedes5-9
      - y cuando la finalice F-jordi-Ganimedes5-9
+ Para las partidas que NO estan finalizadas
    + La primera línia conté dos nombres n (número de files) i m (número de columnes)
    + Les següents n línies contenen m elements separats per ',' on cadascun defineix una cel.la
    + Valors de cada cel∙la:
      - 1. ? per indicar cel.la blanca a omplir
      - 2. nombre per indicar una cel.la ja emplenada
      - 3. * per indicar cel.la negra buida
      - 4. Fnombre per indicar cel.la negra amb suma de fila
      - 5. Cnombre per indicar cel.la negra amb suma de columna
      - 6. CnombreFnombre per indicar cel.la negra amb sumes de columna i fila
      - 7. Numero del 1 al 9 que indica que numero ha colocado el usuario mientras jugaba
      - 8. Anumero del 1 al 9  que indica que numero ha colocado el solucionador
    + Las ultima línea contendra el valor de tiempo que lleva el usuario jugando a la partida
+ Para las Partidas Finalizadas
    + La primera linia contendra el valor de la puntacion que ha conseguido el usuario
    + En el segundo diria con la String Ayuda N, siendo N las veces que ha recibido ayuda
    + En la tercera linia dira con la String "AutoSolucionada true" o "AutoSolucionado False"
