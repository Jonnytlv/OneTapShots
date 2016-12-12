package application.android.com.onetapshotsf;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by jonny on 12/12/2016.
 */

public class LanguageHelper {
    public static void changelocale (Resources res, String locale) {
        Configuration config;
        config = new Configuration(res.getConfiguration());

        switch (locale) {
            case "es":
                config.locale = new Locale("es");
                break;
            case "fr":
                config.locale = Locale.FRENCH;
                break;
            default:
                config.locale = Locale.ENGLISH;
                break;
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
