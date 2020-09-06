package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsapp.Adapter.RecyclerViewAdapter;
import com.example.newsapp.Parameter.Articles;
import com.example.newsapp.Parameter.Source;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "https://newsapi.org/v2/top-headlines?country=in";
    private final String BASE_URL1 = "https://newsapi.org/v2/everything?";
    private final String API_kEY = "&apiKey=eceae3e8b84342efa412e808844cdc41";
    private RequestQueue requestQueue;
    private List<Articles>articles;
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    DialogFragment fragment;

    LottieAnimationView lview;


    String URL,category;

    Button top_news_button,sports_news_button,business_news_button,health_news_button,tech_news_button;

    EditText search_text;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        articles = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);


        top_news_button = findViewById(R.id.top_news_button);
        sports_news_button = findViewById(R.id.sports_button);
        business_news_button = findViewById(R.id.business_button);
        health_news_button = findViewById(R.id.health_button);
        tech_news_button = findViewById(R.id.technology_button);

        search_text = findViewById(R.id.search_text);

        fragment = new DialogFragment();


        lview = new LottieAnimationView(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jsonrequest(URL);
            }
        });

        top_news_button.setBackgroundResource(R.drawable.button2);
        top_news_button.setTextColor(getResources().getColor(R.color.black));

        sports_news_button.setBackgroundResource(R.drawable.button1);
        sports_news_button.setTextColor(getResources().getColor(R.color.white));
        health_news_button.setBackgroundResource(R.drawable.button1);
        health_news_button.setTextColor(getResources().getColor(R.color.white));
        business_news_button.setBackgroundResource(R.drawable.button1);
        business_news_button.setTextColor(getResources().getColor(R.color.white));
        tech_news_button.setBackgroundResource(R.drawable.button1);
        tech_news_button.setTextColor(getResources().getColor(R.color.white));
        search_text.setText("");



        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append(API_kEY);
        URL = stringBuilder.toString();
        linearLayoutManager.scrollToPositionWithOffset(0,0);
        articles.clear();
        jsonrequest(URL);
    }




    public void topnews(View view) {

        top_news_button.setBackgroundResource(R.drawable.button2);
        top_news_button.setTextColor(getResources().getColor(R.color.black));

        sports_news_button.setBackgroundResource(R.drawable.button1);
        sports_news_button.setTextColor(getResources().getColor(R.color.white));
        health_news_button.setBackgroundResource(R.drawable.button1);
        health_news_button.setTextColor(getResources().getColor(R.color.white));
        business_news_button.setBackgroundResource(R.drawable.button1);
        business_news_button.setTextColor(getResources().getColor(R.color.white));
        tech_news_button.setBackgroundResource(R.drawable.button1);
        tech_news_button.setTextColor(getResources().getColor(R.color.white));
        search_text.setText("");



        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append(API_kEY);
        URL = stringBuilder.toString();
        linearLayoutManager.scrollToPositionWithOffset(0,0);
        articles.clear();
        jsonrequest(URL);
    }

    public void sports(View view) {
        sports_news_button.setBackgroundResource(R.drawable.button2);
        sports_news_button.setTextColor(getResources().getColor(R.color.black));

        top_news_button.setBackgroundResource(R.drawable.button1);
        top_news_button.setTextColor(getResources().getColor(R.color.white));
        health_news_button.setBackgroundResource(R.drawable.button1);
        health_news_button.setTextColor(getResources().getColor(R.color.white));
        business_news_button.setBackgroundResource(R.drawable.button1);
        business_news_button.setTextColor(getResources().getColor(R.color.white));
        tech_news_button.setBackgroundResource(R.drawable.button1);
        tech_news_button.setTextColor(getResources().getColor(R.color.white));
        search_text.setText("");

        StringBuilder stringBuilder = new StringBuilder();
        category = "&category=sports";
        stringBuilder.append(BASE_URL);
        stringBuilder.append(category);
        stringBuilder.append(API_kEY);
        URL = stringBuilder.toString();
        articles.clear();
        linearLayoutManager.scrollToPositionWithOffset(0,0);
        jsonrequest(URL);


    }

    public void business(View view) {
        business_news_button.setBackgroundResource(R.drawable.button2);
        business_news_button.setTextColor(getResources().getColor(R.color.black));

        sports_news_button.setBackgroundResource(R.drawable.button1);
        sports_news_button.setTextColor(getResources().getColor(R.color.white));
        health_news_button.setBackgroundResource(R.drawable.button1);
        health_news_button.setTextColor(getResources().getColor(R.color.white));
        top_news_button.setBackgroundResource(R.drawable.button1);
        top_news_button.setTextColor(getResources().getColor(R.color.white));
        tech_news_button.setBackgroundResource(R.drawable.button1);
        tech_news_button.setTextColor(getResources().getColor(R.color.white));
        search_text.setText("");

        StringBuilder stringBuilder = new StringBuilder();
        category = "&category=business";
        stringBuilder.append(BASE_URL);
        stringBuilder.append(category);
        stringBuilder.append(API_kEY);
        URL = stringBuilder.toString();
        articles.clear();
        linearLayoutManager.scrollToPositionWithOffset(0,0);
        jsonrequest(URL);
    }

    public void health(View view) {
        health_news_button.setBackgroundResource(R.drawable.button2);
        health_news_button.setTextColor(getResources().getColor(R.color.black));

        sports_news_button.setBackgroundResource(R.drawable.button1);
        sports_news_button.setTextColor(getResources().getColor(R.color.white));
        top_news_button.setBackgroundResource(R.drawable.button1);
        top_news_button.setTextColor(getResources().getColor(R.color.white));
        business_news_button.setBackgroundResource(R.drawable.button1);
        business_news_button.setTextColor(getResources().getColor(R.color.white));
        tech_news_button.setBackgroundResource(R.drawable.button1);
        tech_news_button.setTextColor(getResources().getColor(R.color.white));
        search_text.setText("");

        StringBuilder stringBuilder = new StringBuilder();
        category = "&category=health";
        stringBuilder.append(BASE_URL);
        stringBuilder.append(category);
        stringBuilder.append(API_kEY);
        URL = stringBuilder.toString();
        articles.clear();
        linearLayoutManager.scrollToPositionWithOffset(0,0);
        jsonrequest(URL);
    }

    public void tech(View view) {
        tech_news_button.setBackgroundResource(R.drawable.button2);
        tech_news_button.setTextColor(getResources().getColor(R.color.black));

        sports_news_button.setBackgroundResource(R.drawable.button1);
        sports_news_button.setTextColor(getResources().getColor(R.color.white));
        health_news_button.setBackgroundResource(R.drawable.button1);
        health_news_button.setTextColor(getResources().getColor(R.color.white));
        business_news_button.setBackgroundResource(R.drawable.button1);
        business_news_button.setTextColor(getResources().getColor(R.color.white));
        top_news_button.setBackgroundResource(R.drawable.button1);
        top_news_button.setTextColor(getResources().getColor(R.color.white));
        search_text.setText("");

        StringBuilder stringBuilder = new StringBuilder();
        category = "&category=technology";
        stringBuilder.append(BASE_URL);
        stringBuilder.append(category);
        stringBuilder.append(API_kEY);
        URL = stringBuilder.toString();
        articles.clear();
        linearLayoutManager.scrollToPositionWithOffset(0,0);
        jsonrequest(URL);

    }

    public void search(View view)  {

        String text = search_text.getText().toString().trim();
        if (text.matches("")) {
            Toast.makeText(this, "You did not enter anything to search", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            tech_news_button.setBackgroundResource(R.drawable.button1);
            tech_news_button.setTextColor(getResources().getColor(R.color.white));
            sports_news_button.setBackgroundResource(R.drawable.button1);
            sports_news_button.setTextColor(getResources().getColor(R.color.white));
            health_news_button.setBackgroundResource(R.drawable.button1);
            health_news_button.setTextColor(getResources().getColor(R.color.white));
            business_news_button.setBackgroundResource(R.drawable.button1);
            business_news_button.setTextColor(getResources().getColor(R.color.white));
            top_news_button.setBackgroundResource(R.drawable.button1);
            top_news_button.setTextColor(getResources().getColor(R.color.white));

            hidekeyboard();
            String url = "q="+text;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(BASE_URL1);
            stringBuilder.append(url);
            stringBuilder.append(API_kEY);
            URL = stringBuilder.toString();
            articles.clear();
            linearLayoutManager.scrollToPositionWithOffset(0,0);

            showLoadingDialog();

            jsonrequest(URL);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hideLoadingDialog();
                }
            },500);

        }
    }

    private void jsonrequest(String JSON_URL) {
        swipeRefreshLayout.setRefreshing(false);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    swipeRefreshLayout.setRefreshing(false);

                    JSONArray jsonArray = response.getJSONArray("articles");

                    for(int i= 0; i<jsonArray.length(); i++){
                        JSONObject article = jsonArray.getJSONObject(i);
                        Articles articles1 = new Articles();
                        Source source1 = new Source();
                        articles1.setAuthor(article.getString("author"));
                        articles1.setDescription(article.getString("description"));
                        articles1.setPublishedAt(article.getString("publishedAt"));
                        articles1.setTitle(article.getString("title"));
                        articles1.setUrl(article.getString("url"));
                        articles1.setUrlToImage(article.getString("urlToImage"));
                        source1.setName(article.getJSONObject("source").getString("name"));
                        source1.setId(article.getJSONObject("source").getString("id"));
                        articles1.setSource(source1);

                        articles.add(articles1);
                    }
                    System.out.println("No of articles" + articles.size());

                    adapter = new RecyclerViewAdapter(getApplicationContext(),articles);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d("tag","onErrorResponse"+error.getMessage());
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
    public void hidekeyboard() {
        View view = getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)this.getSystemService(this.INPUT_METHOD_SERVICE);
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    public void showLoadingDialog(){
        fragment = (DialogFragment)getSupportFragmentManager().findFragmentByTag(DialogFragment.FRAGMENT_TAG);
        if(fragment == null){
            fragment = new DialogFragment();
            fragment.setCancelable(false);
            getSupportFragmentManager().beginTransaction()
                    .add(fragment,DialogFragment.FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }
    }
    public void hideLoadingDialog() {
        fragment.dismiss();
    }
}
