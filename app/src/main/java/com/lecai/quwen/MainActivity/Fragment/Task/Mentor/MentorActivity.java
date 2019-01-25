package com.lecai.quwen.MainActivity.Fragment.Task.Mentor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.lecai.quwen.MyView.CircleImage;
import com.lecai.quwen.R;

public class MentorActivity extends AppCompatActivity {
    private ListView listView;
    private CircleImage headimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);
        listView = findViewById(R.id.act_mentor_list_view);
        MentorAdapter adapter = new MentorAdapter(this);
        listView.setAdapter(adapter);

    }

    public void Back(View view) {
        finish();
    }
}
