package Logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Juego {
	private int empezar = 0;
	private Celda[][] tablero;
	private int cantFilas;
	private int cantColumnas;
	static Integer[][] matrizArchivo;
	static Integer[][] matrizCargada;

	public Juego() throws FileNotFoundException {
		boolean filasOK = true;
		boolean columnasOK = true;
		boolean cuadrantesOK = true;
		cantFilas = 9;
		cantColumnas = 9;
		tablero = new Celda[cantFilas][cantColumnas];
		FileReader a = new FileReader("inicial.txt");
		String linealeida;
		BufferedReader leer;
		String[] palabras;
		matrizArchivo = new Integer[9][9];
		int j = 0;
			leer = new BufferedReader(a);
			try {
				while ((linealeida = leer.readLine()) != null) {
					if (!linealeida.isEmpty()) {
						palabras = linealeida.split(" ");
						for (int i = 0; i < palabras.length; i++) {
							matrizArchivo[i][j] = (Integer.parseInt(palabras[i]));
						}
						j++;
					}
				}
				leer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		// Comprobamos las filas
		for (int fila = 0; fila < cantFilas; fila++)
			if (lineaEsCorrecta(matrizArchivo[fila]) == false) {
				filasOK = false;
				System.out.println("Fila no valida: " + fila);
			}
		System.out.println(filasOK ? "Las filas son correctas" : "Las filas estan mal.");
		// Comprobamos columnas
		for (int columna = 0; columna < cantColumnas; columna++) {
			if (lineaEsCorrecta(creaLineaParaColumna(columna)) == false) {
				columnasOK = false;
				System.out.println("Columna no valida: " + columna);
			}
		}
		System.out.println(columnasOK && filasOK ? "Las columnas son correctas" : "Las columnas estan mal.");
		// Comprobamos cuadrante
		for (int cuad = 0; cuad < 9; cuad++) {
			cuadrantesOK = chequearcuadrantearchivo(cuad);
		}

		if (filasOK && columnasOK && cuadrantesOK) {
			System.out.println("\nEl Sudoku es correcto");
			for (int f = 0; f < cantFilas; f++) {
				for (int c = 0; c < cantColumnas; c++) {
					Random rand = new Random();
					int value = rand.nextInt(2);
					tablero[c][f] = new Celda();
					if (value == 0)
						tablero[c][f].setValor(matrizArchivo[f][c]);
					else
						tablero[c][f].setValor(null);
				}
			}
		} else {
			System.out.println("\nEl Sudoku es incorrecto");
			empezar = 1;
		}
	}

	public static Integer[] creaLineaParaColumna(int columna) {
		Integer[] linea = new Integer[9];
		for (int fila = 0; fila < 9; fila++)
			linea[fila] = matrizArchivo[fila][columna];
		return linea;
	}
	public static Integer[] creaLineaParaColumnaComprobar(int columna) {
		Integer[] linea = new Integer[9];
		for (int fila = 0; fila < 9; fila++)
			linea[fila] = matrizCargada[fila][columna];
		return linea;
	}
	public static boolean lineaEsCorrecta(Integer[] matriz2) {
		int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0, c7 = 0, c8 = 0, c9 = 0;
		for (int num : matriz2) {
			switch (num) {
			case 1:
				c1++;
				break;
			case 2:
				c2++;
				break;
			case 3:
				c3++;
				break;
			case 4:
				c4++;
				break;
			case 5:
				c5++;
				break;
			case 6:
				c6++;
				break;
			case 7:
				c7++;
				break;
			case 8:
				c8++;
				break;
			case 9:
				c9++;
				break;
			default:
				return false;// Si encontre un numero fuera del rango 1-9, Sudoku es erroneo
			}
		}
		if (c1 == 1 && c2 == 1 && c3 == 1 && c4 == 1 && c5 == 1 && c6 == 1 && c7 == 1 && c8 == 1 && c9 == 1)
			return true;
		else
			return false;
	}

	public static Integer[] lineaEsCorrectaMarcar(Integer[] matriz2) {
		Integer[] retorno;
		retorno = new Integer[9];
		int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0, c7 = 0, c8 = 0, c9 = 0;
		for (int num : matriz2) {
			switch (num) {
			case 1:
				c1++;
				break;
			case 2:
				c2++;
				break;
			case 3:
				c3++;
				break;
			case 4:
				c4++;
				break;
			case 5:
				c5++;
				break;
			case 6:
				c6++;
				break;
			case 7:
				c7++;
				break;
			case 8:
				c8++;
				break;
			case 9:
				c9++;
				break;
			}
		}
		retorno[0] = c1 > 1 ? 1 : 0;
		retorno[1] = c2 > 1 ? 2 : 0;
		retorno[2] = c3 > 1 ? 3 : 0;
		retorno[3] = c4 > 1 ? 4 : 0;
		retorno[4] = c5 > 1 ? 5 : 0;
		retorno[5] = c6 > 1 ? 6 : 0;
		retorno[6] = c7 > 1 ? 7 : 0;
		retorno[7] = c8 > 1 ? 8 : 0;
		retorno[8] = c9 > 1 ? 9 : 0;
		if (c1 == 1 && c2 == 1 && c3 == 1 && c4 == 1 && c5 == 1 && c6 == 1 && c7 == 1 && c8 == 1 && c9 == 1)
			return null;
		else
			return retorno;
	}

	public String comprobar() {
		String retorno = "";
		Integer[] matrizErronea;
		matrizErronea = new Integer[9];
		matrizCargada = new Integer[cantFilas][cantColumnas];
		boolean filasOK = true;
		boolean columnasOK = true;
		boolean cuadrantesOK = true;
		for (int f = 0; f < cantFilas; f++) {
			for (int c = 0; c < cantColumnas; c++) {
				matrizCargada[f][c] = tablero[f][c].getValor();
			}
		}
		// Comprobamos filas
		for (int fila = 0; fila < cantFilas; fila++)
			if (lineaEsCorrectaMarcar(matrizCargada[fila]) != null) {
				matrizErronea = lineaEsCorrectaMarcar(matrizCargada[fila]);
				filasOK = false;
				System.out.println("Fila no valida: " + fila);
				for (int i = 0; i < cantFilas; i++) {
					if (matrizErronea[i] != 0) {
						colocar_x_linea(fila, matrizErronea[i]);
					}
				}
			}
		System.out.println(filasOK ? "Las filas son correctas" : "Las filas estan mal.");
		// Comprobamos columnas
		for (int columna = 0; columna < cantColumnas; columna++) {
			if (lineaEsCorrectaMarcar(creaLineaParaColumnaComprobar(columna)) != null) {
				columnasOK = false;
				matrizErronea = lineaEsCorrectaMarcar(creaLineaParaColumnaComprobar(columna));
				System.out.println("Columna no valida: " + columna);
				for (int i = 0; i < 9; i++) {
					if (matrizErronea[i] != 0) {
						colocar_x_col(columna, matrizErronea[i]);
					}
				}
			}
		}
		System.out.println(columnasOK && filasOK ? "Las columnas son correctas" : "Las columnas estan mal.");
		// Comprobamos cuadrante
		for (int cuadrante = 0; cuadrante < 9; cuadrante++) {
			cuadrantesOK = chequearcuadrante(cuadrante);
		}
		// si todo esta okey entonces el sudoku es correcto.
		if (filasOK && columnasOK && cuadrantesOK) {
			retorno = ("El Sudoku es correcto. Has Ganado!");
		} else
			retorno = ("El Sudoku tiene errores marcados. Sigue intentado!");
		return retorno;
	}

	public boolean chequearcuadrantearchivo(int c) {
		int filaInicio = 0;
		int filaFin = 0;
		int columnaInicio = 0;
		int columnaFin = 0;
		int num;
		boolean noentre = true;
		if (c == 0) {
			filaInicio = 0;
			filaFin = 2;
			columnaInicio = 0;
			columnaFin = 2;
		}
		if (c == 1) {
			filaInicio = 0;
			filaFin = 2;
			columnaInicio = 3;
			columnaFin = 5;
		}
		if (c == 2) {
			filaInicio = 0;
			filaFin = 2;
			columnaInicio = 6;
			columnaFin = 8;
		}
		if (c == 3) {
			filaInicio = 3;
			filaFin = 5;
			columnaInicio = 0;
			columnaFin = 2;
		}
		if (c == 4) {
			filaInicio = 3;
			filaFin = 5;
			columnaInicio = 3;
			columnaFin = 5;
		}
		if (c == 5) {
			filaInicio = 3;
			filaFin = 5;
			columnaInicio = 6;
			columnaFin = 8;
		}
		if (c == 6) {
			filaInicio = 6;
			filaFin = 8;
			columnaInicio = 0;
			columnaFin = 2;
		}
		if (c == 7) {
			filaInicio = 6;
			filaFin = 8;
			columnaInicio = 3;
			columnaFin = 5;
		}
		if (c == 8) {
			filaInicio = 6;
			filaFin = 8;
			columnaInicio = 6;
			columnaFin = 8;
		}
		for (int k = 1; k < 10; k++) {
			num = k;
			int cont = 0;
			for (int i = filaInicio; i <= filaFin; i++) {
				for (int j = columnaInicio; j <= columnaFin; j++) {
					if (matrizArchivo[i][j] == num) {
						cont++;
					}
					if (cont > 1) {
						noentre = false;
					}
				}
			}
		}
		return noentre;
	}

	public boolean chequearcuadrante(int c) {
		int filaInicio = 0;
		int filaFin = 0;
		int columnaInicio = 0;
		int columnaFin = 0;
		int num;
		boolean noentre = true;
		if (c == 0) {
			filaInicio = 0;
			filaFin = 2;
			columnaInicio = 0;
			columnaFin = 2;
		}
		if (c == 1) {
			filaInicio = 0;
			filaFin = 2;
			columnaInicio = 3;
			columnaFin = 5;
		}
		if (c == 2) {
			filaInicio = 0;
			filaFin = 2;
			columnaInicio = 6;
			columnaFin = 8;
		}
		if (c == 3) {
			filaInicio = 3;
			filaFin = 5;
			columnaInicio = 0;
			columnaFin = 2;
		}
		if (c == 4) {
			filaInicio = 3;
			filaFin = 5;
			columnaInicio = 3;
			columnaFin = 5;
		}
		if (c == 5) {
			filaInicio = 3;
			filaFin = 5;
			columnaInicio = 6;
			columnaFin = 8;
		}
		if (c == 6) {
			filaInicio = 6;
			filaFin = 8;
			columnaInicio = 0;
			columnaFin = 2;
		}
		if (c == 7) {
			filaInicio = 6;
			filaFin = 8;
			columnaInicio = 3;
			columnaFin = 5;
		}
		if (c == 8) {
			filaInicio = 6;
			filaFin = 8;
			columnaInicio = 6;
			columnaFin = 8;
		}
		for (int k = 1; k < 10; k++) {
			num = k;
			int cont = 0;
			int posi = 0;
			int posj = 0;
			for (int i = filaInicio; i <= filaFin; i++) {
				for (int j = columnaInicio; j <= columnaFin; j++) {
					if (tablero[i][j].getValor() == num) {
						if (cont == 0) {
							posi = i;
							posj = j;
						}
						cont++;
					}
					if (cont > 1) {
						tablero[posi][posj].setValor(-1);
						tablero[i][j].setValor(-1);
						noentre = false;
					}
				}
			}
		}
		return noentre;
	}

	public void colocar_x_linea(int f, int num) {
		for (int c = 0; c < cantFilas; c++) {
			if (tablero[f][c].getValor() == num) {
				tablero[f][c].setValor(-1);
			}
		}
	}

	public void colocar_x_col(int c, int num) {
		for (int f = 0; f < cantFilas; f++) {
			if (tablero[f][c].getValor() == num) {
				tablero[f][c].setValor(-1);
			}
		}
	}

	public void accionar(Celda c) {
		c.actualizar();
	}

	public Celda getCelda(int i, int j) {
		return tablero[i][j];
	}

	public int getCantFilas() {
		return cantFilas;
	}

	public int getEmpezar() {
		return empezar;
	}
}
