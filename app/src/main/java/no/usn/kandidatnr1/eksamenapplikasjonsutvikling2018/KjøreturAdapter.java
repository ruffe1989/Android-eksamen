package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
/**Adapter som setter info inn i recycleview*/
public class KjøreturAdapter extends RecyclerView.Adapter<KjøreturAdapter.ViewHolder>{

    private ArrayList<Kjøretur> mKjøreturData;
    private Context mContext;

    public KjøreturAdapter(Context context, ArrayList<Kjøretur> mKjøreturData) {
        this.mContext = context;
        this.mKjøreturData = mKjøreturData;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mDato,mStartSted,mSluttSted,mStartTid,
                mSluttTid,mKjenteStopp,mLedigPlass;

        public ViewHolder(View itemView) {
            super(itemView);
            mDato = itemView.findViewById(R.id.dato);
            mStartSted = itemView.findViewById(R.id.fraInfo);
            mSluttSted = itemView.findViewById(R.id.tilInfo);
            mStartTid = itemView.findViewById(R.id.fraTidInfo);
            mSluttTid = itemView.findViewById(R.id.tilTidInfo);
            mKjenteStopp = itemView.findViewById(R.id.kjenteStoppInfo);
            mLedigPlass = itemView.findViewById(R.id.antallLedigInfo);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Kjøretur kjøretur = mKjøreturData.get(getAdapterPosition());
            Intent infoKjøretur = new Intent(mContext,InfoTurActivity.class);
            infoKjøretur.putExtra("kjøreturID",kjøretur.getKjøreturID());
            infoKjøretur.putExtra("dato",kjøretur.getDato());
            infoKjøretur.putExtra("fra",kjøretur.getStartSted());
            infoKjøretur.putExtra("til",kjøretur.getSluttSted());
            infoKjøretur.putExtra("fraTid",kjøretur.getStartTid());
            infoKjøretur.putExtra("tilTid",kjøretur.getSluttTid());
            infoKjøretur.putExtra("stopp",kjøretur.getKjenteStopp());
            infoKjøretur.putExtra("ledig",kjøretur.getLedigPlass());
            infoKjøretur.putExtra("merke",kjøretur.getMerke());
            infoKjøretur.putExtra("modell", kjøretur.getModell());
            infoKjøretur.putExtra("årsmodell",kjøretur.getÅrsmodell());
            infoKjøretur.putExtra("sykkelfeste",kjøretur.getSykkelfeste());
            infoKjøretur.putExtra("takboks",kjøretur.getTakboks());
            infoKjøretur.putExtra("sjåførNavn",kjøretur.getSjaaforNavn());
            infoKjøretur.putExtra("sjåførTlf",kjøretur.getSjaaforTlf());
            infoKjøretur.putExtra("sjåførEpost",kjøretur.getSjaaforEpost());
            mContext.startActivity(infoKjøretur);
        }
    }


    @Override
    public KjøreturAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_tur,
                parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kjøretur nyKjøretur = mKjøreturData.get(position);
        holder.mDato.setText(nyKjøretur.getDato());
        holder.mStartSted.setText(nyKjøretur.getStartSted());
        holder.mSluttSted.setText(nyKjøretur.getSluttSted());
        holder.mStartTid.setText(nyKjøretur.getStartTid());
        holder.mSluttTid.setText(nyKjøretur.getSluttTid());
        holder.mKjenteStopp.setText(nyKjøretur.getKjenteStopp());
        holder.mLedigPlass.setText(""+ nyKjøretur.getLedigPlass());

    }

    @Override
    public int getItemCount() {
        return mKjøreturData.size();
    }

}
