package tw.org.iii.appps.brad07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private int k = 0;
    private TextView mesg;
    private UIHandler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiHandler = new UIHandler();
        mesg = findViewById(R.id.mesg);

        timer = new Timer();
        timer.schedule(new MyTask(), 0, 1000);
    }

    public void test1(View view) {
        new Thread(){
            @Override
            public void run() {
                for (int i=0; i<20; i++){
                    Log.v("brad", "i = " + i);

                    Message message = new Message();
                    Bundle data = new Bundle();
                    data.putInt("i", i);
                    message.setData(data);
                    uiHandler.sendMessage(message);

                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){

                    }
                }
            }
        }.start();

    }


    private class UIHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle data = msg.getData();
            int i = data.getInt("i");
            mesg.setText("i = " + i);
        }
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            Log.v("brad", "k = " + k++);
        }
    }

}
