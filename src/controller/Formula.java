package controller;

import java.util.concurrent.Semaphore;

public class Formula extends Thread {
	private int Car;
	private int Escude;
	private int tempoVolta;

	static int indice;

	private Semaphore Pista;
	private Semaphore Table;
	private Semaphore Escuderia;

	public Formula (int Car, int Escude, Semaphore pista, Semaphore tabela, Semaphore Escuderia) {
		this.Escuderia = Escuderia;
		this.Car = Car;
		this.Escude = Escude;
		this.Pista = pista;
		this.Table = tabela;
	}

	@Override
	public void run() {
		try {
			Pista.acquire();
			Escuderia.acquire();
			Run();
			Table.acquire();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			Pista.release();
			Escuderia.release();
			Table.release();
		}
	}

	private void Run() {
		double tempI;
		double tempF;
		double tempT;
		int distanciaPista = 1000;
		int distanciaT = 0;
		int gerador;

		for (int numero_voltas = 1; numero_voltas <= 3; numero_voltas++) {
			tempI = System.nanoTime();
			while (distanciaT < distanciaPista) {
				gerador = (int) ((Math.random() * 101) + 100);
				distanciaT += gerador;
			}
			tempF = System.nanoTime();
			tempT = tempF - tempI;
			tempT = tempT / Math.pow(10, 9);
			System.out.println("O carro #" + Car + " da Escuderia " + Escude + " fez a " + numero_voltas
					+ "º volta em " + tempT);

			if (tempT < tempoVolta) {
				tempoVolta += (int) tempT;

			}

			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
