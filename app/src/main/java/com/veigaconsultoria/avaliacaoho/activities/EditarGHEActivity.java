package com.veigaconsultoria.avaliacaoho.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.models.Empresa;
import com.veigaconsultoria.avaliacaoho.models.GrupoHomogeneo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class EditarGHEActivity extends AppCompatActivity {

    EditText editTextNomeGhe;
    EditText editTextDescricaoGhe;
    EditText editTextQtdEmpGhe;
    EditText editTeFuncaoGhe;
    EditText editTextPeDirGhe;
    EditText editTextAreaGhe;
    EditText editTextventilacaoGhe;
    EditText editTextrevestimentoGhe;
    EditText editTextpisoGhe;
    EditText editTextiluminanciaGhe;
    EditText editTextequipamentosGhe;
    private Button takePhoto1;
    private Button btnSalvaGhe;
    private Button btnSalvaGheAvanca;


    GrupoHomogeneo grupoHomogeneo = new GrupoHomogeneo();

    File currentFile = null;
    String absolutePath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ghe);



        getSupportActionBar().setTitle("EDITA GHE ");


          editTextNomeGhe = findViewById(R.id.editText_nomeGhe);
          editTextDescricaoGhe = findViewById(R.id.editText_descricaoGhe);
          editTextQtdEmpGhe = findViewById(R.id.editText_qtdEmpGhe);
          editTeFuncaoGhe = findViewById(R.id.editText_funcoesGhe);
          editTextPeDirGhe = findViewById(R.id.editText_peDirGhe);
          editTextAreaGhe = findViewById(R.id.editText_areaGhe);
          editTextventilacaoGhe = findViewById(R.id.editText_ventilacaoGhe);
          editTextrevestimentoGhe = findViewById(R.id.editText_revestimentoGhe);
          editTextpisoGhe = findViewById(R.id.editText_pisoGhe);
          editTextiluminanciaGhe = findViewById(R.id.editText_iluminanciaGhe);
          editTextequipamentosGhe = findViewById(R.id.editText_equipamentosGhe);
          takePhoto1 = findViewById(R.id.takePhoto1);

         btnSalvaGhe = findViewById(R.id.btn_salva_ghe);
         btnSalvaGheAvanca = findViewById(R.id.btn_salva_ghe_avanca);





/////// foto ***************************************************************

        takePhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(138);
            }
        });


        /////// foto ***************************************************************





        btnSalvaGhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaGhe();



            }
        });


    }

    private void salvaGhe() {

        GrupoHomogeneo grupoRiscosHomogeneo = new GrupoHomogeneo();
            grupoRiscosHomogeneo.setNomeGhe(editTextNomeGhe.getText().toString());
            grupoRiscosHomogeneo.setDescricao(editTextDescricaoGhe.getText().toString());
            grupoRiscosHomogeneo.setQtdEmpGhe(editTextQtdEmpGhe.getText().toString());
            grupoRiscosHomogeneo.setFuncoesGhe(editTeFuncaoGhe.getText().toString());
            grupoRiscosHomogeneo.setPeDirGhe(editTextPeDirGhe.getText().toString());
            grupoRiscosHomogeneo.setAreaGhe(editTextAreaGhe.getText().toString());
            grupoRiscosHomogeneo.setVentilacaoGhe(editTextventilacaoGhe.getText().toString());
            grupoRiscosHomogeneo.setRevestimentoGhe(editTextrevestimentoGhe.getText().toString());
            grupoRiscosHomogeneo.setPisoGhe(editTextpisoGhe.getText().toString());
            grupoRiscosHomogeneo.setIluminanciaGhe(editTextiluminanciaGhe.getText().toString());
            grupoRiscosHomogeneo.setEquipamentosGhe(editTextequipamentosGhe.getText().toString());



        LinearLayout listaFotos = findViewById(R.id.images);

        for (String foto:grupoHomogeneo.getFotos()){
            ImageView imageView = new ImageView(this);
            int oneHundredDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
            int tenDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(oneHundredDP,oneHundredDP);
            lp.setMargins(0,tenDP,0,tenDP);
            imageView.setLayoutParams(lp);

            Picasso.get().load(foto).into(imageView);
            listaFotos.addView(imageView);
        }



        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("empresa").document(getIntent().getStringExtra("empresaId")).collection("grupoHomogeneo").add(grupoRiscosHomogeneo);
        finish();


//        Toast.makeText(this, "GHE " + editTextNomeGhe +" Cadastrado com Sucesso!!!", Toast.LENGTH_LONG).show();
//
//        Intent intent = new Intent(this, EditarGHEActivity.class);
//        finish();
//        startActivity(intent);









    }

    /////////////////   FOTOS    ///////////////////////////////////////////////////////////////////

    private void takePicture (int codigoImage) {
        try {
            final File root = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            currentFile = File.createTempFile(String.valueOf(codigoImage),  ".jpg", root);
            absolutePath = currentFile.getAbsolutePath();
            Toast.makeText(EditarGHEActivity.this, "Criou", Toast.LENGTH_SHORT).show();
            Toast.makeText(EditarGHEActivity.this, currentFile.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(EditarGHEActivity.this, "Falha ao criar arquivo temporário", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            if (currentFile != null) {
                Uri photoURI = FileProvider.getUriForFile(EditarGHEActivity.this,
                        "br.com.veigaconsultoria.ergosocial.fileprovider",
                        currentFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, codigoImage);
                Toast.makeText(EditarGHEActivity.this, "1" + String.valueOf(currentFile), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveImage() {
        Bitmap imageBitmap = null;

        try {
            File f = new File(absolutePath);
            ExifInterface exif = new ExifInterface(f.getPath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            int angle = 0;

            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                angle = 90;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                angle = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                angle = 270;
            }

            Matrix mat = new Matrix();
            mat.postRotate(angle);

            Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(f), null, null);
            imageBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), mat, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (imageBitmap.getHeight() > imageBitmap.getWidth()){
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap,480,640,false);
        } else {
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap,640,480,false);
        }

        int oneHundredDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        LinearLayout linearLayout= findViewById(R.id.images);
        ImageView imageView = new ImageView(this);
        int tenDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(oneHundredDP,oneHundredDP);
        lp.setMargins(0,tenDP,0,tenDP);
        imageView.setLayoutParams(lp);
        imageView.setImageBitmap(imageBitmap);
        linearLayout.addView(imageView);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        final StorageReference mountainsRef = storage.getReference().child("usuarios").child(mAuth.getCurrentUser().getUid()).child(System.currentTimeMillis() + ".jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data2 = baos.toByteArray();

        btnSalvaGheAvanca.setEnabled(false);
        btnSalvaGheAvanca.setText("Enviando imagens...");

        btnSalvaGhe.setEnabled(false);
        btnSalvaGhe.setText("Enviando imagens...");



        UploadTask uploadTask = mountainsRef.putBytes(data2);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return mountainsRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    List<String> fotos = grupoHomogeneo.getFotos();

                    fotos.add(downloadUri.toString());
                    grupoHomogeneo.setFotos(fotos);

                } else {

                    Toast.makeText(EditarGHEActivity.this, "Erro ao salvar imagem", Toast.LENGTH_SHORT).show();
                }

                btnSalvaGheAvanca.setEnabled(true);
                btnSalvaGheAvanca.setText("salvar e finalizar");

                btnSalvaGhe.setEnabled(true);
                btnSalvaGhe.setText("salvar e adicionar outro");


            }
        });
    }


    /////////////////   FOTOS    ///////////////////////////////////////////////////////////////////

}
