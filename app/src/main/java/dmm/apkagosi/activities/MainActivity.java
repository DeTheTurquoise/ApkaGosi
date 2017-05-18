package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends GeneralActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        Log.i("Info","MainActivity created");
        startActivity(profileIntent);
    }

}
