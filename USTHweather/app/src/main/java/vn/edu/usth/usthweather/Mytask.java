package vn.edu.usth.usthweather;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class Mytask extends AsyncTask<String, Integer, Bitmap> {
    @Override
    protected void onPreExecute() {
// do some preparation here, if needed
    }
    @Override
    protected Bitmap doInBackground(String... params) {
// This is where the worker thread's code is executed
// params are passed from the execute() method call
        return null;
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
// This method is called in the main thread. After #doInBackground returns
// the bitmap data, we simply set it to an ImageView using ImageView.setImageBitmap()
    }
};

