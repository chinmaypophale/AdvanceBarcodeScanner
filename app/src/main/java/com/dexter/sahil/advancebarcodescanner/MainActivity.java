package com.dexter.sahil.advancebarcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button bScan;
    String contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bScan = (Button)findViewById(R.id.bScan);

        final Activity activity = this;
        bScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
                integrator.setBeepEnabled(false);
                integrator.setCameraId(0);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null){
            if (result.getContents()==null) {
                Toast.makeText(this,"You have cancelled the scanning",Toast.LENGTH_LONG).show();
            }
            else {

               // contents = result.getContents().toString().trim();
                Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,MultipleView.class);
                intent.putExtra("stringPasser",result.getContents());
                startActivity(intent);
            }
        }

        else  {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}
