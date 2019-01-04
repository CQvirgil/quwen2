package com.lecai.quwen.MainActivity.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lecai.quwen.R;

import java.lang.reflect.Method;

public class MallFragment extends Fragment {
    private WebView webView;

    public MallFragment() {
        // Required empty public constructor
    }

    public static MallFragment newInstance() {
        MallFragment fragment = new MallFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mall, container, false);
        initView(view);
        setWebView();
        return view;
    }

    private void initView(View view){
        webView = view.findViewById(R.id.fgm_Mall_WebView);
    }

    private void setWebView(){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
//需要升级complieversion为21以上，不升级的话，用反射的方式来实现，如下代码所示
        try {
            Method m = WebSettings.class.getMethod("setMixedContentMode", int.class);
            if ( m == null ) {
                Log.e("WebSettings", "Error getting setMixedContentMode method");
            }
            else {
                m.invoke(webView.getSettings(), 2); // 2 = MIXED_CONTENT_COMPATIBILITY_MODE
                Log.i("WebSettings", "Successfully set MIXED_CONTENT_COMPATIBILITY_MODE");
            }
        }
        catch (Exception ex) {
            Log.e("WebSettings", "Error calling setMixedContentMode: " + ex.getMessage(), ex);
        }
        webView.loadUrl("http://quwen.lecaigogo.com/pdd/pdd.html");
        webView.setWebViewClient(new WebViewClient(){

        });
    }
}
