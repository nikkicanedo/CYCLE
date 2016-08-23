package com.nikkay.canedo.merge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SELFIE extends AppCompatActivity {

    public static final int CAMERA_REQUEST = 10;

    private ImageView imgPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie);

        imgPhoto=(ImageView)findViewById(R.id.imgPhoto);
    }


    public void btnCapture(View v){

        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==CAMERA_REQUEST){
                Bitmap image =(Bitmap) data.getExtras().get("data");
                imgPhoto.setImageBitmap(image);

                Toast.makeText(this,"Photo successfully saved",Toast.LENGTH_LONG).show();

            }
        }
    }
    public void btnShare(View v){
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        Uri uri_image = getLocalBitmapUri(imgPhoto);
        shareIntent.putExtra(Intent.EXTRA_STREAM,uri_image);

        shareIntent.putExtra(Intent.EXTRA_TEXT,"Goodmorning");

        Intent chooser=Intent.createChooser(shareIntent,"Share Image Via");
        if(shareIntent.resolveActivity(getPackageManager())!=null){
            startActivity(chooser);
        }
    }

    private Uri getLocalBitmapUri(ImageView imgPhoto) {
        Drawable drawable = imgPhoto.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imgPhoto.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri uri_image = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            uri_image = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uri_image;

    }
}
