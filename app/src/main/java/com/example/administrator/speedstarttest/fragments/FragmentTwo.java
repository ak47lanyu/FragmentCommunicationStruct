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
public class FragmentTwo extends BaseFragment {

    public static final String METHOD_NAME_FOR_SUBMIT = FragmentTwo.class.getName() + "submit";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f_two, null);
        v.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String str = FunctionManager.getInstance().invokeMethod(METHOD_NAME_FOR_SUBMIT, String.class);
                    showToast(str, 2);
                } catch (FunctionNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }
}
