package com.example.cryptographie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Vigenere extends AppCompatActivity {
    private EditText textEditCrypter,textEditDecrypter, cle;
    private TextView cryptage, decryptage;
    private Button bntCrypter,btnDecrypter,btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere);

        textEditCrypter = findViewById(R.id.editText_Crypter);
        textEditDecrypter = findViewById(R.id.editText_NCrypter);
        cle = findViewById(R.id.editText_Cle);

        bntCrypter = findViewById(R.id.Btn_crypter);
        btnDecrypter = findViewById(R.id.Btn_decrypter);
        btnRetour = findViewById(R.id.Btn_retour);

        cryptage = findViewById(R.id.resulta);
        decryptage = findViewById(R.id.txt_decryptage);

        bntCrypter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mot = textEditCrypter.getText().toString();
                String cle_long = cle.getText().toString();
                String cle="";
                String resultat="";
                for(int i=0;i<mot.length();i++){
                    cle+=cle_long.charAt(i%cle_long.length());
                }
                for(int i=0;i<mot.length();i++){
                    int entier = (ASUtil.getCode(mot.charAt(i)));
                    int entier2 = (ASUtil.getCode(cle.charAt(i)));
                    int entier_resul = (entier+entier2)%256;
                    resultat +=ASUtil.getAscii(entier_resul);
                }
                cryptage.setText("Message Crypté: "+resultat);

            }
        });

    }
}