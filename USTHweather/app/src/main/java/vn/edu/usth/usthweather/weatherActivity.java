package vn.edu.usth.usthweather;

import static android.app.PendingIntent.getActivity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {
    private ViewpagerAdapter viewpagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.mono);
//        mediaPlayer.start();

        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        // Create a new Fragment to be placed in the activity layout
//        ForecastFragment firstFragment = new ForecastFragment();
//        // Add the fragment to the 'container' FrameLayout
//        getSupportFragmentManager().beginTransaction().add(
//                R.id.main, firstFragment).commit();

//        WeatherFragment secondFragment = new WeatherFragment();
//        getSupportFragmentManager().beginTransaction().add(
//                R.id.main, secondFragment).commit();

//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;

        viewPager = findViewById(R.id.Viewpager);
        viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewpagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView refreshButton = toolbar.findViewById(R.id.refreshButton);

        class MyTask extends AsyncTask<String, Integer, Bitmap> {

            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }

            protected void onPreExecute() {
// do some preparation here, if needed
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
// This method is called in the main thread, so it's possible
// to update UI to reflect the worker thread progress here.
// In a network access task, this should update a progress bar
// to reflect how many percent of data has been retrieved
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                Toast.makeText(refreshButton.getContext(), "refreshed", Toast.LENGTH_SHORT).show();
// This method is called in the main thread. After #doInBackground returns
// the bitmap data, we simply set it to an ImageView using ImageView.setImageBitmap()
            }
        }
        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String content = msg.getData().getString("server_response");
                Toast.makeText(refreshButton.getContext(), content, Toast.LENGTH_SHORT).show();
            }
        };

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
// this method is run in a worker thread
                // wait for 5 seconds to simulate a long network access
                MyTask task = new MyTask();
                task.execute("http://ict.usth.edu.vn/wp-content/uploads/usth/usthlogo.png");
                // Assume that we got our data from server
                Bundle bundle = new Bundle();
                bundle.putString("server_response", "Refreshed");
// notify main thread
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        });
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Toast.makeText(toolbar.getContext(), "Button clicked", Toast.LENGTH_SHORT).show();
                t.start();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart","onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume","onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStop","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","onDestroy");
    }
}