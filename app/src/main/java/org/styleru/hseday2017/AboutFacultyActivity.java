package org.styleru.hseday2017;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.styleru.hseday2017.ApiClasses.ApiFaculties;
import org.styleru.hseday2017.RecyclerViewAdapters.RecyclerViewAdapterDepartments;

import java.util.ArrayList;


public class AboutFacultyActivity extends AppCompatActivity {
    ImageView facultyImage;
    TextView facultyTitle;
    TextView facultyInformation;
    TextView facultyContacts;
    RecyclerView departmentsRecyclerView;
    RecyclerViewAdapterDepartments mAdapter;
    StaggeredGridLayoutManager mGridLayoutManager;
    ArrayList<String> dataDepartments;
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
        facultyContacts = (TextView) this.findViewById(R.id.faculty_contacts);
        departmentsRecyclerView = (RecyclerView) this.findViewById(R.id.departments_recyclerview);
        Intent intent = getIntent();
        myFaculty = new ApiFaculties();
        myFaculty.setName(String.valueOf(intent.getStringExtra("facultyName")));
        setTitle(myFaculty.getName());

        dbHelper = new DataBaseHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor c = database.query(DataBaseHelper.TABLE_FACULTIES_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_ID);
            int nameIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_NAME);
            int descriptionIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_DESCRIPTION);
            int contactsIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_CONTACTS);
            int imageIndex = c.getColumnIndex(DataBaseHelper.FACULTIES_IMAGE_URL);

            do {
                if (c.getString(nameIndex).equals(myFaculty.getName())) {
                    myFaculty.setId(c.getInt(idIndex));
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
        Glide.with(this).load(myFaculty.getImageurl()).into(facultyImage);
        dataDepartments = new ArrayList<String>();
        Cursor departmentsCursor = database.query(DataBaseHelper.TABLE_DEPARTMENTS_NAME, null, null, null, null, null, null);
        if(departmentsCursor.moveToFirst()){
            int nameIndex = departmentsCursor.getColumnIndex(DataBaseHelper.DEPARTMENTS_NAME);
            int parentIdIndex = departmentsCursor.getColumnIndex(DataBaseHelper.DEPARTMENTS_FACULTY_ID);
            do{
                if(departmentsCursor.getInt(parentIdIndex) == myFaculty.getId()){
                    dataDepartments.add(departmentsCursor.getString(nameIndex));
                }
            }while(departmentsCursor.moveToNext());
        }
        departmentsCursor.close();
        mAdapter = new RecyclerViewAdapterDepartments(getApplicationContext(), dataDepartments);
        mGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        departmentsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        departmentsRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter.setHasStableIds(true);
        departmentsRecyclerView.setAdapter(mAdapter);
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
