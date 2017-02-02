package com.example.hseday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class AboutFacultyActivity extends AppCompatActivity {
    ImageView FacultyImage;
    TextView FacultyTitle;
    TextView FacultyInformation;
    TextView FacultyDepartments;
    TextView FacultyContancts;
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

        Intent intent = getIntent();
        switch(intent.getIntExtra("FacultyImage", 1)){
            case 1: FacultyImage.setImageResource(R.drawable.faculty_1);break;
            case 2: FacultyImage.setImageResource(R.drawable.faculty_2);break;
            case 3: FacultyImage.setImageResource(R.drawable.faculty_3);break;
            case 4: FacultyImage.setImageResource(R.drawable.faculty_4);break;
            case 5: FacultyImage.setImageResource(R.drawable.faculty_5);break;
            case 6: FacultyImage.setImageResource(R.drawable.faculty_6);break;
            case 7: FacultyImage.setImageResource(R.drawable.faculty_7);break;
            case 8: FacultyImage.setImageResource(R.drawable.faculty_8);break;
            case 9: FacultyImage.setImageResource(R.drawable.faculty_9);break;
            case 10: FacultyImage.setImageResource(R.drawable.faculty_10);break;
            case 11: FacultyImage.setImageResource(R.drawable.faculty_11);break;
            case 12: FacultyImage.setImageResource(R.drawable.faculty_12);break;
            case 13: FacultyImage.setImageResource(R.drawable.faculty_13);break;
            case 14: FacultyImage.setImageResource(R.drawable.faculty_14);break;
            case 15: FacultyImage.setImageResource(R.drawable.faculty_15);break;
            case 16: FacultyImage.setImageResource(R.drawable.faculty_16);break;

        }
        FacultyTitle.setText(intent.getIntExtra("FacultyName", 1));
        FacultyInformation.setText(intent.getIntExtra("FacultyInformation", 1));
        FacultyContancts.setText(intent.getIntExtra("FacultyContacts", 1));
        FacultyDepartments.setText(intent.getIntExtra("FacultyContacts", 1));

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
