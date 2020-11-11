package com.example.tp_integrador.ui.admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.UsuarioDao;
import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.actLogin;
import com.example.tp_integrador.ui.actRegistrarse;
import com.google.android.material.navigation.NavigationView;

import static androidx.navigation.Navigation.findNavController;

public class navAdmin extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration_adm;

    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }

    public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    private FragmentRefreshListener fragmentRefreshListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_admin);
        Toolbar toolbar = findViewById(R.id.toolbar_adm);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_adm);
        NavigationView navigationView = findViewById(R.id.nav_view_adm);
        try {
        //recupero header navadmin
        View headerLayout = navigationView.getHeaderView(0);

        //Recupero los TextView
        TextView lblUsuario= headerLayout.findViewById(R.id.LblUsuario);
        TextView lblEmail= headerLayout.findViewById(R.id.LblEmailUsuario);

        //datos de session
        SharedPreferences prefs = getSharedPreferences("login_data", Context.MODE_PRIVATE);
        String username = prefs.getString("username", "");
        String key = prefs.getString("key", "");

        //si username esta vacio redirecciona al login
        if(username.isEmpty()){
            Intent Sig=new Intent(this, actLogin.class);
            startActivity(Sig);
        }

        //Cargo los TextView
            Usuario User = new Usuario();
            User.setNameUser(username);
            UsuarioDao x = new UsuarioDao(this,User,3);
            String[] split = x.execute().get().split(";");
            lblUsuario.setText(username);
            lblEmail.setText(split[2]);
        }catch (Exception e){
            Toast.makeText(this,"ERROR: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration_adm = new AppBarConfiguration.Builder(
                R.id.nav_usuarios, R.id.nav_usuarios_mod, R.id.nav_usuarios_list,
                R.id.nav_consignas, R.id.nav_consignas_alta,
                R.id.nav_opciones, R.id.nav_opciones_alta, R.id.nav_opciones_mod, R.id.nav_opciones_list,
                R.id.nav_orden, R.id.nav_orden_alta, R.id.nav_orden_mod, R.id.nav_orden_list)
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

    public void RedirecLogin(MenuItem v){
        finish();
        Intent Reg=new Intent(this, actLogin.class);
        startActivity(Reg);
    }

    //Actualiza los fragments
    public interface FragmentRefreshListener{
        void onRefresh();
    }

    public void Actualizar(){
        getFragmentRefreshListener().onRefresh();
    }
}