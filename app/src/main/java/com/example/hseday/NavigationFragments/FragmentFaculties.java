package com.example.hseday.NavigationFragments;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hseday.FacultiesListElement;
import com.example.hseday.MainActivity;
import com.example.hseday.R;
import com.example.hseday.RecyclerViewAdapters.RecyclerViewAdapterFaculties;

import java.util.List;


public class FragmentFaculties extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterFaculties mAdapter;
    private String[] mList;
    private StaggeredGridLayoutManager mGridLayoutManager;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentFaculties() {
    }

    public static FragmentFaculties newInstance(String param1, String param2) {
        FragmentFaculties fragment = new FragmentFaculties();
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


        //RVAdapterInitiation(mRecyclerView, mGridLayoutManager, mAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Факультеты");

        View view = inflater.inflate(R.layout.fragment_faculties, container, false);

        mList = getResources().getStringArray(R.array.faculties);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.faculty_recycler_view);
        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter = new RecyclerViewAdapterFaculties(getActivity(), mList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
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
}
