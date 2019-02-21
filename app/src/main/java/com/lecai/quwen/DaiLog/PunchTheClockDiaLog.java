package com.lecai.quwen.DaiLog;

import android.content.Context;
import android.os.Bundle;

import com.lecai.quwen.R;

public class PunchTheClockDiaLog extends BaseDiaLog{
    public PunchTheClockDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_punch_the_clock);
    }
}
