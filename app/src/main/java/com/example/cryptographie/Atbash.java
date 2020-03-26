package com.example.cryptographie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Atbash extends AppCompatActivity {
    private EditText textEditCrypter,textEditDecrypter;
    private TextView cryptage, popo1;
    private Button bntCrypter;
    private Button btnDecypter;
    private Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item0);

        textEditCrypter = findViewById(R.id.editText_Crypter);
        textEditDecrypter = findViewById(R.id.editText_NCrypter);
        bntCrypter = findViewById(R.id.Btn_crypter);
        cryptage = findViewById(R.id.resulta);
        popo1 = findViewById(R.id.popo);
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
                cryptage.setText(resulat);

            }
        });
    }

}
