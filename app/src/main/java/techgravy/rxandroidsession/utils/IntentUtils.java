package techgravy.rxandroidsession.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created by aditlal on 03/09/15.
 */
public class IntentUtils {


    public static void openYoutubeVideoID(Context x, String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            x.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            x.startActivity(intent);
        }
    }

    public static void openYoutubeVideoSearch(Context x, String term) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEARCH);
            intent.setPackage("com.google.android.youtube");
            intent.putExtra("query", term);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            x.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Log.e("Youtube Error", ex.getMessage());
        }
    }


}
