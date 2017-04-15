package com.example.hseday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class AboutFacultyActivity extends AppCompatActivity {
    ImageView facultyImage;
    TextView facultyTitle;
    TextView facultyInformation;
    TextView facultyDepartments;
    TextView facultyContancts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_faculty);

        facultyImage = (ImageView) this.findViewById(R.id.faculty);
        facultyTitle = (TextView) this.findViewById(R.id.faculty_title);
        facultyInformation = (TextView) this.findViewById(R.id.faculty_information);
        facultyDepartments = (TextView) this.findViewById(R.id.faculty_departments);
        facultyContancts = (TextView) this.findViewById(R.id.faculty_contacts);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        switch(intent.getIntExtra("FacultyImage", 1)){
            case 1: facultyImage.setImageResource(R.drawable.faculty_1);break;
            case 2: facultyImage.setImageResource(R.drawable.faculty_2);break;
            case 3: facultyImage.setImageResource(R.drawable.faculty_3);break;
            case 4: facultyImage.setImageResource(R.drawable.faculty_4);break;
            case 5: facultyImage.setImageResource(R.drawable.faculty_5);break;
            case 6: facultyImage.setImageResource(R.drawable.faculty_6);break;
            case 7: facultyImage.setImageResource(R.drawable.faculty_7);break;
            case 8: facultyImage.setImageResource(R.drawable.faculty_8);break;
            case 9: facultyImage.setImageResource(R.drawable.faculty_9);break;
            case 10: facultyImage.setImageResource(R.drawable.faculty_10);break;
            case 11: facultyImage.setImageResource(R.drawable.faculty_11);break;
            case 12: facultyImage.setImageResource(R.drawable.faculty_12);break;
            case 13: facultyImage.setImageResource(R.drawable.faculty_13);break;
            case 14: facultyImage.setImageResource(R.drawable.faculty_14);break;
            case 15: facultyImage.setImageResource(R.drawable.faculty_15);break;
            case 16: facultyImage.setImageResource(R.drawable.faculty_16);break;

        }
        setTitle(intent.getIntExtra("FacultyName", 1));
        facultyTitle.setText(intent.getIntExtra("FacultyName", 1));
        facultyInformation.setText(intent.getIntExtra("FacultyInformation", 1));
        facultyContancts.setText(intent.getIntExtra("FacultyContacts", 1));
        facultyDepartments.setText(intent.getIntExtra("FacultyDepartments", 1));

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
