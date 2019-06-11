package com.veigaconsultoria.avaliacaoho.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.adapters.GrupoHomogeneoAdapter;
import com.veigaconsultoria.avaliacaoho.models.Empresa;
import com.veigaconsultoria.avaliacaoho.models.GrupoHomogeneo;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class GrupoHomogeneoActivity extends AppCompatActivity {

    private ListView listaGHE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_homogeneo);

        listaGHE = findViewById(R.id.list_ghe);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Empresa empresa = (Empresa) getIntent().getSerializableExtra("empresa");
        db.collection("empresa").document(empresa.getIdEmpresa()).collection("grupoHomogeneo").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                ArrayList<GrupoHomogeneo> gruposhomogeneos = new ArrayList<>();

                for (QueryDocumentSnapshot doc:queryDocumentSnapshots){
                    GrupoHomogeneo grupoHomogeneo = doc.toObject(GrupoHomogeneo.class);
                    grupoHomogeneo.setIdGHE(doc.getId());

                    gruposhomogeneos.add(grupoHomogeneo);
                }

                GrupoHomogeneoAdapter adapter = new GrupoHomogeneoAdapter(GrupoHomogeneoActivity.this, gruposhomogeneos, empresa);
                listaGHE.setAdapter(adapter);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ghe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.adcionar_ghe:
                Empresa empresa = (Empresa) getIntent().getSerializableExtra("empresa");
                Intent intent = new Intent(this, EditarGHEActivity.class);
                intent.putExtra("empresaId", empresa.getIdEmpresa());
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
