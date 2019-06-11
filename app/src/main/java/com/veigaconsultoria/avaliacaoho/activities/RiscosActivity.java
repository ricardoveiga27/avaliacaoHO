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
import com.veigaconsultoria.avaliacaoho.adapters.RiscosAdapter;
import com.veigaconsultoria.avaliacaoho.models.GrupoHomogeneo;
import com.veigaconsultoria.avaliacaoho.models.Riscos;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class RiscosActivity extends AppCompatActivity {

    private ListView listaRiscos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riscos);

        listaRiscos = findViewById(R.id.list_riscos);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        GrupoHomogeneo grupoHomogeneo = (GrupoHomogeneo) getIntent().getSerializableExtra("ghe");

        db.collection("empresa")
                .document(getIntent()
                .getStringExtra("empresaId"))
                .collection("grupoHomogeneo")
                .document(grupoHomogeneo.getIdGHE())
                .collection("riscos")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                ArrayList<Riscos> riscos = new ArrayList<>();

                for (QueryDocumentSnapshot doc:queryDocumentSnapshots){
                    Riscos risco = doc.toObject(Riscos.class);
                    risco.setIdRisco(doc.getId());

                    riscos.add(risco);
                }

                RiscosAdapter adapter = new RiscosAdapter(RiscosActivity.this, riscos);
                listaRiscos.setAdapter(adapter);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_risco, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nova_risco:

                GrupoHomogeneo grupoHomogeneo = (GrupoHomogeneo) getIntent().getSerializableExtra("ghe");

                Intent intent = new Intent(this, EditaRiscosActivity.class);
                intent.putExtra("gheId", grupoHomogeneo.getIdGHE());
                intent.putExtra("empresaId",getIntent().getStringExtra("empresaId") );
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
