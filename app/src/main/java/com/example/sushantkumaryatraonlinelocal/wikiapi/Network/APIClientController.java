package com.example.sushantkumaryatraonlinelocal.wikiapi.Network;

import android.content.Context;
import android.util.Log;

import com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.SearchResultPojo.SearchResponseContainer;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.WikiPagePojo.WikiPageResult;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Utils.ListWrapper;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Utils.WikiConstants;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Wrapper.ViewContract;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by YATRAONLINE\sushant.kumar on 22/7/18.
 */

public class APIClientController {

    private static Retrofit retrofit = null;
    private ViewContract.SearchViewCallbacks searchViewCallbacks;
    private Context context;

    public APIClientController(Context context,ViewContract.SearchViewCallbacks searchViewCallbacks){
        this.searchViewCallbacks = searchViewCallbacks;
        this.context = context;
    }

    public void onStart(int taskCode, String path){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(WikiConstants.BASE_URL_WIKI_RESPONSE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        WIKIAPIInterface wikiapiInterface = retrofit.create(WIKIAPIInterface.class);

        if(taskCode == WikiConstants.RESPONSE_CODE_WIKI_PAGE){
            wikiapiInterface.getWikiPage(path).enqueue(wikiPageCallBack);
        }else if(taskCode == WikiConstants.RESPONSE_CODE_WIKI_SEARCH){
            wikiapiInterface.getSearchResult(path).enqueue(wikiSearchList);
        }


    }


    Callback<List<Object>> wikiSearchList = new Callback<List<Object>>() {
        @Override
        public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
            Log.d(TAG, response.raw().toString());
           if(response.isSuccessful()){
               searchViewCallbacks.notifySearchView(response.body());
           }
             else {
                Log.d("AutoSuggestCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<List<Object>> call, Throwable t) {
            Log.d("Error","SearchKey not found,Some internal Error,Please try again");
        }
    };
    
    Callback<SearchResponseContainer> wikiPageCallBack = new Callback<SearchResponseContainer>() {
        @Override
        public void onResponse(Call<SearchResponseContainer> call, Response<SearchResponseContainer> response) {
            SearchResponseContainer wikiPage = response.body();
            if(wikiPage != null){
                searchViewCallbacks.updateWikiPage(wikiPage);
            }

        }

        @Override
        public void onFailure(Call<SearchResponseContainer> call, Throwable t) {
            Log.d("404","PageNotFound");
            t.printStackTrace();
        }
    };
}
