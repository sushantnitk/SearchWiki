package com.example.sushantkumaryatraonlinelocal.wikiapi.Wrapper;

/**
 * Created by sushant.kumar@yatraonline.local on 4/9/17.
 */

public interface ResponseContainer {
    void OnSuccess(String result,int taskCode);
    void OnError(String error);
}
