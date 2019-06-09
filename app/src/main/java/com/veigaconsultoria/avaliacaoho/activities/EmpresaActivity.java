package com.veigaconsultoria.avaliacaoho.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.adapters.EmpresaAdapter;
import com.veigaconsultoria.avaliacaoho.models.Empresa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class EmpresaActivity extends AppCompatActivity {

    private ListView listaEmpresas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        listaEmpresas = findViewById(R.id.list_empresa);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("empresa").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                ArrayList<Empresa> empresas = new ArrayList<>();

               for (QueryDocumentSnapshot doc:queryDocumentSnapshots){
                   Empresa empresa = doc.toObject(Empresa.class);
                   empresa.setIdEmpresa(doc.getId());

                   empresas.add(empresa);
               }

                EmpresaAdapter adapter = new EmpresaAdapter(EmpresaActivity.this, empresas);
               listaEmpresas.setAdapter(adapter);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_empresa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nova_empresa:
                Intent intent = new Intent(this, EditaEmpresaActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
