package org.styleru.hseday2017_2.NavigationFragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.styleru.hseday2017_2.ApiClasses.ApiAboutHSE;
import org.styleru.hseday2017_2.DataBaseHelper;
import org.styleru.hseday2017_2.MainActivity;
import org.styleru.hseday2017_2.R;



public class FragmentAboutHSE extends android.support.v4.app.Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    ApiAboutHSE aboutHSE;
    DataBaseHelper dbHelper;
    TextView name;
    TextView description;
    TextView contacts;
    ImageView image;

    private OnFragmentInteractionListener mListener;

    public FragmentAboutHSE() {

    }


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
        View view = inflater.inflate(org.styleru.hseday2017_2.R.layout.fragment_about_hse, container, false);
        name = (TextView) view.findViewById(R.id.about_hse_title);
        description = (TextView) view.findViewById(R.id.about_hse_description);
        contacts = (TextView) view.findViewById(R.id.about_hse_contacts);
        image = (ImageView) view.findViewById(R.id.about_hse_image);
        dbHelper = new DataBaseHelper(getContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursorAboutHSE = database.query(DataBaseHelper.TABLE_ABOUT_HSE_NAME, null, null, null, null, null, null);
        if (cursorAboutHSE.moveToFirst()) {
            int nameIndex = cursorAboutHSE.getColumnIndex(DataBaseHelper.ABOUT_HSE_NAME);
            int descriptionIndex = cursorAboutHSE.getColumnIndex(DataBaseHelper.ABOUT_HSE_DESCRIPTION);
            int contactsIndex = cursorAboutHSE.getColumnIndex(DataBaseHelper.ABOUT_HSE_CONTACTS);
            int imageurlIndex = cursorAboutHSE.getColumnIndex(DataBaseHelper.ABOUT_HSE_IMAGE_URL);
            int codeIndex = cursorAboutHSE.getColumnIndex(DataBaseHelper.ABOUT_HSE_CODE);
            do {
                aboutHSE = new ApiAboutHSE();
                aboutHSE.setName(cursorAboutHSE.getString(nameIndex));
                aboutHSE.setDescription(cursorAboutHSE.getString(descriptionIndex));
                aboutHSE.setContacts(cursorAboutHSE.getString(contactsIndex));
                aboutHSE.setImageurl(cursorAboutHSE.getString(imageurlIndex));
                aboutHSE.setCode(cursorAboutHSE.getString(codeIndex));
            } while (cursorAboutHSE.moveToNext());
        }
        cursorAboutHSE.close();
        try {
            name.setText(aboutHSE.getName());
            description.setText(aboutHSE.getDescription());
            contacts.setText(aboutHSE.getContacts());
            Glide.with(getContext()).load(aboutHSE.getImageurl()).into(image);
        } catch (NullPointerException e){
            Log.d("myLogs", e.toString());
        }
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
        menu.findItem(org.styleru.hseday2017_2.R.id.map_quest_mark).setVisible(false);
        menu.findItem(org.styleru.hseday2017_2.R.id.map_ball).setVisible(false);
        menu.findItem(org.styleru.hseday2017_2.R.id.map_tent).setVisible(false);
        menu.findItem(org.styleru.hseday2017_2.R.id.map_paper).setVisible(false);
        menu.findItem(org.styleru.hseday2017_2.R.id.map_microphone).setVisible(false);
    }
}
