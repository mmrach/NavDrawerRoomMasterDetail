package com.amm.navdrawerroommasterdetail.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.amm.navdrawerroommasterdetail.guisos.Guisos;
import com.amm.navdrawerroommasterdetail.guisos.GuisosDao;
import com.amm.navdrawerroommasterdetail.guisos.TiposGuisos;
import com.amm.navdrawerroommasterdetail.guisos.TiposGuisosDao;
import com.amm.navdrawerroommasterdetail.ingrediente.Ingrediente;
import com.amm.navdrawerroommasterdetail.ingrediente.IngredienteDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Ingrediente.class, TiposGuisos.class, Guisos.class}, version = 1, exportSchema = false)
abstract public class AppRoomDatabase extends RoomDatabase {
    public abstract IngredienteDao ingredienteDao();
    public abstract TiposGuisosDao tiposGuisosDao();
    public abstract GuisosDao guisosDao();

    private static volatile AppRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "ingredientes")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(new PopulateIngredientes(INSTANCE));
            databaseWriteExecutor.execute(new PopulateGuisos(INSTANCE));

        }


    };
}
