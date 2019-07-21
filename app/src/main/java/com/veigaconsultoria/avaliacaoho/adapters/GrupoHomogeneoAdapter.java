package com.veigaconsultoria.avaliacaoho.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.veigaconsultoria.avaliacaoho.R;
import com.veigaconsultoria.avaliacaoho.activities.EditarGHEActivity;
import com.veigaconsultoria.avaliacaoho.activities.RiscosActivity;
import com.veigaconsultoria.avaliacaoho.models.Empresa;
import com.veigaconsultoria.avaliacaoho.models.GrupoHomogeneo;
import com.veigaconsultoria.avaliacaoho.models.Riscos;

import java.util.ArrayList;

import javax.annotation.Nullable;


public class GrupoHomogeneoAdapter extends ArrayAdapter<GrupoHomogeneo> {

    private final Context context;
    private final ArrayList<GrupoHomogeneo> elementos;
    private final Empresa empresa;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

//    private GrupoHomogeneo grupoHomogeneo;

    private String resultadoRiscos;

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


        TextView nome = rowView.findViewById(R.id.text_nome_ghe);

        nome.setText(elementos.get(position).getNomeGhe());



        TextView listaRiscosEditText = rowView.findViewById(R.id.editText_riscosGhe);

        encontraRiscos(elementos.get(position), listaRiscosEditText);

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

                                db.collection("empresa")
                                        .document(empresa.getIdEmpresa())
                                        .collection("grupoHomogeneo")
                                        .document(elementos.get(position).getIdGHE()).delete();
                            }
                        })
                        .setNegativeButton("CANCELAR", null);


                alert.create();
                alert.show();

                return false;
            }
        });

        ImageButton btnEditaGHE = rowView.findViewById(R.id.btn_editar_ghe);

        btnEditaGHE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditarGHEActivity.class);
                intent.putExtra("empresaId", empresa.getIdEmpresa());
                intent.putExtra("ghe", elementos.get(position));
                context.startActivity(intent);
            }
        });

//        *********************  DELETA GHE  ************************************************************************
        ImageButton btnEnviaGHE = rowView.findViewById(R.id.btn_enviar_ghe);

        btnEnviaGHE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviarGrupoHomogeneo(elementos.get(position));

            }
        });

        return rowView;

    }

    private void encontraRiscos(GrupoHomogeneo ghe, final TextView view) {
        db.collection("empresa")
                .document(empresa.getIdEmpresa())
                .collection("grupoHomogeneo")
                .document(ghe.getIdGHE())
                .collection("riscos")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                        resultadoRiscos = "riscos ->> ";

                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            Riscos risco = doc.toObject(Riscos.class);

                            resultadoRiscos = resultadoRiscos +
                                    risco.getRiscoEsocialRiscos() + " | ";
                        }

                        view.setText(resultadoRiscos);
                    }

                });
    }


    private void enviarGrupoHomogeneo(final GrupoHomogeneo ghe) {
        String result = "----------- EMPRESA -----------\n\n" +
                "Nome: " + empresa.getNome() + "\n" +
                "Razao Social: " + empresa.getRazaoSocial() + "\n" +
                "Endereço: " + empresa.getEndereco() + "\n" +
                "CNPJ: " + empresa.getCnpj() + "\n" +
                "Cidade: " + empresa.getCidade() + "\n" +
                "Estado: " + empresa.getEstado() + "\n" +
                "Quantidade de Empregados: " + empresa.getQtdEmpregados() + "\n" +
                "CNAE: " + empresa.getCnae() + "\n";

        result += "\n----------- GHE -----------\n\n";

        result += "Nome Ghe: " + ghe.getNomeGhe() + "\n" +
                "Descrição: " + ghe.getDescricao() + "\n" +
                "Qnt. Empmpregado Ghe: " + ghe.getQtdEmpGhe() + "\n" +
                "Funções Ghe: " + ghe.getFuncoesGhe() + "\n" +
                "Pé Direito Ghe: " + ghe.getPeDirGhe() + "\n" +
                "àrea Ghe: " + ghe.getAreaGhe() + "\n" +
                "Ventilação Ghe: " + ghe.getVentilacaoGhe() + "\n" +
                "Iluminação Ghe: " + ghe.getIluminacaoGhe() + "\n" +
                "Paredes / Divisões Ghe: " + ghe.getRevestimentoGhe() + "\n" +
                "Piso Ghe: " + ghe.getPisoGhe() + "\n" +
                "Iluminancia Ghe: " + ghe.getIluminanciaGhe() + "\n" +
                "Equipamentos Ghe: " + ghe.getEquipamentosGhe() + "\n\n"
        ;

        if (ghe.getFotos().size() > 0) {
            result += "fotos: \n";

            for (String foto : ghe.getFotos()) {
                result += "\n" + foto;
            }

            result += "\n\n";
        }

        final String finalResult = result;


        db.collection("empresa")
                .document(empresa.getIdEmpresa())
                .collection("grupoHomogeneo")
                .document(ghe.getIdGHE())
                .collection("riscos")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        String continueResult = finalResult;

                        continueResult += "----------- RISCOS -----------\n\n";

                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            Riscos risco = doc.toObject(Riscos.class);

                            continueResult += "Risco: " + risco.getRiscoEsocialRiscos() + "\n" +
                                    "Descrição: " + risco.getDescricaoRiscos() + "\n" +
                                    "Meio de Propagacao: " + risco.getMeioDePropagacaoRiscos() + "\n" +
                                    "Grupo: " + risco.getGrupoRiscos() + "\n" +
                                    "Tipo do Risco: " + risco.getTipoRisco() + "\n" +
                                    "Habitual ou Eventual: " + risco.getHabtualEventualRiscos() + "\n" +
                                    "Continuo ou Intermitente: " + risco.getContinuoIntermitenteRiscos() + "\n" +
                                    "Quantitativo: " + (risco.getQuantitativoRiscos() != null && risco.getQuantitativoRiscos() ? "Sim" : "Não") + "\n" +
                                    "Intensidade: " + risco.getIntensidadeRiscos() + "\n" +
                                    "Limite Tolerancia: " + risco.getLimiteToleranciaRiscos() + "\n" +
                                    "Epi: " + risco.getEpiRiscos() + "\n" +
                                    "Epc: " + risco.getEpcRiscos() + "\n" +
                                    "Comentarios: " + risco.getComentarioRiscos() + "\n\n";
                        }

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Empresa: " + empresa.getNome() + ", GHE: " + ghe.getNomeGhe());
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, continueResult);
                        context.startActivity(Intent.createChooser(sharingIntent, "ENVIAR GHE"));
                    }
                });
    }

}