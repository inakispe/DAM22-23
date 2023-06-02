package Rave;


public class BanoA {
	public static void main(String[] args) {
		
		Mensaje msg = new Mensaje("process it");
		
		Esperando waiter2= new Esperando(msg);
		
		new Thread (waiter2,"waiter2").start();
		
		Esperando waiter = new Esperando(msg);
		
		new Thread(waiter,"waiter").start();
		
		Esperando waiter1 = new Esperando(msg);
		
		new Thread(waiter1, "waiter1").start();
		
		Notificacion notifier = new Notificacion(msg);
		
		new Thread(notifier, "notifier").start();
		
		System.out.println("All the threads are started");
		}

}
