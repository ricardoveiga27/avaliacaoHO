package com.veigaconsultoria.avaliacaoho.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.firestore.FirebaseFirestore;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.models.Riscos;

public class EditaRiscosActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_riscos);

        final EditText RiscoEsocialRiscos = findViewById(R.id.editText_RiscoEsocialRiscos);
        final EditText descricaoRiscos = findViewById(R.id.editText_descricaoRiscos);
        final EditText meioDePropagacaoRiscos = findViewById(R.id.editText_meioDePropagacaoRiscos);
        final RadioGroup grupoRiscos = findViewById(R.id.radio_grupo_risco);
        final EditText habtualEventualRiscos = findViewById(R.id.editText_habtualEventualRiscos);
        final EditText continuoIntermitenteRiscos = findViewById(R.id.editText_continuoIntermitenteRiscos);
        final CheckBox quantitativoRiscos = findViewById(R.id.editText_quantitativoRiscos);
        final EditText intensidadeRiscos = findViewById(R.id.editText_intensidadeRiscos);
        final EditText limiteToleranciaRiscos = findViewById(R.id.editText_limiteToleranciaRiscos);
        final EditText epiRiscos = findViewById(R.id.editText_epiRiscos);
        final EditText epcRiscos = findViewById(R.id.editText_epcRiscos);
        final EditText comentarioRiscos = findViewById(R.id.editText_editText_comentarioRiscos);
        final String tipoRisco = "0";




        grupoRiscos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String tipoRisco = "0";

                if (checkedId == R.id.radio_biologico){
                    tipoRisco = "Biológico";
                }
                else if (checkedId == R.id.radio_fisico){
                    tipoRisco = "Físico)";
                }
                else if (checkedId == R.id.radio_quimico){
                    tipoRisco = "Químico)";
                }
                else if (checkedId == R.id.radio_mecanico){
                    tipoRisco = "Mecânico | Acidentes";
                }
                else {
                    tipoRisco = "Não Informado";
                }
            }
        });


        final Button botaoSalvar = findViewById(R.id.btn_salva_riscos);


        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                Riscos riscos = new Riscos();
                riscos.setRiscoEsocialRiscos(RiscoEsocialRiscos.getText().toString());
                riscos.setDescricaoRiscos(descricaoRiscos.getText().toString());
                riscos.setMeioDePropagacaoRiscos(meioDePropagacaoRiscos.getText().toString());
                riscos.setTipoRisco(tipoRisco);
                riscos.setHabtualEventualRiscos(habtualEventualRiscos.getText().toString());
                riscos.setContinuoIntermitenteRiscos(continuoIntermitenteRiscos.getText().toString());
                riscos.setQuantitativoRiscos(quantitativoRiscos.isChecked());
                riscos.setIntensidadeRiscos(Float.parseFloat(intensidadeRiscos.getText().toString()));
                riscos.setLimiteToleranciaRiscos(Float.parseFloat(limiteToleranciaRiscos.getText().toString()));
                riscos.setEpiRiscos(epiRiscos.getText().toString());
                riscos.setEpcRiscos(epcRiscos.getText().toString());
                riscos.setComentarioRiscos(comentarioRiscos.getText().toString());




                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("empresa")
                        .document(getIntent()
                                .getStringExtra("empresaId"))
                        .collection("grupoHomogeneo")
                        .document((getIntent()
                                .getStringExtra("gheId")))
                        .collection("riscos").add(riscos);
                finish();
            }
        });





    }
}
