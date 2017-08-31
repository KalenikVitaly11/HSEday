package org.styleru.hseday2017.DialogFragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;

import org.styleru.hseday2017.ApiClasses.ApiEvents;
import org.styleru.hseday2017.DataBaseHelper;
import org.styleru.hseday2017.R;
import org.styleru.hseday2017.RecyclerViewAdapters.RecyclerViewAdapterLections;

import java.util.ArrayList;


public class ActivityLection extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterLections mAdapter;
    private StaggeredGridLayoutManager mGridLayoutManager;
    DataBaseHelper dbHelper;
    ArrayList<ApiEvents> dataEvents;
    ApiEvents myEvent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Лекция");

        Intent intent = getIntent();
        String pointType = intent.getStringExtra("pointtype");
        Integer pointId = intent.getIntExtra("pointid", 1);
        Log.d("tags", pointType + "   " + String.valueOf(pointId));

        dataEvents = new ArrayList<ApiEvents>();
        dbHelper = new DataBaseHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursorEvent = database.query(DataBaseHelper.TABLE_EVENTS_NAME, null, null, null, null, null, null);
        if(cursorEvent.moveToFirst()){
            int pointIdIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_POINTID);
            int pointTypeIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_POINTTYPE);
            int nameIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_NAME);
            int descriptionIndex= cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_DESCRIPTION);
            int starttimeIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_STARTTIME);
            int endtimeIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_ENDTIME);
            do{
                if(cursorEvent.getString(pointTypeIndex).equals(pointType) && cursorEvent.getInt(pointIdIndex) == pointId){
                    myEvent = new ApiEvents();
                    myEvent.setName(cursorEvent.getString(nameIndex));
                    myEvent.setDescription(cursorEvent.getString(descriptionIndex));
                    myEvent.setStarttime(cursorEvent.getString(starttimeIndex));
                    myEvent.setEndtime(cursorEvent.getString(endtimeIndex));
                    dataEvents.add(myEvent);
                }
                Log.d("tags", cursorEvent.getString(nameIndex) + "   " + cursorEvent.getString(pointTypeIndex) + "  id =  " + String.valueOf(cursorEvent.getInt(pointIdIndex)));
            }while (cursorEvent.moveToNext());
        }


        mAdapter = new RecyclerViewAdapterLections(this, dataEvents);
        mRecyclerView = (RecyclerView) findViewById(R.id.lections_recycler_view);
        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        //setHasOptionsMenu(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
