package com.example.johnnhidalgo.ceraviproject;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AgregarObreroDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private Obrero mItem;

    public AgregarObreroDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Obrero.ITEM_MAPOb.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getNombre());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.agregarobrero_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.agregarobrero_detail)).setText("Telefono : "+mItem.getTelefono()+"\n\nCiudad : "+mItem.getCiudad());

        }

        return rootView;
    }
}
