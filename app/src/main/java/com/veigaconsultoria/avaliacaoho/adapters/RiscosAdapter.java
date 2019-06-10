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
import com.veigaconsultoria.avaliacaoho.activities.EditarGHEActivity;
import com.veigaconsultoria.avaliacaoho.activities.GrupoHomogeneoActivity;
import com.veigaconsultoria.avaliacaoho.models.Empresa;
import com.veigaconsultoria.avaliacaoho.models.GrupoHomogeneo;
import com.veigaconsultoria.avaliacaoho.models.Riscos;

import java.util.ArrayList;


public class RiscosAdapter extends ArrayAdapter<Riscos> {

    private final Context context;
    private final ArrayList<Riscos> elementos;

    public RiscosAdapter(Context context, ArrayList<Riscos> elementos) {
        super(context, R.layout.linha_riscos, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_riscos, parent, false);
        TextView nomeRisco = (TextView) rowView.findViewById(R.id.text_nome_risco);
        TextView intensidadeRisco = (TextView) rowView.findViewById(R.id.text_intensidade_risco);
        nomeRisco.setText(elementos.get(position).getRiscoEsocialRiscos());
        intensidadeRisco.setText(elementos.get(position).getIntensidadeRiscos().toString());

//        rowView.findViewById(R.id.linha_empresa).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, GrupoHomogeneoActivity.class);
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
//                                db.collection("grupoRiscosClienteEmpresa")
//                                        .document(preferences.getString("grupoRiscosId", null))
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