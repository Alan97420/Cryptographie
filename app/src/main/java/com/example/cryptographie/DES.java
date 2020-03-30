package com.example.cryptographie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DES extends AppCompatActivity {
    private EditText textEditCrypter,textEditDecrypter, cle;
    private Button bntCrypter,btnDecrypter,btnRetour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_e_s);
        textEditCrypter = findViewById(R.id.editText_Crypter);
        textEditDecrypter = findViewById(R.id.editText_NCrypter);
        cle = findViewById(R.id.editText_Cle);


        bntCrypter = findViewById(R.id.Btn_crypter);
        btnDecrypter = findViewById(R.id.Btn_decrypter);
        btnRetour = findViewById(R.id.Btn_retour);
        cle.setText("\\x133457799BBCDFF1");

        bntCrypter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cle_long = cle.getText().toString();
                permute(transfo(cle_long),Des_table.PC1);

            }
        });
    }
    public String transfo(String  mot){
        String entier_cle ="" ;
        String mot_modifier="";
        if(mot.charAt(0)=='\\'&& mot.charAt(1)=='x') {
            for (int i = 2; i < mot.length(); i+=2) {
                int entier = (ASUtil.getCode(mot.charAt(i)));

                    char letter1 = ASUtil.getAscii(ASUtil.getCode(mot.charAt(i)));
                    char letter2 = ASUtil.getAscii(ASUtil.getCode(mot.charAt(i +1)));
                    String hexString = "" + letter1 + letter2;
                    mot_modifier += ASUtil.getAscii((Integer.parseInt(hexString, 16)) % 256);

            }
        }else{
            mot_modifier = mot;
        }


        int i=0;
        Log.v("coucou",Integer.toString(mot_modifier.length()));

        for(;i<mot_modifier.length();i++){
            //entier_cle+= String.format("%s",ASUtil.getCode(mot.charAt(i)));
            String lon_mot= Integer.toBinaryString(ASUtil.getCode(mot_modifier.charAt(i)));
            String zero="";
            if(lon_mot.length()<8){
                for(int k=0;k<8-lon_mot.length();k++){
                    zero+="0";
                }
            }
            entier_cle+=zero+lon_mot;
        }
        if(entier_cle.length()<64){
            for (int j = entier_cle.length();j<64;j++){
                entier_cle+= "0";
            }
        }

        Log.v("coucou",Integer.toString(entier_cle.length()));
        return entier_cle;
    }
    public String permute( String entier, byte[]tab){
        String resul="";
        for (int i=0;i<tab.length;i++){
            resul+=entier.charAt(tab[i]);
        }
        Log.v("coucou",resul);
        Log.v("coucou",Integer.toString(resul.length()));
        return resul;
    }

}
