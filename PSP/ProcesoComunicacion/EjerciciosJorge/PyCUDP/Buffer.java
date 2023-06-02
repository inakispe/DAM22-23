package PyCUDP;

import java.lang.System.Logger;

public class Buffer {
	private String[] buffer;
	private char[]prueba;
	private int siguiente=0;
	private boolean estaVacío;
	private boolean estaLleno;

	// Creamos un constructor
	public Buffer(int tamanio) {
		super();
		this.buffer = new String[tamanio];
		
		//this.prueba= new char[tamanio];
		this.siguiente = siguiente;
		this.estaVacío = estaVacío;
		this.estaLleno = estaLleno;
	}
	//Cuando vamos a consumir el array de Strings
	public synchronized String consumir() {
		while (this.estaVacío) {
			try {
				wait();
			}catch (Exception e) {
				
			}
			siguiente--;
			//Al consumir ya no esta lleno por lo que
			this.estaLleno=false;
			if (siguiente==0) {
				this.estaVacío=true;
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
			//Al producir ya no esta vacío por lo que esta vacío es false
			this.estaVacío = false;
			if (this.siguiente == buffer.length) {
				this.estaLleno = true;
			}
			notifyAll();
		}

	}

