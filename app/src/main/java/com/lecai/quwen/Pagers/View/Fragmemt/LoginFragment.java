package com.lecai.quwen.Pagers.View.Fragmemt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lecai.quwen.R;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private static LoginFragment fragment;
    private Button btn_login_ponenum;
    public LoginFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        synchronized (LoginFragment.class){
            if(fragment == null){
                fragment  = new LoginFragment();
            }
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        btn_login_ponenum = view.findViewById(R.id.fgm_login_pone_num_btn);
        btn_login_ponenum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fgm_login_pone_num_btn:
                starActLogin();
                break;
        }
    }

    private void starActLogin(){
        Intent intent = new Intent();
        intent.setAction("startLogInActivity");
        startActivity(intent);
    }
}
