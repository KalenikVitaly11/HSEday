package org.styleru.hseday2017.NavigationFragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.styleru.hseday2017.MainActivity;


public class FragmentDedication extends android.support.v4.app.Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public Integer Count = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView DedicationText;
    ImageView Crow;
    ProgressBar ProgressBar;
    Button NextCrow;
    Button PreviousCrow;
    Button StartDedication;
    EditText enterCode;

    private OnFragmentInteractionListener mListener;

    public FragmentDedication() {
        // Required empty public constructor
    }


    public static FragmentDedication newInstance(String param1, String param2) {
        FragmentDedication fragment = new FragmentDedication();
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
        Count = 0;
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Посвящение");

        View view = inflater.inflate(org.styleru.hseday2017.R.layout.fragment_dedication, container, false);
        DedicationText = (TextView) view.findViewById(org.styleru.hseday2017.R.id.DedicationWords);
        Crow = (ImageView) view.findViewById(org.styleru.hseday2017.R.id.crow);
        NextCrow = (Button) view.findViewById(org.styleru.hseday2017.R.id.next_crow);
        PreviousCrow = (Button) view.findViewById(org.styleru.hseday2017.R.id.previous_crow);
        ProgressBar = (ProgressBar) view.findViewById(org.styleru.hseday2017.R.id.dedication_progress);
        StartDedication = (Button) view.findViewById(org.styleru.hseday2017.R.id.start_dedication);
        enterCode = (EditText) view.findViewById(org.styleru.hseday2017.R.id.edit_text_enter_code);

        StartDedication.setOnClickListener(this);
        NextCrow.setOnClickListener(this);
        PreviousCrow.setOnClickListener(this);

        Count = 0;
        ProgressBar.setProgress(0);
        ProgressBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(255, 127, 0))); //  Настройка цвета прогресс бара
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(org.styleru.hseday2017.R.id.map_quest_mark).setVisible(false);
        menu.findItem(org.styleru.hseday2017.R.id.map_ball).setVisible(false);
        menu.findItem(org.styleru.hseday2017.R.id.map_tent).setVisible(false);
        menu.findItem(org.styleru.hseday2017.R.id.map_paper).setVisible(false);
        menu.findItem(org.styleru.hseday2017.R.id.map_microphone).setVisible(false);
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
        setMenuVisibility(true);
        ProgressBar.setProgress(0);
        mListener = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        ProgressBar.setProgress(0);
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case org.styleru.hseday2017.R.id.next_crow:
                if(Count != 7){
                    Count++;
                }
                break;
            case org.styleru.hseday2017.R.id.previous_crow:
                if(Count != 1){
                    Count--;
                }
                break;
            case org.styleru.hseday2017.R.id.start_dedication:
                Count++;
                StartDedication.setEnabled(false);
                StartDedication.setVisibility(View.GONE);
                enterCode.setEnabled(false);
                enterCode.setVisibility(View.GONE);
                NextCrow.setEnabled(true);
                NextCrow.setVisibility(View.VISIBLE);
                ProgressBar.setVisibility(View.VISIBLE);
                PreviousCrow.setEnabled(true);
                PreviousCrow.setVisibility(View.VISIBLE);
                break;

        }
        ProgressBar.setProgress(Count);
        switch(Count){
            case 1: DedicationText.setText(org.styleru.hseday2017.R.string.crow_1);
                Crow.setImageResource(org.styleru.hseday2017.R.drawable.crow_1);
                break;
            case 2: DedicationText.setText(org.styleru.hseday2017.R.string.crow_2);
                Crow.setImageResource(org.styleru.hseday2017.R.drawable.crow_2);
                break;
            case 3: DedicationText.setText(org.styleru.hseday2017.R.string.crow_3);
                Crow.setImageResource(org.styleru.hseday2017.R.drawable.crow_3);
                break;
            case 4: DedicationText.setText(org.styleru.hseday2017.R.string.crow_4);
                Crow.setImageResource(org.styleru.hseday2017.R.drawable.crow_4);
                break;
            case 5: DedicationText.setText(org.styleru.hseday2017.R.string.crow_5);
                Crow.setImageResource(org.styleru.hseday2017.R.drawable.crow_5);
                break;
            case 6: DedicationText.setText(org.styleru.hseday2017.R.string.crow_6);
                Crow.setImageResource(org.styleru.hseday2017.R.drawable.crow_6);
                break;
            case 7: DedicationText.setText(org.styleru.hseday2017.R.string.crow_7);
                Crow.setImageResource(org.styleru.hseday2017.R.drawable.crow_7);
                break;
        }

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
