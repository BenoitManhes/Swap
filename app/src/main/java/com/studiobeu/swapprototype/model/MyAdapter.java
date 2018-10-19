package com.studiobeu.swapprototype.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.studiobeu.swapprototype.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final List<Reseau> characters = new ArrayList<>();

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reseau_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Reseau reseau = characters.get(position);
        holder.display(reseau);
    }

    public void add(Reseau pair) {
        characters.add(pair);
        this.notifyDataSetChanged();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final EditText pseudo;
        private final EditText lien;
        private final TextView titre;
        private int id;
        private final ImageView icone;

        private final Context context;
        private Reseau currentReseau;

        public MyViewHolder(final View itemView) {
            super(itemView);

            titre = ((TextView) itemView.findViewById(R.id.titre));
            pseudo = ((EditText) itemView.findViewById(R.id.editPseudo));
            lien = ((EditText) itemView.findViewById(R.id.editLien));
            icone = ((ImageView) itemView.findViewById(R.id.icone_reseau));

            context = itemView.getContext();

            pseudo.setEnabled(false);
            pseudo.setEnabled(false);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    View v = view.inflate(context, R.layout.alert_dialog,null);
                    alert.setView(v);
                    final EditText description = (EditText)view.findViewById(R.id.enterPseudo);
                    final EditText noms = (EditText)view.findViewById(R.id.enterLien);



                    alert.setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });

                    alert.show();
                }
            });
        }

        public void display(Reseau res) {
            currentReseau = res;
            pseudo.setText(res.getName());
            lien.setText(res.getAdress());
            titre.setText(res.getTitre());
            id = res.getType();
            printIcone();
        }

        public void printIcone(){
            switch (id){
                case Parametre.ID_FACEBOOK : icone.setBackgroundResource(R.drawable.facebook);break;
                case Parametre.ID_TELEPHONE : icone.setBackgroundResource(R.drawable.tel);break;
                case Parametre.ID_MAIL : icone.setBackgroundResource(R.drawable.mail);break;
                case Parametre.ID_LINKEDIN : icone.setBackgroundResource(R.drawable.linkedin);break;
                case Parametre.ID_SNAP : icone.setBackgroundResource(R.drawable.snapchat);break;
                default: break;
            }
        }

    }

}
