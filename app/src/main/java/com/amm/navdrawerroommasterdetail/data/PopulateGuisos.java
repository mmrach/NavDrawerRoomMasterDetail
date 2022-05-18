package com.amm.navdrawerroommasterdetail.data;

import com.amm.navdrawerroommasterdetail.guisos.Guisos;
import com.amm.navdrawerroommasterdetail.guisos.GuisosDao;
import com.amm.navdrawerroommasterdetail.guisos.TiposGuisos;
import com.amm.navdrawerroommasterdetail.guisos.TiposGuisosDao;

import java.util.List;

public class PopulateGuisos implements Runnable {
    private AppRoomDatabase INSTANCE;

    public PopulateGuisos(AppRoomDatabase instance) {
        INSTANCE = instance;
    }

    @Override
    public void run() {
        //Añadimos los tipos de Guisos básicos al crear la base de datos.
        TiposGuisosDao tiposGuisosDao = INSTANCE.tiposGuisosDao();
        TiposGuisos tipoGuisos = new TiposGuisos(TiposGuisos.GUISOS_DE_CARNE);
        tiposGuisosDao.insert(tipoGuisos);

        tipoGuisos = new TiposGuisos(TiposGuisos.GUISOS_DE_PESCADO);
        tiposGuisosDao.insert(tipoGuisos);

        tipoGuisos = new TiposGuisos(TiposGuisos.GUISOS_DE_VERDURA);
        tiposGuisosDao.insert(tipoGuisos);

//        //---------------------------------------------------------
        //Obtenemos los ids de cada tipo
        List<TiposGuisos> listaGuisos = tiposGuisosDao.getTipoGuisos(TiposGuisos.GUISOS_DE_CARNE);
        Integer deCarne = listaGuisos.get(0).getId();
        listaGuisos = tiposGuisosDao.getTipoGuisos(TiposGuisos.GUISOS_DE_PESCADO);
        Integer dePescado = listaGuisos.get(0).getId();
        listaGuisos = tiposGuisosDao.getTipoGuisos(TiposGuisos.GUISOS_DE_VERDURA);
        Integer deVerdura = listaGuisos.get(0).getId();

        //Añadimos algunos Guisos al crear la base de datos
        GuisosDao guisosDao = INSTANCE.guisosDao();
        guisosDao.insert(new Guisos("Caldereta de cordero con patatas",deCarne));
        guisosDao.insert(new Guisos("Caldereta de cordero con tomate",deCarne));
        guisosDao.insert(new Guisos("Cazuela de costilla con cardo",deCarne));
        guisosDao.insert(new Guisos("Ossobuco con puré de boniato",deCarne));
        guisosDao.insert(new Guisos("Guisote de ternera con patatas",deCarne));
        guisosDao.insert(new Guisos("Guiso de cordero marroquí",deCarne));
        guisosDao.insert(new Guisos("Guiso de cordero con hongos",deCarne));
        guisosDao.insert(new Guisos("Guiso de vaca a la cerveza negra",deCarne));
        guisosDao.insert(new Guisos("Guiso de cerdo con almejas",deCarne));
        guisosDao.insert(new Guisos("Conejo guisado con ciruelas",deCarne));
        guisosDao.insert(new Guisos("Conejo al curry con arroz frito",deCarne));
        guisosDao.insert(new Guisos("Conejo en salsa con frutas pasas",deCarne));

        //De Pescado
        guisosDao.insert(new Guisos("Caldereta de sepia",dePescado));
        guisosDao.insert(new Guisos("Merluza con salsa de piquillos",dePescado));
        guisosDao.insert(new Guisos("Calamares en salsa verde",dePescado));
        guisosDao.insert(new Guisos("Fideos caldosos con salmón",dePescado));
        guisosDao.insert(new Guisos("Merluza en salsa de langostinos",dePescado));
        guisosDao.insert(new Guisos("Marmitako de salmón",dePescado));
        guisosDao.insert(new Guisos("Merluza con patatas en salsa verde",dePescado));
        guisosDao.insert(new Guisos("Caldereta de lubina y dorada",dePescado));

        //De Verduras
        guisosDao.insert(new Guisos("Crema de calabacín",deVerdura));
        guisosDao.insert(new Guisos("Crema de zanahoria ",deVerdura));
        guisosDao.insert(new Guisos("Crema de remolacha y yogur",deVerdura));
        guisosDao.insert(new Guisos("Crema de coliflor con huevo",deVerdura));
        guisosDao.insert(new Guisos("Crema de setas",deVerdura));
        guisosDao.insert(new Guisos("Consomé de verduras",deVerdura));
        guisosDao.insert(new Guisos("Caldo de verduras",deVerdura));
        guisosDao.insert(new Guisos("Potaje tradicional de lentejas",deVerdura));
        guisosDao.insert(new Guisos("Guiso de espinacas con garbanzos",deVerdura));

        //---------------------------------------------------------

    }
}
