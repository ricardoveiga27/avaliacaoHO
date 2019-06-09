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
import com.veigaconsultoria.avaliacaoho.adapters.GrupoHomogeneoAdapter;
import com.veigaconsultoria.avaliacaoho.models.Empresa;
import com.veigaconsultoria.avaliacaoho.models.GrupoHomogeneo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class GrupoHomogeneoActivity extends AppCompatActivity {

    private ListView listaGHE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_homogeneo);

        listaGHE = findViewById(R.id.list_ghe);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("grupoHomogeneo").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                ArrayList<GrupoHomogeneo> gruposhomogeneos = new ArrayList<>();

                for (QueryDocumentSnapshot doc:queryDocumentSnapshots){
                    GrupoHomogeneo grupoHomogeneo = doc.toObject(GrupoHomogeneo.class);
                    grupoHomogeneo.setIdGHE(doc.getId());

                    gruposhomogeneos.add(grupoHomogeneo);
                }

                GrupoHomogeneoAdapter adapter = new GrupoHomogeneoAdapter(GrupoHomogeneoActivity.this, gruposhomogeneos);
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
                Intent intent = new Intent(this, EditaEmpresaActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
