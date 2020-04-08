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

        // charatere non affichable 0-31,127,25. a coder


        bntCrypter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mot = textEditCrypter.getText().toString();

                String resultat = "";
                for(int i=0; i<mot.length();i++){
                    int entier = (ASUtil.getCode(mot.charAt(i)))+3;
                    if(entier>=0&&entier<=31||entier==127||entier==255){
                        resultat+= String.format("\\x%2x",entier);
                        //resultat+=Integer.toHexString(entier_resul);
                    }else{
                        resultat +=ASUtil.getAscii(entier);
                    }
                    textEditDecrypter.setText(resultat);
                }



            }
        });

        btnDecrypter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mot_decrypt = textEditDecrypter.getText().toString();
                String resulta1 ="";

                for(int i=0;i<mot_decrypt.length();i++){
                    int entier = (ASUtil.getCode(mot_decrypt.charAt(i)));
                    if(entier==92 && ASUtil.getCode(mot_decrypt.charAt(i+1))==120){
                        char letter1 =  ASUtil.getAscii(ASUtil.getCode(mot_decrypt.charAt(i+2)));
                        char letter2 = ASUtil.getAscii(ASUtil.getCode(mot_decrypt.charAt(i+3)));
                        String hexString =""+letter1+letter2;
                        resulta1+= ASUtil.getAscii((Integer.parseInt(hexString,16)-3)%256);

                        i+=3;

                    }else{
                        resulta1 += ASUtil.getAscii((entier-3)%256);
                    }

                }
                textEditCrypter.setText(resulta1);

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

