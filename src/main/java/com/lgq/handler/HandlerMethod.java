package com.lgq.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author lgq
 * @date 2019/10/25
 */
@Data
@AllArgsConstructor
public class HandlerMethod {

    private Object bean;

    private Class<?> beanType;

    private Method method;
}
