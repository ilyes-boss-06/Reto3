package app;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Alumno 1
		Main1.ejercicio1();
		Main1.ejercicio2(sc);

		// Alumno 2
		Main2.ejercicio3(sc);
		Main2.ejercicio4(sc);

		// Alumno 3
		Main3.ejercicio5(sc);
		Main3.ejercicio6();

		// Alumno 1
		Main1.ejercicio7(sc);
		Main1.ejercicio8(sc);

		// Alumno 2
		Main2.ejercicio9(sc);
		Main2.ejercicio10(sc);

		// Alumno 3
		Main3.ejercicio11(sc);
		Main3.ejercicio12(sc);

		// Alumno 1
		Main1.ejercicio13(sc);
		Main1.ejercicio14();

		// Alumno 2
		Main2.ejercicio15(sc);
		Main2.ejercicio16(sc);

		// Alumno 3
		Main3.ejercicio17(sc);

		sc.close();
	}
}