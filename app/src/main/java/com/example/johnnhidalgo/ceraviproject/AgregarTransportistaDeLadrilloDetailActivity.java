package com.example.johnnhidalgo.ceraviproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class AgregarTransportistaDeLadrilloDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregartransportistadeladrillo_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(AgregarTransportistaDeLadrilloDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(AgregarTransportistaDeLadrilloDetailFragment.ARG_ITEM_ID));
            AgregarTransportistaDeLadrilloDetailFragment fragment = new AgregarTransportistaDeLadrilloDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.agregartransportistadeladrillo_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, AgregarTransportistaDeLadrilloListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
