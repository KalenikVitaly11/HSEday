package org.styleru.hseday2017_2.MarkerScreens;

import android.content.ContentValues;
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
import android.widget.TextView;
import android.widget.Toast;

import org.styleru.hseday2017_2.ApiClasses.ApiComments;
import org.styleru.hseday2017_2.ApiClasses.ApiEvents;
import org.styleru.hseday2017_2.DataBaseHelper;
import org.styleru.hseday2017_2.HseDayApi;
import org.styleru.hseday2017_2.R;
import org.styleru.hseday2017_2.RecyclerViewAdapters.RecyclerViewAdapterLections;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityLection extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterLections mAdapter;
    private StaggeredGridLayoutManager mGridLayoutManager;
    DataBaseHelper dbHelper;
    ArrayList<ApiEvents> dataEvents;
    public ArrayList<ApiComments> dataComments;
    ApiEvents myEvent;
    TextView lectureName;
    TextView lectureInfo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //lectureInfo = (TextView)findViewById(R.id.lection_info);
        //lectureName = (TextView) findViewById(R.id.lection_title);

        Intent intent = getIntent();
        String pointType = intent.getStringExtra("pointtype");
        Integer pointId = intent.getIntExtra("pointid", 1);
        Log.d("tags", pointType + "   " + String.valueOf(pointId));
        if(pointType.equals("tent")){
            setTitle("Тент");
        } else if(pointType.equals("lecture")){
            setTitle("Лекция");
        } else if(pointType.equals("mic")){
            setTitle("Музыка");
        }

        dataEvents = new ArrayList<ApiEvents>();
        myEvent = new ApiEvents();
        myEvent.setName("marker"); // Это своего рода ключ, который показывает, что это запись для описания самой метки (используется в адаптере)
        myEvent.setDescription(intent.getStringExtra("info"));
        dataEvents.add(myEvent);

        dbHelper = new DataBaseHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursorEvent = database.query(DataBaseHelper.TABLE_EVENTS_NAME, null, null, null, null, null, null);
        if(cursorEvent.moveToFirst()){
            int idIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_ID);
            int pointIdIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_POINTID);
            int pointTypeIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_POINTTYPE);
            int nameIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_NAME);
            int descriptionIndex= cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_DESCRIPTION);
            int starttimeIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_STARTTIME);
            int endtimeIndex = cursorEvent.getColumnIndex(DataBaseHelper.EVENTS_ENDTIME);
            do{
                if(cursorEvent.getString(pointTypeIndex).equals(pointType) && cursorEvent.getInt(pointIdIndex) == pointId){
                    myEvent = new ApiEvents();
                    myEvent.setId(cursorEvent.getInt(idIndex));
                    myEvent.setName(cursorEvent.getString(nameIndex));
                    myEvent.setDescription(cursorEvent.getString(descriptionIndex));
                    myEvent.setStarttime(cursorEvent.getString(starttimeIndex));
                    myEvent.setEndtime(cursorEvent.getString(endtimeIndex));
                    dataEvents.add(myEvent);
                }
            }while (cursorEvent.moveToNext());
        }
        //lectureName.setText(intent.getStringExtra("name"));
        //lectureInfo.setText(intent.getStringExtra("info"));

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
