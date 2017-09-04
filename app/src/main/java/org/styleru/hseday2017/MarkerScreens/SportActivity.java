package org.styleru.hseday2017.MarkerScreens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.styleru.hseday2017.R;
import org.w3c.dom.Text;

public class SportActivity extends AppCompatActivity {
    TextView sportName;
    TextView sportInfo;
    ImageView sportImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sportName = (TextView) findViewById(R.id.sport_title);
        sportInfo = (TextView) findViewById(R.id.sport_description);
        sportImage = (ImageView) findViewById(R.id.sport_image);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String info = intent.getStringExtra("info");
        String imageUrl = intent.getStringExtra("image");
        sportName.setText(name);
        sportInfo.setText(info);
        Glide.with(this).load(imageUrl).into(sportImage);
        //Glide.with(this).load("http://dayhse.styleru.net/docs/img/spr/evs.jpg").into(sportImage);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
