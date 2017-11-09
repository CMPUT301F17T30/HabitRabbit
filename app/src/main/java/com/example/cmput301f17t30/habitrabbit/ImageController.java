package com.example.cmput301f17t30.habitrabbit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Irissama on 2017-11-08.
 */

public class ImageController {
    //image
    private Bitmap selectImage;


    public ImageController (){

    }


    /**
     * pass the file path to decode it into bitmap
     * then resize and compress it to desired file size
     * then set it to image view to show it
     *
     * @param filePath the file path of image in this phone
     */
    public Bitmap decodeFile(String filePath) {
        //set max image file size
        int maxSize = 65536;

        // get the original image size
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, option);

        // The new size we want to scale to 500
        final int REQUIRED_SIZE = 500;
        // Find the correct scale value. It should be the power of 2.
        int width_origin = option.outWidth, height_origin = option.outHeight;
        int scale = 1;
        while (true) {
            if (width_origin < REQUIRED_SIZE && height_origin < REQUIRED_SIZE)
                break;
            width_origin /= 2;
            height_origin /= 2;
            scale *= 2;
        }

        // get image with desired size
        BitmapFactory.Options optionSet = new BitmapFactory.Options();
        optionSet.inSampleSize = scale;
        selectImage = BitmapFactory.decodeFile(filePath, optionSet);

        // compress the image to desired file size
        int compressQuality = 100;
        int streamLength = maxSize;

        while (streamLength >= maxSize) {
            compressQuality -= 1;
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            selectImage.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream);

            byte[] bmpPicByteArray = bmpStream.toByteArray();
            streamLength = bmpPicByteArray.length;
        }

        return selectImage;

    }




}
