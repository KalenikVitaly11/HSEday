package com.example.hseday.DialogFragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hseday.R;

public class ActivityTent extends AppCompatActivity {
    private TextView TentTitle;
    private TextView TentText;
    private TextView TentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tent);
        setTitle("Шатер");

        TentTitle = (TextView) findViewById(R.id.tent_title);
        TentText = (TextView) findViewById(R.id.tent_text);
        TentTime = (TextView) findViewById(R.id.tent_time);

        TentTitle.setText(this.getResources().getText(R.string.tent_title_1).toString());
        TentText.setText(this.getResources().getText(R.string.tent_text_1).toString());
        TentTime.setText(this.getResources().getText(R.string.tent_time_1).toString());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
