package vn.edu.usth.usthweather;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        // Create a new Fragment to be placed in the activity layout
        ForecastFragment firstFragment = new ForecastFragment();
        // Add the fragment to the 'container' FrameLayout
        getSupportFragmentManager().beginTransaction().add(
                R.id.main, firstFragment).commit();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onstart","onstart");
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