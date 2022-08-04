package steven.lambrinos.n01429063;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

public class SharedPreference {

    final static String PREFS_NAME = "Persistent";
    final static String FullN ="Steven Lambrinos";
    TextView textView;

    public SharedPreference() {
        super();
    }

    public void createPreference(Context context) {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;

        preferences = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("Name", FullN);
        editor.commit();
    }

    public void onView(Context context, View view) {
        SharedPreferences settings;
        textView = (TextView) view.findViewById(R.id.steven_preferenceView);

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        textView.setText(settings.getAll().toString());


    }
}
