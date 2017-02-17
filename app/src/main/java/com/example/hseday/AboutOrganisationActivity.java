package com.example.hseday;

import android.content.Intent;
import android.os.Bundle;
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

        Intent intent = getIntent();
        OrganisationTitle.setText(String.valueOf(intent.getIntExtra("OrganisationName", 1)));
        OrganisationInformation.setText(String.valueOf(intent.getIntExtra("OrganisationInformation", 1)));
        OrganisationContacts.setText(String.valueOf(intent.getIntExtra("OrganisationContacts", 1)));

        switch(intent.getIntExtra("OrganisationImage", 1)){
            case 0: OrganisationImage.setImageResource(R.drawable.organisation_image_1);break;
            case 1: OrganisationImage.setImageResource(R.drawable.organisation_image_2);break;
            case 2: OrganisationImage.setImageResource(R.drawable.organisation_image_3);break;
            case 3: OrganisationImage.setImageResource(R.drawable.organisation_image_4);break;
            case 4: OrganisationImage.setImageResource(R.drawable.organisation_image_5);break;
            case 5: OrganisationImage.setImageResource(R.drawable.organisation_image_6);break;
            case 6: OrganisationImage.setImageResource(R.drawable.organisation_image_7);break;
            case 7: OrganisationImage.setImageResource(R.drawable.organisation_image_8);break;
            case 8: OrganisationImage.setImageResource(R.drawable.organisation_image_9);break;
            case 9: OrganisationImage.setImageResource(R.drawable.organisation_image_10);break;
            case 10: OrganisationImage.setImageResource(R.drawable.organisation_image_11);break;
            case 11: OrganisationImage.setImageResource(R.drawable.organisation_image_12);break;
            case 12: OrganisationImage.setImageResource(R.drawable.organisation_image_13);break;
            case 13: OrganisationImage.setImageResource(R.drawable.organisation_image_14);break;
            case 14: OrganisationImage.setImageResource(R.drawable.organisation_image_15);break;
            case 15: OrganisationImage.setImageResource(R.drawable.organisation_image_16);break;
            case 16: OrganisationImage.setImageResource(R.drawable.organisation_image_17);break;
            case 17: OrganisationImage.setImageResource(R.drawable.organisation_image_18);break;
            case 18: OrganisationImage.setImageResource(R.drawable.organisation_image_19);break;
            case 19: OrganisationImage.setImageResource(R.drawable.organisation_image_20);break;
            case 20: OrganisationImage.setImageResource(R.drawable.organisation_image_21);break;
            case 21: OrganisationImage.setImageResource(R.drawable.organisation_image_22);break;
            case 22: OrganisationImage.setImageResource(R.drawable.organisation_image_23);break;
            case 23: OrganisationImage.setImageResource(R.drawable.organisation_image_24);break;
            case 24: OrganisationImage.setImageResource(R.drawable.organisation_image_25);break;
            case 25: OrganisationImage.setImageResource(R.drawable.organisation_image_26);break;
            case 26: OrganisationImage.setImageResource(R.drawable.organisation_image_27);break;

        }

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
