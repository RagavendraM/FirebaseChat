package com.example.mragavendra.firebasechat;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    EditText ev1,ev2;
    public static final String EXTRA_POSITION = "position";
    TextToSpeech tts;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//
//        //Securing EditText
//      ev1=(EditText)findViewById(R.id.Ev1);
//        ev2=(EditText)findViewById(R.id.Ev2);
//
//
//        ev1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                SecureViewBucket.addInSecureViewBucket(ev1);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                SecureViewBucket.addInSecureViewBucket(ev1);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                SecureViewBucket.removeFromSecureViewBucket(ev1);
//            }
//        });
//        ev2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                SecureViewBucket.addInSecureViewBucket(ev2);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                SecureViewBucket.addInSecureViewBucket(ev2);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
       final Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar11);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //collapsing toolbar

        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.ctoolbar);

        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();
        String[] places = resources.getStringArray(R.array.places_name);
        collapsingToolbar.setTitle(places[postion % places.length]);
//
//        /*  */
//        //Creating the LayoutInflater instance
//        LayoutInflater li = getLayoutInflater();
//        //Getting the View object as defined in the customtoast.xml file
//        final View layout = li.inflate(R.layout.custom_toast,
//                (ViewGroup) findViewById(R.id.linear));

        String[] placeDetails = resources.getStringArray(R.array.place_details);
        final TextView placeDetail = (TextView) findViewById(R.id.place_detail);
        placeDetail.setText(placeDetails[postion % placeDetails.length]);



        String[] placeLocations = resources.getStringArray(R.array.place_locations);
        TextView placeLocation =  (TextView) findViewById(R.id.place_location);
        placeLocation.setText(placeLocations[postion % placeLocations.length]);





        TypedArray placePictures = resources.obtainTypedArray(R.array.places_picture);
        ImageView placePicture = (ImageView) findViewById(R.id.iv11);
        placePicture.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

        placePictures.recycle();


        Log.v(DetailActivity.class.getSimpleName(), DetailActivity.class.getSimpleName() + "OnCreate");


    }
    @Override
    protected void onPause() {
        if(tts!=null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
    private Bitmap Bit(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
