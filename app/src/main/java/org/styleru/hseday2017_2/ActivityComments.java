package org.styleru.hseday2017_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

import org.styleru.hseday2017_2.ApiClasses.ApiComments;
import org.styleru.hseday2017_2.RecyclerViewAdapters.RecyclerViewAdapterComments;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityComments extends AppCompatActivity {
    ArrayList<ApiComments> dataComments;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterComments mAdapter;
    private StaggeredGridLayoutManager mGridLayoutManager;
    private FloatingActionButton floatingActionButton;
    private SpinKitView mSpinKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Комментарии");
    }
    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    public void updateData(){
        dataComments = new ArrayList<ApiComments>();
        Intent intent = getIntent();
        final int eventId = intent.getIntExtra("eventId", 0);


        dataComments = new ArrayList<ApiComments>();
        HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
        Call<List<ApiComments>> callComments = hseDayApi.getCommentById(eventId);
        callComments.enqueue(new Callback<List<ApiComments>>() {
            @Override
            public void onResponse(Call<List<ApiComments>> call, Response<List<ApiComments>> response) {
                dataComments.addAll(response.body());
                for(int i = 0;i < dataComments.size(); i++){
                    Log.d("myLogs", dataComments.get(i).getAuthor());
                }
                Log.d("myLogs", String.valueOf(dataComments.size()));
            }

            @Override
            public void onFailure(Call<List<ApiComments>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Требуется подключение к интернету", Toast.LENGTH_SHORT).show();
            }
        });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter = new RecyclerViewAdapterComments(getApplicationContext(), dataComments);
                mRecyclerView = (RecyclerView) findViewById(R.id.comments_recycler_view);
                mSpinKit = (SpinKitView) findViewById(R.id.comments_spin_kit);
                floatingActionButton = (FloatingActionButton) findViewById(R.id.comments_floating_button);
                mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setLayoutManager(mGridLayoutManager);
                mAdapter.setHasStableIds(true);
                mRecyclerView.setAdapter(mAdapter);
                mSpinKit.setVisibility(View.GONE);

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
        }, 2000);




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
