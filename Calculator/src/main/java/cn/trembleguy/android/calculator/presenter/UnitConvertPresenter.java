package cn.trembleguy.android.calculator.presenter;

import android.content.Context;

import cn.trembleguy.android.calculator.viewinterface.UnitConversionView;

/**
 * Created by HYL on 2016/9/10.
 */
public class UnitConvertPresenter {
    private UnitConversionView view;

    private double[][] convert = {
            {1, 0.1, 0.01, 0.001, 0.000001,},
            {10, 1, 0.1, 0.01, 0.00001,},
            {100, 10, 1, 0.1, 0.0001,},
            {1000, 100, 10, 1, 0.001,},
            {1000000, 100000, 10000, 1000, 1,},
    };

    public UnitConvertPresenter(UnitConversionView view) {
        this.view = view;
    }

    public void convert() {
        double value1 = view.getValue1();
        double value2 = value1 * convert[view.getUnit1()][view.getUnit2()];
        view.setValue2(value2 + "");
    }
}
