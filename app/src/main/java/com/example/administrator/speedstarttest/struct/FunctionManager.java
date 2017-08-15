package com.example.administrator.speedstarttest.struct;

import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

/**
 * @author Ly
 * @version 1.0
 * email: 596671530@qq.com
 */
public class FunctionManager {
    private static FunctionManager _instance;
    /* 方法名-->对应不同类型的方法类 */
    private HashMap<String, MethodForNotParamNotRes> notParamNotResHashMap;
    private HashMap<String, MethodForNotParamHaveRes> notParamHaveResHashMap;
    private HashMap<String, MethodForHaveParamHaveRes> haveParamHaveResHashMap;
    private HashMap<String, MethodForHaveParamNotRes> haveParamNotResHashMap;

    private FunctionManager() {
        notParamNotResHashMap = new HashMap<>();
        notParamHaveResHashMap = new HashMap<>();
        haveParamHaveResHashMap = new HashMap<>();
        haveParamNotResHashMap = new HashMap<>();
    }

    public static FunctionManager getInstance() {
        if (_instance == null) {
            synchronized (FunctionManager.class) {
                _instance = new FunctionManager();
            }
        }
        return _instance;
    }

    public FunctionManager addMethod(MethodForNotParamNotRes methodBody) {
        notParamNotResHashMap.put(methodBody.funcName, methodBody);
        return this;
    }

    public FunctionManager addMethod(MethodForNotParamHaveRes methodBody) {
        notParamHaveResHashMap.put(methodBody.funcName, methodBody);
        return this;
    }

    public FunctionManager addMethod(MethodForHaveParamHaveRes methodBody) {
        haveParamHaveResHashMap.put(methodBody.funcName, methodBody);
        return this;
    }

    public FunctionManager addMethod(MethodForHaveParamNotRes methodBody) {
        haveParamNotResHashMap.put(methodBody.funcName, methodBody);
        return this;
    }

    public void invokeMethod(String methodName) throws FunctionNotFoundException {
        if (TextUtils.isEmpty(methodName)) {
            return;
        }
        MethodForNotParamNotRes forNotParamNotRes = notParamNotResHashMap.get(methodName);
        if (forNotParamNotRes != null) {
            forNotParamNotRes.function();
        } else {
            throw new FunctionNotFoundException("function not found --> " + methodName);
        }
    }

    public <Result> Result invokeMethod(String methodName, Class<Result> clazz) throws FunctionNotFoundException {
        if (TextUtils.isEmpty(methodName)) {
            return null;
        }
        MethodForNotParamHaveRes forNotParamHaveRes = notParamHaveResHashMap.get(methodName);
        if (forNotParamHaveRes != null) {
            if (clazz != null) {
                return clazz.cast(forNotParamHaveRes.function());
            } else {
                return (Result) forNotParamHaveRes.function();
            }
        } else {
            throw new FunctionNotFoundException("function not found --> " + methodName);
        }
    }

    public <Param> void invokeMethod(String methodName, Param param) throws FunctionNotFoundException {
        if (TextUtils.isEmpty(methodName)) {
            return;
        }
        MethodForHaveParamNotRes forHaveParamNotRes = haveParamNotResHashMap.get(methodName);
        if (forHaveParamNotRes != null) {
            forHaveParamNotRes.function(param);
        } else {
            throw new FunctionNotFoundException("function not found --> " + methodName);
        }
    }

    /**
     * 如果传递多个参数可以用粗颗粒的方式传递参数实体
     * @return 根据你指定的返回值类型，返回数据
     * @throws FunctionNotFoundException
     */
    public <Result, Param> Result invokeMethod(String methodName, Class<Result> resultClazz, Param paramClazz) throws FunctionNotFoundException {
        if (TextUtils.isEmpty(methodName)) {
            return null;
        }
        MethodForHaveParamHaveRes forHaveParamHaveRes = haveParamHaveResHashMap.get(methodName);
        if (forHaveParamHaveRes != null) {
            if (resultClazz != null) {
                return resultClazz.cast(forHaveParamHaveRes.function(paramClazz));
            } else {
                return (Result) forHaveParamHaveRes.function(paramClazz);
            }
        } else {
            throw new FunctionNotFoundException("function not found --> " + methodName);
        }
    }
}
