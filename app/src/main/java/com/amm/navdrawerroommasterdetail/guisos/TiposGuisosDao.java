package com.amm.navdrawerroommasterdetail.guisos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface TiposGuisosDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TiposGuisos tiposGuisos);

    @Query("SELECT id FROM tiposguisos WHERE tipo = :strTipoGuiso")
    LiveData<List<Integer>> getTipoId(String strTipoGuiso);

    @Query("SELECT id FROM tiposguisos")
    LiveData<List<Integer>> getIdsTiposGuisos();

    @Query("SELECT * FROM tiposguisos")
    LiveData<List<TiposGuisos>> getTiposGuisos();

    @Query("SELECT * FROM tiposguisos WHERE tipo = :strTipoGuiso")
    List<TiposGuisos> getTipoGuisos(String strTipoGuiso);

}
