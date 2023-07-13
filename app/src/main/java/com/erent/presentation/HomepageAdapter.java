package com.erent.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erent.objects.Post;
import com.erent.R;

import java.util.List;

public class HomepageAdapter extends RecyclerView.Adapter<HomepageViewHolder> {

    Context context;
    private final RecyclerViewInterface recyclerViewInterface;
    List<Post> posts;

    public HomepageAdapter(Context context, List<Post> posts, RecyclerViewInterface recyclerViewInterface)
    {
        this.context = context;
        this.posts = posts;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public HomepageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new HomepageViewHolder(LayoutInflater.from(context).inflate(R.layout.post_card, parent, false), recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HomepageViewHolder holder, int position)
    {
        holder.titleView.setText(posts.get(position).getPostName()); //grabs the post title
        holder.rentPeriodView.setText(posts.get(position).getRentDuration() + " Days");
        holder.priceView.setText("$" + posts.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
