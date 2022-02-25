package com.niit.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hmqhmq
 */
@ControllerAdvice
public class MyException {

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public String  showExe(){
        System.out.print("进入showExe控制器里。。。。。");
        return "出错了，别着急，马上解决，稍后在放请求！";
    }



}
