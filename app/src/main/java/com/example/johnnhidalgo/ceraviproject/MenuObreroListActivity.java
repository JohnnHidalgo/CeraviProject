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

public class MenuObreroListActivity extends AppCompatActivity {

    private boolean mTwoPaneOb;
    private static final String PATH_FOODob = "Obreros";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuobrero_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent h= new Intent(MenuObreroListActivity.this,AgregarObreroListActivity.class);
                startActivity(h);
            }
        });

        if (findViewById(R.id.menuobrero_detail_container) != null) {
            mTwoPaneOb = true;
        }

        View recyclerView = findViewById(R.id.menuobrero_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMSOb, mTwoPaneOb));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_FOODob);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DummyContent.Obrero obrero = dataSnapshot.getValue(DummyContent.Obrero.class);
                obrero.setId(dataSnapshot.getKey());
                if (!DummyContent.ITEMSOb.contains(obrero)) {
                    DummyContent.addItemOb(obrero);
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
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final MenuObreroListActivity mParentActivity;
        private final List<DummyContent.Obrero> mValues;
        private final boolean mTwoPaneOb;//mTwoPaneOb
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DummyContent.Obrero item = (DummyContent.Obrero) view.getTag();
                if (mTwoPaneOb) {
                    Bundle arguments = new Bundle();
                    arguments.putString(MenuObreroDetailFragment.ARG_ITEM_ID, item.getId());
                    MenuObreroDetailFragment fragment = new MenuObreroDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.menuobrero_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, MenuObreroDetailActivity.class);
                    intent.putExtra(MenuObreroDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }


        };

        SimpleItemRecyclerViewAdapter(MenuObreroListActivity parent,
                                      List<DummyContent.Obrero> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPaneOb = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menuobrero_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).getTelefono());
            holder.mContentView.setText(mValues.get(position).getNombre());
            holder.mContentViewap.setText(mValues.get(position).getCiudad());

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
            final TextView mContentViewap;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                mIdView = (TextView) view.findViewById(R.id.id_telefonoP);
                mContentView = (TextView) view.findViewById(R.id.nombreP);
                mContentViewap = (TextView) view.findViewById(R.id.ciudadP);
            }
        }
    }
}
