package com.lecai.quwen.MainActivity.Fragment.Task.Ranking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lecai.quwen.R;

public class RankingActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        TextView title = findViewById(R.id.title_bar_title_text_view);
        title.setText("朋友圈排名");

        listView = findViewById(R.id.act_ranking_list_view);
        RankingAdapter adapter = new RankingAdapter(this);
        listView.setAdapter(adapter);

    }

    public void Back(View view) {
        finish();
    }
}
