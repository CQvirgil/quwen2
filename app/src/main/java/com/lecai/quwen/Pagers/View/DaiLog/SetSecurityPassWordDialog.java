package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.widget.Toast;

import com.lecai.quwen.MyView.PassWordEditorText;
import com.lecai.quwen.R;

public class SetSecurityPassWordDialog extends BaseDiaLog {
    private PassWordEditorText passWordEditorText;
    public SetSecurityPassWordDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_set_security_pass_word);
        passWordEditorText = findViewById(R.id.dialog_set_security_password_editor);
        passWordEditorText.setOnPasswordListener(new PassWordEditorText.PasswordListener() {
            @Override
            public void onFinish(String text) {
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEmpty() {

            }
        });
    }
}
