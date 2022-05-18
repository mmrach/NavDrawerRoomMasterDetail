package com.amm.navdrawerroommasterdetail.data;

import com.amm.navdrawerroommasterdetail.ingrediente.Ingrediente;
import com.amm.navdrawerroommasterdetail.ingrediente.IngredienteDao;

public class PopulateIngredientes implements Runnable {
    private AppRoomDatabase INSTANCE;

    public PopulateIngredientes(AppRoomDatabase instance) {
        INSTANCE = instance;
    }

    @Override
    public void run() {
        // Populate the database in the background.
        // If you want to start with more words, just add them.
        IngredienteDao ingredienteDao = INSTANCE.ingredienteDao();
        //dao.deleteAll();
        //Añadimos ingredientes por defecto al crear la base de datos.
        Ingrediente ingrediente = new Ingrediente("Leche");
        ingredienteDao.insert(ingrediente);

        ingrediente = new Ingrediente("Canela");
        ingredienteDao.insert(ingrediente);

        ingrediente = new Ingrediente("Harina");
        ingredienteDao.insert(ingrediente);


    }
}
