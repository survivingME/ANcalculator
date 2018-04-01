package cn.trembleguy.android.calculator.model;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

/**
 * 计算器后台处理类
 * Created by HYL on 2016/9/8.
 */
public class Calculator extends WebView{

    public Calculator(Context context) {
        super(context);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
    }
}