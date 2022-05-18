package com.amm.navdrawerroommasterdetail.data;

import android.app.Application;

import com.amm.navdrawerroommasterdetail.guisos.Guisos;
import com.amm.navdrawerroommasterdetail.guisos.GuisosDao;
import com.amm.navdrawerroommasterdetail.guisos.TiposGuisosDao;

import java.util.List;

public class GuisosRepository {

    private GuisosDao guisosDao;
    private TiposGuisosDao tiposGuisosDao;

    public GuisosRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);

        //Obteniendo los DAO
        guisosDao= db.guisosDao();
        tiposGuisosDao = db.tiposGuisosDao();
    }

    public List<Guisos> getAllGuisos(){
        List<Guisos> guisos = guisosDao.getGuisos();
        return guisos;
    }

    public List<Guisos> queryGuisosByType(Integer tipoGuiso){
        List<Guisos> guisos = guisosDao.getGuisosByType(tipoGuiso);
        return guisos;
    }

    public List<Guisos> queryGuisosByType(List<Integer> tipos){
        List<Guisos> guisos = guisosDao.getGuisosByType(tipos);
        return guisos;
    }


}
