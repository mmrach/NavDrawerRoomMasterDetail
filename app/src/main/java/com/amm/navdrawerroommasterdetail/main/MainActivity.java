package com.amm.navdrawerroommasterdetail.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.amm.navdrawerroommasterdetail.data.IngredientesRepository;
import com.amm.navdrawerroommasterdetail.guisos.TiposGuisos;
import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

import navdrawerrecyclerviewroommasterdetail.R;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity:" ;
    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;

    private SharedViewModel sharedViewModel;
    private IngredientesViewModel ingredientesViewModel;

    private static final String ARG_TIPO_GUISO = "ARG_TIPO_GUISO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavHostFragment host = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        NavController navController = host.getNavController();

        setupActionBar(navController, appBarConfiguration);
        setupNavigationMenu(navController);

        forceDatabaseCreation();

        setupSharedViewModel();
        setupIngredientesViewModel();

        //para quitar la capa de color negro que evita que se vean los colores en el menu
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String itemTitle = item.getTitle().toString();
                Bundle args = new Bundle();

                navController.popBackStack(R.id.wellcome_dest,false);
                if (itemTitle.equals("Guisos de Carne")){
                    args.putInt(ARG_TIPO_GUISO,TiposGuisos.ID_GUISOS_DE_CARNE);
                    navController.navigate(item.getItemId(),args);
                }
                else if (itemTitle.equals("Guisos de Pescado")){
                    args.putInt(ARG_TIPO_GUISO,TiposGuisos.ID_GUISOS_DE_PESCADO);
                    navController.navigate(item.getItemId(),args);

                }
                else if (itemTitle.equals("Guisos de Verduras")){
                    args.putInt(ARG_TIPO_GUISO,TiposGuisos.ID_GUISOS_DE_VERDURA);
                    navController.navigate(item.getItemId(),args);

                }
                else{
                    navController.navigate(item.getItemId());
                }
                drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void setupSharedViewModel(){
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
    }

    private void setupIngredientesViewModel(){
        ingredientesViewModel = new ViewModelProvider(this).get(IngredientesViewModel.class);
    }

    private void forceDatabaseCreation(){
        IngredientesRepository ingredientesRepository = new IngredientesRepository(getApplication());
        ingredientesRepository.forceDBCreation();
    }

    private void setupNavigationMenu(NavController navController) {
        //TODO STEP 10 - Use NavigationUI to set up a Navigation View
        // In split screen mode, you can drag this view out from the left
        // This does NOT modify the actionbar
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null){
            NavigationUI.setupWithNavController(navigationView,navController);
        }
        //END STEP 10
    }

    private void setupActionBar(NavController navController, AppBarConfiguration appBarConfig) {
        drawerLayout = findViewById(R.id.drawer_layout);

        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.wellcome_dest);
        topLevelDestinations.add(R.id.guisos_dest);
        topLevelDestinations.add(R.id.guisos_de_dest);
        topLevelDestinations.add(R.id.guisos_de_dest);
        topLevelDestinations.add(R.id.guisos_de_dest);

        if (null != drawerLayout) {
            appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations)
                    .setOpenableLayout(drawerLayout)
                    .build();
        }
        else {
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        }

        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        if (NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.nav_host_fragment)))
            return true;
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Allows NavigationUI to support proper up navigation or the drawer layout
        // drawer menu, depending on the situation
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), appBarConfiguration);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }


}