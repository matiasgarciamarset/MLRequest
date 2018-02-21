package com.matias.service.util;

public class AnalizadorConsecutivos {
	
	private Boolean poseeConsecutivos;
	private int cantConsecutivos;
	private Acumulado[][] matrizAcumuladora;
	
	public AnalizadorConsecutivos(String[] dna, int cantConsecutivos){
		this.cantConsecutivos = cantConsecutivos;
		this.poseeConsecutivos = false;
		checkConsecutivos(dna);
	}
	
	private void checkConsecutivos(String[] dna) {
		int n = dna.length;
		if (n < cantConsecutivos) {
			return;
		}
		matrizAcumuladora = new Acumulado[n][n];
		//Recorro todas las celdas de izq a der y arriba para abajo
		for (int i = 0; i < n; i++) {
			//Chequeo matriz cuadrada
			if (dna[i].length() != n) {
				return;
			}
			for (int j = 0; j < n; j++) {
				// caso letra nueva
				if (matrizAcumuladora[i][j] == null) {
					matrizAcumuladora[i][j] = new Acumulado();
				}
				//caso reviso vecinos
				if ( acumularDerecha(dna, i, j) || //Miro a la derecha
				acumularAbajoDerecha(dna, i, j) || //Miro diagonal, abajo a la derecha
				acumularAbajo(dna, i, j) || //Miro abajo
				acumularAbajoIzquierda(dna, i, j)) { //Miro diagonal, abajo a la izquierda
					poseeConsecutivos = true;
					return;
				}
			}
		}
	}
	
	private Boolean acumularDerecha(String[] dna, int i, int j) {
		if (fueraRango(dna.length,i,j+1) || dna[i].charAt(j) != dna[i].charAt(j+1)) {
			return false;
		}
		int nuevoAcumulado = matrizAcumuladora[i][j].cantIzquierda + 1;
		if (matrizAcumuladora[i][j+1] == null) {
			matrizAcumuladora[i][j+1] = new Acumulado(0, 0, nuevoAcumulado, 0);
		} else {
			matrizAcumuladora[i][j+1].cantIzquierda = nuevoAcumulado;
		}
		if (matrizAcumuladora[i][j+1].cantIzquierda >= cantConsecutivos) {
			return true;
		}
		return false;
	}
	
	private Boolean acumularAbajoDerecha(String[] dna, int i, int j) {
		if (fueraRango(dna.length,i+1,j+1) || dna[i].charAt(j) != dna[i+1].charAt(j+1)) {
			return false;
		}
		int nuevoAcumulado = matrizAcumuladora[i][j].cantArribaIzquierda + 1;
		if (matrizAcumuladora[i+1][j+1] == null) {
			matrizAcumuladora[i+1][j+1] = new Acumulado(0, nuevoAcumulado, 0, 0);
		} else {
			matrizAcumuladora[i+1][j+1].cantArribaIzquierda = nuevoAcumulado;
		}
		if (matrizAcumuladora[i+1][j+1].cantArribaIzquierda >= cantConsecutivos) {
			return true;
		}
		return false;
	}
	
	private Boolean acumularAbajo(String[] dna, int i, int j) {
		if (fueraRango(dna.length,i+1,j) || dna[i].charAt(j) != dna[i+1].charAt(j)) {
			return false;
		}
		int nuevoAcumulado = matrizAcumuladora[i][j].cantArriba + 1;
		if (matrizAcumuladora[i+1][j] == null) {
			matrizAcumuladora[i+1][j] = new Acumulado(nuevoAcumulado, 0, 0, 0);
		} else {
			matrizAcumuladora[i+1][j].cantArriba = nuevoAcumulado;
		}
		if (matrizAcumuladora[i+1][j].cantArriba >= cantConsecutivos) {
			return true;
		}
		return false;
	}
	
	private Boolean acumularAbajoIzquierda(String[] dna, int i, int j) {
		if (fueraRango(dna.length,i+1,j-1) || dna[i].charAt(j) != dna[i+1].charAt(j-1)) {
			return false;
		}
		int nuevoAcumulado = matrizAcumuladora[i][j].cantArribaDerecha + 1;
		if (matrizAcumuladora[i+1][j-1] == null) {
			matrizAcumuladora[i+1][j-1] = new Acumulado(0, 0, 0, nuevoAcumulado);
		} else {
			matrizAcumuladora[i+1][j-1].cantArribaDerecha = nuevoAcumulado;
		}
		if (matrizAcumuladora[i+1][j-1].cantArribaDerecha >= cantConsecutivos) {
			return true;
		}
		return false;
	}

	private boolean fueraRango(int n, int i, int j) {
		return i >= n || j >= n || i < 0 || j < 0;
	}

	public Boolean poseeConsecutivos() {
		return poseeConsecutivos;
	}

	class Acumulado {

		public int cantArriba;
		public int cantArribaIzquierda;
		public int cantIzquierda;
		public int cantArribaDerecha;
		
		public Acumulado() {
			cantArriba = 1;
			cantArribaIzquierda = 1;
			cantIzquierda = 1;
			cantArribaDerecha = 1;
		}
		
		public Acumulado(int cantArriba, int cantArribaIzquierda, int cantIzquierda, int cantArribaDerecha) {
			this.cantArriba = cantArriba;
			this.cantArribaIzquierda = cantArribaIzquierda;
			this.cantIzquierda = cantIzquierda;
			this.cantArribaDerecha = cantArribaDerecha;
		}
	}

}
