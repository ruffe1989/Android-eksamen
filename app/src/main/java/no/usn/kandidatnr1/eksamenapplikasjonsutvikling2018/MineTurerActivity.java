package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/**I denne klassen er det mye uferdige ting men jeg har gjort
 * et forsøk på å få frem hva jeg ønsker å gjøre. klassen er
 * veldig lik FinnReiseAcvtivity. Dette fordi jeg ønsket
 * å vise et recycleview på samme måte.*/

public class MineTurerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Kjøretur> reiseData;
    private ReiseAdapter reiseAdapter;
    private RequestQueue kø;
    private SharedPreferences mPreferences;
    private String brukerID, passasjeren, bestillingen;
    private int passasjerNr,bestillingNr;
    private Button finnReiserBtn;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_turer);
        recyclerView = findViewById(R.id.mineReiserListe);
        finnReiserBtn = findViewById(R.id.reiserBtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));
        reiseData = new ArrayList<>();


        brukerID = SessionManager.KEY_ID;
        finnReiserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utførSøk(brukerID);
            }
        });

        reiseAdapter = new ReiseAdapter(this,reiseData);
        recyclerView.setAdapter(reiseAdapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        int dragdir = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN |ItemTouchHelper.UP;
        int movedir = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        ItemTouchHelper fjernReise = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(dragdir,movedir) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MineTurerActivity.this);
                builder.setMessage("Ønsker du å slette turen?");
                builder.setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        builder.setCancelable(true);
                        reiseAdapter.notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int kjøretur = reiseData.get(viewHolder.getAdapterPosition()).getKjøreturID();
                        reiseData.remove(viewHolder.getAdapterPosition());

                        sendSms(kjøretur);

                        /**Denne delen har jeg kommentert ut for jeg skulle hatt en sjekk
                         * om bruker er sjåfør til akkurat den reisen*/
                        /*String deleteRequestURL = getResources().getString(R.string.ENDPOINT)+"/kjoretur/"+kjøretur;
                        if(isOnline()){
                            kø = Volley.newRequestQueue(getApplicationContext());

                            StringRequest stringRequest = new StringRequest(Request.Method.DELETE, deleteRequestURL, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.i("LoggVolley", response);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("LoggVolleyError",error.toString());
                                }
                            });
                            kø.add(stringRequest);
                        }*/

                        Toast toast = Toast.makeText(getApplicationContext(),"Delete funker kanskje",Toast.LENGTH_LONG);
                        toast.show();
                    }
                });

                builder.create();
                builder.show();

                /**/
            }
        });
        fjernReise.attachToRecyclerView(recyclerView);


    }

    private void sendSms(int tlfnr) {
        /** Her skulle jeg først funnet passasjerene og hentet nr men
         * som sagt har jeg hatt problemer med dette. Skriver likevel inn koden
         * som skal gjennomføres etter å hentet nr. jeg bruker integeren som er sendt
         * med metoden for å slippe feil i koden og la programmet kjøre likevell*/
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + tlfnr));
        intent.putExtra("sms_body","Turen er avlyst");
        startActivity(intent);


    }

    private void utførSøk(String søkeord) {
        String url = getResources().getString(R.string.ENDPOINT) +"passasjer?filter=brukerID,eq,"
                + søkeord;

        if (isOnline()){
            kø = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest finnPassasjer = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject tabell = response.getJSONObject("passasjer");
                                JSONArray infoArray = tabell.getJSONArray("records");
                                for (int i = 0; i < infoArray.length(); i++) {
                                    JSONArray passasjer = infoArray.getJSONArray(i);
                                    passasjerNr = passasjer.getInt(0);
                                }
                                passasjeren = String.valueOf(passasjerNr);
                                Toast toast = Toast.makeText(getApplicationContext(),passasjeren,Toast.LENGTH_LONG);
                                toast.show();
                                bestillingSøk(getResources().getString(R.string.ENDPOINT)+
                                        "bestilling?filter=passasjerID,eq,"+passasjeren);


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
            kø.add(finnPassasjer);
        }
    }

    private void bestillingSøk(String s) {
        String url = s;

        if (isOnline()){
            kø = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest finnBestilling = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject tabell = response.getJSONObject("bestilling");
                                JSONArray infoArray = tabell.getJSONArray("records");
                                for (int i = 0; i < infoArray.length(); i++) {
                                    JSONArray passasjer = infoArray.getJSONArray(i);
                                    bestillingNr = passasjer.getInt(0);

                                }
                                bestillingen = String.valueOf(bestillingNr);
                                kjøreturSøk(getResources().getString(R.string.ENDPOINT)+
                                        "kjoretur?filter=kjoreturID,eq,"+bestillingen);


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
            kø.add(finnBestilling);
        }

    }

    private void kjøreturSøk(String s) {
        String url = s;
        if (isOnline()){
            kø = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest finnTur = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject tabell = response.getJSONObject("kjoretur");
                                JSONArray infoArray = tabell.getJSONArray("records");

                                reiseData.clear();

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

                                        Kjøretur nyTur = new Kjøretur(kjøreturID,dato, startTid, sluttTid, start,
                                                destinasjon, stopp, ledig, merke,modell,årsmodell,
                                                sykkelfeste,takboks,sjåførNavn,sjåførTlf,sjåførEpost);
                                        reiseData.add(nyTur);
                                    }


                                }
                                reiseAdapter.notifyDataSetChanged();
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
