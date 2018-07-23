package com.example.sushantkumaryatraonlinelocal.wikiapi.Wrapper;


import com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.SearchResultPojo.SearchResponseContainer;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.WikiPagePojo.WikiPageResult;

import java.util.List;

public interface ViewContract {
    interface SearchViewCallbacks{
        void updateWikiPage(SearchResponseContainer searchResponseContainer);
        void notifySearchView(List<Object> jsonParserWikiResponselIsts);
    }

    interface ViewPresenter{
        void onTextChanged(String word);
        void onSearchClick(String key);
    }


}
