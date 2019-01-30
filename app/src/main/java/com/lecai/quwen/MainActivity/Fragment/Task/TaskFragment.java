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

import com.lecai.quwen.MainActivity.MainActivity;
import com.lecai.quwen.R;



public class TaskFragment extends Fragment implements View.OnClickListener {
    ImageView hit1,hit2,hit3,hit4;
    Button btn_1,btn_2,btn_3,btn_4;



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
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        hit2.setOnClickListener(this);
        hit3.setOnClickListener(this);
        hit4.setOnClickListener(this);
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
                MainActivity.handler.sendEmptyMessage(2100);
                break;
            case R.id.fgm_task_btn2:
                intent.setAction("startPunchTheClockActivity");
                startActivity(intent);
                break;
            case R.id.fgm_task_btn3:
                intent.setAction("startAssembleActivity");
                startActivity(intent);
                break;
            case R.id.fgm_task_btn4:
                intent.setAction("startMentorActivity");
                startActivity(intent);
                break;
                default:
                    intent.setAction("startHelpActivity");
                    startActivity(intent);
                    break;
        }
    }
}
