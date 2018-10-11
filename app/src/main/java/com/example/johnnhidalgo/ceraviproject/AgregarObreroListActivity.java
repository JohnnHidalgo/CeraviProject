package com.example.johnnhidalgo.ceraviproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnnhidalgo.ceraviproject.dummy.DummyContent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgregarObreroListActivity extends AppCompatActivity {

    private static final String PATH_FOODOb = "Obreros";
    private static final String PATH_PROFILEOb = "profile";
    private static final String PATH_CODEOb = "code";
    @BindView(R.id.etNameO)
    EditText etName;
    @BindView(R.id.etTelefonoO)
    EditText etTelefono;
    @BindView(R.id.btnSaveO)
    Button btnSave;
    @BindView(R.id.etCiudadO)
    EditText etCiudad;
    private boolean mTwoPaneOb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarobrero_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());



        if (findViewById(R.id.agregarobrero_detail_container) != null) {
            mTwoPaneOb = true;
        }

        View recyclerView = findViewById(R.id.agregarobrero_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMSOb, mTwoPaneOb));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOODOb);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded( DataSnapshot dataSnapshot,  String s) {
                DummyContent.Obrero obrero =dataSnapshot.getValue(DummyContent.Obrero.class);
                obrero.setId(dataSnapshot.getKey());
                if (!DummyContent.ITEMSOb.contains(obrero)) {
                    DummyContent.addItemOb(obrero);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged( DataSnapshot dataSnapshot,  String s) {
                DummyContent.Obrero obrero = dataSnapshot.getValue(DummyContent.Obrero.class);
                obrero.setId(dataSnapshot.getKey());

                if (DummyContent.ITEMSOb.contains(obrero)) {
                    DummyContent.updateItemOb(obrero);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved( DataSnapshot dataSnapshot) {
                DummyContent.Obrero obrero = dataSnapshot.getValue(DummyContent.Obrero.class);
                obrero.setId(dataSnapshot.getKey());

                if (DummyContent.ITEMSOb.contains(obrero)) {
                    DummyContent.deleteItemOb(obrero);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildMoved( DataSnapshot dataSnapshot,  String s) {
                Toast.makeText(AgregarObreroListActivity.this, "Moved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                Toast.makeText(AgregarObreroListActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @OnClick(R.id.btnSaveO)
    public void onViewClicked() {
        DummyContent.Obrero obrero = new DummyContent.Obrero(etName.getText().toString().trim(),
                etTelefono.getText().toString().trim(),etCiudad.getText().toString().trim());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOODOb);
        reference.push().setValue(obrero);
        etName.setText("");
        etTelefono.setText("");
        etCiudad.setText("");
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final AgregarObreroListActivity mParentActivity;
        private final List<DummyContent.Obrero> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.Obrero item = (DummyContent.Obrero) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(AgregarObreroDetailFragment.ARG_ITEM_ID, item.getId());
                    AgregarObreroDetailFragment fragment = new AgregarObreroDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.agregarobrero_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AgregarObreroDetailActivity.class);
                    intent.putExtra(AgregarObreroDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(AgregarObreroListActivity parent,
                                      List<DummyContent.Obrero> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.agregarobrero_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mIdView.setText(mValues.get(position).getTelefono());
            holder.mContentView.setText(mValues.get(position).getNombre());
            holder.mContentViewap.setText(mValues.get(position).getCiudad());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference(PATH_FOODOb);
                    reference.child(mValues.get(position).getId()).removeValue();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;
            final TextView mContentViewap;

            @BindView(R.id.btnDeleteO)
            Button btnDelete;
            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                mIdView = (TextView) view.findViewById(R.id.telefonoO);
                mContentView = (TextView) view.findViewById(R.id.nombreO);
                mContentViewap = (TextView) view.findViewById(R.id.ciudadO);
            }
        }
    }
}