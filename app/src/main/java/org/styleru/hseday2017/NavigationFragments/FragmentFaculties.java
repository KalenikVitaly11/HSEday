package org.styleru.hseday2017.NavigationFragments;

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

import org.styleru.hseday2017.ApiClasses.ApiFaculties;
import org.styleru.hseday2017.DataBaseHelper;
import org.styleru.hseday2017.MainActivity;
import org.styleru.hseday2017.RecyclerViewAdapters.RecyclerViewAdapterFaculties;

import java.util.ArrayList;


public class FragmentFaculties extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterFaculties mAdapter;
    DataBaseHelper dbHelper;
    private StaggeredGridLayoutManager mGridLayoutManager;
    ArrayList<ApiFaculties> dataFaculties;
    ApiFaculties myFaculty;

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
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Факультеты");
        View view = inflater.inflate(org.styleru.hseday2017.R.layout.fragment_faculties, container, false);




        dbHelper = new DataBaseHelper(getContext());
        dataFaculties = new ArrayList<ApiFaculties>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor c = database.query(DataBaseHelper.TABLE_FACULTIES_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int nameIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_NAME);
            int descriptionIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_DESCRIPTION);
            int contactsIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_CONTACTS);
            int imageIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_IMAGE_URL);
            do {
                myFaculty = new ApiFaculties();
                myFaculty.setName(c.getString(nameIndex));
                myFaculty.setDescription(c.getString(descriptionIndex));
                myFaculty.setContacts(c.getString(contactsIndex));
                myFaculty.setImageurl(c.getString(imageIndex));
                dataFaculties.add(myFaculty);
            } while (c.moveToNext());
        }
        c.close();



        mRecyclerView = (RecyclerView)view.findViewById(org.styleru.hseday2017.R.id.faculty_recycler_view);
        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter = new RecyclerViewAdapterFaculties(getActivity(), dataFaculties);
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

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(org.styleru.hseday2017.R.id.map_quest_mark).setVisible(false);
        menu.findItem(org.styleru.hseday2017.R.id.map_ball).setVisible(false);
        menu.findItem(org.styleru.hseday2017.R.id.map_tent).setVisible(false);
        menu.findItem(org.styleru.hseday2017.R.id.map_paper).setVisible(false);
        menu.findItem(org.styleru.hseday2017.R.id.map_microphone).setVisible(false);
    }
}
