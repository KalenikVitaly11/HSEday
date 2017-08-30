package org.styleru.hseday;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.styleru.hseday.ApiClasses.ApiFaculties;


public class AboutFacultyActivity extends AppCompatActivity {
    ImageView facultyImage;
    TextView facultyTitle;
    TextView facultyInformation;
    TextView facultyDepartments;
    TextView facultyContacts;
    ApiFaculties myFaculty;
    DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_faculty);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        facultyImage = (ImageView) this.findViewById(R.id.faculty);
        facultyTitle = (TextView) this.findViewById(R.id.faculty_title);
        facultyInformation = (TextView) this.findViewById(R.id.faculty_information);
        facultyDepartments = (TextView) this.findViewById(R.id.faculty_departments);
        facultyContacts = (TextView) this.findViewById(R.id.faculty_contacts);
        Intent intent = getIntent();
        myFaculty = new ApiFaculties();
        myFaculty.setName(String.valueOf(intent.getStringExtra("facultyName")));
        setTitle(myFaculty.getName());

        dbHelper = new DataBaseHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor c = database.query(DataBaseHelper.TABLE_FACULTIES_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int nameIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_NAME);
            int descriptionIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_DESCRIPTION);
            int contactsIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_CONTACTS);
            int imageIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_IMAGE_URL);

            do {
                if(c.getString(nameIndex).equals(myFaculty.getName())){
                    myFaculty.setDescription(c.getString(descriptionIndex));
                    myFaculty.setContacts(c.getString(contactsIndex));
                    myFaculty.setImageurl(c.getString(imageIndex));
                    break;
                }
            } while (c.moveToNext());
        }
        c.close();
        facultyTitle.setText(myFaculty.getName());
        facultyInformation.setText(myFaculty.getDescription());
        facultyContacts.setText(myFaculty.getContacts());

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
