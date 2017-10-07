package com.jithin.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jithin.android.R;
import com.jithin.android.util.ImageUtil;
import com.jithin.android.util.ScreenUtil;
import com.jithin.android.util.Util;
import com.jithin.core.model.Article;

import java.util.List;

/**
 * Created by Jithin on 05/10/17.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.Holder> {

    private List<Article> articles;
    private String TAG = ArticleAdapter.class.getSimpleName();

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_article, parent, false);
        Holder holder = new Holder(view);
        holder.itemView.setOnClickListener(view1 -> {
            if (holder.getAdapterPosition() != RecyclerView.NO_POSITION && articles != null && articles.size() > holder.getAdapterPosition()) {
                ScreenUtil.openChromeTab(view.getContext(), articles.get(holder.getAdapterPosition()).getUrl());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Article article = articles.get(position);
        holder.text_title.setText(article.getTitle());
        holder.text_desc.setText(article.getDescription());

        String info = "";
        String date = Util.parseDate(article.getPublishedAt());
        String author = article.getAuthor();
        if (author != null)
            info += "By " + author;
        if (date != null)
            info += " on " + date;
        holder.text_info.setText(info);
        ImageUtil.load(holder.image, article.getUrlToImage());
    }

    @Override
    public int getItemCount() {
        if (articles == null) return 0;
        return articles.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView text_title;
        TextView text_desc;
        TextView text_info;
        ImageView image;

        public Holder(View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_article_title);
            text_desc = itemView.findViewById(R.id.text_article_description);
            text_info = itemView.findViewById(R.id.text_article_info);
            image = itemView.findViewById(R.id.image_article);
        }
    }

}
