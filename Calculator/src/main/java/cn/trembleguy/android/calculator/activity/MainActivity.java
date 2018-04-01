
package cn.trembleguy.android.calculator.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.trembleguy.android.calculator.R;
import cn.trembleguy.android.calculator.presenter.MainPresenter;
import cn.trembleguy.android.calculator.viewinterface.MainView;

/**
 * 计算器主窗口
 * Created by trembleguy on 2017/3/31.
 */

public class MainActivity extends Activity implements MainView {
    public static final String TAG = "calculator_Main";


    //所有视图控件
    private Button btn_toConvertion;

    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_dot;
    private Button btn_enter;
    private Button btn_add;
    private Button btn_sub;
    private Button btn_mul;
    private Button btn_div;
    private Button btn_del;

    private TextView tv_result;

    boolean isDotPressed = false;
    boolean isOPPressed = false;

    //控件数据
    private List<Button> buttons;

    //presenter
    private MainPresenter presenter;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter = new MainPresenter(this);
    }


    /**
     * 初始化所有控件
     */
    @Override
    public void initViews() {
        buttons = new ArrayList<>();
        btn_toConvertion = (Button) findViewById(R.id.btn_calculator_to_unit_convert);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_enter = (Button) findViewById(R.id.btn_enter);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_del = (Button) findViewById(R.id.btn_del);
        buttons.add(btn_toConvertion);
        buttons.add(btn_0);
        buttons.add(btn_1);
        buttons.add(btn_2);
        buttons.add(btn_3);
        buttons.add(btn_4);
        buttons.add(btn_5);
        buttons.add(btn_6);
        buttons.add(btn_7);
        buttons.add(btn_8);
        buttons.add(btn_9);
        buttons.add(btn_dot);
        buttons.add(btn_enter);
        buttons.add(btn_add);
        buttons.add(btn_sub);
        buttons.add(btn_mul);
        buttons.add(btn_div);
        buttons.add(btn_del);
        for (Button b : buttons) {
            b.setOnClickListener(new ButtonClickListener());
        }

        tv_result = (TextView) findViewById(R.id.tv_calcu_result);

    }

    /**
     * 更新界面显示，增加一个运算符
     * @param op 要增加的运算符
     */
    @Override
    public void update(char op) {
        tv_result.setText(tv_result.getText().toString() + op);
    }

    /**
     * 更新界面显示，增加一位数字
     * @param digit 要增加的数字
     */
    @Override
    public void update(int digit) {
        tv_result.setText(tv_result.getText().toString() + digit);
    }

    /**
     * 更新界面显示，直接更新结果
     * @param result
     */
    @Override
    public void update(String result) {
        tv_result.setText(result);
    }

    /**
     * 清空结果文本框
     */
    @Override
    public void clear() {
        tv_result.setText("");
    }

    /**
     * 让运算符输入有效或无效
     * @param isEnable 是否有效
     */
    @Override
    public void setOPInputEnable(boolean isEnable) {
        btn_add.setEnabled(isEnable);
        btn_sub.setEnabled(isEnable);
        btn_mul.setEnabled(isEnable);
        btn_div.setEnabled(isEnable);
        this.isOPPressed = !isEnable;
    }

    /**
     * 让小数点输入是否有效
     * @param isEnable 有效或无效
     */
    @Override
    public void setDotInputEnable(boolean isEnable) {
        btn_dot.setEnabled(isEnable);
        this.isDotPressed = !isEnable;
    }

    /**
     * 界面所有按钮的监听器
     */
    public class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_0:
                    update(0);
                    break;
                case R.id.btn_1:
                    update(1);
                    break;
                case R.id.btn_2:
                    update(2);
                    break;
                case R.id.btn_3:
                    update(3);
                    break;
                case R.id.btn_4:
                    update(4);
                    break;
                case R.id.btn_5:
                    update(5);
                    break;
                case R.id.btn_6:
                    update(6);
                    break;
                case R.id.btn_7:
                    update(7);
                    break;
                case R.id.btn_8:
                    update(8);
                    break;
                case R.id.btn_9:
                    update(9);
                    break;
                case R.id.btn_dot:
                    update('.');
                    break;
                case R.id.btn_enter:
                    //事件处理向后层转移
                    presenter.calcu(tv_result.getText() + "");
                    break;
                case R.id.btn_add:
                    update('+');
                    break;
                case R.id.btn_sub:
                    update('-');
                    break;
                case R.id.btn_mul:
                    update('*');
                    break;
                case R.id.btn_div:
                    update('/');
                    break;
                case R.id.btn_del:
                    clear();
                    break;
                case R.id.btn_calculator_to_unit_convert:
                    //跳转到单位转换界面
                    Intent intent = new Intent(MainActivity.this,UnitConversionActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

}
