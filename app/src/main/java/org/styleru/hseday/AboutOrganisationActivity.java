package org.styleru.hseday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutOrganisationActivity extends AppCompatActivity {
    ImageView organisationImage;
    TextView organisationTitle;
    TextView organisationInformation;
    TextView organisationContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_organisation);

        organisationImage = (ImageView) this.findViewById(R.id.organisation_image);
        organisationTitle = (TextView) this.findViewById(R.id.organisation_title);
        organisationInformation = (TextView) this.findViewById(R.id.organisation_information);
        organisationContacts = (TextView) this.findViewById(R.id.organisation_contancts);

        Intent intent = getIntent();
        organisationTitle.setText(String.valueOf(intent.getIntExtra("OrganisationName", 1)));
        organisationInformation.setText(String.valueOf(intent.getIntExtra("organisationInformation", 1)));
        organisationContacts.setText(String.valueOf(intent.getIntExtra("organisationContacts", 1)));

        switch(intent.getIntExtra("organisationImage", 1)){
            case 0: organisationImage.setImageResource(R.drawable.organisation_image_1);break;
            case 1: organisationImage.setImageResource(R.drawable.organisation_image_2);break;
            case 2: organisationImage.setImageResource(R.drawable.organisation_image_3);break;
            case 3: organisationImage.setImageResource(R.drawable.organisation_image_4);break;
            case 4: organisationImage.setImageResource(R.drawable.organisation_image_5);break;
            case 5: organisationImage.setImageResource(R.drawable.organisation_image_6);break;
            case 6: organisationImage.setImageResource(R.drawable.organisation_image_7);break;
            case 7: organisationImage.setImageResource(R.drawable.organisation_image_8);break;
            case 8: organisationImage.setImageResource(R.drawable.organisation_image_9);break;
            case 9: organisationImage.setImageResource(R.drawable.organisation_image_10);break;
            case 10: organisationImage.setImageResource(R.drawable.organisation_image_11);break;
            case 11: organisationImage.setImageResource(R.drawable.organisation_image_12);break;
            case 12: organisationImage.setImageResource(R.drawable.organisation_image_13);break;
            case 13: organisationImage.setImageResource(R.drawable.organisation_image_14);break;
            case 14: organisationImage.setImageResource(R.drawable.organisation_image_15);break;
            case 15: organisationImage.setImageResource(R.drawable.organisation_image_16);break;
            case 16: organisationImage.setImageResource(R.drawable.organisation_image_17);break;
            case 17: organisationImage.setImageResource(R.drawable.organisation_image_18);break;
            case 18: organisationImage.setImageResource(R.drawable.organisation_image_19);break;
            case 19: organisationImage.setImageResource(R.drawable.organisation_image_20);break;
            case 20: organisationImage.setImageResource(R.drawable.organisation_image_21);break;
            case 21: organisationImage.setImageResource(R.drawable.organisation_image_22);break;
            case 22: organisationImage.setImageResource(R.drawable.organisation_image_23);break;
            case 23: organisationImage.setImageResource(R.drawable.organisation_image_24);break;
            case 24: organisationImage.setImageResource(R.drawable.organisation_image_25);break;
            case 25: organisationImage.setImageResource(R.drawable.organisation_image_26);break;
            case 26: organisationImage.setImageResource(R.drawable.organisation_image_27);break;

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
