//Steven Lambrinos N01429063 RNA
package steven.lambrinos.n01429063;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StevenHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StevenHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StevenHome() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EshanHome.
     */
    // TODO: Rename and change types and number of parameters
    public static StevenHome newInstance() {
        StevenHome fragment = new StevenHome();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        sharedPreference = new SharedPreference();
    }
    SharedPreference sharedPreference;
    Activity activity;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.steven_home, container, false);
        CurrentDate(view);
        editText = view.findViewById(R.id.steven_ET);
        Button btn = view.findViewById(R.id.steven_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFile(v, activity.getApplicationContext());
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        sharedPreference.createPreference(activity);
    }
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;
    TextView textView;
    private void CurrentDate(View view) {
        textView = view.findViewById(R.id.steven_date);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat(getString(R.string.DateFormat));
        Date = simpleDateFormat.format(calendar.getTime());
        textView.setText(Date);
    }

    public final static String FILE_N = "Steven.txt";
    FileOutputStream fileOutputStream;

    private void CreateFile(View v, Context context) {
        try {
            File file = new File(context.getFilesDir(), FILE_N);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(editText.getText().toString().getBytes(Charset.forName("UTF-8")));
            Toast.makeText(context, R.string.FCreateSuccess, Toast.LENGTH_SHORT).show();

        }catch (NullPointerException | IOException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.FCreateFail,  Toast.LENGTH_SHORT).show();
        }
    }
}