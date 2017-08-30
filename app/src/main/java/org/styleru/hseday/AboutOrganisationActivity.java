package org.styleru.hseday;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.styleru.hseday.ApiClasses.ApiOrganisations;

public class AboutOrganisationActivity extends AppCompatActivity {
    ImageView organisationImage;
    TextView organisationTitle;
    TextView organisationInformation;
    TextView organisationContacts;
    DataBaseHelper dbHelper;
    ApiOrganisations myOrganisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_organisation);

        organisationImage = (ImageView) this.findViewById(R.id.organisation_image);
        organisationTitle = (TextView) this.findViewById(R.id.organisation_title);
        organisationInformation = (TextView) this.findViewById(R.id.organisation_information);
        organisationContacts = (TextView) this.findViewById(R.id.organisation_contancts);

        Intent intent = getIntent();
        myOrganisation = new ApiOrganisations();
        myOrganisation.setName(String.valueOf(intent.getStringExtra("organisationName")));

        dbHelper = new DataBaseHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor c = database.query(DataBaseHelper.TABLE_ORGANISATIONS_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int nameIndex = c.getColumnIndex(DataBaseHelper.ORGANISATION_NAME);
            int descriptionIndex = c.getColumnIndex(DataBaseHelper.ORGANISATION_DESCRIPTION);
            int contactsIndex = c.getColumnIndex(DataBaseHelper.ORGANISATION_CONTACTS);
            int imageIndex = c.getColumnIndex(DataBaseHelper.ORGANISATION_IMAGE_URL);

            do {
                if(c.getString(nameIndex).equals(myOrganisation.getName())){
                    myOrganisation.setDescription(c.getString(descriptionIndex));
                    myOrganisation.setContacts(c.getString(contactsIndex));
                    myOrganisation.setImageurl(c.getString(imageIndex));
                    break;
                }
            } while (c.moveToNext());
        }
        c.close();
        //myOrganisation.setName("123123");

        setTitle(myOrganisation.getName());
        organisationTitle.setText(myOrganisation.getName());
        organisationInformation.setText(myOrganisation.getDescription());
        organisationContacts.setText(myOrganisation.getContacts());
        Glide.with(this).load(myOrganisation.getImageurl()).into(organisationImage);

        //organisationImage;


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
