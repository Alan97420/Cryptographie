package com.example.cryptographie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test commentaire

        listView =(ListView)findViewById(R.id.list_crypto);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Atbash");
        arrayList.add("César");
        arrayList.add("Vigenère");
        arrayList.add("Homophone");
        arrayList.add("Playfair");
        arrayList.add("Hill");
        arrayList.add("Transposition Rectangulaire");
        arrayList.add("DES");
        arrayList.add("Suprise");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);


        // Now we will creat action Click for each item

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(MainActivity.this, Atbash.class);
                    startActivity(intent);

                }
                if(position==1){

                }
                if(position==2){

                }
                if(position==3){

                }
                if(position==4){

                }
                if(position==5){

                }
                if(position==6){

                }
                if(position==7){

                }
                if(position==8){

                }


            }
        });



    }
}
