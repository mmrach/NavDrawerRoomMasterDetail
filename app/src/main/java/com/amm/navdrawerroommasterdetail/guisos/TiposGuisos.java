package com.amm.navdrawerroommasterdetail.guisos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "tiposguisos",
        indices = {@Index(value = {"tipo"}, unique = true)}
       )
public class TiposGuisos implements Comparable<TiposGuisos>{

    public final static String GUISOS_DE_CARNE = "Guisos de Carne";
    public final static String GUISOS_DE_PESCADO = "Guisos de Pescado";
    public final static String GUISOS_DE_VERDURA = "Guisos de Verdura";
    public final static Integer ID_GUISOS_DE_CARNE = 1;
    public final static Integer ID_GUISOS_DE_PESCADO = 2;
    public final static Integer ID_GUISOS_DE_VERDURA = 3;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "tipo")
    @NonNull
    private String tipo;

    public TiposGuisos(@NonNull String tipo) {
        this.tipo = tipo;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public String getTipo() {
        return tipo;
    }

    public void setTipo(@NonNull String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int compareTo(TiposGuisos tiposGuisos) {
        return this.id.compareTo(tiposGuisos.getId());
    }

    public static DiffUtil.ItemCallback<TiposGuisos> tiposGuisosItemCallback = new DiffUtil.ItemCallback<TiposGuisos>() {
        @Override
        public boolean areItemsTheSame(@NonNull TiposGuisos oldItem, @NonNull TiposGuisos newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull TiposGuisos oldItem, @NonNull TiposGuisos newItem) {
            return ( oldItem.getId().equals(newItem.getId()) &&
                     oldItem.getTipo().equals(newItem.getTipo())
            );
        }
    };

}
