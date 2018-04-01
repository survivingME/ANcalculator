package cn.trembleguy.android.calculator.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import cn.trembleguy.android.calculator.R;
import cn.trembleguy.android.calculator.presenter.UnitConvertPresenter;
import cn.trembleguy.android.calculator.viewinterface.UnitConversionView;

/**
 * 单位转换界面
 */
public class UnitConversionActivity extends AppCompatActivity implements UnitConversionView {
    //控件数据
    private Button btn_toCalculator;
    private Button btn_unit_conversion_swap;
    private Button btn_unit_conversion_convert;
    private TextView text_unit_conversion_value1;
    private TextView text_unit_conversion_value2;
    private Spinner spinner_unit_conversion_unit1;
    private Spinner spinner_unit_conversion_unit2;

    //presenter
    private UnitConvertPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion);
        initViews();
        presenter = new UnitConvertPresenter(this);
    }

    /**
     * 初始化所有控件
     */
    @Override
    public void initViews() {
        spinner_unit_conversion_unit1 = (Spinner) findViewById(R.id.spinner_unit_conversion_unit1);
        spinner_unit_conversion_unit2 = (Spinner) findViewById(R.id.spinner_unit_conversion_unit2);

        //交互优化

        text_unit_conversion_value1 = (TextView) findViewById(R.id.text_unit_conversion_value1);
        text_unit_conversion_value1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_unit_conversion_value1.setText("");
            }
        });

        text_unit_conversion_value2 = (TextView) findViewById(R.id.text_unit_conversion_value2);
        text_unit_conversion_value2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_unit_conversion_value2.setText("");
            }
        });

        //跳转按钮监听器
        btn_toCalculator = (Button) findViewById(R.id.btn_conversion_to_calculator);
        btn_toCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //交换按钮监听器
        btn_unit_conversion_swap = (Button) findViewById(R.id.btn_unit_conversion_swap);
        btn_unit_conversion_swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = text_unit_conversion_value1.getText() + "";
                text_unit_conversion_value1.setText(text_unit_conversion_value2.getText());
                text_unit_conversion_value2.setText(t);
            }
        });

        //单位转换按钮监听器
        btn_unit_conversion_convert = (Button) findViewById(R.id.btn_unit_conversion_convert);
        btn_unit_conversion_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.convert();
            }
        });
    }

    @Override
    public double getValue1() {
        double ret = 0;
        try {
            ret = Double.parseDouble(text_unit_conversion_value1.getText() + "");
        } catch (Exception e) {
        }
        return ret;
    }

    @Override
    public double getValue2() {
        double ret = 0;
        try {
            ret = Double.parseDouble(text_unit_conversion_value2.getText() + "");
        } catch (Exception e) {
        }
        return ret;
    }

    @Override
    public int getUnit1() {

        return unitToInt(spinner_unit_conversion_unit1.getSelectedItem().toString());
    }

    @Override
    public int getUnit2() {
        return unitToInt(spinner_unit_conversion_unit2.getSelectedItem().toString());
    }

    @Override
    public void setValue2(String value2) {
        text_unit_conversion_value2.setText(value2);
    }

    public int unitToInt(String unit) {
        if (unit.equals("mm")) return 0;
        if (unit.equals("cm")) return 1;
        if (unit.equals("dm")) return 2;
        if (unit.equals("m")) return 3;
        if (unit.equals("km")) return 4;
        return -1;
    }
}
