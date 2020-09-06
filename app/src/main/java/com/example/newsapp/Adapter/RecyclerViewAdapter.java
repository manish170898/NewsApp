package com.example.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Detailed;
import com.example.newsapp.Parameter.Articles;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Articles>articles;

    public RecyclerViewAdapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.news_layout_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Articles a =articles.get(position);
        String url = a.getUrl();
        holder.Title.setText(a.getTitle());
        holder.Source.setText(a.getSource().getName());
        holder.Date.setText("\u2022"+datetime(a.getPublishedAt()));

        String imageUrl = a.getUrlToImage();
        Picasso.get().load(imageUrl).into(holder.img);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Detailed.class);
                intent.putExtra("title",a.getTitle());
                intent.putExtra("source",a.getSource().getName());
                intent.putExtra("time",datetime(a.getPublishedAt()));
                intent.putExtra("desc",a.getDescription());
                intent.putExtra("imageUrl",a.getUrlToImage());
                intent.putExtra("url",a.getUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Title,Source,Date;
        ImageView img;
        CardView cardView;


        public MyViewHolder(View itemview) {
            super(itemview);

            Title = itemview.findViewById(R.id.title);
            Source = itemview.findViewById(R.id.source);
            Date = itemview.findViewById(R.id.date);
            img = itemview.findViewById(R.id.img);
            cardView = itemview.findViewById(R.id.cardview);

        }
    }

    public String datetime(String t) {
        PrettyTime prettyTime = new PrettyTime();
        String time =null;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format(date);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return time;
    }
}
