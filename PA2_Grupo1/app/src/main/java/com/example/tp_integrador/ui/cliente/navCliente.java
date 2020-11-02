package com.example.tp_integrador.ui.cliente;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tp_integrador.R;
import com.google.android.material.navigation.NavigationView;

import static androidx.navigation.Navigation.findNavController;

public class navCliente extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView lblUsuario=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation_cliente);

        Toolbar toolbar = findViewById(R.id.toolbar_cli);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_cli);
        NavigationView navigationView = findViewById(R.id.nav_view_cli);
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
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_listadosenas, R.id.nav_ca, R.id.nav_mapa,R.id.nav_perfil)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = findNavController(this, R.id.nav_host_fragment_cli);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
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
        NavController navController = findNavController(this, R.id.nav_host_fragment_cli);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}