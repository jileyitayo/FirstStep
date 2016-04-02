package com.example.jil.firststep;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jil.Users.Child;

import java.io.File;

public class schedule_menu extends AppCompatActivity {
    Child newdata12 = new Child();
    TextView tFname, tLname;
    String uriPath;
    ImageButton img;

    Button btnVacc, btnDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_menu);
        newdata12= (Child)getIntent().getExtras().getSerializable("DATA12");
        this.setTitle(newdata12.getfirstName());
        tFname  =(TextView) findViewById(R.id.TVFName);
        tLname = (TextView) findViewById(R.id.TVLName);
    btnVacc = (Button) findViewById(R.id.btnVaccination12);
        btnDoc = (Button) findViewById(R.id.btndoctAppointment);
        img = (ImageButton) findViewById(R.id.profilePic);
        tFname.setText(newdata12.getfirstName().toUpperCase());
        tLname.setText(newdata12.getLastName().toUpperCase());
        uriPath = newdata12.getImg_path();
        if(uriPath != null)
        {
            retrievePath(uriPath);
        }
        btnVacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void retrievePath(String path) {
        File imgFile = new File(path);
        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);
        img.setImageBitmap(bm);
    }
}
