package com.example.jsandroid;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yakun on 2016/7/15.
 */
public class JsFragment extends Fragment {


    TextView mTextview;
    Button mBtn1;
    WebView mWebView;
    Context mContext;

    public JsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.js2android, null);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        //设置编码
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //设置背景颜色 透明
        mWebView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        //设置本地调用对象及其接口
        mWebView.addJavascriptInterface(new JavaScriptObject(mContext), "myObj");
        //载入js
        mWebView.loadUrl("file:///android_asset/test.html");

        //点击调用js中方法
        mBtn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:funFromjs()");
                Toast.makeText(mContext, "调用javascript:funFromjs()", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initViews(View view) {

        mBtn1 = (Button)view.findViewById(R.id.btn_1);

        mTextview = (TextView)view.findViewById(R.id.tv_view);
        mWebView = (WebView)view.findViewById(R.id.wv_view);

        mContext = getActivity();
    }

    public class JavaScriptObject {
        Context mContxt;

        public JavaScriptObject(Context mContxt) {
            this.mContxt = mContxt;
        }
        @JavascriptInterface
        public void fun1FromAndroid(String name) {
            Toast.makeText(mContxt, name, Toast.LENGTH_LONG).show();
        }
        @JavascriptInterface
        public void fun2(String name) {
            Toast.makeText(mContxt, "调用fun2:" + name, Toast.LENGTH_SHORT).show();
        }
    }

}
