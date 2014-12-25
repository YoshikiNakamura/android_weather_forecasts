package io.keiji.weatherforecasts;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;



public class MyActivity extends Activity {

    private TextView textView;

    private class GetWeatherForecastTask extends GetWeatherForecastApiTask {

        public GetWeatherForecastTask(Context context) {
            super(context);
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            if(data != null) {
                textView.setText(data);
            } else if (exception != null) {
                Toast.makeText(MyActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);

        textView = (TextView) findViewById(R.id.tv_main);   //idがtv_mainのTextViewオブジェクトを作る。

        new GetWeatherForecastTask(this).execute("400040");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}