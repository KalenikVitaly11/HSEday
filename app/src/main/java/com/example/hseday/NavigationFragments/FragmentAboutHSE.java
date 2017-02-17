package com.example.hseday.NavigationFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.example.hseday.MainActivity;
import com.example.hseday.R;


public class FragmentAboutHSE extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentAboutHSE() {

    }


    // TODO: Rename and change types and number of parameters
    public static FragmentAboutHSE newInstance(String param1, String param2) {
        FragmentAboutHSE fragment = new FragmentAboutHSE();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("О ВШЭ");
        View view = inflater.inflate(R.layout.fragment_about_hse, container, false);
        return inflater.inflate(R.layout.fragment_about_hse, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.map_quest_mark).setVisible(false);
        menu.findItem(R.id.map_ball).setVisible(false);
        menu.findItem(R.id.map_tent).setVisible(false);
        menu.findItem(R.id.map_paper).setVisible(false);
        menu.findItem(R.id.map_microphone).setVisible(false);
    }
}
