package com.example.hseday;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutOrganisationActivity extends AppCompatActivity {
    ImageView OrganisationImage;
    TextView OrganisationTitle;
    TextView OrganisationInformation;
    TextView OrganisationContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_organisation);

        OrganisationImage = (ImageView) this.findViewById(R.id.OrganisationImage);
        OrganisationTitle = (TextView) this.findViewById(R.id.OrganisationTitle);
        OrganisationInformation = (TextView) this.findViewById(R.id.OrganisationInformation);
        OrganisationContacts = (TextView) this.findViewById(R.id.OrganisationContacts);

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
