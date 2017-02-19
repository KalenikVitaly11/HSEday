package com.example.hseday.NavigationFragments;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hseday.DialogFragments.ActivityTent;
import com.example.hseday.DialogFragments.ActivityLection;
import com.example.hseday.DialogFragments.DialogQuest;
import com.example.hseday.MainActivity;
import com.example.hseday.R;
import com.squareup.picasso.Picasso;


public class FragmentMap extends android.support.v4.app.Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private float mx, my;
    private float curX, curY;

    private ScrollView vScroll;
    private HorizontalScrollView hScroll;

    DialogQuest DialogQuest;


    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    ImageView MapImage;
    ImageView MapImageBall1;
    ImageView MapImageQuest1;
    ImageView MapImageTent1;
    ImageView MapImageMicrophone1;

    public FragmentMap() {
    }


    public static FragmentMap newInstance(String param1, String param2) {
        FragmentMap fragment = new FragmentMap();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Карта");
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        MapImage = (ImageView) view.findViewById(R.id.map_image);
        DialogQuest = new DialogQuest();

        Glide.with(this).load(R.drawable.map_park).into(MapImage);
        //MapImage.setImageResource(R.drawable.map_park);
        //Picasso.with(getContext()).load(R.drawable.map_park).into(MapImage);

        vScroll = (ScrollView) view.findViewById(R.id.vScroll);
        hScroll = (HorizontalScrollView) view.findViewById(R.id.hScroll);

        MapImageBall1 = (ImageView) view.findViewById(R.id.map_ball_1);
        MapImageBall1.setOnClickListener(this);

        MapImageQuest1 = (ImageView) view.findViewById(R.id.map_quest_1);
        MapImageQuest1.setOnClickListener(this);

        MapImageMicrophone1 = (ImageView) view.findViewById(R.id.map_microphone_1);
        MapImageMicrophone1.setOnClickListener(this);

        MapImageTent1 = (ImageView) view.findViewById(R.id.map_tent_1);
        MapImageTent1.setOnClickListener(this);

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
    public void onClick(View view) {
        Snackbar snackBar = Snackbar.make(getView(), "", Snackbar.LENGTH_LONG);
        View snackBarView = snackBar.getView();

        snackBarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.actionBarbackground));
        TextView snackBarTextView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        snackBarTextView.setTextColor(Color.WHITE);
        snackBarTextView.setTextSize(18);
        snackBar.setDuration(3000);
        snackBar.setActionTextColor(Color.rgb(255, 127, 0));

        switch (view.getId()) {
            case R.id.map_ball_1:
                snackBar.setText("Мяч");
                break;
            case R.id.map_microphone_1:
                snackBar.setText("Микрофон");
                snackBar.setAction("ПОДРОБНЕЕ", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ActivityLection.class);
                        getActivity().startActivity(intent);
                    }
                });
                break;
            case R.id.map_quest_1:
                snackBar.setText("Квест");
                snackBar.setAction("ПОДРОБНЕЕ", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogQuest.show(getFragmentManager(), "dialogQuest");
                    }
                });
                break;
            case R.id.map_tent_1:
                snackBar.setText("Шатер");
                snackBar.setAction("ПОДРОБНЕЕ", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ActivityTent.class);
                        getActivity().startActivity(intent);
                    }
                });
                break;


        }
        snackBar.show();
    }

    public boolean onTouchEvent(MotionEvent event) {
        float curX, curY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mx = event.getX();
                my = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                curX = event.getX();
                curY = event.getY();
                vScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                hScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                mx = curX;
                my = curY;
                break;
            case MotionEvent.ACTION_UP:
                curX = event.getX();
                curY = event.getY();
                vScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                hScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                break;
        }
        return true;
    }
}
