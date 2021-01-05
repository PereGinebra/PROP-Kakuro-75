#Makefile del projecte
PathSrc=			./src
PathMain=			./src/Main.java
PathDomini=			./src/CapaDomini
PathControladors=		./src/CapaDomini/Controladors/*.java
PathClasses=			./src/CapaDomini/Classes/*.java
PathUtils=			./src/CapaDomini/Utils/*.java
PathPersistencia=		./src/CapaPersistencia/*.java
PathException=			./src/Exceptions/*.java
PathPresentacio=		./src/CapaPresentacio/*.java

PathTest=			./test

PathConfigDocumentation=	./Documentació/DOXYGEN/Doxyfile
PathDocumentation=		./Documentació/DOXYGEN/html/index.html

PathData=			./data

PathLib= 			./lib
PathLibHamcrest= 		./lib/hamcrest-core-1.3.jar
PathLibJunit= 			./lib/junit-4.13.1.jar

PathProtectedData=		./ProtectedData

PathAnterior=			./Entrega_Anterior/

PathAnteriortest=		./Entrega_Anterior/tests
PathAnteriortest1=		./Entrega_Anterior/tests/CasellaTest.java
PathAnteriortest2=		./Entrega_Anterior/tests/TaulellTest.java

PathAnteriorlib=		./Entrega_Anterior/lib
PathAnteriorJunit=		./Entrega_Anterior/lib/junit-4.13.1.jar
PathAnteriorHamcrest=		./Entrega_Anterior/lib/hamcrest-core-1.3.jar

PathAnteriorSrc=		./Entrega_Anterior/src
PathAnteriorMain=		./Entrega_Anterior/src/Main.java
PathAnteriorControladors=	./Entrega_Anterior/src/CapaDomini/Controladors/*.java
PathAnteriorUtils=		./Entrega_Anterior/src/CapaDomini/Utils/*.java
PathAnteriorClasses=		./Entrega_Anterior/src/CapaDomini/Classes/*.java
PathAnteriorPersistencia=	./Entrega_Anterior/src/CapaPersistencia/*.java
PathAnteriorException=		./Entrega_Anterior/src/Exceptions/*.java

all: purgeandclean copyProtected compile

compile:	
	javac -cp $(PathMain) $(PathUtils) $(PathClasses) $(PathControladors) $(PathPersistencia) $(PathException) $(PathPresentacio)

#executa la Aplicació amb dades
appProtected: clean purgealldata copyProtected
	java $(PathMain)

#executa la Aplicació amb les dades actuals
app:
	java $(PathMain)	

#java -cp .:./Entrega_Anterior/src/lib/junit-4.13.1.jar org.junit.runner.JUnitCore ./Entrega_Anterior/tests/CasellaTest.class

#per compilar l'anterior
cd Entrega_Anterior
make compile #sobre amb el makefile de la subcarpeta
#compileanterior:
#	javac -cp  $(PathAnteriorSrc) $(PathAnteriorUtils) $(PathAnteriorClasses) $(PathAnteriorControladors) $(PathAnteriorPersistencia) $(PathAnteriorException) 

#runanterior: compileanterior
#	java $(PathAnteriorMain)

#executa la Aplicació sense dades
appClean: clean purgealldata
	java $(PathMain)

runtest: 
	java Entrega_Anterior.tests.CasellaTest.class -cp Entrega_Anterior.src:Entrega_Anterior.lib:.
	java Entrega_Anterior.tests.TaulellTest.class -cp Entrega_Anterior.src:Entrega_Anterior.lib:.
	 
compilejunit:
	javac -cp $(PathAnteriorJunit):$(PathAnteriorHamcrest):. -sourcepath $(PathAnteriorSrc) $(PathAnteriorException) $(PathAnteriorUtils) $(PathAnteriorClasses) $(PathAnteriortest1) 
	javac -cp $(PathAnteriorJunit):$(PathAnteriorHamcrest):. -sourcepath $(PathAnteriorSrc) $(PathAnteriorException) $(PathAnteriorUtils) $(PathAnteriorClasses) $(PathAnteriortest2)

testanterior: compilejunit runtest

userPurge:
	rm -rf $(PathData)/Usuarios/*

partidaPurge:
	rm -rf $(PathData)/Partidas/*

kakuroPurge:
	rm -rf $(PathData)/Kakuros/*
	mkdir $(PathData)/Kakuros/Solucions

listusuari:
	tree $(PathData)/Usuarios/

#Copia datos de una session un poco avançada con reepositorios de Kakuros i
#Usuarios con partidas i rankings
copyProtected:
	rm -rf $(PathData)
	mkdir  $(PathData)
	cp -R $(PathProtectedData)/* $(PathData)

#Copia el únicament el repositori de kakuros
copyProtectedKakuros:
	rm -rf $(PathData)/Kakuros
	mkdir  $(PathData)/Kakuros
	cp -R $(PathProtectedData)/Kakuros $(PathData)/Kakuros

#elimina totes les dades relacionades amb els usuaris
purgeusers: userPurge partidaPurge

#elimina totes les dades guardades a l'aplicatiu
purgealldata: userPurge kakuroPurge partidaPurge

cleanAnterior:
	rm -f $(PathAnteriorSrc)/*.class $(PathAnteriorSrc)/*/*.class $(PathAnteriorSrc)/*/*/*.class $(PathAnteriorSrc)/*/*/*/*.class $(PathAnteriortest)/*.class

purgeandclean: purgeusers clean

clean: 
	rm -f $(PathSrc)/*.class $(PathSrc)/*/*.class $(PathSrc)/*/*/*.class $(PathSrc)/*/*/*/*.class
	rm -f $(PathAnteriorSrc)/*.class $(PathAnteriorSrc)/*/*.class $(PathAnteriorSrc)/*/*/*.class $(PathAnteriorSrc)/*/*/*/*.class
