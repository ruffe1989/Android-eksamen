package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class RegistrerSjaaforActivity extends AppCompatActivity {

    private ImageView sjåførBilde;
    private String brukerID;
    private RequestQueue kø;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer_sjaafor);
        mContext = getApplicationContext();
        brukerID = SessionManager.KEY_ID;
        sjåførBilde = findViewById(R.id.sjåførBilde);
        Glide.with(this).load(R.drawable.carfancy).into(sjåførBilde);
        sjåførBilde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegistrerSjåfør(brukerID);
                Intent intent = new Intent(mContext,MainActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    private void RegistrerSjåfør(String brukerID) {
        String url = getResources().getString(R.string.ENDPOINT)+
                "kjoretur?filter=kjoreturID,eq,"+brukerID;
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
                    params.put("isDriver","1");
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
