#Makefile del projecte
PathSrc = ./src
PathMain = ./src/Main.java
PathDomini = ./src/CapaDomini
PathControladors = ./src/CapaDomini/Controladors/*.java
PathClasses = ./src/CapaDomini/Classes/*.java
PathUtils = ./src/CapaDomini/Utils/*.java
PathPersistencia = ./src/CapaPersistencia/*.java
PathDrivers = ./src/CapaDomini/Drivers
PathException = ./src/Exceptions/*.java
PathTests = ./src/tests
PathDocumentation = ./Documentació/DOXYGEN/html/index.html
PathDriver = ./src/CapaDomini/Drivers
PathLib = ./lib

all: clean compile

compile:	
	javac -cp $(PathSrc) $(PathUtils) $(PathClasses) $(PathControladors) $(PathPersistencia) $(PathException) 
run: 
	java $(PathMain)
	
Drivers: DriverCasella DriverCtrlDomain DriverKakuroSolver DriverPartida DriverTaulell
 
DriverCtrlDomain:
	javac -cp $(PathDriver)/DriverCtrlDomain.java $(PathUtils) $(PathClasses) $(PathControladors) $(PathPersistencia) $(PathException)

DriverKakuroSolver:
	javac -cp $(PathDriver)/DriverKakuroSolver.java $(PathUtils) $(PathClasses) $(PathControladors) $(PathPersistencia) $(PathException)

DriverPartida:
	javac -cp $(PathDriver)/DriverPartida.java $(PathUtils) $(PathClasses) $(PathControladors) $(PathPersistencia) $(PathException)


DriverTaulell:
	javac -cp $(PathDriver)/DriverTaulell.java $(PathUtils) $(PathClasses) $(PathControladors) $(PathPersistencia) $(PathException)

DriverCasella:
	javac -cp $(PathDriver)/DriverCasella.java $(PathUtils) $(PathClasses) $(PathControladors) $(PathPersistencia) $(PathException)

runDriverCtrlDomain: 
	java $(PathDriver)/DriverCasella.java < $(PathDriver)/TestDriverCasellaInput.txt

runDriverCtrlDomain1:
	java $(PathDriver)/DriverCtrlDomain.java < $(PathDriver)/TestDriverCtrlDomainInput1.txt

runDriverCtrlDomain2: 
	java $(PathDriver)/DriverCtrlDomain.java < $(PathDriver)/TestDriverCtrlDomainInput2.txt

runDriverKakuroSolver1: 
	java $(PathDriver)/DriverKakuroSolver.java < $(PathDriver)/TestDriverKakuroSolverInput1.txt

runDriverKakuroSolver2: 
	java $(PathDriver)/DriverKakuroSolver.java < $(PathDriver)/TestDriverKakuroSolverInput2.txt 

runDriverPartida: 
	java $(PathDriver)/DriverPartida.java < $(PathDriver)/TestDriverPartidaInput.txt.txt 

runDriverTaulell: 
	java $(PathDriver)/DriverTaulell.java < $(PathDriver)/TestDriverTaulellInput.txt

clean:
	rm -f $(PathSrc)/*/*.class $(PathSrc)/*/*/*.class $(PathSrc)/*/*/*/*.class


#En la creació de les comandes per crear els executables del JUnit no hem pogut compilar-les amb distribució Ubuntu
# i desconeixem el motiu havent seguit les diapositives i buscat informació a Internet. 
# En canvi a Windows hem pogut executar-los correctament, comprovant les seva correcta execució.
# Les comandes utilitzades han estat:

#Posar classpath:

#set CLASSPATH=C:\Program Files\NetBeans-12.1\netbeans\platform\modules\ext\junit-4.12.jar;C:\Program Files\NetBeans-12.1\netbeans\platform\modules\ext\hamcrest-core-1.3.jar;
#C:\Program Files\NetBeans-12.1\netbeans\platform\modules\ext\hamcrest-core-1.3.jar;

#compilar i executar TaulellTest:

#javac src\CapaDomini\Classes\Taulell.java
#javac tests\TaulellTest.java
#java org.junit.runner.JUnitCore tests.TaulellTest

#Compilar i executar CasellaTest:

#javac src\CapaDomini\Classes\Casella.java
#javac src\CapaDomini\Classes\CasellaNegra.java
#javac src\CapaDomini\Classes\CasellaBlanca.java
#javac src\CapaDomini\Classes\CasellaSuma.java
#javac tests\CasellaTest.java
#java org.junit.runner.JUnitCore tests.CasellaTest


