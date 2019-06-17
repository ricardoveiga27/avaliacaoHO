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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.activities.EditaEmpresaActivity;
import com.veigaconsultoria.avaliacaoho.activities.EditarGHEActivity;
import com.veigaconsultoria.avaliacaoho.activities.GrupoHomogeneoActivity;
import com.veigaconsultoria.avaliacaoho.activities.RiscosActivity;
import com.veigaconsultoria.avaliacaoho.models.Empresa;
import com.veigaconsultoria.avaliacaoho.models.GrupoHomogeneo;

import java.util.ArrayList;


public class EmpresaAdapter extends ArrayAdapter<Empresa> {

    private final Context context;
    private final ArrayList<Empresa> elementos;

    public EmpresaAdapter(Context context, ArrayList<Empresa> elementos) {
        super(context, R.layout.linha_empresa, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_empresa, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.text_nome_empresa);
        TextView cnpj = (TextView) rowView.findViewById(R.id.text_cnpj);
        nome.setText(elementos.get(position).getNome());
        cnpj.setText(elementos.get(position).getCnpj());

        rowView.findViewById(R.id.linha_empresa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GrupoHomogeneoActivity.class);
                intent.putExtra("empresa", elementos.get(position));
                context.startActivity(intent);
            }
        });

        rowView.findViewById(R.id.linha_empresa).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context)
                        .setTitle("DESEJA REALMENTE EXCLUIR ESSA EMPRESA?")
                        .setMessage("ATENÇÃO! \n não será possivel reverter essa exclusão após confirmar.")
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseFirestore db = FirebaseFirestore.getInstance();

                                db.collection("empresa")
                                        .document(elementos.get(position).getIdEmpresa()).delete();
                            }
                        })
                        .setNegativeButton("CANCELAR", null);

                alert.create();
                alert.show();

                return false;
            }
        });

        ImageButton btnEditaEmpresa = rowView.findViewById(R.id.btn_editar_empresa);

        btnEditaEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditaEmpresaActivity.class);
                intent.putExtra("empresa", elementos.get(position));
                context.startActivity(intent);
            }
        });

        return rowView;
    }

}