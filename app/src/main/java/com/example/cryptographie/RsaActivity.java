package com.example.cryptographie;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RsaActivity extends AppCompatActivity {

    private EditText textEditCrypter,textEditDecrypter, editCle;
    private TextView cryptage, popo1;
    private Button bntCrypter;
    private Button btnDecypter;
    private Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
        editCle = findViewById(R.id.editText_Cle);
        textEditCrypter = findViewById(R.id.editText_Crypter);
        textEditDecrypter = findViewById(R.id.editText_NCrypter);
        bntCrypter = findViewById(R.id.Btn_crypter);
        cryptage = findViewById(R.id.resulta);
        popo1 = findViewById(R.id.popo);
    }

    public void crypt(View v) {
        String mot = textEditCrypter.getText().toString();
        String cle = editCle.getText().toString();
        Rsa crypt = new Rsa(cle);
        cryptage.setText(crypt.crypt(mot));
    }

    public void decrypt(View v) {
        String mot = textEditDecrypter.getText().toString();
        String cle = editCle.getText().toString();
        Rsa crypt = new Rsa(cle);
        cryptage.setText(crypt.decrypt(mot));
    }

}
