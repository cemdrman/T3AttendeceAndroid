package jekirdek.com.t3mobil.activityes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jekirdek.com.t3mobil.R;
import jekirdek.com.t3mobil.database.BilgilerDB;
import jekirdek.com.t3mobil.model.User;
import jekirdek.com.t3mobil.utility.JsonParse;
import jekirdek.com.t3mobil.utility.RequestURL;
import jekirdek.com.t3mobil.utility.Security;

import static android.content.ContentValues.TAG;

public class LoginActivity extends Activity {

    private JsonParse jsonParse = new JsonParse();
    private EditText txtEmail;
    private EditText txtSifre;
    private Button btnGiris;
    private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String sifre = txtSifre.getText().toString();
                if (!validate(email)) {
                    Toast.makeText(getApplicationContext(), "Geçersiz Email!", Toast.LENGTH_LONG).show();
                }else if(email.equals("") || sifre.equals("")){
                    Toast.makeText(getApplicationContext(), "Lütfen Bilgilerizi Tam Giriniz!", Toast.LENGTH_LONG).show();
                }else{
                    login();
                }

            }
        });

    }

    private void login(){
        String loginRequestUrl = RequestURL.baseUrl.concat(RequestURL.loginUrl).concat("&email="+txtEmail.getText()+"&password="+txtSifre.getText()).concat(Security.getUrl());
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);

                if (response.equals("[]")) {
                    Toast.makeText(getApplicationContext(),"Yanlış Email veya Şifre!",Toast.LENGTH_LONG).show();
                }else{
                    User user = jsonParse.userJsonParser(response);
                    BilgilerDB bilgilerDB = new BilgilerDB(getApplicationContext());
                    bilgilerDB.saveUser(user);
                    Intent ıntent = new Intent(LoginActivity.this,MainActivity.class );
                    startActivity(ıntent);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"onError " + error);
                Toast.makeText(getApplicationContext(),"Giriş Yapılamadı!\n Bağlantı ve Bilgilerinizi Kontrol Ediniz",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void init(){
        txtEmail = (EditText) findViewById(R.id.email);
        txtSifre = (EditText) findViewById(R.id.password);
        btnGiris = (Button)findViewById(R.id.btnGiris);
        //tokenDB = new TokenDB(getApplicationContext());
    }

    private boolean validate(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
