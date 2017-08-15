package com.example.administrator.speedstarttest;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.speedstarttest.base.BaseFragment;
import com.example.administrator.speedstarttest.fragments.FragmentOne;
import com.example.administrator.speedstarttest.fragments.FragmentThree;
import com.example.administrator.speedstarttest.fragments.FragmentTwo;
import com.example.administrator.speedstarttest.struct.FunctionManager;
import com.example.administrator.speedstarttest.struct.MethodForHaveParamHaveRes;
import com.example.administrator.speedstarttest.struct.MethodForHaveParamNotRes;
import com.example.administrator.speedstarttest.struct.MethodForNotParamHaveRes;
import com.example.administrator.speedstarttest.struct.MethodForNotParamNotRes;

public class MainActivity extends AppCompatActivity {

    private FragmentOne fragmentOne = new FragmentOne();
    private FragmentTwo fragmentTwo = new FragmentTwo();
    private FragmentThree fragmentThree = new FragmentThree();
    private FragmentManager fm = getSupportFragmentManager();
    private final String TAG = "I Like You Ly --> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTo(0);
            }
        });
        findViewById(R.id.tv_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTo(1);
            }
        });
        findViewById(R.id.tv_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTo(2);
            }
        });
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.rl_fragment_container, fragmentOne, "oneFragment");
        ft.add(R.id.rl_fragment_container, fragmentTwo, "twoFragment");
        ft.add(R.id.rl_fragment_container, fragmentThree, "threeFragment");
        ft.commit();
        switchTo(0);
    }

    private void switchTo(int page) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(fragmentOne);
        ft.hide(fragmentTwo);
        ft.hide(fragmentThree);
        switch (page) {
            case 0:
                ft.show(fragmentOne);
                break;
            case 1:
                ft.show(fragmentTwo);
                break;
            case 2:
                ft.show(fragmentThree);
                break;
        }
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    /**
     * 每个Fragment回调Activity的方法，用于注册每个Fragment对应的事件集合
     * 不同Fragment的名字不能重名，不能重名，不能重名
     * 因此建议采取Fragment名 + 功能模块名的方式来命名
     * 涉及Fragment通用可以回调同一方法，在参数传递的Bean里用特定字段，区分身份
     * Simple：ViewPager显示多标题内容，衣服，裤子，鞋，Bean类中指定字段prodType
     * 分别代表以上三种内容，容器中做判断，完成业务
     * @param tag
     */
    public void setFunctionsForFragment(String tag) {
        BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(tag);
        FunctionManager manager = FunctionManager.getInstance();
        fragment.setFunctionManager(manager.addMethod(new MethodForNotParamNotRes(FragmentOne.METHOD_NAME_FOR_SUBMIT) {
            @Override
            public void function() {
                Toast.makeText(MainActivity.this, TAG + "无参无返回值方法成功", Toast.LENGTH_SHORT).show();
            }
        }).addMethod(new MethodForNotParamHaveRes<String>(FragmentTwo.METHOD_NAME_FOR_SUBMIT) {
            @Override
            public String function() {
                return "无参有返回值方法成功";
            }
        }).addMethod(new MethodForHaveParamHaveRes<String, String>(FragmentThree.METHOD_NAME_FOR_SUBMIT) {
            @Override
            public String function(String param) {
                return TAG + param;
            }
        }).addMethod(new MethodForHaveParamNotRes<String>(FragmentThree.METHOD_NAME_FOR_CANCEL) {
            @Override
            public void function(String param) {
                Toast.makeText(MainActivity.this, TAG + param, Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
