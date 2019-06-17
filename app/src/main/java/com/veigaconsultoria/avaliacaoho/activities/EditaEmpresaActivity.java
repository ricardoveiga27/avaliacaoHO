package com.veigaconsultoria.avaliacaoho.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.models.Empresa;

public class EditaEmpresaActivity extends AppCompatActivity {
    private String empresaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_empresa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("EDITA EMPRESA");

        final EditText nome = findViewById(R.id.edit_text_nome_empresa);
        final EditText cnpj = findViewById(R.id.edit_text_cnpj);
        final EditText razaoSocial = findViewById(R.id.edit_text_razaoSocial);
        final EditText endreco = findViewById(R.id.edit_text_endereco);
        final EditText cnae = findViewById(R.id.edit_text_cnae);
        final EditText cidade = findViewById(R.id.edit_text_cidade);
        final EditText estado = findViewById(R.id.edit_text_estado);
        final EditText qtdEmpregados = findViewById(R.id.edit_text_qtdEmpregados);
        final Button botaoSalvar = findViewById(R.id.btn_salvar);

        if (getIntent().hasExtra("empresa")) {
            Empresa empresa = (Empresa)getIntent().getSerializableExtra("empresa");

            this.empresaId = empresa.getIdEmpresa();

            nome.setText(empresa.getNome());
            cnpj.setText(empresa.getCnpj());
            razaoSocial.setText(empresa.getRazaoSocial());
            endreco.setText(empresa.getEndereco());
            cnae.setText(empresa.getCnae());
            cidade.setText(empresa.getCidade());
            estado.setText(empresa.getEstado());
            qtdEmpregados.setText(String.valueOf(empresa.getQtdEmpregados()));
        }

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empresa empresa = new Empresa();
                empresa.setNome(nome.getText().toString());
                empresa.setCnpj(cnpj.getText().toString());
                empresa.setRazaoSocial(razaoSocial.getText().toString());
                empresa.setEndereco(endreco.getText().toString());
                empresa.setCnae(cnae.getText().toString());
                empresa.setCidade(cidade.getText().toString());
                empresa.setEstado(estado.getText().toString());
                empresa.setQtdEmpregados(Integer.parseInt(qtdEmpregados.getText().toString()));

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                if (getIntent().hasExtra("empresa")) {
                    db.collection("empresa").document(empresaId).set(empresa);
                } else {
                    db.collection("empresa").add(empresa);
                }

                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
