package quwen.lecai.com.quwen2.MainActivity.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import quwen.lecai.com.quwen2.R;

@SuppressLint("ValidFragment")
public class MineFragment extends Fragment {
    private Context context;
    private Button login;
    private LinearLayout fgm_Mine_LL_user;

    @SuppressLint({"ValidFragment", "CommitPrefEdits"})
    public MineFragment(Context context) {
       this.context = context;
    }

    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(Context context) {
        MineFragment fragment = new MineFragment(context);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        setEvent();
        setLoginDiaLog();
        initLoginBtn();
        return view;
    }


    private void setLoginDiaLog(){
    }

    private void setEvent(){
    }

    @SuppressLint("HandlerLeak")
    private void initView(View view){
        login = view.findViewById(R.id.fgm_Mine_btn_login);
        fgm_Mine_LL_user = view.findViewById(R.id.fgm_Mine_LL_user);
    }

    private void initLoginBtn(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setVisibility(View.INVISIBLE);
                fgm_Mine_LL_user.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
