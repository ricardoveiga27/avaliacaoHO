package com.veigaconsultoria.avaliacaoho.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.veigaconsultoria.avaliacaoho.R;

import java.io.IOException;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEditName;
    private EditText mEditEmail;
    private EditText mEditSenha;
    private Button mBtnCadastrar;
    private Button mBtnSelectedphotoRiscos;
    private ImageView mImagephotoRiscos;

    private Uri mSelectedUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEditName = findViewById(R.id.nome);
        mEditEmail = findViewById(R.id.email);
        mEditSenha = findViewById(R.id.senha);
        mBtnCadastrar = findViewById(R.id.botaoCadastrar);
        mBtnSelectedphotoRiscos = findViewById(R.id.btn_seleciona_foto);
        mImagephotoRiscos = findViewById(R.id.img_foto_logim);


        mBtnSelectedphotoRiscos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selecionarFoto();

            }
        });


        mBtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createrUser();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0){
            mSelectedUri = data.getData();

            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mSelectedUri);
                mImagephotoRiscos.setImageDrawable(new BitmapDrawable(bitmap));
                mBtnSelectedphotoRiscos.setAlpha(0);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void selecionarFoto() {

       Intent intent = new Intent(Intent.ACTION_PICK);
       intent.setType("image/*");
       startActivityForResult(intent, 0);

    }

    private void createrUser() {

        String nome = mEditName.getText().toString();
        String email = mEditEmail.getText().toString();
        String senha = mEditSenha.getText().toString();

        if (nome==null || nome.isEmpty() || email== null || email.isEmpty() || senha == null || senha.isEmpty()){
            Toast.makeText(this, "Nome , Email e Senha ser preenchidos", Toast.LENGTH_LONG).show();
            return;
        }


// autenticação de usuario *************************************************************

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("teste", task.getResult().getUser().getUid());


                            
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });


    }

    private void saveUserFirebase() {

        String filename = UUID.randomUUID().toString();
        final StorageReference ref = FirebaseStorage.getInstance().getReference("/images/"+filename);
        ref.putFile(mSelectedUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.i("teste", uri.toString());
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("teste", e.getMessage(), e);
            }
        });
    }
}
