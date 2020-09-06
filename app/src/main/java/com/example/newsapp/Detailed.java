package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detailed extends AppCompatActivity {

    TextView Title,Source,Date,Description;
    ImageView imageView;
    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Title = findViewById(R.id.title);
        Source = findViewById(R.id.source);
        Date = findViewById(R.id.date);
        Description = findViewById(R.id.desc);
        imageView = findViewById(R.id.img);

        webView = findViewById(R.id.webview);

        progressBar = findViewById(R.id.webview_progressbar);
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String source = intent.getStringExtra("source");
        String time = intent.getStringExtra("time");
        String desc = intent.getStringExtra("desc");
        String imageUrl = intent.getStringExtra("imageUrl");
        String url = intent.getStringExtra("url");

        Title.setText(title);
        Source.setText(source);
        Date.setText(time);
        Description.setText(desc);

        Picasso.get().load(imageUrl).into(imageView);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        if(webView.isShown()){
            progressBar.setVisibility(View.INVISIBLE);
        }


    }
}
