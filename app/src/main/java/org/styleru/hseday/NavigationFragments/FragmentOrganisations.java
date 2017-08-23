package org.styleru.hseday.NavigationFragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import org.styleru.hseday.ApiClasses.ApiOrganisations;
import org.styleru.hseday.DataBaseHelper;
import org.styleru.hseday.MainActivity;
import org.styleru.hseday.RecyclerViewAdapters.RecyclerViewAdapterOrganisations;

import java.util.ArrayList;


public class FragmentOrganisations extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterOrganisations mAdapter;
    private StaggeredGridLayoutManager mGridLayoutManager;
    ArrayList<ApiOrganisations> dataOrganisations;
    DataBaseHelper dbHelper;
    ApiOrganisations myOrganisation;
    String result;

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentOrganisations() {
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentOrganisations newInstance(String param1, String param2) {
        FragmentOrganisations fragment = new FragmentOrganisations();
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
        ((MainActivity) getActivity()).setActionBarTitle("Организации");
        View view = inflater.inflate(org.styleru.hseday.R.layout.fragment_organisations, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(org.styleru.hseday.R.id.organisations_recycler_view);

        dbHelper = new DataBaseHelper(getContext());
        dataOrganisations = new ArrayList<ApiOrganisations>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor c = database.query(DataBaseHelper.TABLE_ORGANISATIONS_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int nameIndex = c.getColumnIndex(DataBaseHelper.ORGANISATION_NAME);
            int descriptionIndex = c.getColumnIndex(DataBaseHelper.ORGANISATION_DESCRIPTION);
            int contactsIndex = c.getColumnIndex(DataBaseHelper.ORGANISATION_CONTACTS);
            int imageIndex = c.getColumnIndex(DataBaseHelper.ORGANISATION_IMAGE_URL);

            do {
                myOrganisation = new ApiOrganisations();
                myOrganisation.setName(c.getString(nameIndex));
                myOrganisation.setDescription(c.getString(descriptionIndex));
                myOrganisation.setContacts(c.getString(contactsIndex));
                myOrganisation.setImageurl(c.getString(imageIndex));
                dataOrganisations.add(myOrganisation);
            } while (c.moveToNext());
        }
        c.close();


        mAdapter = new RecyclerViewAdapterOrganisations(getContext(), dataOrganisations);
        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(org.styleru.hseday.R.id.map_quest_mark).setVisible(false);
        menu.findItem(org.styleru.hseday.R.id.map_ball).setVisible(false);
        menu.findItem(org.styleru.hseday.R.id.map_tent).setVisible(false);
        menu.findItem(org.styleru.hseday.R.id.map_paper).setVisible(false);
        menu.findItem(org.styleru.hseday.R.id.map_microphone).setVisible(false);
    }
}
