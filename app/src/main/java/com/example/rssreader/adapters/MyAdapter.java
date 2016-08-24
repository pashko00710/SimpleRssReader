package com.example.rssreader.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.rssreader.R;
import com.example.rssreader.models.RssItem;
import com.example.rssreader.ui.activities.SecondActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<RssItem>feedItems;
    Context context;

    public MyAdapter(Context context,ArrayList<RssItem>feedItems){
        this.feedItems=feedItems;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_main_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        RssItem current=feedItems.get(position);
        holder.title.setText(current.getTitle());
        holder.description.setText(current.getDescription());
        holder.date.setText(current.getPubDate());
        Picasso.with(context).load(current.getThumbnailUrl()).into(holder.thumbnail);
    }


    @Override
    public int getItemCount() {
        return feedItems.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        TextView title,description,date;
        ImageView thumbnail;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind((Activity) context);
            title= (TextView) itemView.findViewById(R.id.title_text);
            description= (TextView) itemView.findViewById(R.id.description_text);
            date= (TextView) itemView.findViewById(R.id.date_text);
            thumbnail= (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            RssItem current=feedItems.get(getAdapterPosition());
            intent = new Intent(context, SecondActivity.class);
            intent.putExtra("link", current.getLink());
            intent.putExtra("date", current.getPubDate());
            intent.putExtra("title", current.getTitle());
            intent.putExtra("pic", current.getThumbnailUrl());
            context.startActivity(intent);
        }
    }
}
