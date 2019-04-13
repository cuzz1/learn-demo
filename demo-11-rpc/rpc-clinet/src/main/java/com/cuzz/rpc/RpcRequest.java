package com.cuzz.rpc;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: cuzz
 * @Date: 2019/4/13 14:37
 * @Description: 用于请求封装的对象
 */
@Data
public class RpcRequest implements Serializable{

    private static final long serialVersionUID = 8950282180133484732L;

    private String className;
    private String methodName;
    private Object[] params;
}
