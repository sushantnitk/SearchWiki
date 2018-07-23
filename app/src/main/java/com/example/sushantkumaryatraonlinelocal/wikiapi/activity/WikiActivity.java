package com.example.sushantkumaryatraonlinelocal.wikiapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.SearchResultPojo.WikiPage;
import com.example.sushantkumaryatraonlinelocal.wikiapi.R;

/**
 * Created by sushant.kumar@yatraonline.local on 5/9/17.
 */

public class WikiActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_page);
        intializeUI();
        initialiseData();

    }

    private void initialiseData() {
        Intent intent = getIntent();
        String result = intent.getStringExtra("results");
        int pageId = intent.getIntExtra("pageId",0);
        /*JsonParserWikiPage wikiPage = new JsonParserWikiPage(result,pageId);
        WikiPage wikiPageData = wikiPage.parseResponse();
        Glide.with(this).load(wikiPageData.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);*/

        //textView.setText(wikiPageData.getExtract());
    }

    private void intializeUI() {
        imageView = (ImageView)findViewById(R.id.wiki_image);
        textView = (TextView)findViewById(R.id.wiki_text);
    }
}
