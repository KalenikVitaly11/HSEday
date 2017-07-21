package org.styleru.hseday.NavigationFragments;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.styleru.hseday.DialogFragments.ActivityTent;
import org.styleru.hseday.DialogFragments.ActivityLection;
import org.styleru.hseday.DialogFragments.DialogQuest;
import org.styleru.hseday.MainActivity;
import org.styleru.hseday.R;


public class FragmentMap extends android.support.v4.app.Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    MapView mMapView;
    private GoogleMap googleMap;


    private float mx, my;
    private float curX, curY;

    private ScrollView vScroll;
    private HorizontalScrollView hScroll;

    org.styleru.hseday.DialogFragments.DialogQuest DialogQuest;


    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    ImageView MapImage;
    ImageView MapImageBall1;
    ImageView MapImageQuest1;
    ImageView MapImageTent1;
    ImageView MapImageMicrophone1;
    ImageView quest1;

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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Карта");
        View view = inflater.inflate(org.styleru.hseday.R.layout.fragment_map, container, false);
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);


                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(0, 0);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(3).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                        .image(BitmapDescriptorFactory.fromResource(R.drawable.map_park2))
                        .position(new LatLng(0, 0), 27000000f, 9000000f);
                googleMap.addGroundOverlay(newarkMap);
                LatLngBounds ADELAIDE = new LatLngBounds(
                        new LatLng(-20, -100), new LatLng(20, 100));
                googleMap.setLatLngBoundsForCameraTarget(ADELAIDE);
                googleMap.setMinZoomPreference(2.5f);
                googleMap.setMaxZoomPreference(4f);

                /*LatLngBounds AUSTRALIA = new LatLngBounds(
                        new LatLng(10000, 10000), new LatLng(30000, 30000));

                LatLngBounds ADELAIDE = new LatLngBounds(
                        new LatLng(-20, -20), new LatLng(20, 20));

                googleMap.setMinZoomPreference(2.5f);
                googleMap.setMaxZoomPreference(3.5f);
                googleMap.setLatLngBoundsForCameraTarget(ADELAIDE);*/
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*if (item.isChecked()) {
            item.setChecked(false);
        } else{
            item.setChecked(true);
        }

        switch(id){
            case org.styleru.hseday.R.id.map_quest_mark:
                if (item.isChecked()) {
                    MapImageQuest1.setVisibility(View.VISIBLE);
                } else{
                    MapImageQuest1.setVisibility(View.INVISIBLE);
                }
                break;
            case org.styleru.hseday.R.id.map_ball:
                if (item.isChecked()) {
                    MapImageBall1.setVisibility(View.VISIBLE);
                } else{
                    MapImageBall1.setVisibility(View.INVISIBLE);
                }
                break;
            case org.styleru.hseday.R.id.map_paper:
                if (item.isChecked()) {
                    //MapImageTent1.setVisibility(View.VISIBLE);
                } else{
                    //MapImageTent1.setVisibility(View.INVISIBLE);
                }
                break;
            case org.styleru.hseday.R.id.map_tent:
                if (item.isChecked()) {
                    MapImageTent1.setVisibility(View.VISIBLE);
                } else{
                    MapImageTent1.setVisibility(View.INVISIBLE);
                }
                break;
            case org.styleru.hseday.R.id.map_microphone:
                if (item.isChecked()) {
                    MapImageMicrophone1.setVisibility(View.VISIBLE);
                } else{
                    MapImageMicrophone1.setVisibility(View.INVISIBLE);
                }
                break;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Snackbar snackBar = Snackbar.make(getView(), "", Snackbar.LENGTH_LONG);
        View snackBarView = snackBar.getView();

        snackBarView.setBackgroundColor(ContextCompat.getColor(getActivity(), org.styleru.hseday.R.color.actionBarbackground));
        TextView snackBarTextView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        snackBarTextView.setTextColor(Color.WHITE);
        snackBarTextView.setTextSize(18);
        snackBar.setDuration(3000);
        snackBar.setActionTextColor(Color.rgb(255, 127, 0));


        snackBar.show();
    }

}
