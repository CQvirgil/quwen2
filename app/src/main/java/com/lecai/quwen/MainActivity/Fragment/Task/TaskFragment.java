package com.lecai.quwen.MainActivity.Fragment.Task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lecai.quwen.DaiLog.GotoReadDiaLog;
import com.lecai.quwen.MainActivity.Fragment.Task.Assemble.AssembleActivity;
import com.lecai.quwen.MainActivity.Fragment.Task.Mentor.MentorActivity;
import com.lecai.quwen.MainActivity.MainActivity;
import com.lecai.quwen.R;



public class TaskFragment extends Fragment implements View.OnClickListener {
    private ImageView hit1,hit2,hit3,hit4;
    private Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7;
    private TextView text_Ranking;


    private static volatile TaskFragment instance;


    public TaskFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static TaskFragment newInstance() {
        if(instance == null){
            instance = new TaskFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        hit2 = view.findViewById(R.id.hint2);
        hit3 = view.findViewById(R.id.hint3);
        hit4 = view.findViewById(R.id.hint4);
        btn_1 = view.findViewById(R.id.fgm_task_btn1);
        btn_2 = view.findViewById(R.id.fgm_task_btn2);
        btn_3 = view.findViewById(R.id.fgm_task_btn3);
        btn_4 = view.findViewById(R.id.fgm_task_btn4);
        btn_5 = view.findViewById(R.id.fgm_task_btn5);
        btn_6 = view.findViewById(R.id.fgm_task_btn6);
        btn_7 = view.findViewById(R.id.fgm_task_btn7);
        text_Ranking = view.findViewById(R.id.fgm_task_text_Ranking);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        hit2.setOnClickListener(this);
        hit3.setOnClickListener(this);
        hit4.setOnClickListener(this);
        text_Ranking.setOnClickListener(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.fgm_task_btn1:
                GotoReadDiaLog diaLog = new GotoReadDiaLog(getContext());
                diaLog.show();
                break;
            case R.id.fgm_task_btn2:
                intent.setAction("startPunchTheClockActivity");
                startActivity(intent);
                break;
            case R.id.fgm_task_btn3:
                intent.setClass(getContext(),AssembleActivity.class);
                intent.putExtra("show_dialog",0);
                startActivity(intent);
                break;
            case R.id.fgm_task_btn4:
                intent.setClass(getContext(),MentorActivity.class);
                startActivity(intent);
                break;
            case R.id.fgm_task_btn5:
                intent.putExtra("show_dialog",1);
                intent.setClass(getContext(),AssembleActivity.class);
                startActivity(intent);
                break;
            case R.id.fgm_task_btn6:
                intent.putExtra("show_dialog",2);
                intent.setClass(getContext(),AssembleActivity.class);
                startActivity(intent);
                break;
            case R.id.fgm_task_btn7:
                intent.setAction("startBindingMasterActivity");
                startActivity(intent);
                break;
            case R.id.fgm_task_text_Ranking:
                intent.setClass(getContext(),MentorActivity.class);
                startActivity(intent);
                break;
                default:
                    intent.setAction("startHelpActivity");
                    startActivity(intent);
                    break;
        }
    }
}
