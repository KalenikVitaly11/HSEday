package org.styleru.hseday;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.mvc.imagepicker.ImagePicker;


public class ActivityAddComment extends AppCompatActivity implements View.OnClickListener {
    private final static int SELECT_PHOTO = 100;

    Button addPhotoButton;
    Button deleteAllPhotos;
    HorizontalScrollView scrollViewImages;
    ImageView commentImage1;
    ImageView commentImage2;
    ImageView commentImage3;
    ImageView commentImage4;
    ImageView commentImage5;

    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Новый комментарий");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_comment);
        addPhotoButton = (Button) findViewById(R.id.button_add_photo);
        deleteAllPhotos = (Button) findViewById(R.id.button_delete_photos);
        scrollViewImages = (HorizontalScrollView) findViewById(R.id.scroll_view_images);
        addPhotoButton.setOnClickListener(this);
        deleteAllPhotos.setOnClickListener(this);
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
                ImagePicker.pickImage(this, "Выбери фото:");
                break;
            case R.id.button_delete_photos:
                if (commentImage1.getDrawable() != null) {
                    deleteAllPhotos.setVisibility(View.INVISIBLE);
                    fadeOutAndHideImage(commentImage1);
                    fadeOutAndHideImage(commentImage2);
                    fadeOutAndHideImage(commentImage3);
                    fadeOutAndHideImage(commentImage4);
                    fadeOutAndHideImage(commentImage5);
                }
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
        if (bitmap != null) {

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
            deleteAllPhotos.setVisibility(View.VISIBLE);
        }
    }

    private void fadeOutAndHideImage(final ImageView img) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(500);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                img.setImageResource(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });

        img.startAnimation(fadeOut);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
