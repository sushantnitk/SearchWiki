package com.example.sushantkumaryatraonlinelocal.wikiapi.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sushantkumaryatraonlinelocal.wikiapi.Manager.SearchPresenter;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.SearchResultPojo.SearchResponseContainer;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.WikiPagePojo.WikiPageResult;
import com.example.sushantkumaryatraonlinelocal.wikiapi.R;
import com.example.sushantkumaryatraonlinelocal.wikiapi.Wrapper.ViewContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements ViewContract.SearchViewCallbacks {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rvRecentSearches;
    private AutoCompleteTextView actSearchBox;
    private LinearLayout llRecents,content;
    private Button btnSearch;
    private RelativeLayout rlMain;
    private SearchPresenter searchPresenter;
    private SharedPreferences sharedpreferences;
    private ArrayList<String> autoSuggestList;
    private ArrayAdapter<String> autosuggestAdapter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        searchPresenter = new SearchPresenter(this);
        setTextChangeListnerOnSearchBox();
        setClickListnerForSearchButton();
    }



    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SearchWiki");
        rvRecentSearches = (RecyclerView) findViewById(R.id.rv_recent_searches);
        actSearchBox = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        btnSearch = (Button) findViewById(R.id.search_wiki_page);
        llRecents = (LinearLayout) findViewById(R.id.ll_recents);
        autoSuggestList = new ArrayList<>();
        autosuggestAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, autoSuggestList);
        actSearchBox.setThreshold(1);
        actSearchBox.setAdapter(autosuggestAdapter);


    }


    private void setTextChangeListnerOnSearchBox() {
        actSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence.toString())) {
                    searchPresenter.onTextChanged(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setClickListnerForSearchButton() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPresenter.onSearchClick(actSearchBox.getText().toString());
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
            }
        });
    }


    @Override
    public void updateWikiPage(SearchResponseContainer response) {
            if (response != null && response.getQueryResponse() != null) {
                progressDialog.dismiss();
                actSearchBox.setText("");
                Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                intent.putExtra("SEARCHDATA", response.getQueryResponse().getPages());
                startActivity(intent);
            } else {
                Snackbar.make(rlMain, "No results to show", Snackbar.LENGTH_SHORT).show();
            }
    }

    @Override
    public void notifySearchView(List<Object> response) {
        if (response != null) {
            autoSuggestList.clear();
            autosuggestAdapter.clear();
            for (int i = 0; i < ((ArrayList<String>) (response.get(1))).size(); i++) {
                String SuggestKey = ((ArrayList<String>) (response.get(1))).get(i);
                autoSuggestList.add(SuggestKey);
            }
            Log.d(TAG,autoSuggestList.toString());
            autosuggestAdapter.addAll(autoSuggestList);
            autosuggestAdapter.notifyDataSetChanged();

        } else {
            Log.d("AutoSuggestCallback", "Code: " + "Error");
        }
    }
}

