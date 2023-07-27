package com.erent.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erent.R;
import com.erent.application.Services;
import com.erent.logic.IPostLogic;
import com.erent.logic.IRentalLogic;
import com.erent.logic.RentalLogic;
import com.erent.objects.Post;

import java.util.List;

public class RentalsFragment extends Fragment {
    private IPostLogic postLogic;
    private IRentalLogic rentalLogic;
    private RecyclerView recyclerView;
    private List<Post> posts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rentals, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        postLogic = Services.getPostLogic();
        rentalLogic = Services.getRentalLogic();

        List <Integer> postIDList = rentalLogic.getRentedPosts();
        posts = postLogic.getPostsFromIDList(postIDList);

        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(new HomepageAdapter(getContext().getApplicationContext(), posts, new RecyclerViewInterface() {
            @Override
            public void onItemClick(int position) {
                int postID = posts.get(position).getPostID();

                Intent switchActivityIntent = new Intent(getActivity(), PostActivity.class);
                switchActivityIntent.putExtra("postID",postID);
                /* send that we clicked on the post from the rental page*/
                switchActivityIntent.putExtra("onRentalPage",true);

                startActivity(switchActivityIntent);
            }
        }));
        recyclerView = (RecyclerView)getView().findViewById(R.id.recycler_view);
    }
}