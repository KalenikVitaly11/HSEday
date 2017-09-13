package org.styleru.hseday2017_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vk.sdk.VKSdk;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityAddComment extends AppCompatActivity implements View.OnClickListener {
    Button sendComment;
    EditText commentContent;
    String userName;
    int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Новый комментарий");
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_add_comment);
        Intent intent = getIntent();
        eventId = intent.getIntExtra("eventid", 0);
        Log.d("myLogs", String.valueOf(eventId));
        commentContent = (EditText) findViewById(R.id.comment_content);
        sendComment = (Button) findViewById(R.id.button_send_comment);
        SharedPreferences sharedPref = getSharedPreferences("userInfo", MODE_PRIVATE);
        if (VKSdk.isLoggedIn()) {
            userName = sharedPref.getString("VKname", "");
        } else {
            userName = sharedPref.getString("FBname", "");
        }
        sendComment.setOnClickListener(this);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_send_comment:
                if (commentContent.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Но тут ведь пусто", Toast.LENGTH_LONG).show();
                } else {
                    //URLEncoder.encode(userName, "UTF-8");
                    HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
                    Call<ResponseBody> postComment = hseDayApi.postComment(userName, commentContent.getText().toString(), eventId);
                    postComment.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                    finish();
                }

                break;
        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
