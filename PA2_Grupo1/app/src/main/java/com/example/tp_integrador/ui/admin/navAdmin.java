package com.example.tp_integrador.ui.admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tp_integrador.R;
import com.google.android.material.navigation.NavigationView;

import static androidx.navigation.Navigation.findNavController;

public class navAdmin extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration_adm;
    private TextView lblUsuario=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_admin);
        Toolbar toolbar = findViewById(R.id.toolbar_adm);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_adm);
        NavigationView navigationView = findViewById(R.id.nav_view_adm);
        //recupero header navadmin
        View headerLayout = navigationView.getHeaderView(0);
        //cargo lblusuario
        TextView lblUsuario= headerLayout.findViewById(R.id.LblUsuario);
        //datos de session
        SharedPreferences prefs = getSharedPreferences("login_data", Context.MODE_PRIVATE);
        String username = prefs.getString("username", "");
        String key = prefs.getString("key", "");
        lblUsuario.setText(username);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration_adm = new AppBarConfiguration.Builder(
                R.id.nav_usuarios, R.id.nav_usuarios_mod, R.id.nav_consignas, R.id.nav_consignas_alta, R.id.nav_opciones, R.id.nav_opciones_alta, R.id.nav_opciones_mod, R.id.nav_opciones_list)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = findNavController(this,R.id.nav_host_fragment_adm);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration_adm);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = findNavController(this, R.id.nav_host_fragment_adm);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration_adm)
                || super.onSupportNavigateUp();
    }
}