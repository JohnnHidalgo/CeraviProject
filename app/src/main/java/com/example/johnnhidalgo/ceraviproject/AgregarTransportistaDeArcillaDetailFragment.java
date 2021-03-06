package com.example.johnnhidalgo.ceraviproject;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AgregarTransportistaDeArcillaDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private TransArcilla mItem;

    public AgregarTransportistaDeArcillaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = TransArcilla.ITEM_MAPTRA.get(getArguments().getString(ARG_ITEM_ID));

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
        View rootView = inflater.inflate(R.layout.agregartransportistadearcilla_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.agregartransportistadearcilla_detail)).setText("Telefono :"+mItem.getTelefono()+"\n\nCooperativa :"+mItem.getCooperativa()+"\n\nPlaca :"+mItem.getPlaca());

        }
        return rootView;
    }
}
