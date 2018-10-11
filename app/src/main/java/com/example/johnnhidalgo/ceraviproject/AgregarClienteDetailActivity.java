package com.example.johnnhidalgo.ceraviproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class AgregarClienteDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarcliente_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbarCliente);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(AgregarClienteDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(AgregarClienteDetailFragment.ARG_ITEM_ID));
            AgregarClienteDetailFragment fragment = new AgregarClienteDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.agregarcliente_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, AgregarClienteListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
