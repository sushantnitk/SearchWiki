package com.example.sushantkumaryatraonlinelocal.wikiapi.Network;

import com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.SearchResultPojo.SearchResponseContainer;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.WikiPagePojo.WikiPageResult;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Utils.ListWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.sushantkumaryatraonlinelocal.wikiapi.Utils.WikiConstants.BASE_QUERY_URL_SEARCH;
import static com.example.sushantkumaryatraonlinelocal.wikiapi.Utils.WikiConstants.URL_PATH_WIKI_PAGE;

/**
 * Created by YATRAONLINE\sushant.kumar on 22/7/18.
 */

public interface WIKIAPIInterface {

    @GET(BASE_QUERY_URL_SEARCH)
     Call<List<Object>> getSearchResult(@Query("search") String text);

    @GET(URL_PATH_WIKI_PAGE)
    Call<SearchResponseContainer> getWikiPage(@Query("gpssearch") String titles);

}
