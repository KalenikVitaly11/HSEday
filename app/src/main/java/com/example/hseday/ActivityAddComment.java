package com.example.hseday;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mvc.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class ActivityAddComment extends AppCompatActivity implements View.OnClickListener {
    private final static int SELECT_PHOTO = 100;

    Button addPhotoButton;
    HorizontalScrollView scrollViewImages;
    ImageView commentImage1;
    ImageView commentImage2;
    ImageView commentImage3;
    ImageView commentImage4;
    ImageView commentImage5;
    Button addFromCamera;
    Button addFromGallery;
    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Новый комментарий");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_comment);
        addPhotoButton = (Button) findViewById(R.id.button_add_photo);
        scrollViewImages = (HorizontalScrollView) findViewById(R.id.scroll_view_images);
        addPhotoButton.setOnClickListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_photo:
                ImagePicker.pickImage(this, "Выбери картинку:");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        commentImage1 = (ImageView) findViewById(R.id.comment_image_1);
        commentImage2 = (ImageView) findViewById(R.id.comment_image_2);
        commentImage3 = (ImageView) findViewById(R.id.comment_image_3);
        commentImage4 = (ImageView) findViewById(R.id.comment_image_4);
        commentImage5 = (ImageView) findViewById(R.id.comment_image_5);

        Bitmap bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);

        scrollViewImages.setVisibility(View.VISIBLE);

        if (commentImage1.getDrawable() == null) {
            commentImage1.setVisibility(View.VISIBLE);
            commentImage1.setImageBitmap(bitmap);
        } else if (commentImage2.getDrawable() == null) {
            commentImage2.setVisibility(View.VISIBLE);
            commentImage2.setImageBitmap(bitmap);
        } else if (commentImage3.getDrawable() == null) {
            commentImage3.setVisibility(View.VISIBLE);
            commentImage3.setImageBitmap(bitmap);
        } else if (commentImage4.getDrawable() == null) {
            commentImage4.setVisibility(View.VISIBLE);
            commentImage4.setImageBitmap(bitmap);
        } else if (commentImage5.getDrawable() == null) {
            commentImage5.setVisibility(View.VISIBLE);
            commentImage5.setImageBitmap(bitmap);
        }

        commentImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    commentImage1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    commentImage1.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    commentImage1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    commentImage1.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
