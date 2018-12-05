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

import com.example.johnnhidalgo.ceraviproject.dummy.DummyContent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgregarTransportistaDeLadrilloListActivity extends AppCompatActivity {

    private static final String PATH_FOODTL = "TransportistasDeLadrillo";
    @BindView(R.id.LName)
    EditText LName;
    @BindView(R.id.LCooperativa)
    EditText LCooperativa;
    @BindView(R.id.LTelefono)
    EditText LTelefono;
    @BindView(R.id.LbtnASave)
    Button btnASave;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregartransportistadeladrillo_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.agregartransportistadeladrillo_detail_container) != null) {
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.agregartransportistadeladrillo_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMSTRL, mTwoPane));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOODTL);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded( DataSnapshot dataSnapshot,  String s) {
                DummyContent.TransLadrillo transLadrillo = dataSnapshot.getValue(DummyContent.TransLadrillo.class);
                transLadrillo.setId(dataSnapshot.getKey());
                if (!DummyContent.ITEMSTRL.contains(transLadrillo)) {
                    DummyContent.addItemTrl(transLadrillo);
                }
                recyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onChildChanged( DataSnapshot dataSnapshot,  String s) {
                DummyContent.TransLadrillo transLadrillo = dataSnapshot.getValue(DummyContent.TransLadrillo.class);
                transLadrillo.setId(dataSnapshot.getKey());

                if (DummyContent.ITEMSTRL.contains(transLadrillo)) {
                    DummyContent.updateItemTrl(transLadrillo);
                }
                recyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved( DataSnapshot dataSnapshot) {
                DummyContent.TransLadrillo transLadrillo = dataSnapshot.getValue(DummyContent.TransLadrillo.class);
                transLadrillo.setId(dataSnapshot.getKey());

                if (DummyContent.ITEMSTRL.contains(transLadrillo)) {
                    DummyContent.deleteItemTrl(transLadrillo);
                }
                recyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onChildMoved( DataSnapshot dataSnapshot,  String s) {
                Toast.makeText(AgregarTransportistaDeLadrilloListActivity.this, "Moved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                Toast.makeText(AgregarTransportistaDeLadrilloListActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.LbtnASave)
    public void onViewClicked() {
        DummyContent.TransLadrillo transLadrillo = new DummyContent.TransLadrillo(LName.getText().toString().trim(),
                LCooperativa.getText().toString().trim(),
                LTelefono.getText().toString().trim());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOODTL);
        reference.push().setValue(transLadrillo);

        LName.setText("");
        LCooperativa.setText("");
        LTelefono.setText("");
    }


    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final AgregarTransportistaDeLadrilloListActivity mParentActivity;
        private final List<DummyContent.TransLadrillo> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.TransLadrillo item = (DummyContent.TransLadrillo) view.getTag();

                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(AgregarTransportistaDeLadrilloDetailFragment.ARG_ITEM_ID, item.getId());
                    AgregarTransportistaDeLadrilloDetailFragment fragment = new AgregarTransportistaDeLadrilloDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.agregartransportistadeladrillo_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AgregarTransportistaDeLadrilloDetailActivity.class);
                    intent.putExtra(AgregarTransportistaDeLadrilloDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(AgregarTransportistaDeLadrilloListActivity parent,
                                      List<DummyContent.TransLadrillo> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.agregartransportistadeladrillo_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mIdView.setText(mValues.get(position).getTelefono());
            holder.mContentView.setText(mValues.get(position).getNombre());
            holder.mContentViewco.setText(mValues.get(position).getCooperativa());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);

            holder.btnDeleteTl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference(PATH_FOODTL);
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
            final TextView mContentViewco;

            @BindView(R.id.btnDeleteTl)
            Button btnDeleteTl;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);

                mIdView = (TextView) view.findViewById(R.id.LaTelefono);
                mContentView = (TextView) view.findViewById(R.id.LaNombre);
                mContentViewco = (TextView) view.findViewById(R.id.LaCooperativa);
            }
        }
    }
}