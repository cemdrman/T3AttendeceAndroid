package jekirdek.com.t3mobil.activityes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.HashMap;

import jekirdek.com.t3mobil.R;
import jekirdek.com.t3mobil.adapter.CustomAdapter;
import jekirdek.com.t3mobil.model.AttendeceListModel;
import jekirdek.com.t3mobil.model.Attendence;
import jekirdek.com.t3mobil.utility.JsonParse;
import jekirdek.com.t3mobil.utility.RequestURL;
import jekirdek.com.t3mobil.utility.Security;

import static android.content.ContentValues.TAG;

/**
 * Created by cem
 */
public class YoklamaGetirActivity extends Activity {

    private ListView tumOgrenciListView;
    private Button btnKaydet;
    private Attendence[] attendences;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoklama_listesi_getir);
        init();
        int deneyap = getIntent().getExtras().getInt("deneyap");
        int ders = getIntent().getExtras().getInt("ders");
        String tarih = getIntent().getExtras().getString("tarih");
        getTumSinifListe(deneyap,ders,tarih);

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * NameSurname ve Id yi map de tutuyoruz ki listviewdan aldığımız name-surname
                 * göre id alabilelim ve sonuç olarak request atabilelim
                 */

                HashMap<String,String> nameIdMap = new HashMap<>();
                for (int i = 0; i < attendences.length; i++) {
                    nameIdMap.put(attendences[i].getStudentNameSurname(),attendences[i].getId());
                }

                for (int i = 0; i < customAdapter.getCount(); i++) {
                    System.out.println(customAdapter.getItem(i).getName() + " " + customAdapter.getItem(i).isChecked());
                }

                for (int i = 0; i < customAdapter.getCount(); i++) {
                    String studentID = nameIdMap.get(customAdapter.getItem(i).getName());
                    if (customAdapter.getItem(i).isChecked()) {
                        yoklamaAl(Integer.valueOf(studentID), 1);
                    }else{
                        yoklamaAl(Integer.valueOf(studentID), 0);
                    }
                }

                Toast.makeText(getApplicationContext(),"Yoklama Kaydedildi",Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    private void init(){
        tumOgrenciListView = (ListView) findViewById(R.id.list);
        btnKaydet = (Button) findViewById(R.id.btnYoklamaKaydet);
    }

    private void yoklamaAl(int studentId,int presence){
        String yoklamaGuncellemeUrl = RequestURL.getYoklamaGuncellemeUrl(studentId,presence).concat(Security.getUrl());
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, yoklamaGuncellemeUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"onError: " + error);
            }
        });
        requestQueue.add(request);
    }

    private void getTumSinifListe(int deneyap,int ders,String tarih){

        String tumOgrenciListesiUrl = RequestURL.getTumOgrenciListesiUrl(deneyap, ders, tarih).concat(Security.getUrl());
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication().getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, tumOgrenciListesiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"onResponse: " + response);

                if (response.equals("[]")) {
                    Toast.makeText(getApplicationContext(),"Verilen bilgilere göre yoklama listesi bulunamadı!",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    JsonParse jsonParse = new JsonParse();
                    attendences = jsonParse.getAttendenceList(response);
                    if (jsonParse.getAttendenceList(response).length > 0) {

                        /**
                         * dizinin iki boyutlu olmasının nedeni hem öğrenci bilgisi hemde devamsızlık durumunun tutulması için.
                         * liste tekrar açıldığında önceden yoklama alındıysa tekrar gelmesi sağlanır
                         */
                        ArrayList<AttendeceListModel> ogrenciler = new ArrayList<>();
                        for (int i = 0; i < attendences.length; i++) {
                            /**
                             * getPresence() devamsızlık durumunu verir
                             * 0 -> gelmediğini
                             * 1 -> geldiğini belirtir
                             */
                            AttendeceListModel attendeceListModel ;
                            if (attendences[i].getPresence().equals("0")) {
                                attendeceListModel = new AttendeceListModel(attendences[i].getStudentNameSurname(),false);
                            }else{
                                attendeceListModel = new AttendeceListModel(attendences[i].getStudentNameSurname(),true);
                            }

                            ogrenciler.add(attendeceListModel);
                        }

                        customAdapter = new CustomAdapter( getApplicationContext(), ogrenciler);
                        tumOgrenciListView.setAdapter(customAdapter);
                    }else{
                        Toast.makeText(getApplicationContext(),"Öğrenci Liste Yok",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"onError: " + error);
                Toast.makeText(getApplicationContext(),"Listeye Ulaşılamadı!",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);

    }

}
