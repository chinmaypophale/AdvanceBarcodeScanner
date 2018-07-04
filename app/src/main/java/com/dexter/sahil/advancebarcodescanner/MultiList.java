package com.dexter.sahil.advancebarcodescanner;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MultiList extends AppCompatActivity {

    String multiLink,webString;
    ListView lv;
    int i;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Button buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_list);

        buttonList = (Button)findViewById(R.id.MultiListButton);
        multiLink = getIntent().getStringExtra("temp");
        lv = (ListView)findViewById(R.id.ListView);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(MultiList.this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(arrayAdapter);


        onClick();

    }

    private void onClick() {
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] Link = multiLink.split(",");
                for (i = 0;i<Link.length;i++) {
                    String tempLink = Link[i];
                    arrayList.add(tempLink);
                    arrayAdapter.notifyDataSetChanged();

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            webString = Link[i].toString();

                            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                            intent.putExtra(SearchManager.QUERY, webString);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

}
