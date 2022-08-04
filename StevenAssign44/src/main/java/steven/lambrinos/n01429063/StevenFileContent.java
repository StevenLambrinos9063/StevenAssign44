//Steven Lambrinos N01429063 RNA
package steven.lambrinos.n01429063;

import static steven.lambrinos.n01429063.StevenHome.FILE_N;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StevenFileContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StevenFileContent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StevenFileContent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EshanFileContent.
     */
    // TODO: Rename and change types and number of parameters
    public static StevenFileContent newInstance() {
        StevenFileContent fragment = new StevenFileContent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    Activity activity;
    TextView textView;
    Button btnShow, btnDelete;
    File file;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.steven_file, container, false);
        textView = (TextView) view.findViewById(R.id.steven_fileContent);

        btnShow = (Button) view.findViewById(R.id.steven_btnShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile(activity);
            }
        });

        btnDelete = (Button) view.findViewById(R.id.steven_btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(activity);
            }
        });
        return view;
    }

    private void readFile(Context context) {
        try {
            FileInputStream fileInputStream;

            fileInputStream = context.openFileInput(FILE_N);


            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
            List<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            if (lines.isEmpty()){
                textView.setText(getString(R.string.FSetTextView));
            }else {
                textView.setText(TextUtils.join("\n", lines));
                Toast.makeText(context, R.string.RFileSuccess, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.RFileFail, Toast.LENGTH_SHORT).show();
            textView.setText(R.string.FNoContent);

        }
    }

    private void deleteFile(Context context) {
        file = new File(context.getFilesDir(), FILE_N);

        if (file.exists()) {
            file.delete();
            textView.setText(R.string.FDeleted);
            Toast.makeText(context, R.string.FDeletedSuccess, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.FDeleteFail, Toast.LENGTH_SHORT).show();
        }
    }
}