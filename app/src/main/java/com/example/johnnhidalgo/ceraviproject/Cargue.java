package com.example.johnnhidalgo.ceraviproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class  Cargue extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TableLayout tableLayout;
    private EditText txtHorno;
    private EditText txtCamara;
    private String[]header={"FECHA","HORNO","CAMARA"};
    private ArrayList<String[]> rows= new ArrayList<>();
    private TableDynamic tableDynamic;
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private DatabaseReference mDatabase;
    private static final String PATH_FOOD = "T_Cargue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference(PATH_FOOD);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                T_Cargue t_cargue =dataSnapshot.getValue(T_Cargue.class);
                t_cargue.setId(dataSnapshot.getKey());

                if (!T_Cargue.ITEMS.contains(t_cargue)) {
                    t_cargue.addItem(t_cargue);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                T_Cargue t_cargue =dataSnapshot.getValue(T_Cargue.class);
                t_cargue.setId(dataSnapshot.getKey());

                if (!T_Cargue.ITEMS.contains(t_cargue)) {
                    t_cargue.updateItem(t_cargue);
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                T_Cargue t_cargue =dataSnapshot.getValue(T_Cargue.class);
                t_cargue.setId(dataSnapshot.getKey());

                if (!T_Cargue.ITEMS.contains(t_cargue)) {
                    t_cargue.deleteItem(t_cargue);
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText(Cargue.this, "Moved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Cargue.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.AgregarT_cargur);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                T_Cargue t_cargue= new T_Cargue(simpleDateFormat.format(calendar.getTime()),txtHorno.getText().toString(),txtCamara.getText().toString());

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference(PATH_FOOD);
                reference.push().setValue(t_cargue);

                String[]item= new String[]{simpleDateFormat.format(calendar.getTime()),txtHorno.getText().toString(),txtCamara.getText().toString()};
                tableDynamic.addItem(item);
                txtHorno.setText("");
                txtCamara.setText("");
            }
        });

        tableLayout=(TableLayout)findViewById(R.id.table);
        txtHorno=(EditText)findViewById(R.id.Horno);
        txtCamara=(EditText)findViewById(R.id.Camara);

        tableDynamic= new TableDynamic(tableLayout,getApplicationContext());
        tableDynamic.addHeader(header);
        tableDynamic.backgroundHeader(Color.rgb(230,74,25));
        tableDynamic.textColorHeader(Color.WHITE);

        tableDynamic.addData(getClients());
        tableDynamic.backgroundData(Color.rgb(255,204,188),Color.rgb(255,204,188));
        tableDynamic.lineColor(Color.WHITE);
        tableDynamic.textColorData(Color.BLACK);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private ArrayList<String[]> getClients(){
        rows.add(new String[]{"15-02-2017","A","10"});
        rows.add(new String[]{"20-12-2017","A","19"});
        rows.add(new String[]{"06-09-2018","A","28"});
        return rows;
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
        getMenuInflater().inflate(R.menu.cargue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
            Intent h= new Intent(Cargue.this,Estadisticas.class);
            startActivity(h);
        } else if (id == R.id.arcilla) {
            Intent h= new Intent(Cargue.this,Arcilla.class);
            startActivity(h);
        } else if (id == R.id.moldeado) {
            Intent h= new Intent(Cargue.this,Moldeado.class);
            startActivity(h);
        } else if (id == R.id.cargue) {
            Intent h= new Intent(Cargue.this,Cargue.class);
            startActivity(h);
        } else if (id == R.id.quemado) {
            Intent h= new Intent(Cargue.this,Quemado.class);
            startActivity(h);
        } else if (id == R.id.descargue) {
            Intent h= new Intent(Cargue.this,Descargue.class);
            startActivity(h);
        }else if (id == R.id.view_cliente) {
            Intent h= new Intent(Cargue.this,MenuClienteListActivity.class);
            startActivity(h);
        }else if (id == R.id.view_obreros) {
            Intent h = new Intent(Cargue.this,MenuObreroListActivity.class);
            startActivity(h);
        }else if (id == R.id.view_tArcilla) {
            Intent h = new Intent(Cargue.this,MenuTransportistaDeArcillaListActivity.class);
            startActivity(h);
        }else if (id == R.id.view_tLadrillo) {
            Intent h = new Intent(Cargue.this, MenuTransportistaDeLadrilloListActivity.class);
            startActivity(h);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
