package com.example.administrator.speedstarttest.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.administrator.speedstarttest.MainActivity;
import com.example.administrator.speedstarttest.struct.FunctionManager;

/**
 * @author Ly
 * @version 1.0
 * email: 596671530@qq.com
 */
public class BaseFragment extends Fragment {
    protected FunctionManager functionManager;
    private MainActivity rootActivity;
    protected Toast toast;

    public void setFunctionManager(FunctionManager functionManager) {
        this.functionManager = functionManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initToast(context);
        if (context instanceof MainActivity) {
            rootActivity = (MainActivity) context;
            rootActivity.setFunctionsForFragment(getTag());
        }
    }

    private void initToast(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
    }

    protected void showToast(CharSequence message, int duration) {
        toast.setText(message);
        toast.setDuration(duration);
        toast.show();
    }
}
