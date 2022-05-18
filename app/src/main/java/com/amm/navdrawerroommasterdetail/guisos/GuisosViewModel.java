package com.amm.navdrawerroommasterdetail.guisos;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.amm.navdrawerroommasterdetail.data.GuisosRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuisosViewModel extends AndroidViewModel {

    private GuisosRepository guisosRepository;

    public MutableLiveData<List<Guisos>> ldGuisos = new MutableLiveData<>();

    private static final ExecutorService repositoryExecutor = Executors.newFixedThreadPool(2);

    private Application theApplication;

    public GuisosViewModel(Application application) {
        super(application);
        theApplication = application;

        guisosRepository = new GuisosRepository(application);

        repositoryExecutor.execute( () -> {
            ldGuisos.postValue(guisosRepository.getAllGuisos());
            //ldGuisos.postValue(guisosRepository.queryGuisosByType(TiposGuisos.ID_GUISOS_DE_VERDURA));
            List<Integer> tipos = Arrays.asList(TiposGuisos.ID_GUISOS_DE_VERDURA,TiposGuisos.ID_GUISOS_DE_PESCADO);
            //ldGuisos.postValue(guisosRepository.queryGuisosByType(tipos));
        });
    }

    public MutableLiveData<List<Guisos>> getGuisos(){
        return ldGuisos;
    }

    public void queryGuisosByType(Integer idGuiso){
        repositoryExecutor.execute( () -> {
            ldGuisos.postValue(guisosRepository.queryGuisosByType(idGuiso));
        });
    }

}
