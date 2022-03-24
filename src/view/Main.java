package view;

import java.util.concurrent.Semaphore;

import controller.Formula;

public class Main {


	public static void main(String[] args) {
		int permissaoPista = 5;
		int permissaoTabela = 1;

		Semaphore Pista = new Semaphore(permissaoPista);
		Semaphore[] Escuderia = new Semaphore[8];
		Semaphore Tabela = new Semaphore(permissaoTabela);

		for (int Escude = 1; Escude <= 7; Escude++) {
			Escuderia[Escude] = new Semaphore(1);
			for (int Car = 1; Car < 3; Car++) {
				Thread treino = new Formula (Car, Escude, Pista, Tabela, Escuderia[Escude]);
				treino.start();
			}
		}

	}

}