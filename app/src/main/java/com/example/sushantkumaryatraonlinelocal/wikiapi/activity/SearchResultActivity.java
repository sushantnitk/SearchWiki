package com.example.sushantkumaryatraonlinelocal.wikiapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toolbar;

import com.example.sushantkumaryatraonlinelocal.wikiapi.Adapter.SearchResultAdapter;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.SearchResultPojo.Page;
import com.example.sushantkumaryatraonlinelocal.wikiapi.R;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Utils.ClickListener;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Utils.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by YATRAONLINE\sushant.kumar on 23/7/18.
 */

public class SearchResultActivity extends AppCompatActivity {

    private SearchResultAdapter searchResultAdapter;
    private RecyclerView rvSearchResults;
    private ArrayList<Page> pageList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiyt_search_layout);
        initUI();
        if(getIntent()!=null &&
                getIntent().getParcelableArrayListExtra("SEARCHDATA")!=null &&
                !getIntent().getParcelableArrayListExtra("SEARCHDATA").isEmpty()){
            pageList = getIntent().getParcelableArrayListExtra("SEARCHDATA");
            searchResultAdapter = new SearchResultAdapter(this, pageList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            rvSearchResults.setLayoutManager(mLayoutManager);
            DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            if(ContextCompat.getDrawable(this, R.drawable.custom_divider)!=null) {
                itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
            }
            rvSearchResults.addItemDecoration(itemDecorator);
            rvSearchResults.setAdapter(searchResultAdapter);
        }
        setUpClickListnerForRecyclerViewItems();
    }

    private void setUpClickListnerForRecyclerViewItems() {
        rvSearchResults.addOnItemTouchListener(new RecyclerTouchListener
                (this, rvSearchResults, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        redirectToWebView(pageList.get(position));
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
    }

    private void redirectToWebView(Page page) {
        if(page!=null && page.getPageid()>0L) {
            Intent intent = new Intent(this,WebViewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("WIKI_PAGE_TITLE",page.getTitle());
            bundle.putString("WIKI_URL",page.getPageid()+"");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private void initUI() {
        rvSearchResults = (RecyclerView) findViewById(R.id.rv_search_result);
        pageList=  new ArrayList<>();
    }
}


