package com.example.sushantkumaryatraonlinelocal.wikiapi.Manager;

import android.content.Context;

import com.example.sushantkumaryatraonlinelocal.wikiapi.Network.APIClientController;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Utils.WikiConstants;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Wrapper.ViewContract;

import static com.example.sushantkumaryatraonlinelocal.wikiapi.Utils.WikiConstants.RESPONSE_CODE_WIKI_PAGE;

/**
 * Created by YATRAONLINE\sushant.kumar on 22/7/18.
 */

public class SearchPresenter implements ViewContract.ViewPresenter {
    private Context context;
    private APIClientController apiClientController;

    public SearchPresenter(Context context){
        this.context = context;
        apiClientController = new APIClientController(context, (ViewContract.SearchViewCallbacks) context);
    }


    @Override
    public void onTextChanged(String word) {
        apiClientController.onStart(WikiConstants.RESPONSE_CODE_WIKI_SEARCH,word);
    }

    @Override
    public void onSearchClick(String key) {
        apiClientController.onStart(RESPONSE_CODE_WIKI_PAGE,key);
    }
}
