package com.example.sushantkumaryatraonlinelocal.wikiapi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.sushantkumaryatraonlinelocal.wikiapi.R;

/**
 * Created by YATRAONLINE\sushant.kumar on 23/7/18.
 */

public class WebViewActivity extends AppCompatActivity {

    private static final String WIKI_BASE_URL_FOR_WEBVIEW = "http://en.wikipedia.org/?curid=" ;
    private WebView webview;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initUI();
        initWebView();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getString("WIKI_PAGE_TITLE") != null
                && !TextUtils.isEmpty(bundle.getString("WIKI_PAGE_TITLE"))) {
            getSupportActionBar().setTitle(bundle.getString("WIKI_PAGE_TITLE"));
        } else {
            getSupportActionBar().setTitle("Wiki Page");
        }
        if (bundle != null && bundle.getString("WIKI_URL") != null
                && !TextUtils.isEmpty(bundle.getString("WIKI_URL"))) {
            webview.loadUrl(WIKI_BASE_URL_FOR_WEBVIEW + getIntent().getStringExtra("WIKI_URL"));
        } else {
            //showBadURLErrorOnWebView(webview);
        }
    }

    private void initWebView() {
        webview.getSettings().setDefaultTextEncodingName("utf-8");
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(true);
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }

                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                }
            }
        });
    }

    private void initUI() {
        webview = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }
}


