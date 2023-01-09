package com.example.shoppingmall.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 어노테이션 적용대상
public @interface RunningTime {
}
