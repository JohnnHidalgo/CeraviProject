package com.example.johnnhidalgo.ceraviproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Arcilla extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcilla);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.arcilla, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.estadisticas) {
            Intent h= new Intent(Arcilla.this,Estadisticas.class);
            startActivity(h);
        } else if (id == R.id.arcilla) {
            Intent h= new Intent(Arcilla.this,Arcilla.class);
            startActivity(h);
        } else if (id == R.id.moldeado) {
            Intent h= new Intent(Arcilla.this,Moldeado.class);
            startActivity(h);
        } else if (id == R.id.cargue) {
            Intent h= new Intent(Arcilla.this,Cargue.class);
            startActivity(h);
        } else if (id == R.id.quemado) {
            Intent h= new Intent(Arcilla.this,Quemado.class);
            startActivity(h);
        } else if (id == R.id.descargue) {
            Intent h= new Intent(Arcilla.this,Descargue.class);
            startActivity(h);
        }else if (id == R.id.view_cliente) {
            Intent h= new Intent(Arcilla.this,MenuClienteListActivity.class);
            startActivity(h);
        }else if (id == R.id.view_obreros) {
            Intent h = new Intent(Arcilla.this,MenuObreroListActivity.class);
            startActivity(h);
        }else if (id == R.id.view_tArcilla) {
            Intent h = new Intent(Arcilla.this,MenuTransportistaDeArcillaListActivity.class);
            startActivity(h);
        }
        /*else if (id == R.id.view_obreros) {
            Intent h = new Intent(Arcilla.this,ObrerosActivity.class);
            startActivity(h);
        } else if (id == R.id.view_tArcilla) {
            Intent h = new Intent(Arcilla.this,Transportistas_de_ArcillaActivity.class);
            startActivity(h);
        } else if (id == R.id.view_tLadrillo) {
            Intent h = new Intent(Arcilla.this,Transportistas_de_LadrilloActivity.class);
            startActivity(h);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
