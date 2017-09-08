package org.styleru.hseday2017_2;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.styleru.hseday2017_2.ApiClasses.ApiComments;
import org.styleru.hseday2017_2.RecyclerViewAdapters.RecyclerViewAdapterComments;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityComments extends AppCompatActivity {
    private String[] commentsTextList;
    private String[] commentsNamesList;
    ArrayList<ApiComments> dataComments;
    DataBaseHelper dbHelper;
    ApiComments myComment;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterComments mAdapter;
    private StaggeredGridLayoutManager mGridLayoutManager;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Комментарии");
        updateData();
        /*Intent intent = getIntent();
        final int eventId = intent.getIntExtra("eventId", 0);
        dataComments = new ArrayList<ApiComments>();
        dbHelper = new DataBaseHelper(this);

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        dataComments.clear();
        Cursor cursorComments = database.query(DataBaseHelper.TABLE_COMMENTS_NAME, null, null, null, null, null, null);
        if(cursorComments.moveToFirst()){
            int idIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_ID);
            int eventIdIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_EVENT_ID);
            int authorIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_AUTHOR);
            int contentIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_CONTENT);
            int timeIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_TIME);
            int imageIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_IMAGE_URL);
            int typeIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_TYPE);
            do{
                myComment = new ApiComments();
                myComment.setId(cursorComments.getInt(idIndex));
                myComment.setEventid(cursorComments.getInt(eventIdIndex));
                myComment.setAuthor(cursorComments.getString(authorIndex));
                myComment.setContent(cursorComments.getString(contentIndex));
                myComment.setTime(cursorComments.getString(timeIndex));
                myComment.setImageurl(cursorComments.getString(imageIndex));
                myComment.setType(cursorComments.getString(typeIndex));
                if(cursorComments.getInt(eventIdIndex) == eventId){
                    dataComments.add(myComment);
                }
            }while(cursorComments.moveToNext());
        }
        cursorComments.close();


        commentsTextList = getResources().getStringArray(R.array.comments_comments);
        commentsNamesList = getResources().getStringArray(R.array.comments_names);

        mAdapter = new RecyclerViewAdapterComments(this, dataComments);
        mRecyclerView = (RecyclerView) findViewById(R.id.comments_recycler_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.comments_floating_button);
        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && floatingActionButton.isShown()) {
                    floatingActionButton.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    floatingActionButton.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SharedPreferences sharedPref = getSharedPreferences("userInfo", MODE_PRIVATE);
                if(sharedPref.getString("VKname", "").equals("") && sharedPref.getString("FBname", "").equals("")){
                    Toast.makeText(getApplicationContext(), "Чтобы оставлять комментарии нужно залогиниться через социальную сеть", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(v.getContext(), ActivityAddComment.class);
                    intent.putExtra("eventid", eventId);
                    v.getContext().startActivity(intent);
                }
            }
        });*/
    }
    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    public void updateData(){
        dataComments = new ArrayList<ApiComments>();
        dbHelper = new DataBaseHelper(this);
        Intent intent = getIntent();
        final int eventId = intent.getIntExtra("eventId", 0);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        dataComments.clear();
        Cursor cursorComments = database.query(DataBaseHelper.TABLE_COMMENTS_NAME, null, null, null, null, null, null);
        if(cursorComments.moveToFirst()){
            int idIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_ID);
            int eventIdIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_EVENT_ID);
            int authorIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_AUTHOR);
            int contentIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_CONTENT);
            int timeIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_TIME);
            int imageIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_IMAGE_URL);
            int typeIndex = cursorComments.getColumnIndex(DataBaseHelper.COMMENTS_TYPE);
            do{
                myComment = new ApiComments();
                myComment.setId(cursorComments.getInt(idIndex));
                myComment.setEventid(cursorComments.getInt(eventIdIndex));
                myComment.setAuthor(cursorComments.getString(authorIndex));
                myComment.setContent(cursorComments.getString(contentIndex));
                myComment.setTime(cursorComments.getString(timeIndex));
                myComment.setImageurl(cursorComments.getString(imageIndex));
                myComment.setType(cursorComments.getString(typeIndex));
                if(cursorComments.getInt(eventIdIndex) == eventId){
                    dataComments.add(myComment);
                }
            }while(cursorComments.moveToNext());
        }
        cursorComments.close();


        commentsTextList = getResources().getStringArray(R.array.comments_comments);
        commentsNamesList = getResources().getStringArray(R.array.comments_names);

        mAdapter = new RecyclerViewAdapterComments(this, dataComments);
        mRecyclerView = (RecyclerView) findViewById(R.id.comments_recycler_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.comments_floating_button);
        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && floatingActionButton.isShown()) {
                    floatingActionButton.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    floatingActionButton.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SharedPreferences sharedPref = getSharedPreferences("userInfo", MODE_PRIVATE);
                if(sharedPref.getString("VKname", "").equals("") && sharedPref.getString("FBname", "").equals("")){
                    Toast.makeText(getApplicationContext(), "Чтобы оставлять комментарии нужно залогиниться через социальную сеть", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(v.getContext(), ActivityAddComment.class);
                    intent.putExtra("eventid", eventId);
                    v.getContext().startActivity(intent);
                }
            }
        });
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
