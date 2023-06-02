package com.example.n_primos_mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Es como una base de datos que separa la logica de la interfaz de usuario
public class PrimoViewModel extends ViewModel {

    private PrimoModel primoModel;

    private MutableLiveData<PrimoModel> dato;

    //El executor service es como un hilo que hace que se cargen los numeros en segundo plano
    private ExecutorService executor;

    //Aqui se construyen los dos objetos
    public PrimoViewModel(){
        primoModel = new PrimoModel();
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<PrimoModel> getDato(){
        if (dato==null){
            dato = new MutableLiveData<>();
            dato.setValue(primoModel);
        }
        return dato;
    }

    public void generaNPrimos(int rango1, int rango2){
        //Aqui el obejto executor ejecuta el programa en segundo pla
        executor.execute(()->{
            primoModel.generarNPrimos(rango1, rango2);
            dato.postValue(primoModel);
        });
    }
}