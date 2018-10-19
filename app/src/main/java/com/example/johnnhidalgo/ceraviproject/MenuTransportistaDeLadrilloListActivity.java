package com.example.johnnhidalgo.ceraviproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.johnnhidalgo.ceraviproject.dummy.DummyContent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import butterknife.ButterKnife;

public class MenuTransportistaDeLadrilloListActivity extends AppCompatActivity {

    private boolean mTwoPanetrl;
    private static final String PATH_FOODTrl = "TransportistasDeLadrillo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menutransportistadeladrillo_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h= new Intent(MenuTransportistaDeLadrilloListActivity.this,AgregarTransportistaDeLadrilloListActivity.class);
                startActivity(h);
            }
        });

        if (findViewById(R.id.menutransportistadeladrillo_detail_container) != null) {
            mTwoPanetrl = true;
        }

        View recyclerView = findViewById(R.id.menutransportistadeladrillo_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMSTRL, mTwoPanetrl));
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOODTrl);
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

            }

            @Override
            public void onChildRemoved( DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved( DataSnapshot dataSnapshot,  String s) {

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final MenuTransportistaDeLadrilloListActivity mParentActivity;
        private final List<DummyContent.TransLadrillo> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.TransLadrillo item = (DummyContent.TransLadrillo) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(MenuTransportistaDeLadrilloDetailFragment.ARG_ITEM_ID, item.getId());
                    MenuTransportistaDeLadrilloDetailFragment fragment = new MenuTransportistaDeLadrilloDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.menutransportistadeladrillo_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, MenuTransportistaDeLadrilloDetailActivity.class);
                    intent.putExtra(MenuTransportistaDeLadrilloDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(MenuTransportistaDeLadrilloListActivity parent,
                                      List<DummyContent.TransLadrillo> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menutransportistadeladrillo_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).getTelefono());
            holder.mContentView.setText(mValues.get(position).getNombre());
            holder.mContentViewco.setText(mValues.get(position).getCooperativa());



            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;
            final TextView mContentViewco;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                mIdView = (TextView) view.findViewById(R.id.LMTelefono);
                mContentView = (TextView) view.findViewById(R.id.LMNombre);
                mContentViewco = (TextView) view.findViewById(R.id.LMCooperativa);
            }
        }
    }
}
