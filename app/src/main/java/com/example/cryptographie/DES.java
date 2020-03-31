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
        cle.setText("\\x0123456789abcdef");
        textEditCrypter.setText("\\x23456789abcdef01");

        bntCrypter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cle_long = cle.getText().toString();
                String Crypt = textEditCrypter.getText().toString();
                String T_origine = permute(transfo(Crypt),Des_table.IP);
                //Log.v("coucou",T_origine);
                //String test ="1100110000000000110011001111111111110000101010101111000010101010";
                if(!cle_long.isEmpty()){
                    String K_origne = permute(transfo(cle_long),Des_table.PC1);
                    String[] cle_fini = decalage(K_origne.substring(0,28),K_origne.substring(28,56),Des_table.rotations);
                    String hug = encodage(T_origine,cle_fini);
                    String popo = permute(hug,Des_table.FP);
                    Log.v("coucou",popo);
                    //String popi = Integer.toString(Integer.parseInt(popo,2),16);
                    //textEditDecrypter.setText(popi);
                }



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

        return entier_cle;
    }
    public String permute( String entier, byte[]tab){
        String resul="";
        for (int i=0;i<tab.length;i++){
            resul+=entier.charAt(tab[i]-1);
        }
        return resul;
    }
    public String rotation(String mot, int entier){
        String resultat = mot.substring(entier, mot.length())+mot.substring(0,entier);
        return resultat;

    }
    public String[] decalage(String mot1, String mot2, byte[]tab){
        String[][] resultat = new String[17][2];
        resultat [0][0] = mot1;
        resultat [0][1] = mot2;
        for(int y=0;y<tab.length;y++) {
            resultat [y+1][0] = rotation(resultat[y][0],tab[y]);
            resultat [y+1][1] = rotation(resultat[y][1],tab[y]);
        }
        String[] conct =new String[16];
        for(int i=0; i<16;i++){
            // je concat la chaine et applique la fonction permute de pc2
            conct[i] = permute(resultat[i+1][0]+resultat[i+1][1],Des_table.PC2);

            //Log.v("coucou",conct[i]);
        }


        return conct;
    }
    public String encodage(String mot, String [] cle){
        String L0 = mot.substring(0,32);
        String R0 = mot.substring(32,64);
        String[][]tab = new String[17][2];
        tab[0][0] = L0;
        tab[0][1] = R0;

        for(int i=0;i<16;i++){
            tab[i+1][0] = tab[i][1];
            String r_bit = permute(tab[i][1],Des_table.E);
            String A = XOR(r_bit,cle[i]);
            tab[i+1][1] = XOR(tab[i][0],sBlock(A));
        }
        return tab[16][1]+tab[16][0];
    }
    public String XOR( String Rn, String Kn){
        String result ="";
        for (int i = 0; i < Rn.length(); i++) {
            result+=(Kn.charAt(i)^Rn.charAt(i));
        }
        return result;
    }
    public String sBlock( String mot){
        byte[][][] s_tab = Des_table.S;
        String resultat = "";
        int rang=0;
        for(int i=0;i< mot.length();i+=6){
            String bit2 =""+mot.charAt(i)+mot.charAt(i+5);
            String bit4 =""+mot.charAt(i+1)+ mot.charAt(i+2)+mot.charAt(i+3)+mot.charAt(i+4);
            int inbit2 = Integer.parseInt(bit2,2);
            int inbit4 = Integer.parseInt(bit4,2);
            String inter =Integer.toBinaryString(s_tab[rang][inbit2][inbit4]);
            String zero="";
            if(inter.length()<4){
                for(int k=inter.length();k<4;k++){
                    zero+="0";
                }
            }
            resultat+=zero+inter;
            rang++;



        }

        return (permute(resultat,Des_table.P));
    }



}
