package com.example.sushantkumaryatraonlinelocal.wikiapi.Utils;

/**
 * Created by sushant.kumar@yatraonline.local on 4/9/17.
 */

public class WikiConstants {
    public static int  RESPONSE_CODE_WIKI_SEARCH = 0;
    public static int  RESPONSE_CODE_WIKI_PAGE = 1;
    public static String BASE_URL_WIKI_RESPONSE = "https://en.wikipedia.org/w/";
    public static final String BASE_QUERY_URL_SEARCH = "api.php?action=opensearch&namespace=0&format=json";
    public static final String URL_PATH_WIKI_PAGE = "api.php?action=query&format=json&prop=pageimages|pageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=100";
}
