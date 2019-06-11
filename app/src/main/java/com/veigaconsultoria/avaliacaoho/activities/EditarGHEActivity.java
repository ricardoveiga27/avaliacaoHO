package com.veigaconsultoria.avaliacaoho.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.models.GrupoHomogeneo;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ghe);

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

         Button btnSalvaGhe = findViewById(R.id.btn_salva_ghe);
         Button btnSalvaGheAvanca = findViewById(R.id.btn_salva_ghe_avanca);


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



        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("empresa").document(getIntent().getStringExtra("empresaId")).collection("grupoHomogeneo").add(grupoRiscosHomogeneo);
        finish();


//        Toast.makeText(this, "GHE " + editTextNomeGhe +" Cadastrado com Sucesso!!!", Toast.LENGTH_LONG).show();
//
//        Intent intent = new Intent(this, EditarGHEActivity.class);
//        finish();
//        startActivity(intent);

    }
}
