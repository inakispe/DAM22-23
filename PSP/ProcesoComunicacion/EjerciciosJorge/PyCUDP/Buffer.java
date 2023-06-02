package PyCUDP;

import java.lang.System.Logger;

public class Buffer {
	private String[] buffer;
	private char[]prueba;
	private int siguiente=0;
	private boolean estaVac�o;
	private boolean estaLleno;

	// Creamos un constructor
	public Buffer(int tamanio) {
		super();
		this.buffer = new String[tamanio];
		
		//this.prueba= new char[tamanio];
		this.siguiente = siguiente;
		this.estaVac�o = estaVac�o;
		this.estaLleno = estaLleno;
	}
	//Cuando vamos a consumir el array de Strings
	public synchronized String consumir() {
		while (this.estaVac�o) {
			try {
				wait();
			}catch (Exception e) {
				
			}
			siguiente--;
			//Al consumir ya no esta lleno por lo que
			this.estaLleno=false;
			if (siguiente==0) {
				this.estaVac�o=true;
			}
			notifyAll();
		}
		return this.buffer[this.siguiente];
		//return this.buffer[this.siguiente];
	}

	// Cuando a rellenar el array de String
	public synchronized void producir(String palabra) {
		while (this.estaLleno) {
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
			buffer[siguiente] = palabra;
		}
			siguiente++;
			//Al producir ya no esta vac�o por lo que esta vac�o es false
			this.estaVac�o = false;
			if (this.siguiente == buffer.length) {
				this.estaLleno = true;
			}
			notifyAll();
		}

	}

