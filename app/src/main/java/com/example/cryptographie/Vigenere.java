package com.example.cryptographie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Vigenere extends AppCompatActivity {
    private EditText textEditCrypter,textEditDecrypter, cle;
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



        bntCrypter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mot = textEditCrypter.getText().toString();
                String cle_long = cle.getText().toString();
                String cle="";
                String resultat="";
                if(!cle_long.isEmpty()){
                    for(int i=0;i<mot.length();i++){
                        cle+=cle_long.charAt(i%cle_long.length());
                    }

                    for(int i=0;i<mot.length();i++){
                        int entier = (ASUtil.getCode(mot.charAt(i)));
                        int entier2 = (ASUtil.getCode(cle.charAt(i)));
                        int entier_resul = (entier+entier2)%256;
                        if(entier_resul>=0&&entier_resul<=31||entier_resul==127||entier_resul==255){
                            resultat+= String.format("\\x%02x",entier_resul);
                            //resultat+=Integer.toHexString(entier_resul);
                        }else{
                            resultat +=ASUtil.getAscii(entier_resul);
                        }
                    }

                }

                textEditDecrypter.setText(resultat);

            }
        });


        btnDecrypter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mot = textEditDecrypter.getText().toString();
                String cle_long = cle.getText().toString();
                String mot_modifier="";
                String cle = "";
                String resultat = "";
                for(int i=0;i<mot.length();i++){
                    int entier = (ASUtil.getCode(mot.charAt(i)));
                    if(entier==92 && ASUtil.getCode(mot.charAt(i+1))==120){
                        char letter1 =  ASUtil.getAscii(ASUtil.getCode(mot.charAt(i+2)));
                        char letter2 = ASUtil.getAscii(ASUtil.getCode(mot.charAt(i+3)));
                        String hexString =""+letter1+letter2;
                        mot_modifier+= ASUtil.getAscii((Integer.parseInt(hexString,16))%256);
                        i+=3;
                    }else{
                        mot_modifier += ASUtil.getAscii((entier)%256);

                    }
                }
                if(!cle_long.isEmpty()) {
                    for (int i = 0; i < mot_modifier.length(); i++) {
                        cle += cle_long.charAt(i % cle_long.length());
                    }


                    for(int i=0;i<mot_modifier.length();i++){
                        int entier = (ASUtil.getCode(mot_modifier.charAt(i)));
                        int entier2 = (ASUtil.getCode(cle.charAt(i)));
                        int entier_resul = (entier-entier2)%256;
                        if(entier_resul>=0&&entier_resul<=31||entier_resul==127||entier_resul==255){
                            resultat+= String.format("\\x%02x",entier_resul);
                            //resultat+=Integer.toHexString(entier_resul);
                        }else{
                            resultat +=ASUtil.getAscii(entier_resul);
                        }
                    }

                }

                textEditCrypter.setText(resultat);


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