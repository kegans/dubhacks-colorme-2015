package com.example.android.colorme;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_color);
        Bitmap extraImage = (Bitmap)getIntent().getParcelableExtra("imageExtra");

         // Asynchronous
            Palette.from(extraImage).generate(new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette p) {
                    // Use generated instance

                    Palette.Swatch displaySwatch = null;

                    displaySwatch = p.getLightMutedSwatch();

                    if (displaySwatch != null) {

                        int rgbColor = displaySwatch.getRgb();

//                        String hex = Integer.toHexString(rgbColor);


                        ColorUtils Utils = new ColorUtils();
                        String colorName = Utils.getColorNameFromHex(rgbColor);

                        String colorDisplay = colorName;

                        TextView textView = (TextView) findViewById(R.id.display_color);
                        textView.setText("The color is: " + colorDisplay);

//                        RelativeLayout displayColorLayout = (RelativeLayout) findViewById(R.id.display_color_activity);
//                        displayColorLayout.setBackground(Color.hex);

                        Log.d("DisplayColorActivity", colorName);
                    }

                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_color, menu);
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
}
