package com.example.administrator.speedstarttest.struct;

/**
 * @author Ly
 * @version 1.0
 * email: 596671530@qq.com
 */
public abstract class MethodForNotParamHaveRes<Result> extends Function {

    public MethodForNotParamHaveRes(String name) {
        super(name);
    }

    public abstract Result function();
}
