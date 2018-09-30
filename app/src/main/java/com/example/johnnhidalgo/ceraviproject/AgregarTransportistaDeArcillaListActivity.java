package com.example.johnnhidalgo.ceraviproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class AgregarTransportistaDeArcillaListActivity extends AppCompatActivity {

    private static final String PATH_FOOD = "TransportistasDeArcilla";
    private static final String PATH_PROFILE = "profile";
    private static final String PATH_CODE = "code";
    @BindView(R.id.AName)
    EditText AName;
    @BindView(R.id.ATelefono)
    EditText ATelefono;
    @BindView(R.id.btnASave)
    Button btnASave;
    @BindView(R.id.ACooperativa)
    EditText ACooperativa;
    @BindView(R.id.Aplaca)
    EditText APlaca;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregartransportistadearcilla_list);
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

        if (findViewById(R.id.agregartransportistadearcilla_detail_container) != null) {
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.agregartransportistadearcilla_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMSTRA, mTwoPane));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOOD);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded( DataSnapshot dataSnapshot,  String s) {
                DummyContent.TransArcilla transArcilla = dataSnapshot.getValue(DummyContent.TransArcilla.class);
                transArcilla.setId(dataSnapshot.getKey());
                if (!DummyContent.ITEMSTRA.contains(transArcilla)) {
                    DummyContent.addItemTra(transArcilla);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged( DataSnapshot dataSnapshot,  String s) {
                DummyContent.TransArcilla transArcilla = dataSnapshot.getValue(DummyContent.TransArcilla.class);
                transArcilla.setId(dataSnapshot.getKey());

                if (DummyContent.ITEMSTRA.contains(transArcilla)) {
                    DummyContent.updateItemTra(transArcilla);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved( DataSnapshot dataSnapshot) {
                DummyContent.TransArcilla transArcilla = dataSnapshot.getValue(DummyContent.TransArcilla.class);
                transArcilla.setId(dataSnapshot.getKey());

                if (DummyContent.ITEMSTRA.contains(transArcilla)) {
                    DummyContent.deleteItemTra(transArcilla);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildMoved( DataSnapshot dataSnapshot,  String s) {
                Toast.makeText(AgregarTransportistaDeArcillaListActivity.this, "Moved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                Toast.makeText(AgregarTransportistaDeArcillaListActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @OnClick(R.id.btnASave)
    public void onViewClicked() {
        DummyContent.TransArcilla transArcilla =  new DummyContent.TransArcilla(AName.getText().toString().trim(),ATelefono.getText().toString().trim(),
                                                        ACooperativa.getText().toString().trim(),APlaca.getText().toString().trim());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOOD);
        reference.push().setValue(transArcilla);
        AName.setText("");
        ATelefono.setText("");
        ACooperativa.setText("");
        APlaca.setText("");

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
                        Toast.makeText(AgregarTransportistaDeArcillaListActivity.this, "No se puede cargar el código.",
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

        private final AgregarTransportistaDeArcillaListActivity mParentActivity;
        private final List<DummyContent.TransArcilla> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.TransArcilla item = (DummyContent.TransArcilla) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(AgregarTransportistaDeArcillaDetailFragment.ARG_ITEM_ID, item.getId());
                    AgregarTransportistaDeArcillaDetailFragment fragment = new AgregarTransportistaDeArcillaDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.agregartransportistadearcilla_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AgregarTransportistaDeArcillaDetailActivity.class);
                    intent.putExtra(AgregarTransportistaDeArcillaDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(AgregarTransportistaDeArcillaListActivity parent,
                                      List<DummyContent.TransArcilla> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.agregartransportistadearcilla_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mIdView.setText(mValues.get(position).getTelefono());
            holder.mContentView.setText(mValues.get(position).getNombre());
            holder.mContentViewco.setText(mValues.get(position).getCooperativa());
            holder.mContentViewpl.setText(mValues.get(position).getPlaca());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
//            holder.btnADelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference reference = database.getReference(PATH_FOOD);
//                    reference.child(mValues.get(position).getId()).removeValue();
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;
            final TextView mContentViewco;
            final TextView mContentViewpl;

            @BindView(R.id.btnADelete)
            Button btnADelete;
            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.telefonoA);
                mContentView = (TextView) view.findViewById(R.id.nombreA);
                mContentViewco = (TextView) view.findViewById(R.id.cooperativaA);
                mContentViewpl = (TextView) view.findViewById(R.id.placaA);
            }
        }
    }
}
