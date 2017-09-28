package jekirdek.com.t3mobil.activityes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import jekirdek.com.t3mobil.R;
import jekirdek.com.t3mobil.database.BilgilerDB;
import jekirdek.com.t3mobil.model.User;

import static android.content.ContentValues.TAG;

/**
 * Created by cem
 */
public class BilgilerFragment extends Fragment {

    private EditText txtAd;
    private EditText txtSoyad;
    private EditText txtTelNo;
    private EditText txtEmail;
    private EditText txtSifre;
    private BilgilerDB bilgilerDB;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bilgilerim, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        getUserInfo();
    }

    private void init(View view){
        txtAd = (EditText)view.findViewById(R.id.txtAd);
        txtSoyad = (EditText)view.findViewById(R.id.txtSoyad);
        txtTelNo = (EditText)view.findViewById(R.id.txtTelNo);
        txtEmail = (EditText)view.findViewById(R.id.txtEmail);
        txtSifre = (EditText)view.findViewById(R.id.txtSifre);
        bilgilerDB = new BilgilerDB(getContext());
    }

    private void getUserInfo(){
        User user = bilgilerDB.getUser();
        Log.i(TAG, "getUserInfo: " + user.toString());
        txtAd.setText(user.getName());
        txtSoyad.setText(user.getSurname());
        txtEmail.setText(user.getEmail());
        txtSifre.setText("");
        txtTelNo.setText(user.getPhoneNumber());
    }


}
