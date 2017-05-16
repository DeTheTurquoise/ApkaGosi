package dmm.apkagosi.activities;

import android.os.Bundle;
import android.util.Log;

import dmm.apkagosi.R;

/**
 * Created by ddabrowa on 2017-04-28.
 */

public class ProfileActivity extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        Log.i("Info","ProfileActivity created");
    }
}
