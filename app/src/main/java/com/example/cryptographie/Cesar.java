package com.example.cryptographie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Cesar extends AppCompatActivity {
    private EditText textEditCrypter,textEditDecrypter;
    private TextView cryptage, decryptage;
    private Button bntCrypter,btnDecrypter,btnRetour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item0);

        textEditCrypter = findViewById(R.id.editText_Crypter);
        textEditDecrypter = findViewById(R.id.editText_NCrypter);

        bntCrypter = findViewById(R.id.Btn_crypter);
        btnDecrypter = findViewById(R.id.Btn_decrypter);
        btnRetour = findViewById(R.id.Btn_retour);

        cryptage = findViewById(R.id.resulta);
        decryptage = findViewById(R.id.txt_decryptage);

        bntCrypter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mot = textEditCrypter.getText().toString();
                String resulat = "";
                int entier;
                for(int i=0; i<mot.length();i++){
                    entier = (ASUtil.getCode(mot.charAt(i)))+3;
                    resulat+=ASUtil.getAscii(entier);

                }
                cryptage.setText("Message Crypté: "+resulat);

            }
        });

        btnDecrypter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mot_decrypt = textEditDecrypter.getText().toString();
                String resulta1 ="";
                int entier;
                for(int i=0;i<mot_decrypt.length();i++){
                    entier = (ASUtil.getCode(mot_decrypt.charAt(i)))-3;
                    resulta1 += ASUtil.getAscii(entier);
                }
                decryptage.setText("Message Décrypté: "+resulta1);
            }
        });
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

}