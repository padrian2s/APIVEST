package com.example.adrian.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView formatTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button_scanare);
        button.setOnClickListener(this);

        final Button button_reparare = (Button) findViewById(R.id.button_verificare_reparare);
        button_reparare .setOnClickListener(this);

        final Button button_vopsire = (Button) findViewById(R.id.button_verificare_vopsire);
        button_vopsire.setOnClickListener(this);

        final Button button_salvare = (Button) findViewById(R.id.button_salvare);
        button_salvare.setOnClickListener(this);

        final RatingBar inteapa = (RatingBar) findViewById(R.id.ratingBar);
        inteapa.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                if (rating > 3.0) {
                    Toast toast4 = Toast.makeText(getApplicationContext(),
                            "* Dureros *", Toast.LENGTH_SHORT);
                    toast4.show();
                } else {
                    Toast toast4 = Toast.makeText(getApplicationContext(),
                            "* Placut *", Toast.LENGTH_SHORT);
                    toast4.show();
                }
            }
        });

        formatTxt = (TextView)findViewById(R.id.editQR);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_scanare:
                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                //start scanning
                scanIntegrator.initiateScan();
                break;
            case R.id.button_verificare_reparare:
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Il reparam...", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.button_verificare_vopsire:
                Toast toast2 = Toast.makeText(getApplicationContext(),
                        "Il vopsim...", Toast.LENGTH_SHORT);
                toast2.show();
                break;
            case R.id.button_salvare:
                Toast toast3 = Toast.makeText(getApplicationContext(),
                        "* Salvare stuff *", Toast.LENGTH_SHORT);
                toast3.show();
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve result of scanning - instantiate ZXing object
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //check we have a valid result
        if (scanningResult != null) {
            //get content from Intent Result
            String scanContent = scanningResult.getContents();
            //get format name of data scanned
            String scanFormat = scanningResult.getFormatName();
            //output to UI
            formatTxt.setText("FORMAT: "+scanFormat+" | "+ scanContent);
        }
        else{
            //invalid scan data or scan canceled
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
