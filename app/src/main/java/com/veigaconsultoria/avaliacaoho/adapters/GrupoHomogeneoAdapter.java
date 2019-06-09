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
import android.widget.ImageView;
import android.widget.TextView;

import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.models.Empresa;
import com.veigaconsultoria.avaliacaoho.models.GrupoHomogeneo;

import java.util.ArrayList;


public class GrupoHomogeneoAdapter extends ArrayAdapter<GrupoHomogeneo> {

    private final Context context;
    private final ArrayList<GrupoHomogeneo> elementos;

    public GrupoHomogeneoAdapter(Context context, ArrayList<GrupoHomogeneo> elementos) {
        super(context, R.layout.linha_ghe, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_ghe, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.text_nome_empresa);

        nome.setText(elementos.get(position).getNomeGhe());


//        rowView.findViewById(R.id.editaEmpresa).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, EditarEmpresaActivity.class);
//                intent.putExtra("empresa", elementos.get(position));
//                context.startActivity(intent);
//            }
//        });

//        rowView.findViewById(R.id.editaEmpresa).setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//
//                AlertDialog.Builder alert = new AlertDialog.Builder(context)
//                        .setTitle("DESEJA REALMENTE EXCLUIR ESSA EMPRESA?")
//                        .setMessage("ATENÇÃO! \n não será possivel reverter essa exclusão após confirmar.")
//                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                FirebaseFirestore db = FirebaseFirestore.getInstance();
//                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//
//                                db.collection("grupoClienteEmpresa")
//                                        .document(preferences.getString("grupoId", null))
//                                        .collection("empresas")
//                                        .document(elementos.get(position).getId()).delete();
//                            }
//                        })
//                        .setNegativeButton("CANCELAR", null);
//
//
//                alert.create();
//                alert.show();
//
//                return false;
//            }
//        });

        return rowView;
    }

}