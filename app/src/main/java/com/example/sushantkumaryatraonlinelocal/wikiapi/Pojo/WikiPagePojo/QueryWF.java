package com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.WikiPagePojo;

/**
 * Created by YATRAONLINE\sushant.kumar on 23/7/18.
 */

class QueryWF {
    private PagesWF pages;

    public PagesWF getPages ()
    {
        return pages;
    }

    public void setPages (PagesWF pages)
    {
        this.pages = pages;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pages = "+pages+"]";
    }
}
