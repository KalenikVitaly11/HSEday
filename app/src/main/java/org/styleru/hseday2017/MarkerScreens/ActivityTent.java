package org.styleru.hseday2017.MarkerScreens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityTent extends AppCompatActivity {
    private TextView TentTitle;
    private TextView TentText;
    private TextView TentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.styleru.hseday2017.R.layout.activity_tent);
        setTitle("Шатер");

        TentTitle = (TextView) findViewById(org.styleru.hseday2017.R.id.tent_title);
        TentText = (TextView) findViewById(org.styleru.hseday2017.R.id.tent_text);
        TentTime = (TextView) findViewById(org.styleru.hseday2017.R.id.tent_time);

        TentTitle.setText(this.getResources().getText(org.styleru.hseday2017.R.string.tent_title_1).toString());
        TentText.setText(this.getResources().getText(org.styleru.hseday2017.R.string.tent_text_1).toString());
        TentTime.setText(this.getResources().getText(org.styleru.hseday2017.R.string.tent_time_1).toString());

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
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(org.styleru.hseday2017.R.anim.slide_in_left, org.styleru.hseday2017.R.anim.slide_out_right);
    }

}
