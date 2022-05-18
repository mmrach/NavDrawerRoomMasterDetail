package com.amm.navdrawerroommasterdetail.guisos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "guisos", foreignKeys = {
        @ForeignKey(entity = TiposGuisos.class,
                parentColumns = "id",
                childColumns = "id_tipoguisos",
                onDelete = ForeignKey.CASCADE),
        })
public class Guisos implements Comparable<Guisos>{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "nombre")
    @NonNull
    private String nombre;

    @ColumnInfo(name = "id_tipoguisos")
    @NonNull
    private Integer id_tipoguisos;

    public Guisos(@NonNull String nombre, Integer id_tipoguisos) {
        this.nombre = nombre;
        this.id_tipoguisos = id_tipoguisos;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public Integer getId_tipoguisos() {
        return id_tipoguisos;
    }

    public void setId_tipoguisos(@NonNull Integer id_tipoguisos) {
        this.id_tipoguisos = id_tipoguisos;
    }

    @Override
    public int compareTo(Guisos guisos) {
        return this.id.compareTo(guisos.getId());
    }

    public static DiffUtil.ItemCallback<Guisos> guisosItemCallback = new DiffUtil.ItemCallback<Guisos>() {
        @Override
        public boolean areItemsTheSame(@NonNull Guisos oldItem, @NonNull Guisos newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Guisos oldItem, @NonNull Guisos newItem) {
            return ( oldItem.getId().equals(newItem.getId()) &&
                    oldItem.getNombre().equals(newItem.getNombre())
            );
        }
    };

}
