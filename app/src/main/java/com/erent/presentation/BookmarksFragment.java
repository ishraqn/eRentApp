package com.erent.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erent.R;
import com.erent.application.Services;
import com.erent.logic.IBookmarkLogic;
import com.erent.logic.IPostLogic;
import com.erent.objects.Post;

import java.util.List;

public class BookmarksFragment extends Fragment {

    private IPostLogic postLogic;
    private IBookmarkLogic bookmarkLogic;
    private RecyclerView recyclerView;
    private List<Post> posts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmarks, container, false);
    }

    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        bookmarkLogic = Services.getBookmarkLogic();
        postLogic = Services.getPostLogic();

        /* Get the bookmarked posts for the user */
        List<Integer> postIDList = bookmarkLogic.getBookmarkedPosts();
        posts = postLogic.getPostsFromIDList(postIDList);

        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(new HomepageAdapter(getContext().getApplicationContext(), posts, new RecyclerViewInterface() {
            @Override
            public void onItemClick(int position) {
                int postID = posts.get(position).getPostID();

                Intent switchActivityIntent = new Intent(getActivity(), PostActivity.class);

                switchActivityIntent.putExtra("postID", postID);
                /* send that we clicked on the post from the bookmark page*/
                switchActivityIntent.putExtra("onBookmarkPage", true);

                startActivity(switchActivityIntent);
            }
        }));
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
    }
}