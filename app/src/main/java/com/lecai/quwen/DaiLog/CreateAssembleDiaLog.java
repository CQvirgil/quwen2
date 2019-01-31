package com.lecai.quwen.DaiLog;

import android.content.Context;
import android.os.Bundle;

import com.lecai.quwen.R;

public class CreateAssembleDiaLog extends BaseDiaLog {
    public CreateAssembleDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_create_assemble);
    }
}
