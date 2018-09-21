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
import android.view.Menu;
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


public class AgregarClienteListActivity extends AppCompatActivity {

    private static final String PATH_FOOD = "Clientes";
    private static final String PATH_PROFILE = "profile";
    private static final String PATH_CODE = "code";

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etTelefono)
    EditText etTelefono;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.etCiudad)
    EditText etCiudad;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarcliente_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.agregarcliente_detail_container) != null) {
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.agregarcliente_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOOD);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DummyContent.Cliente cliente = dataSnapshot.getValue(DummyContent.Cliente.class);
                cliente.setId(dataSnapshot.getKey());

                if (!DummyContent.ITEMS.contains(cliente)) {
                    DummyContent.addItem(cliente);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                DummyContent.Cliente cliente = dataSnapshot.getValue(DummyContent.Cliente.class);
                cliente.setId(dataSnapshot.getKey());

                if (DummyContent.ITEMS.contains(cliente)) {
                    DummyContent.updateItem(cliente);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                DummyContent.Cliente cliente = dataSnapshot.getValue(DummyContent.Cliente.class);
                cliente.setId(dataSnapshot.getKey());

                if (DummyContent.ITEMS.contains(cliente)) {
                    DummyContent.deleteItem(cliente);
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
        DummyContent.Cliente cliente = new DummyContent.Cliente(etName.getText().toString().trim(),
                etTelefono.getText().toString().trim(),etCiudad.getText().toString().trim());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOOD);
        reference.push().setValue(cliente);
        etName.setText("");
        etTelefono.setText("");
        etCiudad.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_info:
                final TextView tvCode = new TextView(this);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvCode.setLayoutParams(params);
                tvCode.setGravity(Gravity.CENTER_HORIZONTAL);
                tvCode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference(PATH_PROFILE).child(PATH_CODE);

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tvCode.setText(dataSnapshot.getValue(String.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(AgregarClienteListActivity.this, "No se puede cargar el código.",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle(R.string.clienteList_dialog_title)
                        .setPositiveButton(R.string.comidaList_dialog_ok, null);
                builder.setView(tvCode);
                builder.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final AgregarClienteListActivity mParentActivity;
        private final List<DummyContent.Cliente> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.Cliente item = (DummyContent.Cliente) view.getTag();
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
                                      List<DummyContent.Cliente> items,
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
            @BindView(R.id.btnDelete)
            Button btnDelete;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                mIdView = (TextView) view.findViewById(R.id.id_telefono);
                mContentView = (TextView) view.findViewById(R.id.nombre);
                mContentViewap = (TextView) view.findViewById(R.id.ciudad);
            }
        }
    }
}
