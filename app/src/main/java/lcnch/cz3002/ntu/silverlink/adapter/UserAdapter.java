package lcnch.cz3002.ntu.silverlink.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import lcnch.cz3002.ntu.silverlink.R;
import lcnch.cz3002.ntu.silverlink.model.UserItem;

/**
 *
 *
 * @author Sherry Lau Geok Teng
 * @version 1.0
 * @since 21/02/2017
 */

public class UserAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<UserItem> userItems;

    private ImageView profilePic;
    private TextView fullName;
    private Bitmap bitmap;

    public UserAdapter(Context context, ArrayList<UserItem> userItems) {
        this.context = context;
        this.userItems = userItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.friend_list_item, null);
        }

        profilePic = (ImageView) convertView.findViewById(R.id.iv_profile_pic);
        fullName = (TextView) convertView.findViewById(R.id.tv_name);

        if(userItems.get(position).getProfilePicture() != null) {
            bitmap = BitmapFactory.decodeByteArray(userItems.get(position).getProfilePicture(), 0, userItems.get(position).getProfilePicture().length);
            profilePic.setImageBitmap(bitmap);
        }
        else {
            if(new Random().nextBoolean())
                profilePic.setImageResource(R.drawable.default_guy);
            else
                profilePic.setImageResource(R.drawable.default_girl);
        }
        fullName.setText(userItems.get(position).getFullName());

        return convertView;
    }

    @Override
    public int getCount() {
        return userItems.size();
    }

    @Override
    public Object getItem(int position) {
        return userItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
