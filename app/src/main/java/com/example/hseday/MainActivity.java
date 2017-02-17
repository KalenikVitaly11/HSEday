package com.example.hseday;


import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hseday.NavigationFragments.FragmentAboutHSE;
import com.example.hseday.NavigationFragments.FragmentDedication;
import com.example.hseday.NavigationFragments.FragmentFaculties;
import com.example.hseday.NavigationFragments.FragmentMap;
import com.example.hseday.NavigationFragments.FragmentOrganisations;

import static android.view.KeyCharacterMap.load;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //ImageView MapImage;
    int resourceId = R.drawable.map_park;
    FragmentFaculties FragmentFaculties;
    FragmentOrganisations FragmentOrganisations;
    FragmentAboutHSE FragmentAboutHSE;
    FragmentDedication FragmentDedication;
    FragmentMap FragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Карта");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //MapImage = (ImageView) this.findViewById(R.id.map_image);

        FragmentFaculties = new FragmentFaculties();
        FragmentOrganisations = new FragmentOrganisations();
        FragmentAboutHSE = new FragmentAboutHSE();
        FragmentDedication = new FragmentDedication();
        FragmentMap = new FragmentMap();




        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_map, FragmentMap);
        transaction.commit();

    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.map_quest_mark && item.isChecked() == true)Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
        if(item.isChecked()){
            item.setChecked(false);
        }
        else{
            item.setChecked(true);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_map) {
            transaction.replace(R.id.content_map, FragmentMap);
        } else if (id == R.id.nav_faculties) {
            transaction.replace(R.id.content_map, FragmentFaculties);
        } else if (id == R.id.nav_organisations) {
            transaction.replace(R.id.content_map, FragmentOrganisations);
        } else if (id == R.id.nav_aboutHSE) {
            transaction.replace(R.id.content_map, FragmentAboutHSE);
        } else if (id == R.id.nav_posvyashenie) {
            transaction.replace(R.id.content_map, FragmentDedication);
        }
        transaction.addToBackStack(null);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





}
