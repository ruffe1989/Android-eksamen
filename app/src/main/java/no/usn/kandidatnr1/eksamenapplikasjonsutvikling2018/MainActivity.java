package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView hovedBilde;
    private Button finnReise, mineturer, bliSjåfør, regTur;
    private SessionManager session;
    private SharedPreferences mPreferences;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(this);
        context = getApplicationContext();

        hovedBilde = findViewById(R.id.mainBilde);
        finnReise = findViewById(R.id.finnReise);
        mineturer = findViewById(R.id.mineTurer);
        bliSjåfør = findViewById(R.id.regSjåfør);
        regTur = findViewById(R.id.regtur);

        Glide.with(this).load(R.drawable.usn).into(hovedBilde);

        finnReise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.isLoggedIn()){
                    Intent nyreise = new Intent(context,FinnReiseActivity.class);
                    context.startActivity(nyreise);
                }
                else {
                    session.checkAndSendLogin();
                }
            }
        });

        mineturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.isLoggedIn()){
                    Intent nyMineturer = new Intent(context,MineTurerActivity.class);
                    context.startActivity(nyMineturer);
                }
                else {
                    session.checkAndSendLogin();
                }
            }
        });

        bliSjåfør.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.isLoggedIn()){
                    Intent sjåfør = new Intent(context,RegistrerSjaaforActivity.class);
                    context.startActivity(sjåfør);
                }
                else {
                    session.checkAndSendLogin();
                }
            }
        });
        regTur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.isLoggedIn()){
                    Intent nytur = new Intent(context,RegTurActivity.class);
                    context.startActivity(nytur);
                }
                else {
                    session.checkAndSendLogin();
                }
            }
        });



    }
}
