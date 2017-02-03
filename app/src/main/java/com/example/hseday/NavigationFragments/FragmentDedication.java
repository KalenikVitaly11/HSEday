package com.example.hseday.NavigationFragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hseday.MainActivity;
import com.example.hseday.R;


public class FragmentDedication extends android.app.Fragment implements View.OnClickListener{
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
    Button NextCrow;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Посвящение");
        Count = 0;
        View view = inflater.inflate(R.layout.fragment_dedication, container, false);
        DedicationText = (TextView) view.findViewById(R.id.DedicationWords);
        Crow = (ImageView) view.findViewById(R.id.crow);
        NextCrow = (Button) view.findViewById(R.id.nextCrow);
        NextCrow.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.nextCrow:
                Count++;
                switch(Count){
                    case 1: DedicationText.setText(R.string.crow_1);
                        Crow.setImageResource(R.drawable.crow_1);
                        break;
                    case 2: DedicationText.setText(R.string.crow_2);
                        Crow.setImageResource(R.drawable.crow_2);
                        break;
                    case 3: DedicationText.setText(R.string.crow_3);
                        Crow.setImageResource(R.drawable.crow_3);
                        break;
                    case 4: DedicationText.setText(R.string.crow_4);
                        Crow.setImageResource(R.drawable.crow_4);
                        break;
                    case 5: DedicationText.setText(R.string.crow_5);
                        Crow.setImageResource(R.drawable.crow_5);
                        break;
                    case 6: DedicationText.setText(R.string.crow_6);
                        Crow.setImageResource(R.drawable.crow_6);
                        break;
                    case 7: DedicationText.setText(R.string.crow_7);
                        Crow.setImageResource(R.drawable.crow_7);
                        break;
                }
                break;
        }

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
