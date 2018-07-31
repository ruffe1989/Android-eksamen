package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FinnReiseActivity extends AppCompatActivity {

    private EditText nyttSøk;
    private ImageButton søkBtn;
    private RecyclerView recyclerView;
    private ArrayList<Kjøretur> kjøreturData;
    private KjøreturAdapter kjøreturAdapter;
    private RequestQueue kø;
    private String søkeord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finn_reise);

        nyttSøk = findViewById(R.id.searchTur);
        nyttSøk.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        søkBtn = findViewById(R.id.searchBtn);
        recyclerView = findViewById(R.id.turListe);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));

        kjøreturData = new ArrayList<>();



        søkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                søkeord = nyttSøk.getText().toString();
                utførSøk(søkeord);
            }
        });


        kjøreturAdapter = new KjøreturAdapter(this,kjøreturData);
        recyclerView.setAdapter(kjøreturAdapter);

    }

    private void utførSøk(String søkeord) {
        String url = getResources().getString(R.string.ENDPOINT) +"kjoretur?filter=sluttSted,eq,"
        + søkeord;

        if (isOnline()){
            kø = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest finnTur = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject tabell = response.getJSONObject("kjoretur");
                                JSONArray infoArray = tabell.getJSONArray("records");

                                kjøreturData.clear();

                                for (int i = 0; i < infoArray.length(); i++) {
                                    JSONArray tur = infoArray.getJSONArray(i);
                                    int kjøreturID = tur.getInt(0);
                                    String dato = tur.getString(1);
                                    String startTid = tur.getString(2);
                                    String sluttTid = tur.getString(3);
                                    String start = tur.getString(4);
                                    String destinasjon = tur.getString(5);
                                    String stopp = tur.getString(6);
                                    int ledig = tur.getInt(7);
                                    String merke = tur.getString(8);
                                    String modell = tur.getString(9);
                                    String årsmodell = tur.getString(10);
                                    int sykkelfeste = tur.getInt(11);
                                    int takboks = tur.getInt(12);
                                    String sjåførNavn = tur.getString(13);
                                    String sjåførTlf = tur.getString(14);
                                    String sjåførEpost = tur.getString(15);


                                    if(tur.getInt(7)>0) {

                                        Kjøretur nyTur = new Kjøretur( kjøreturID,dato, startTid, sluttTid, start,
                                                destinasjon, stopp, ledig, merke,modell,årsmodell,
                                                sykkelfeste,takboks,sjåførNavn,sjåførTlf,sjåførEpost);
                                        kjøreturData.add(nyTur);
                                    }


                                }
                                kjøreturAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast = Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            kø.add(finnTur);
        }
    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
