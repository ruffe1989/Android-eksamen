package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ReiseAdapter extends RecyclerView.Adapter<ReiseAdapter.ViewHolder>{

    private ArrayList<Kjøretur> mReiseData;
    private Context mContext;

    public ReiseAdapter(Context context, ArrayList<Kjøretur> mReiseData) {
        this.mContext = context;
        this.mReiseData = mReiseData;
    }

    class ViewHolder extends RecyclerView.ViewHolder  {

        public final TextView mDato,mStartSted,mSluttSted,mStartTid,
                mSluttTid,mKjenteStopp,mLedigPlass;

        public ViewHolder(View itemView) {
            super(itemView);
            mDato = itemView.findViewById(R.id.dato1);
            mStartSted = itemView.findViewById(R.id.fraInfo1);
            mSluttSted = itemView.findViewById(R.id.tilInfo1);
            mStartTid = itemView.findViewById(R.id.fraTidInfo1);
            mSluttTid = itemView.findViewById(R.id.tilTidInfo1);
            mKjenteStopp = itemView.findViewById(R.id.kjenteStoppInfo1);
            mLedigPlass = itemView.findViewById(R.id.antallLedigInfo1);

        }
    }

    @Override
    public ReiseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.liste_mineturer,
                parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kjøretur nyKjøretur = mReiseData.get(position);
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
        return mReiseData.size();
    }

}
