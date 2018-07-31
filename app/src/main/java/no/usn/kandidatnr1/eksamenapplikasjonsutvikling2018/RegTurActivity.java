package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class RegTurActivity extends AppCompatActivity {

    private EditText dato, avreise, ankomst,startSted,destinasjon,
            kjenteStopp,antallLedig;
    private Button registrerBtn;
    private RequestQueue kø;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_tur);

        ImageView turBilde = findViewById(R.id.reisebilde);
        Glide.with(this).load(R.drawable.carpooling).into(turBilde);

        dato = findViewById(R.id.nyDato);
        dato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar dagensDato = Calendar.getInstance();
                int år = dagensDato.get(Calendar.YEAR);
                int måned = dagensDato.get(Calendar.MONTH);
                int dag = dagensDato.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        RegTurActivity.this,
                        android.R.style.Theme_Material_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month+1;
                                String valgtDato = year + "-" + month + "-" + dayOfMonth;
                                dato.setText(valgtDato);
                            }
                        },år,måned,dag);
                dialog.show();
            }
        });



        avreise = findViewById(R.id.avreiseTekst);
        avreise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar tidspunkt = Calendar.getInstance();
                int time = tidspunkt.get(Calendar.HOUR_OF_DAY);
                int minutt = tidspunkt.get(Calendar.MINUTE);
                TimePickerDialog tpd = new TimePickerDialog(
                        RegTurActivity.this,
                        android.R.style.Theme_Material_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                avreise.setText(hourOfDay + ":" + minute);
                            }
                        },time,minutt,true);
                tpd.setTitle("Velg tidspunkt");
                tpd.show();

            }
        });

        ankomst = findViewById(R.id.ankomstTekst);
        ankomst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar tidspunkt = Calendar.getInstance();
                int time = tidspunkt.get(Calendar.HOUR_OF_DAY);
                int minutt = tidspunkt.get(Calendar.MINUTE);
                TimePickerDialog tpd = new TimePickerDialog(
                        RegTurActivity.this,
                        android.R.style.Theme_Material_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                ankomst.setText(hourOfDay + ":" + minute);
                            }
                        },time,minutt,true);
                tpd.setTitle("Velg tidspunkt");
                tpd.show();

            }
        });

        startSted = findViewById(R.id.startTekst);

        destinasjon = findViewById(R.id.sluttTekst);

        kjenteStopp = findViewById(R.id.stoppTekst);

        antallLedig = findViewById(R.id.ledigTekst);

        registrerBtn =findViewById(R.id.registrerTur);
        registrerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });



    }

    private void sendData() {
        kø = newRequestQueue(getApplicationContext());


        String registrerNyTur = getResources().getString(R.string.ENDPOINT) + "kjoretur";
        if (isOnline()){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, registrerNyTur, new Response.Listener<String>() {
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
                    params.put("dato",dato.getText().toString());
                    params.put("startTid",avreise.getText().toString());
                    params.put("sluttTid",ankomst.getText().toString());
                    params.put("startSted",startSted.getText().toString());
                    params.put("sluttSted",destinasjon.getText().toString());
                    params.put("kjentStopp",kjenteStopp.getText().toString());
                    params.put("ledigplass",antallLedig.getText().toString());
                    params.put("kjoretoyID","2");
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
