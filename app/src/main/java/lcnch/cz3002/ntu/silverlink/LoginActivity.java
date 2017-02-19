package lcnch.cz3002.ntu.silverlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *
 *
 * @author Sherry Lau Geok Teng
 * @version 1.0
 * @since 17/02/2017
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new LoginFragment()).commit();
    }
}