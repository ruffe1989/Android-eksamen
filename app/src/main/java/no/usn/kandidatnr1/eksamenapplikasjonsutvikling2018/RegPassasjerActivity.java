package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class RegPassasjerActivity extends AppCompatActivity {
    private RequestQueue kø;
    private Context mContext;
    private String brukerId, kjøreturID, ledigAntall,kjentStopp,ankomstTid;
    private ImageView regBilde;
    private EditText stopp,tid;
    private Button regBtn;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_passasjer);


        brukerId = SessionManager.KEY_ID;

        regBilde = findViewById(R.id.regBilde);
        Glide.with(this).load(R.drawable.car).into(regBilde);
        stopp = findViewById(R.id.valgtStopp);
        tid = findViewById(R.id.valgtTid);
        regBtn = findViewById(R.id.regStart);
        tid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar tidspunkt = Calendar.getInstance();
                int time = tidspunkt.get(Calendar.HOUR_OF_DAY);
                int minutt = tidspunkt.get(Calendar.MINUTE);
                TimePickerDialog tpd = new TimePickerDialog(
                        mContext,
                        android.R.style.Theme_Material_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tid.setText(hourOfDay + ":" + minute);
                            }
                        },time,minutt,true);
                tpd.setTitle("Velg tidspunkt");
                tpd.show();
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndreAntall(kjøreturID,ledigAntall);
                RegistrerPassasjer(brukerId, kjentStopp,ankomstTid,kjøreturID);
            }
        });


    }
    /**Passasjer blir regisrert ved bruker av StringRequest*/
    private void RegistrerPassasjer(final String bruker, final String stopp, final String tid, final String kjøretøyNr) {
        String url = getResources().getString(R.string.ENDPOINT)+
                "kjoretur?filter=kjoreturID,eq,"+kjøreturID;
        kø = newRequestQueue(getApplicationContext());

        if (isOnline()){
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("brukerID",bruker);
                    params.put("pickUpPoint",stopp);
                    params.put("sisteFrist",tid);
                    params.put("kjoreturID",kjøretøyNr);
                    return params;

                }
            };
            kø.add(stringRequest);
        }

    }

    /**Antall ledig plasser på reisen blir endret*/
    private void EndreAntall(String kjøreturID, final String ledigplass) {
        String url = getResources().getString(R.string.ENDPOINT)+
                "kjoretur?filter=kjoreturID,eq,"+kjøreturID;
        kø = newRequestQueue(getApplicationContext());

        if (isOnline()){
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("ledigplass",ledigplass);
                    return params;

                }
            };
            kø.add(stringRequest);
        }


    }


    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
