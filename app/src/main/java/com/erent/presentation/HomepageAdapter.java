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
    List<Post> posts;

    public HomepageAdapter(Context context, List<Post> posts)
    {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public HomepageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new HomepageViewHolder(LayoutInflater.from(context).inflate(R.layout.post_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomepageViewHolder holder, int position)
    {
        holder.titleView.setText(posts.get(position).getPostName()); //grabs the post title
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
