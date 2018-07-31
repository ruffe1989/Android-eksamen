package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;

public class InfoTurActivity extends AppCompatActivity {
    private Button viskart, nyPassasjer;
    private TextView dato, fra, til, fraTid, tilTid, stopp,
    ledig, merke, modell, årsmodell, sykkelfeste, takboks,
    navnsjåfør, tlf, epost;
    private RequestQueue kø;
    private int kjøreturID;
    private Context mContext;
    private String kartStart, kartMål, nyLedig,sendKjøreturID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tur);
        mContext = getApplicationContext();

        kjøreturID = getIntent().getIntExtra("kjøreturID",0);
        sendKjøreturID = String.valueOf(kjøreturID);


        /**Kobling mellom textView og xml for reisen*/
        dato = findViewById(R.id.dato);
        fra = findViewById(R.id.fraInfo);
        til = findViewById(R.id.tilInfo);
        fraTid = findViewById(R.id.fraTidInfo);
        tilTid = findViewById(R.id.tilTidInfo);
        stopp = findViewById(R.id.kjenteStoppInfo);
        ledig = findViewById(R.id.antallLedigInfo);
        /**Kobling mellom textView og xml for kjøretøy*/
        merke = findViewById(R.id.merkeInfo);
        modell = findViewById(R.id.modellInfo);
        årsmodell = findViewById(R.id.årsmodellInfo);
        sykkelfeste = findViewById(R.id.sykkelfesteInfo);
        takboks = findViewById(R.id.takboksInfo);
        /**Kobling mellom textView og xml for sjåfør*/
        navnsjåfør = findViewById(R.id.navnSjåførInfo);
        tlf = findViewById(R.id.tlfInfo);
        epost = findViewById(R.id.epostInfo);

        /**Kobling mellom Button og xml*/
        viskart = findViewById(R.id.visKart);
        nyPassasjer = findViewById(R.id.nyPassasjer);

        /**Hent info fra forrige intent*/
        dato.setText(getIntent().getStringExtra("dato"));
        fra.setText(getIntent().getStringExtra("fra"));
        til.setText(getIntent().getStringExtra("til"));
        fraTid.setText(getIntent().getStringExtra("fraTid"));
        tilTid.setText(getIntent().getStringExtra("tilTid"));
        stopp.setText(getIntent().getStringExtra("stopp"));
        int tmp = getIntent().getIntExtra("ledig",0);
        String antallLedig = String.valueOf(tmp);
        ledig.setText(antallLedig);
        merke.setText(getIntent().getStringExtra("merke"));
        modell.setText(getIntent().getStringExtra("modell"));
        årsmodell.setText(getIntent().getStringExtra("årsmodell"));
        int tmp2 = getIntent().getIntExtra("sykkelfeste",000);
        if (tmp2 == 1){
            sykkelfeste.setText("Ja");
        }else {
            sykkelfeste.setText("Nei");
        }
        int tmp3 = getIntent().getIntExtra("takboks",000);
        if (tmp3 == 1){
            takboks.setText("Ja");
        }else {
            takboks.setText("Nei");
        }
        navnsjåfør.setText(getIntent().getStringExtra("sjåførNavn"));
        tlf.setText(getIntent().getStringExtra("sjåførTlf"));
        epost.setText(getIntent().getStringExtra("sjåførEpost"));

        nyLedig = String.valueOf((tmp-1));


        /**Initiering av string for å kunne sende til kart*/
        kartStart = getIntent().getStringExtra("fra");
        kartMål = getIntent().getStringExtra("til");
        Toast toast = Toast.makeText(getApplicationContext(),kartStart+"-"+kartMål,Toast.LENGTH_LONG);
        toast.show();

        /**OnClick for buttons*/
        viskart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visKartIntent = new Intent(mContext,MapsActivity.class);
                visKartIntent.putExtra("start",kartStart);
                visKartIntent.putExtra("mål",kartMål);
                mContext.startActivity(visKartIntent);

            }
        });
        nyPassasjer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,RegPassasjerActivity.class);
                intent.putExtra("kjøreturID",sendKjøreturID);
                intent.putExtra("ledig",nyLedig);
                mContext.startActivity(intent);

            }
        });

    }

}
