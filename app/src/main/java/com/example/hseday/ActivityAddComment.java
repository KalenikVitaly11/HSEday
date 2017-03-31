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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mvc.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ActivityAddComment extends AppCompatActivity implements View.OnClickListener {
    private final static int SELECT_PHOTO = 100;

    Button addPhotoButton;
    ImageView commentImage;
    Button addFromCamera;
    Button addFromGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Новый комментарий");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_comment);
        addPhotoButton = (Button) findViewById(R.id.button_add_photo);
    addPhotoButton.setOnClickListener(this);
}

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_photo:
                ImagePicker.pickImage(this, "Select your image:");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        commentImage = (ImageView) findViewById(R.id.comment_image);
        Bitmap bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        commentImage.setImageBitmap(bitmap);
    }
}
