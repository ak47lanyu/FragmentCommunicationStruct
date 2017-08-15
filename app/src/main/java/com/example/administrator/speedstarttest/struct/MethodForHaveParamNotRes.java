package com.example.administrator.speedstarttest.struct;

/**
 * @author Ly
 * @version 1.0
 * email: 596671530@qq.com
 */
public abstract class MethodForHaveParamNotRes<Param> extends Function {

    public MethodForHaveParamNotRes(String name) {
        super(name);
    }

    public abstract void function(Param param);
}
