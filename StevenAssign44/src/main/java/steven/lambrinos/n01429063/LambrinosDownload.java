//Steven Lambrinos N01429063 RNA
package steven.lambrinos.n01429063;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LambrinosDownload#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LambrinosDownload extends Fragment implements AdapterView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LambrinosDownload() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SalwanDownload.
     */
    // TODO: Rename and change types and number of parameters
    public static LambrinosDownload newInstance() {
        LambrinosDownload fragment = new LambrinosDownload();
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

    String downloadList[] = {"Cat", "Car", "Pizza"};
    Activity activity;
    ListView productListView;
    ArrayAdapter<String> productListAdapter;
    ImageView imageView;
    ProgressBar progressBar;
    SharedPreference preference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lambrinos_download, container, false);

        productListView = (ListView) view.findViewById(R.id.steven_listView);

        productListAdapter = new ArrayAdapter<String>(activity, R.layout.lambrinos_download, R.id.steven_preferenceView, downloadList);
        productListView.setAdapter(productListAdapter);
        productListView.setOnItemClickListener(this);

        imageView = view.findViewById(R.id.steven_imageView3);
        progressBar = view.findViewById(R.id.steven_progress);
        progressBar.setVisibility(View.INVISIBLE);

        preference = new SharedPreference();
        preference.onView(activity, view);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        if (position == 0) {
            MyAsyncTask asyncTask = new MyAsyncTask();
            asyncTask.execute(getString(R.string.downloadCat));
        } else if (position == 1) {
            MyAsyncTask asyncTask = new MyAsyncTask();
            asyncTask.execute(getString(R.string.downloadCar));
        } else {
            MyAsyncTask asyncTask = new MyAsyncTask();
            asyncTask.execute(getString(R.string.downloadPizza));
        }
    }

    private class MyAsyncTask extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
            progressBar.setProgress(50);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                Thread.sleep(5000);
                URL ImageUrl = new URL(strings[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) ImageUrl.openConnection();

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    InputStream inputStream = urlConnection.getInputStream();

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (imageView != null) {
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}