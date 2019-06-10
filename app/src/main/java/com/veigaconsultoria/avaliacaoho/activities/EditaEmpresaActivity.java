package com.veigaconsultoria.avaliacaoho.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.models.Empresa;

public class EditaEmpresaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_empresa);

        final EditText nome = findViewById(R.id.edit_text_nome_empresa);
        final EditText cnpj = findViewById(R.id.edit_text_cnpj);
        final EditText razaoSocial = findViewById(R.id.edit_text_razaoSocial);
        final EditText endreco = findViewById(R.id.edit_text_endereco);
        final EditText cnae = findViewById(R.id.edit_text_cnae);
        final EditText cidade = findViewById(R.id.edit_text_cidade);
        final EditText estado = findViewById(R.id.edit_text_estado);
        final EditText qtdEmpregados = findViewById(R.id.edit_text_qtdEmpregados);
        final Button botaoSalvar = findViewById(R.id.btn_salvar);


        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Empresa empresa = new Empresa();
                    empresa.setNome(nome.getText().toString());
                    empresa.setCnpj(cnpj.getText().toString());
                    empresa.setRazaoSocial(razaoSocial.getText().toString());
                    empresa.setEndereco(endreco.getText().toString());
                    empresa.setNome(cnae.getText().toString());
                    empresa.setNome(cidade.getText().toString());
                    empresa.setNome(estado.getText().toString());
                    empresa.setNome(qtdEmpregados.getText().toString());




                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("empresa").add(empresa);
                finish();
            }
        });





    }
}
