package techgravy.rxandroidsession.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by aditlal on 03/09/15.
 */
public class IntentUtils {

    public final static int POPULAR_SORT = 01;
    public final static int HIGHEST_RATED_SORT = 02;


    public static void openYoutubeVideo(Context x, String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            x.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            x.startActivity(intent);
        }
    }


}
