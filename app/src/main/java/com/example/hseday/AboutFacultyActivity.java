package com.example.hseday;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hseday.NavigationFragments.FragmentFaculties;

public class AboutFacultyActivity extends AppCompatActivity {
    ImageView FacultyImage;
    TextView FacultyTitle;
    TextView FacultyInformation;
    TextView FacultyDepartments;
    TextView FacultyContancts;

    com.example.hseday.NavigationFragments.FragmentFaculties FragmentFaculties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_faculty);

        FacultyImage = (ImageView) this.findViewById(R.id.Faculty);
        FacultyTitle = (TextView) this.findViewById(R.id.FacultyTitle);
        FacultyInformation = (TextView) this.findViewById(R.id.FacultyInformation);
        FacultyDepartments = (TextView) this.findViewById(R.id.FacultyDepartments);
        FacultyContancts = (TextView) this.findViewById(R.id.FacultyContacts);

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
