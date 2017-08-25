package org.styleru.hseday.NavigationFragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.glidebitmappool.GlideBitmapFactory;
import com.glidebitmappool.GlideBitmapPool;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.styleru.hseday.ApiClasses.ApiLectures;
import org.styleru.hseday.ApiClasses.ApiMics;
import org.styleru.hseday.ApiClasses.ApiQuest;
import org.styleru.hseday.ApiClasses.ApiSports;
import org.styleru.hseday.ApiClasses.ApiTents;
import org.styleru.hseday.CustomMarkerTag;
import org.styleru.hseday.DataBaseHelper;
import org.styleru.hseday.DialogFragments.ActivityTent;
import org.styleru.hseday.DialogFragments.ActivityLection;
import org.styleru.hseday.DialogFragments.DialogQuest;
import org.styleru.hseday.MainActivity;
import org.styleru.hseday.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;


public class FragmentMap extends android.support.v4.app.Fragment implements
        GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    MapView mMapView;
    private GoogleMap googleMap;
    ArrayList<ApiQuest> dataQuests;
    ArrayList<ApiTents> dataTents;
    ArrayList<ApiSports> dataSports;
    ArrayList<ApiMics> dataMics;
    ArrayList<ApiLectures> dataLectures;
    ApiQuest myQuest;
    ApiLectures myLecture;
    ApiTents myTent;
    ApiMics myMic;
    ApiSports mySport;
    DataBaseHelper dbHelper;

    public String tagMicrophone = "microphone";
    public String tagQuest = "quest";
    public String tagTent = "tent";
    public String tagLecture = "lecture";
    public  String tagSport = "sport";

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

        DialogQuest = new DialogQuest();
        mMapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

        Bitmap myMap = BitmapFactory.decodeResource(getResources(), R.drawable.map3);

        GroundOverlayOptions newarkMap = new GroundOverlayOptions() // Загрузка картинки в карту
                .position(new LatLng(0, 0), 27000000f, 12735849f)
                .image(BitmapDescriptorFactory.fromBitmap(myMap));

        googleMap.addGroundOverlay(newarkMap);

        LatLng start = new LatLng(0, 0);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(start).zoom(3).build(); // Начальное положение камеры
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        LatLngBounds cameraBorder = new LatLngBounds(  //  Границы движения камеры
                new LatLng(-24, -89), new LatLng(24, 89));
        //googleMap.setLatLngBoundsForCameraTarget(cameraBorder);

        //googleMap.setMinZoomPreference(3f);  // Ограничения по зуму
        //googleMap.setMaxZoomPreference(3.5f);

        googleMap.getUiSettings().setMapToolbarEnabled(false); // Выключить кнопки, которые вылезают сбоку при нажатии на метку
        googleMap.setOnInfoWindowClickListener(this);


        dbHelper = new DataBaseHelper(getContext());
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        dataQuests = new ArrayList<ApiQuest>();
        Cursor cursorQuests = database.query(DataBaseHelper.TABLE_QUESTS_NAME, null, null, null, null, null, null);  // Взятие из БД всех квестов
        if (cursorQuests.moveToFirst()) {
            int idIndex = cursorQuests.getColumnIndex(DataBaseHelper.QUESTS_ID);
            int nameIndex = cursorQuests.getColumnIndex(DataBaseHelper.QUESTS_NAME);
            int numberIndex = cursorQuests.getColumnIndex(DataBaseHelper.QUESTS_NUMBER);
            int descriptionIndex = cursorQuests.getColumnIndex(DataBaseHelper.QUESTS_DESCRIPTION);
            int passcodeIndex = cursorQuests.getColumnIndex(DataBaseHelper.QUESTS_PASSCODE);
            int imageIndex = cursorQuests.getColumnIndex(DataBaseHelper.QUESTS_IMAGE_URL);
            int xcoordinateIndex = cursorQuests.getColumnIndex(DataBaseHelper.QUESTS_XCOORDINATE);
            int ycoordinateIndex = cursorQuests.getColumnIndex(DataBaseHelper.QUESTS_YCOORDINATE);
            do {
                myQuest = new ApiQuest();
                myQuest.setId(cursorQuests.getInt(idIndex));
                myQuest.setName(cursorQuests.getString(nameIndex));
                myQuest.setDescription(cursorQuests.getString(descriptionIndex));
                myQuest.setNumber(cursorQuests.getString(numberIndex));
                myQuest.setPasscode(cursorQuests.getString(passcodeIndex));
                myQuest.setXposition(cursorQuests.getFloat(xcoordinateIndex));
                myQuest.setYposition(cursorQuests.getFloat(ycoordinateIndex));
                myQuest.setImageurl(cursorQuests.getString(imageIndex));
                dataQuests.add(myQuest);
            } while (cursorQuests.moveToNext());
        }
        cursorQuests.close();
        String questName;
        String questDescription;
        for (int i = 0; i < dataQuests.size(); i++) { // Отрисовка всех квестов на карте
            questName = dataQuests.get(i).getName();
            questDescription = dataQuests.get(i).getDescription();
            LatLng quest = new LatLng(dataQuests.get(i).getXposition(), dataQuests.get(i).getYposition());

            Marker marker = googleMap.addMarker(new MarkerOptions().position(quest).title("Квест").snippet(questDescription));
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_quest));
            CustomMarkerTag markerTag = new CustomMarkerTag();
            markerTag.setPointType(tagQuest);
            markerTag.setPointId(dataQuests.get(i).getId());
            marker.setTag(markerTag);
        }


        dataTents = new ArrayList<ApiTents>();
        Cursor cursorTent = database.query(DataBaseHelper.TABLE_TENTS_NAME, null, null, null, null, null, null);
        if (cursorTent.moveToFirst()) {
            int idIndex = cursorTent.getColumnIndex(DataBaseHelper.TENTS_ID);
            int nameIndex = cursorTent.getColumnIndex(DataBaseHelper.TENTS_NAME);
            int descriptionIndex = cursorTent.getColumnIndex(DataBaseHelper.TENTS_DESCRIPTION);
            int xcoordinateIndex = cursorTent.getColumnIndex(DataBaseHelper.TENTS_XCOORDINATE);
            int ycoordinateIndex = cursorTent.getColumnIndex(DataBaseHelper.TENTS_YCOORDINATE);
            do {
                myTent = new ApiTents();
                myTent.setId(cursorTent.getInt(idIndex));
                myTent.setName(cursorTent.getString(nameIndex));
                myTent.setDescription(cursorTent.getString(descriptionIndex));
                myTent.setXposition(cursorTent.getFloat(xcoordinateIndex));
                myTent.setYposition(cursorTent.getFloat(ycoordinateIndex));
                dataTents.add(myTent);
            } while (cursorTent.moveToNext());
        }
        cursorTent.close();
        String tentName;
        String tentDescription;
        for (int i = 0; i < dataTents.size(); i++) {
            tentName = dataTents.get(i).getName();
            tentDescription = dataTents.get(i).getDescription();
            LatLng tent = new LatLng(dataTents.get(i).getXposition(), dataTents.get(i).getYposition());

            Marker marker = googleMap.addMarker(new MarkerOptions().position(tent).title(tentName).snippet(tentDescription));
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_tent));

            CustomMarkerTag markerTag = new CustomMarkerTag();
            markerTag.setPointType(tagTent);
            markerTag.setPointId(dataTents.get(i).getId());
            marker.setTag(markerTag);
        }

        dataSports = new ArrayList<ApiSports>();
        Cursor cursorSports = database.query(DataBaseHelper.TABLE_SPORTS_NAME, null, null, null, null, null, null);
        if (cursorSports.moveToFirst()) {
            int idIndex = cursorSports.getColumnIndex(DataBaseHelper.SPORTS_ID);
            int nameIndex = cursorSports.getColumnIndex(DataBaseHelper.SPORTS_NAME);
            int descriptionIndex = cursorSports.getColumnIndex(DataBaseHelper.SPORTS_DESCRIPTION);
            int xcooordinateIndex = cursorSports.getColumnIndex(DataBaseHelper.SPORTS_XCOORDINATE);
            int ycoordinateIndex = cursorSports.getColumnIndex(DataBaseHelper.SPORTS_YCOORDINATE);
            do {
                mySport = new ApiSports();
                mySport.setId(cursorSports.getInt(idIndex));
                mySport.setName(cursorSports.getString(nameIndex));
                mySport.setDescription(cursorSports.getString(descriptionIndex));
                mySport.setXposition(cursorSports.getFloat(xcooordinateIndex));
                mySport.setYposition(cursorSports.getFloat(ycoordinateIndex));
                dataSports.add(mySport);
            } while (cursorSports.moveToNext());
        }
        cursorSports.close();
        String sportName;
        String sportDescription;
        for (int i = 0; i < dataSports.size(); i++) {
            sportName = dataSports.get(i).getName();
            sportDescription = dataSports.get(i).getDescription();
            LatLng sport = new LatLng(dataSports.get(i).getXposition(), dataSports.get(i).getYposition());

            Marker marker = googleMap.addMarker(new MarkerOptions().position(sport).title(sportName).snippet(sportDescription));
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_ball));
            CustomMarkerTag markerTag = new CustomMarkerTag();
            markerTag.setPointType(tagSport);
            markerTag.setPointId(dataSports.get(i).getId());
            marker.setTag(markerTag);
        }

        dataLectures = new ArrayList<ApiLectures>();
        Cursor cursorLectures = database.query(DataBaseHelper.TABLE_LECTIONS_NAME, null, null, null, null, null, null);
        if (cursorLectures.moveToFirst()) {
            int idIndex = cursorLectures.getColumnIndex(DataBaseHelper.LECTURES_ID);
            int nameIndex = cursorLectures.getColumnIndex(DataBaseHelper.LECTURES_NAME);
            int descriptionIndex = cursorLectures.getColumnIndex(DataBaseHelper.LECTURES_DESCRIPTION);
            int xcoordinateIndex = cursorLectures.getColumnIndex(DataBaseHelper.LECTURES_XCOORDINATE);
            int ycoordinateIndex = cursorLectures.getColumnIndex(DataBaseHelper.LECTURES_YCOORDINATE);
            do {
                myLecture = new ApiLectures();
                myLecture.setId(cursorLectures.getInt(idIndex));
                myLecture.setName(cursorLectures.getString(nameIndex));
                myLecture.setDescription(cursorLectures.getString(descriptionIndex));
                myLecture.setXposition(cursorLectures.getFloat(xcoordinateIndex));
                myLecture.setYposition(cursorLectures.getFloat(ycoordinateIndex));
                dataLectures.add(myLecture);
            } while (cursorLectures.moveToNext());
        }
        cursorLectures.close();
        String lectureName;
        String lectureDescription;
        for (int i = 0; i < dataLectures.size(); i++) {
            lectureName = dataLectures.get(i).getName();
            lectureDescription = dataLectures.get(i).getDescription();
            LatLng lecture = new LatLng(dataLectures.get(i).getXposition(), dataLectures.get(i).getYposition());

            Marker marker = googleMap.addMarker(new MarkerOptions().position(lecture).title(lectureName).snippet(lectureDescription));
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_list));

            CustomMarkerTag markerTag = new CustomMarkerTag();
            markerTag.setPointType(tagLecture);
            markerTag.setPointId(dataLectures.get(i).getId());
            marker.setTag(markerTag);
        }

        dataMics = new ArrayList<ApiMics>();
        Cursor cursorMics = database.query(DataBaseHelper.TABLE_MICROPHONES_NAME, null, null, null, null, null, null);
        if (cursorMics.moveToFirst()) {
            int idIndex = cursorMics.getColumnIndex(DataBaseHelper.MICROPHONES_ID);
            int nameIndex = cursorMics.getColumnIndex(DataBaseHelper.MICROPHONES_NAME);
            int descriptionIndex = cursorMics.getColumnIndex(DataBaseHelper.MICROPHONES_DESCRIPTION);
            int xcoordinateIndex = cursorMics.getColumnIndex(DataBaseHelper.MICROPHONES_XCOORDINATE);
            int ycoordinateIndex = cursorMics.getColumnIndex(DataBaseHelper.MICROPHONES_YCOORDINATE);
            do {
                myMic = new ApiMics();
                myMic.setId(cursorMics.getInt(idIndex));
                myMic.setName(cursorMics.getString(nameIndex));
                myMic.setDescription(cursorMics.getString(descriptionIndex));
                myMic.setXposition(cursorMics.getFloat(xcoordinateIndex));
                myMic.setYposition(cursorMics.getFloat(ycoordinateIndex));
                dataMics.add(myMic);
            } while (cursorMics.moveToNext());
        }
        cursorMics.close();
        String micName;
        String micDescription;
        for (int i = 0; i < dataMics.size(); i++) {
            micName = dataMics.get(i).getName();
            micDescription = dataMics.get(i).getDescription();
            LatLng mic = new LatLng(dataMics.get(i).getXposition(), dataMics.get(i).getYposition());

            Marker marker = googleMap.addMarker(new MarkerOptions().position(mic).title(micName).snippet(micDescription));
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_microphone));
            CustomMarkerTag markerTag = new CustomMarkerTag();
            markerTag.setPointType(tagMicrophone);
            markerTag.setPointId(dataMics.get(i).getId());
            marker.setTag(markerTag);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent;
        CustomMarkerTag myTag = (CustomMarkerTag) marker.getTag();

        if (myTag.getPointType().equals(tagMicrophone) || myTag.getPointType().equals(tagTent) || myTag.getPointType().equals(tagLecture)) {
            intent = new Intent(getActivity(), ActivityLection.class);
            intent.putExtra("pointtype", myTag.getPointType());
            intent.putExtra("pointid", myTag.getPointId());
            getActivity().startActivity(intent);
        } else if (myTag.getPointType().equals(tagQuest)) {
            Bundle args = new Bundle();
            args.putString("description", marker.getSnippet());
            for (int i = 0; i < dataQuests.size(); i++) {
                if (dataQuests.get(i).getDescription().equals(marker.getSnippet())) {
                    args.putString("passcode", dataQuests.get(i).getPasscode());
                    args.putString("name", dataQuests.get(i).getName());
                    args.putString("imageurl", dataQuests.get(i).getImageurl());
                }
            }
            DialogQuest.setArguments(args);
            DialogQuest.show(getFragmentManager(), "dialogQuest");
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mMapView.onDestroy();
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
}
