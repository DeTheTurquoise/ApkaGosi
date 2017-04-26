package dmm.apkagosi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import dmm.apkagosi.R;
import dmm.apkagosi.activities.FluffActivity;
import dmm.apkagosi.activities.GeneralActivity;

public class MainActivity extends GeneralActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAdventure (View view){
        Intent intent = new Intent(this, FluffActivity.class);
        startActivity(intent);
    }
}
