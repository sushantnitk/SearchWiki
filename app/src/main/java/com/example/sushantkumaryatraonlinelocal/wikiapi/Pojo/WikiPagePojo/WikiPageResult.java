package com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.WikiPagePojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by YATRAONLINE\sushant.kumar on 23/7/18.
 */

public class WikiPageResult implements Serializable{
    private QueryWF query;

        private String batchcomplete;

        public QueryWF getQuery ()
        {
            return query;
        }

        public void setQuery (QueryWF query)
        {
            this.query = query;
        }

        public String getBatchcomplete ()
        {
            return batchcomplete;
        }

        public void setBatchcomplete (String batchcomplete)
        {
            this.batchcomplete = batchcomplete;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [query = "+query+", batchcomplete = "+batchcomplete+"]";
        }
    }

