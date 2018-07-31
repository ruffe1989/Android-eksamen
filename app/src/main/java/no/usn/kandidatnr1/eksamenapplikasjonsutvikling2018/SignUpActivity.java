package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * Activity som lager ny bruker*/
public class SignUpActivity extends AppCompatActivity {
    private RequestQueue kø;
    private EditText navn, epost,tlf, passord, gjentaPass;
    private Button lagNyBruker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        navn = findViewById(R.id.navnBruker);
        epost = findViewById(R.id.epostBruker);
        tlf = findViewById(R.id.tlfBruker);
        passord = findViewById(R.id.passordBruker);
        gjentaPass = findViewById(R.id.gjentaPassord);

        lagNyBruker = findViewById(R.id.nyBruker);
        lagNyBruker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNyBruker();
            }
        });

    }

    private void registerNyBruker() {
        if(!gyldigReg()){
            feilRegistrering();
            return;
        }
        JSONObject nyBruker = new JSONObject();
        try {
            nyBruker.put("navn", navn.getText().toString());
            nyBruker.put("tlf", tlf.getText().toString());
            nyBruker.put("epost", epost.getText().toString());
            nyBruker.put("passord", passord.getText().toString());

            sjekkBruker(nyBruker);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sjekkBruker(final JSONObject nyBruker) {
        if (isOnline()){
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String user_URL = getString(R.string.ENDPOINT)+"/bruker?filter=epost,eq," +epost.getText().toString()+"&transform=1";
            JsonObjectRequest JSONRequest = new JsonObjectRequest(Request.Method.GET, user_URL , null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray temp = response.getJSONArray("bruker");

                        if(temp.length() == 0){
                            onSignupSuccess(nyBruker);
                        }
                        else{
                            Toast toast = Toast.makeText(getApplicationContext(),"Bruker finnes",Toast.LENGTH_LONG);
                            toast.show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast toast = Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG);
                        toast.show();
                    }



                }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast toast = Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            queue.add(JSONRequest);
        }
    }

    private void onSignupSuccess(JSONObject nyBruker) {

        kø = newRequestQueue(getApplicationContext());

        String bruker_URL = getString(R.string.ENDPOINT) + "bruker";

        if(isOnline()){
            JsonObjectRequest JSONRequest = new JsonObjectRequest(Request.Method.POST, bruker_URL, nyBruker, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            kø.add(JSONRequest);
        }


        finish();
    }

    private void feilRegistrering() {
        Toast toast = Toast.makeText(getApplicationContext(), R.string.feil_funket_ikke, Toast.LENGTH_SHORT);
        toast.show();
        recreate();
    }

    private boolean gyldigReg() {
        boolean gyldig = true;
        String mNavn = navn.getText().toString();
        String mEpost = epost.getText().toString();
        String mTlf = tlf.getText().toString();
        String mPassord = passord.getText().toString();
        String mRepassord = gjentaPass.getText().toString();

        if(mNavn.isEmpty() || mNavn.length()<3){
            navn.setError(getString(R.string.feil_navn));
            gyldig = false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mEpost).matches()){
            epost.setError(getString(R.string.feil_epost));
            gyldig = false;
        }
        if(mTlf.length()<8){
            passord.setError(getString(R.string.feil_tlf));
            gyldig=false;
        }
        if(mPassord.length()<4){
            passord.setError(getString(R.string.feil_passord_lengde));
            gyldig=false;
        }
        if(!mPassord.equals(mRepassord)){
            gjentaPass.setError(getString(R.string.feil_gjenta));
            gyldig=false;
        }
        return gyldig;
    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
