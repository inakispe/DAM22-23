package ComederoCuidador;

public class Cuidador implements Runnable {

    private Comedero comedero;
    
    public Cuidador(Comedero comedero) {
        this.comedero = comedero;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (comedero) {
                // Espera si el comdero esta vacio
                while (comedero.estaLleno()) {
                    try {
                        System.out.println("El comedero est� lleno, el cuidador est� esperando...");
                        comedero.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Rellena el comedero
                comedero.llenar();
                System.out.println("El cuidador est� llenando el comedero...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Sale
                comedero.salir();
                comedero.notifyAll();
            }
        }
    }

}