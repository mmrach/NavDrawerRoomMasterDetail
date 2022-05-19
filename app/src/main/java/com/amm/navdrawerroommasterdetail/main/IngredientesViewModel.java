package com.amm.navdrawerroommasterdetail.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.amm.navdrawerroommasterdetail.guisos.TiposGuisos;
import com.amm.navdrawerroommasterdetail.ingrediente.Ingrediente;
import com.amm.navdrawerroommasterdetail.data.IngredientesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IngredientesViewModel extends AndroidViewModel {

    private IngredientesRepository ingredientesRepository;

    private static final ExecutorService repositoryExecutor = Executors.newFixedThreadPool(1);

    public MutableLiveData<List<Ingrediente>> ldIngredientes;

    public IngredientesViewModel(Application application) {
        super(application);
        ingredientesRepository = new IngredientesRepository(application);
        ldIngredientes = new MutableLiveData<>();
        repositoryExecutor.execute( () -> {
            ldIngredientes.postValue(ingredientesRepository.getAllIngredientes());
        });
    }

    public void initList(String[] arrayIngredientes) {
        repositoryExecutor.execute( () -> {
            ingredientesRepository.deleteAllIngredientes();
            ingredientesRepository.insertIngredientes(arrayIngredientes);
            ldIngredientes.postValue(ingredientesRepository.getAllIngredientes());
        });
    }

    public void foceDBCreation() {
        repositoryExecutor.execute( () -> {
            ingredientesRepository.forceDBCreation();
        });
    }

    public void deleteIngrediente(int position) {
        String strIngrediente = ldIngredientes.getValue().get(position).getStrIngrediente();
        repositoryExecutor.execute( () -> {
            ingredientesRepository.deleteIngrediente(strIngrediente);
            ldIngredientes.postValue(ingredientesRepository.getAllIngredientes());
        });
    }

    public void addIngrediente(Ingrediente ingrediente) {
        repositoryExecutor.execute( () -> {
            ingredientesRepository.insertIngrediente(ingrediente);
            ldIngredientes.postValue(ingredientesRepository.getAllIngredientes());
        });
    }
    public boolean findIngredienteByName(String ingredienteName) {
        //NO necesitamos acceder a la base de datos,
        //se supone que la lista ldIngredientes esta actualizada
        boolean retVal=false;
        if (ldIngredientes.getValue() != null) {
            for (Ingrediente ingrediente : ldIngredientes.getValue()) {
                if (ingrediente.toString().equals(ingredienteName)){
                    retVal = true;
                    break;
                }
            }
        }
        else {
            retVal=false;
        }
        return retVal;
    }
    //-------------------------------


}
