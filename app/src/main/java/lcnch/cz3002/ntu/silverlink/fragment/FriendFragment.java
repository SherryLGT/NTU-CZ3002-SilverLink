package lcnch.cz3002.ntu.silverlink.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import lcnch.cz3002.ntu.silverlink.R;
import lcnch.cz3002.ntu.silverlink.adapter.FriendAdapter;
import lcnch.cz3002.ntu.silverlink.controller.Utility;
import lcnch.cz3002.ntu.silverlink.model.Friend;
import lcnch.cz3002.ntu.silverlink.model.FriendItem;

/**
 *
 *
 * @author Sherry Lau Geok Teng
 * @version 1.0
 * @since 27/02/2017
 */

public class FriendFragment extends Fragment {

    private View rootView;
    private Spinner spnSort;
    private ListView lvFriendList;
    private Button btnAddFriend;

    private Gson gson;
    private String response;
    public static ArrayList<Friend> friendList;
    private ArrayList<FriendItem> friendItems;

    /**
     * Default constructor for FriendFragment
     */
    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_friend, container, false);

        spnSort = (Spinner) rootView.findViewById(R.id.spn_sort);
        lvFriendList = (ListView) rootView.findViewById(R.id.lv_friend_list);
        btnAddFriend = (Button) rootView.findViewById(R.id.btn_add_friend);

        new getFriends().execute();

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new AddFriendFragment()).addToBackStack("AddFriendFragment").commit();
            }
        });

        // For testing purpose (remove when not needed)
//        btnAddFriend.performClick();

        return rootView;
    }

    private class getFriends extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
            friendList = new ArrayList<Friend>();
            friendItems = new ArrayList<FriendItem>();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            lvFriendList.setAdapter(new FriendAdapter(getContext(), friendItems));
        }

        @Override
        protected Void doInBackground(Void... params) {
            response = Utility.getRequest("api/Friends");
            friendList = gson.fromJson(response, new TypeToken<List<Friend>>() {}.getType());
            for(Friend friend : friendList) {
                friendItems.add(new FriendItem(friend.getUser().getProfilePicture(), friend.getUser().getFullName()));
            }

            return null;
        }
    }

}