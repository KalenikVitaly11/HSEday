package org.styleru.hseday.DialogFragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;

import org.styleru.hseday.R;
import org.styleru.hseday.RecyclerViewAdapters.RecyclerViewAdapterLections;


public class ActivityLection extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterLections mAdapter;
    private String[] mList;
    private StaggeredGridLayoutManager mGridLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lection);
        setTitle("Лекция");

        mList = getResources().getStringArray(R.array.lection_1);
        mAdapter = new RecyclerViewAdapterLections(this, mList);
        mRecyclerView = (RecyclerView)findViewById(R.id.lections_recycler_view);
        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setHasOptionsMenu(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
