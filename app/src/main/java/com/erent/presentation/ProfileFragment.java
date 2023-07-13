package com.erent.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.erent.R;
import com.erent.application.Services;
import com.erent.objects.Post;
import com.erent.objects.User;
import com.erent.persistence.IPostPersistence;
import com.erent.persistence.IUserPersistence;

import java.util.List;

public class ProfileFragment extends Fragment {

    TextView userName;
    ImageView userProfilePicture;
    RecyclerView recyclerView;
    ImageView backButton;
    TextView listHeader;

    IUserPersistence userPersistence;
    IPostPersistence userPostPersistence;
    String loggedUser;
    List<Post> posts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);

        userName = view.findViewById(R.id.name);
        userProfilePicture = view.findViewById(R.id.picture);
        recyclerView = view.findViewById(R.id.recycler_view);
        backButton = view.findViewById(R.id.buttonBack);
        listHeader = view.findViewById(R.id.rent_period);

        userPersistence = Services.getUserPersistence();
        loggedUser = Services.getCurrentUser();

        User user = userPersistence.getUserByUsername(loggedUser);
        userProfilePicture.setImageIcon(null);
        userName.setText("user.getUsername()");         //cannot login so anyone who can please unquote the function

        userPostPersistence = Services.getPostPersistence();
        posts = userPostPersistence.getPostList();

        // delete since no posts are to be shown
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        recyclerView.setAdapter(new HomepageAdapter(getContext().getApplicationContext(), posts, new RecyclerViewInterface() {
//            @Override
//            public void onItemClick(int position) {
//
//            }
//        }));
//        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
    }
}