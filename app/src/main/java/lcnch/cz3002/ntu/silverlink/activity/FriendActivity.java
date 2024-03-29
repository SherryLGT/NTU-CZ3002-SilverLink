package lcnch.cz3002.ntu.silverlink.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lcnch.cz3002.ntu.silverlink.R;
import lcnch.cz3002.ntu.silverlink.fragment.FriendFragment;

/**
 * @author Sherry Lau Geok Teng
 * @version 1.0
 * @since 27/02/2017
 */

public class FriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new FriendFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentByTag("MessageFragment") != null || getSupportFragmentManager().findFragmentByTag("AddFriendFragment") != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new FriendFragment()).commit();
        }
        else {
            super.onBackPressed();
        }
    }
}
