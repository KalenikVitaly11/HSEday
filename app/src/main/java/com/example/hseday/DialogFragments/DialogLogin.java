package com.example.hseday.DialogFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hseday.MainActivity;
import com.example.hseday.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.hseday.MainActivity.UserImage;
import static com.example.hseday.R.id.login_facebook_button;
import static com.vk.sdk.VKUIHelper.getApplicationContext;

public class DialogLogin extends DialogFragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String[] scope = new String[]{VKScope.MESSAGES, VKScope.FRIENDS};

    private String mParam1;
    private String mParam2;
    Button VKLoginButton;
    Button FacebookLoginButton;

    CallbackManager cm;

    private OnFragmentInteractionListener mListener;

    public DialogLogin() {
    }
    public static DialogLogin newInstance(String param1, String param2) {
        DialogLogin fragment = new DialogLogin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View view = inflater.inflate(R.layout.fragment_dialog_login, null);
        VKLoginButton = (Button) view.findViewById(R.id.login_vk_button);
        VKLoginButton.setOnClickListener(this);
        FacebookLoginButton = (LoginButton) view.findViewById(login_facebook_button);
        FacebookLoginButton.setOnClickListener(this);

        cm = CallbackManager.Factory.create();


        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login_vk_button:
                Toast.makeText(getActivity(), "123", Toast.LENGTH_SHORT).show();
                VKSdk.login(getActivity(), scope);
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

                    }
                });
                break;
            case R.id.login_facebook_button:





        }
        dismiss();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
