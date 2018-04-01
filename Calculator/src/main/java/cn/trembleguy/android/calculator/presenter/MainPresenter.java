package cn.trembleguy.android.calculator.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import cn.trembleguy.android.calculator.model.Calculator;
import cn.trembleguy.android.calculator.model.Result;
import cn.trembleguy.android.calculator.viewinterface.MainView;

/**
 * 计算器界面presenter。
 * 展示了多种方法计算表达式结果。
 * Created by trembleguy on 2018/3/31.
 */
public class MainPresenter {
    public static final String TAG = "MainPresenter";

    private MainView mainView;
    private Handler handler;
    private Calculator calculator;
    private Result ret;

    @SuppressLint("SetJavaScriptEnabled")
    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        handler = new Handler();

        //计算组件初始化，借助js来计算表达式
        calculator = new Calculator((Context) mainView);
        //增加js接口方便调用js计算
        calculator.addJavascriptInterface(new JSInterface(), "calculator");
    }

    /**
     * 计算方法，接收表达式并计算
     * @param expression 表达式
     */
    public void calcu(String expression) {
        Log.d(TAG, "show: expression=" + expression);
        //如果不合法则显示错误
        if (expression.matches("[^a-zA-Z]*?[a-zA-z]+[^a-zA-Z]*?")) {
            mainView.update("error");
            return;
        }
        //否则调用js来计算表达式，并通过js回调Java的方法来传递结果
        calculator.loadUrl("javascript:calculator.show(eval('" + expression + "'))");
    }

    /**
     * Js接口
     */
    class JSInterface {
        /**
         * js计算结果显示接口
         * @param result
         */
        @JavascriptInterface
        public void show(String result) {
            Log.d(TAG, "result: " + result);
            //把结果更新
            handler.post(new resultThread(result));
        }
    }

    /**
     * 结果更新线程
     */
    class resultThread implements Runnable {
        String data;

        public resultThread(String data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                //如果结果有问题，调用view层显示错误。
                Double.parseDouble(data);
            } catch (Exception e) {
                mainView.update("error");
                return;
            }
            //否则更新结果
            mainView.update(data);
        }
    }
}