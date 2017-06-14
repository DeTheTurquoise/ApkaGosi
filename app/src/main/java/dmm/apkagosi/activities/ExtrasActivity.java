package dmm.apkagosi.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.View;

import dmm.apkagosi.R;
import dmm.apkagosi.utils.LogTags;

/**
 * Created by ddabrowa on 2017-06-12.
 */

public class ExtrasActivity extends GeneralActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_layout);
    }

    private Intent shareText(String textToShare, String titleToShare){
        String mimeType = "text/plain";
        return ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle(titleToShare).setText(textToShare).getIntent();
    }

    public void openL5r(View view){
        Uri l5rPage = Uri.parse("http://l5r---sesje.blogspot.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, l5rPage);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openJapanMap(View view){
        String addressName = "Tokyo, Japan";
        Uri addressUri = Uri.parse("geo:0,0?q=" + addressName);
        Log.i(LogTags.TEMPORARY_LOG,addressUri.toString());

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(addressUri);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}
