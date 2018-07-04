package com.dexter.sahil.advancebarcodescanner;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MultipleView extends AppCompatActivity {

    String link,webString;
    TextView textString;
    Button buttonSearch,buttonMultiLink;
    ListView lv;
    int i,count;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_view);
        textString = (TextView) findViewById(R.id.textString);
        lv = (ListView)findViewById(R.id.listView);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonMultiLink = (Button) findViewById(R.id.multiLink);

        link = getIntent().getStringExtra("stringPasser");

//        arrayList = new ArrayList<String>();
//        arrayAdapter = new ArrayAdapter<String>();
        //SpannableString content = new SpannableString(link);
        //content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textString.setPaintFlags(textString.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textString.setText(link);



        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(MultipleView.this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(arrayAdapter);

        onClick();


    }

    private void onClick() {
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String[] multiLink = link.split(",");
                count = multiLink.length;
                if (count == 1) {
                    webString = multiLink[0].toString();
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, webString);
                    startActivity(intent);

                }
                else {

                        for (i = 0; i < multiLink.length; i++) {
                        String tempLink = multiLink[i];
                        arrayList.add(tempLink);
                        arrayAdapter.notifyDataSetChanged();

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            webString = multiLink[i].toString();

                            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                            intent.putExtra(SearchManager.QUERY, webString);
                            startActivity(intent);
                        }
                    });
                }
            }

            }
        });

    }
}
