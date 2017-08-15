package com.example.administrator.speedstarttest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.speedstarttest.R;
import com.example.administrator.speedstarttest.base.BaseFragment;
import com.example.administrator.speedstarttest.struct.FunctionManager;
import com.example.administrator.speedstarttest.struct.FunctionNotFoundException;

/**
 * @author Ly
 * @version 1.0
 * email: 596671530@qq.com
 */
public class FragmentThree extends BaseFragment {

    public static final String METHOD_NAME_FOR_SUBMIT = FragmentThree.class.getName() + "submit";
    public static final String METHOD_NAME_FOR_CANCEL = FragmentThree.class.getName() + "cancel";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f_three, null);
        v.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String str = FunctionManager.getInstance().invokeMethod(METHOD_NAME_FOR_SUBMIT, String.class, "有参有返回值方法成功");
                    showToast(str, 2);
                } catch (FunctionNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        v.findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    functionManager.getInstance().invokeMethod(METHOD_NAME_FOR_CANCEL, "有参无返回值方法成功");
                } catch (FunctionNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }
}
