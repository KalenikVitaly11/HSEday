package org.styleru.hseday;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.styleru.hseday.ApiClasses.ApiFaculties;
import org.styleru.hseday.ApiClasses.ApiLectures;
import org.styleru.hseday.ApiClasses.ApiMics;
import org.styleru.hseday.ApiClasses.ApiOrganisations;
import org.styleru.hseday.ApiClasses.ApiQuest;
import org.styleru.hseday.ApiClasses.ApiSports;
import org.styleru.hseday.ApiClasses.ApiTents;
import org.styleru.hseday.NavigationFragments.FragmentMap;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    FragmentMap FragmentMap;
    public ArrayList<ApiOrganisations> dataOrganisations;
    public ArrayList<ApiFaculties> dataFaculties;
    public ArrayList<ApiQuest> dataQuests;
    public ArrayList<ApiTents> dataTents;
    public ArrayList<ApiMics> dataMics;
    public ArrayList<ApiSports> dataSports;
    public ArrayList<ApiLectures> dataLectures;
    DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
