package com.veigaconsultoria.avaliacaoho.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.activities.RiscosActivity;
import com.veigaconsultoria.avaliacaoho.models.Empresa;
import com.veigaconsultoria.avaliacaoho.models.GrupoHomogeneo;

import java.util.ArrayList;


public class GrupoHomogeneoAdapter extends ArrayAdapter<GrupoHomogeneo> {

    private final Context context;
    private final ArrayList<GrupoHomogeneo> elementos;
    private final Empresa empresa;






    public GrupoHomogeneoAdapter(Context context, ArrayList<GrupoHomogeneo> elementos, Empresa empresa) {
        super(context, R.layout.linha_ghe, elementos);
        this.context = context;
        this.elementos = elementos;
        this.empresa = empresa;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_ghe, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.text_nome_ghe);

        nome.setText(elementos.get(position).getNomeGhe());


        rowView.findViewById(R.id.linha_list_ghe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RiscosActivity.class);
                intent.putExtra("empresaId", empresa.getIdEmpresa());
                intent.putExtra("ghe", elementos.get(position));
                context.startActivity(intent);
            }
        });

//        *********************  DELETA GHE  ************************************************************************
        rowView.findViewById(R.id.linha_list_ghe).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context)
                        .setTitle("DESEJA REALMENTE EXCLUIR ESSA EMPRESA?")
                        .setMessage("ATENÇÃO! \n não será possivel reverter essa exclusão após confirmar.")
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

                                db.collection("grupoRiscosClienteEmpresa")
                                        .document(preferences.getString("empresaId", null))
                                        .collection("ghe")
                                        .document(elementos.get(position).getIdGHE()).delete();
                            }
                        })
                        .setNegativeButton("CANCELAR", null);


                alert.create();
                alert.show();

                return false;
            }
        });
//        *********************  DELETA GHE  ************************************************************************
        Button btnEnviaGHE = rowView.findViewById(R.id.btn_enviar_ghe);

        btnEnviaGHE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviarGrupoHomogeneo();

            }
        });

        return rowView;



    }

    private void enviarGrupoHomogeneo() {

        
    }


}