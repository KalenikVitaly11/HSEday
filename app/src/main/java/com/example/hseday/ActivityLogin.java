package com.example.hseday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.example.hseday.MainActivity.UserImage;
import static com.vk.sdk.VKUIHelper.getApplicationContext;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener{

    private LoginButton FacebookLoginButton;
    private Button VKLoginButton;
    private CallbackManager callbackManager;
    private ProfilePictureView profilePictureView;
    private LinearLayout infoLayout;
    private TextView email;
    private TextView gender;
    private TextView facebookName;

    private String[] scope = new String[]{VKScope.MESSAGES, VKScope.FRIENDS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_login);

        VKLoginButton = (Button) findViewById(R.id.login_vk_button);
        VKLoginButton.setOnClickListener(this);

        FacebookLoginButton = (LoginButton)findViewById(R.id.login_facebook_button);

        FacebookLoginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        FacebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("Main", response.toString());
                                setProfileToView(object);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "Error to Login Facebook", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setProfileToView(JSONObject jsonObject) {
        try {

            MainActivity.UserName.setText(jsonObject.getString("name"));
            MainActivity.UserImage.getLayoutParams().height = 200;
            MainActivity.UserImage.getLayoutParams().width = 200;

            //profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
            //MainActivity.UserImage.setProfileId(jsonObject.getString("id"));
            Glide.with(getApplicationContext()).load("https://graph.facebook.com/" + jsonObject.getString("id") + "/picture?type=large").into(UserImage);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        finish();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login_vk_button:
                VKSdk.login(this, scope);
                VKRequest profileInfo = VKApi.users().get();
                profileInfo.executeWithListener(new VKRequest.VKRequestListener()
                {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        VKList<VKApiUser> userList = (VKList<VKApiUser>) response.parsedModel;
                        VKApiUser user = userList.get(0);
                        MainActivity.UserName.setText(user.first_name + " " + user.last_name);
                        MainActivity.UserImage.getLayoutParams().height = 200;
                        MainActivity.UserImage.getLayoutParams().width = 200;
                        Glide.with(getApplicationContext()).load(user.photo_50).into(UserImage);
                        Toast.makeText(getApplicationContext(),user.first_name + " " + user.last_name, Toast.LENGTH_SHORT).show();

                        finish();
                    }
                });

            break;

        }
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