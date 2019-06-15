package com.veigaconsultoria.avaliacaoho.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.models.Riscos;

public class EditaRiscosActivity extends AppCompatActivity {

    private String epiRiscos;
    private String habtualEventualRiscos;
    private String continuoIntermitenteRiscos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_riscos);

        final EditText RiscoEsocialRiscos = findViewById(R.id.editText_RiscoEsocialRiscos);
        final EditText descricaoRiscos = findViewById(R.id.editText_descricaoRiscos);
        final EditText meioDePropagacaoRiscos = findViewById(R.id.editText_meioDePropagacaoRiscos);
        final RadioGroup grupoRiscos = findViewById(R.id.radio_grupo_risco);
//        final RadioGroup habtualEventualRiscos = findViewById(R.id.radiogroup_frequencia);
//        final RadioGroup continuoIntermitenteRiscos = findViewById(R.id.radiogroup_exposicao);
//        final CheckBox quantitativoRiscos = findViewById(R.id.editText_quantitativoRiscos);
        final EditText intensidadeRiscos = findViewById(R.id.editText_intensidadeRiscos);
        final EditText limiteToleranciaRiscos = findViewById(R.id.editText_limiteToleranciaRiscos);

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


        //CONCATENA EPIS************************************************************

        CheckBox epi1 = findViewById(R.id.epi1_calcado);
        adicionaEpei(epi1);

        CheckBox epi2 = findViewById(R.id.epi2_prot_auricular);
        adicionaEpei(epi2);

        CheckBox epi3 = findViewById(R.id.epi3_oculos_prot);
        adicionaEpei(epi3);

        CheckBox epi4 = findViewById(R.id.epi4_creme_prot);
        adicionaEpei(epi4);

        CheckBox epi5 = findViewById(R.id.epi5_luva_nitriliza);
        adicionaEpei(epi5);

        CheckBox epi6 = findViewById(R.id.epi6_luva_raspa);
        adicionaEpei(epi6);

        CheckBox epi7 = findViewById(R.id.epi7_cinto_paraquedista);
        adicionaEpei(epi7);

        CheckBox epi8 = findViewById(R.id.epi8_avental_raspa);
        adicionaEpei(epi8);

        CheckBox epi9 = findViewById(R.id.epi9_mascara_solda);
        adicionaEpei(epi9);

        CheckBox epi10 = findViewById(R.id.epi10_avental_imper);
        adicionaEpei(epi10);

        CheckBox epi11= findViewById(R.id.epi11_bota_imper);
        adicionaEpei(epi11);

        CheckBox epi12 = findViewById(R.id.epi12_respirador_poeira);
        adicionaEpei(epi12);

        CheckBox epi13 = findViewById(R.id.epi13_respirador_vo);
        adicionaEpei(epi13);

        //CONCATENA EPIS************************************************************




        RadioGroup frequencia = findViewById(R.id.radiogroup_frequencia);

        switch (frequencia.getCheckedRadioButtonId()){
            case R.id.radio_habitual:
                habtualEventualRiscos = "Habitual";
                break;

            case R.id.radio_eventual:
                habtualEventualRiscos = "Eventual";
                break;
        }


        RadioGroup exposicao = findViewById(R.id.radiogroup_exposicao);

        switch (exposicao.getCheckedRadioButtonId()){
            case R.id.radio_intermitente:
                continuoIntermitenteRiscos = "Intermitente";
                break;

            case R.id.radio_eventual:
                continuoIntermitenteRiscos = "Contínua";
                break;
        }







        final Button botaoSalvar = findViewById(R.id.btn_salva_riscos);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Riscos riscos = new Riscos();
                riscos.setRiscoEsocialRiscos(RiscoEsocialRiscos.getText().toString());
                riscos.setDescricaoRiscos(descricaoRiscos.getText().toString());
                riscos.setMeioDePropagacaoRiscos(meioDePropagacaoRiscos.getText().toString());
                riscos.setTipoRisco(tipoRisco);
                riscos.setHabtualEventualRiscos(habtualEventualRiscos);
                riscos.setContinuoIntermitenteRiscos(continuoIntermitenteRiscos);
                riscos.setIntensidadeRiscos(Float.parseFloat(intensidadeRiscos.getText().toString()));
                riscos.setLimiteToleranciaRiscos(Float.parseFloat(limiteToleranciaRiscos.getText().toString()));
                riscos.setEpiRiscos(epiRiscos);
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



                Intent intent = new Intent(EditaRiscosActivity.this, RiscosActivity.class);
                startActivity(intent);

                finish();


                Toast.makeText(EditaRiscosActivity.this, "Risco cadastrado com sucesso!!!", Toast.LENGTH_LONG).show();

            }
        });





    }

    private void adicionaEpei(CheckBox checkBox) {

        if (checkBox.isChecked()){

            epiRiscos = epiRiscos + ",\n" + checkBox.getText().toString();
        }
    }
}
