package com.example.android.colorme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private int mBackgroundColor = -1;
    private Bitmap image = null;
    private boolean colorBitmap = false;
    private String colorDisplay = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void dispatchTakePictureIntent(View view) {
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    REQUEST_IMAGE_CAPTURE);
        }
        else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    public void analyzeImage(View view){

        if(image != null){
            Intent analyzeIntent = new Intent(this, DisplayColorActivity.class);
            analyzeIntent.putExtra("imageExtra", image);
            startActivity(analyzeIntent);

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            image = imageBitmap;


//            Log.d("MainActivity", imageBitmap.toString());

            // Asynchronous
//            Palette.from(imageBitmap).generate(new Palette.PaletteAsyncListener() {
//                public void onGenerated(Palette p) {
//                    // Use generated instance
//
//                    Log.d("MainActivity", p.getVibrantSwatch().getRgb() + "");
//
//                    Palette.Swatch displaySwatch = null;
//
//                    displaySwatch = p.getVibrantSwatch();
//
//                    int rgbColor = displaySwatch.getRgb();
//
//                    ColorUtils Utils = new ColorUtils();
//                    String colorName = Utils.getColorNameFromHex(rgbColor);
//
//                    colorDisplay = colorName;
//
//                    Log.d("MainActivity", colorName);
//
//                }
//            });

        }
    }

}
