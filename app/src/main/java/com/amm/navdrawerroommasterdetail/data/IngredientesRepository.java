package com.amm.navdrawerroommasterdetail.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.amm.navdrawerroommasterdetail.guisos.Guisos;
import com.amm.navdrawerroommasterdetail.guisos.GuisosDao;
import com.amm.navdrawerroommasterdetail.guisos.TiposGuisos;
import com.amm.navdrawerroommasterdetail.guisos.TiposGuisosDao;
import com.amm.navdrawerroommasterdetail.ingrediente.Ingrediente;
import com.amm.navdrawerroommasterdetail.ingrediente.IngredienteDao;

import java.util.List;

public class IngredientesRepository {
    //Ingredientes Specifics --------------------------------------
    private IngredienteDao ingredienteDao;

    //Constructor
    public IngredientesRepository(Application application) {
        //DB Creation
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);

        //Obteniendo los DAO
        ingredienteDao= db.ingredienteDao();
    }

    public List<Ingrediente> getAllIngredientes() {
        return ingredienteDao.getIngredientes();
    }

    public void insertIngrediente(Ingrediente ingrediente) {
        ingredienteDao.insert(ingrediente);
    }

    public void insertIngredientes(String[] ingredientes) {
        for (int i = 0; i < ingredientes.length; i++) {
            ingredienteDao.insert(new Ingrediente(ingredientes[i]));
        }
    }

    public void deleteIngrediente(String strIngrediente){
        ingredienteDao.deleteIngrediente(strIngrediente);
    }

    public void deleteAllIngredientes(){
        ingredienteDao.deleteAll();
    }

    public void forceDBCreation(){
        ingredienteDao.dummyDelete();
    }
}
