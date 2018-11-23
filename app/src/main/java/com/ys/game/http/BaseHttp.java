package com.ys.game.http;

public class BaseHttp {
    private static BaseHttp instance;
    public static BaseHttp getInstance(){
        if(instance == null){
            instance = new BaseHttp();
        }
        return instance;
    }
    public void test(){

    }
}
