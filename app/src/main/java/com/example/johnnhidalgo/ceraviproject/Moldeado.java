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

public class Moldeado extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TableLayout tableLayout;
    private EditText txtLastName;
    private String[]header={"FECHA","CANTIDAD",""};
    private ArrayList<String[]> rows= new ArrayList<>();
    private TableDynamic tableDynamic;
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private DatabaseReference mDatabase;
    private static final String PATH_FOOD = "T_Moldeado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moldeado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference(PATH_FOOD);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                T_Moldeado t_moldeado =dataSnapshot.getValue(T_Moldeado.class);
                t_moldeado.setId(dataSnapshot.getKey());

                if (!T_Moldeado.ITEMS.contains(t_moldeado)) {
                    t_moldeado.addItem(t_moldeado);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                T_Moldeado t_moldeado =dataSnapshot.getValue(T_Moldeado.class);
                t_moldeado.setId(dataSnapshot.getKey());

                if (!T_Moldeado.ITEMS.contains(t_moldeado)) {
                    t_moldeado.updateItem(t_moldeado);
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                T_Moldeado t_moldeado =dataSnapshot.getValue(T_Moldeado.class);
                t_moldeado.setId(dataSnapshot.getKey());

                if (!T_Moldeado.ITEMS.contains(t_moldeado)) {
                    t_moldeado.deleteItem(t_moldeado);
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText(Moldeado.this, "Moved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Moldeado.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.AgregarT_moldeado);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                T_Moldeado t_moldeado= new T_Moldeado(simpleDateFormat.format(calendar.getTime()),txtLastName.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference(PATH_FOOD);
                reference.push().setValue(t_moldeado);
                String[]item= new String[]{simpleDateFormat.format(calendar.getTime()),txtLastName.getText().toString(),""};
                tableDynamic.addItem(item);
                txtLastName.setText("");
            }
        });
        tableLayout=(TableLayout)findViewById(R.id.table);
        txtLastName=(EditText)findViewById(R.id.CantidadProducida);

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
        rows.add(new String[]{"15-02-2017","12300",""});
        rows.add(new String[]{"20-12-2017","19000",""});
        rows.add(new String[]{"06-09-2018","18560",""});
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
        getMenuInflater().inflate(R.menu.moldeado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.estadisticas) {
            Intent h= new Intent(Moldeado.this,Estadisticas.class);
            startActivity(h);
        } else if (id == R.id.arcilla) {
            Intent h= new Intent(Moldeado.this,Arcilla.class);
            startActivity(h);
        } else if (id == R.id.moldeado) {
            Intent h= new Intent(Moldeado.this,Moldeado.class);
            startActivity(h);
        } else if (id == R.id.cargue) {
            Intent h= new Intent(Moldeado.this,Cargue.class);
            startActivity(h);
        } else if (id == R.id.quemado) {
            Intent h= new Intent(Moldeado.this,Quemado.class);
            startActivity(h);
        } else if (id == R.id.descargue) {
            Intent h= new Intent(Moldeado.this,Descargue.class);
            startActivity(h);
        }else if (id == R.id.view_cliente) {
            Intent h= new Intent(Moldeado.this,MenuClienteListActivity.class);
            startActivity(h);
        }else if (id == R.id.view_obreros) {
            Intent h = new Intent(Moldeado.this,MenuObreroListActivity.class);
            startActivity(h);
        }else if (id == R.id.view_tArcilla) {
            Intent h = new Intent(Moldeado.this,MenuTransportistaDeArcillaListActivity.class);
            startActivity(h);
        }else if (id == R.id.view_tLadrillo) {
            Intent h = new Intent(Moldeado.this, MenuTransportistaDeLadrilloListActivity.class);
            startActivity(h);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
