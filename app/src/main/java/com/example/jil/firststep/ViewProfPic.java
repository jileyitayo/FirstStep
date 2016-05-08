package com.example.jil.firststep;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

public class ViewProfPic extends AppCompatActivity {
    int drawable = R.drawable.ic_person_black_24dp;
    ImageView IV;
    String uriPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prof_pic);
        IV = (ImageView) findViewById(R.id.imageViewPic);
        uriPath = getIntent().getExtras().getString("uriPath");
        if(uriPath != null)
        {
            retrievePath(uriPath);
        }
        else
        {
            IV.setImageResource(drawable);
            uriPath = String.valueOf(drawable);
        }
    }


    public void retrievePath(String path) {
        File imgFile = new File(path);
        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        final int REQUIRED_SIZE = 200;//200
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);
        IV.setImageBitmap(bm);
    }
}
