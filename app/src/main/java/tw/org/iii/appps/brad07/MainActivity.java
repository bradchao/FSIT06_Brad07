package tw.org.iii.appps.brad07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        timer.schedule(new MyTask(), 0, 1000);
    }

    public void test1(View view) {
        new Thread(){
            @Override
            public void run() {
                for (int i=0; i<20; i++){
                    Log.v("brad", "i = " + i);
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){

                    }
                }
            }
        }.start();

    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            Log.v("brad", "k = " + k++);
        }
    }

}
