package com.example.johnnhidalgo.ceraviproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgregarClienteListActivity extends AppCompatActivity {

    private static final String PATH_FOOD = "Clientes";

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etTelefono)
    EditText etTelefono;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.etCiudad)
    EditText etCiudad;
    @BindView(R.id.etNit)
    EditText etNit;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarcliente_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.agregarcliente_detail_container) != null) {
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.agregarcliente_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, Cliente.ITEMS, mTwoPane));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOOD);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Cliente cliente = dataSnapshot.getValue(Cliente.class);
                cliente.setId(dataSnapshot.getKey());
                if (!Cliente.ITEMS.contains(cliente)) {
                    Cliente.addItem(cliente);
                }
                recyclerView.getAdapter().notifyDataSetChanged();

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Cliente cliente = dataSnapshot.getValue(Cliente.class);
                cliente.setId(dataSnapshot.getKey());

                if (Cliente.ITEMS.contains(cliente)) {
                    Cliente.updateItem(cliente);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved( DataSnapshot dataSnapshot) {
                Cliente cliente = dataSnapshot.getValue(Cliente.class);
                cliente.setId(dataSnapshot.getKey());

                if (Cliente.ITEMS.contains(cliente)) {
                    Cliente.deleteItem(cliente);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(AgregarClienteListActivity.this, "Moved", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AgregarClienteListActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        Cliente cliente = new Cliente(etName.getText().toString().trim(),
                etTelefono.getText().toString().trim(),etCiudad.getText().toString().trim(),etNit.getText().toString().trim());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOOD);
        reference.push().setValue(cliente);
        etName.setText("");
        etTelefono.setText("");
        etCiudad.setText("");
        etNit.setText("");
    }


    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final AgregarClienteListActivity mParentActivity;
        private final List<Cliente> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente item = (Cliente) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(AgregarClienteDetailFragment.ARG_ITEM_ID, item.getId());
                    AgregarClienteDetailFragment fragment = new AgregarClienteDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.agregarcliente_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AgregarClienteDetailActivity.class);
                    intent.putExtra(AgregarClienteDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(AgregarClienteListActivity parent,
                                      List<Cliente> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.agregarcliente_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mIdView.setText(mValues.get(position).getTelefono());
            holder.mContentView.setText(mValues.get(position).getNombre());
            holder.mContentViewap.setText(mValues.get(position).getCiudad());
            holder.mContentViewnit.setText(mValues.get(position).getNit());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference(PATH_FOOD);
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
            final TextView mContentViewnit;

            @BindView(R.id.btnDelete)
            Button btnDelete;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                mIdView = (TextView) view.findViewById(R.id.telefono);
                mContentView = (TextView) view.findViewById(R.id.nombre);
                mContentViewap = (TextView) view.findViewById(R.id.ciudad);
                mContentViewnit = (TextView) view.findViewById(R.id.nit);
            }
        }
    }
}


