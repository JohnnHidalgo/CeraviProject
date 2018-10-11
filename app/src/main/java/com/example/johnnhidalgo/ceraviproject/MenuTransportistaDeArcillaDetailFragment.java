package com.example.johnnhidalgo.ceraviproject;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.johnnhidalgo.ceraviproject.dummy.DummyContent;

public class MenuTransportistaDeArcillaDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private DummyContent.TransArcilla mItem;

    public MenuTransportistaDeArcillaDetailFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAPTRA.get(getArguments().getString(ARG_ITEM_ID));
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layoutmenuTrarcilla);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getNombre());
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menutransportistadearcilla_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.menutransportistadearcilla_detail)).setText(mItem.getTelefono());
        }
        return rootView;
    }
}
