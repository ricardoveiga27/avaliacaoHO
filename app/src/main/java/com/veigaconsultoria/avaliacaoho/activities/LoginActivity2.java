package com.veigaconsultoria.avaliacaoho.activities;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.veigaconsultoria.avaliacaoho.R;

public class LoginActivity2 extends AppCompatActivity {

    private EditText mEditEmail;
    private EditText mEditSenha;
    private Button mBtnEntrar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mEditEmail = findViewById(R.id.email);
        mEditSenha = findViewById(R.id.senha);
        mBtnEntrar = findViewById(R.id.botaoEntrar);

        mAuth = FirebaseAuth.getInstance();

       mBtnEntrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               attemptLogin(mEditEmail.getText().toString(), mEditSenha.getText().toString());
           }
       });

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), EmpresaActivity.class));
            finish();
        }
    }

    private void attemptLogin(String email, String password) {
        mBtnEntrar.setEnabled(false);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity2.this, "Autenticado com sucesso",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity2.this, EmpresaActivity.class);
                            startActivity(intent);

                            finish();
                        } else {
                            mBtnEntrar.setEnabled(true);

                            Toast.makeText(LoginActivity2.this, "Falha na autenticação.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
