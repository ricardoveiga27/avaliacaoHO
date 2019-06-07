package com.veigaconsultoria.avaliacaoho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity2 extends AppCompatActivity {

    private EditText mEditEmail;
    private EditText mEditSenha;
    private Button mBtnEntrar;
    private TextView mTxtCriarConta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mEditEmail = findViewById(R.id.email);
        mEditSenha = findViewById(R.id.senha);
        mBtnEntrar = findViewById(R.id.botaoEntrar);
        mTxtCriarConta = findViewById(R.id.crieAquiSenha);


       mTxtCriarConta.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               startActivity(new Intent(LoginActivity2.this, RegisterActivity.class));
           }
       });
    }
}
