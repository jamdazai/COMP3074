/*
* Lab Exercise 2 for our
* Week 3 Lab in Mobile Application Development
* @author: James Furaque
* */
package ca.gbc.comp3074.lab3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btn_activity, btn_map, btn_browser, btn_alarm, btn_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // BUTTON FOR ACTIVITY
        btn_activity = findViewById(R.id.btn_activity);
        btn_activity.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v){
                openActivity();
            }
        });

        // BUTTON FOR MAP
        btn_map = findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v){
                openMap();
            }
        });

        // BUTTON FOR BROWSER
        btn_browser = findViewById(R.id.btn_browser);
        btn_browser.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v){
                openBrowser();
            }
        });

        // BUTTON FOR ALARM
        btn_alarm = findViewById(R.id.btn_alarm);
        btn_alarm.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v){
                setAlarm();
            }
        });

        // BUTTON FOR IMAGE
        btn_image = findViewById(R.id.btn_image);
        btn_image.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v){
                captureImage();
            }
        });
    }

    // METHOD FOR ACTIVITY
    private void openActivity(){
        Intent i = new Intent(this, MyActivity2.class);
        startActivity(i);
    }

    // METHOD FOR OPENING MAP
    private void openMap(){
        Uri page = Uri.parse("geo:0,0?q=43.6803674,-79.3685524(Some place in Toronto)");
        Intent i = new Intent(Intent.ACTION_VIEW, page);
        startActivity(i);
    }

    // METHOD FOR OPENING BROWSER
    private void openBrowser(){
        Uri page = Uri.parse("https://georgebrown.ca");
        Intent i = new Intent(Intent.ACTION_VIEW, page);
        startActivity(i);
    }

    // METHOD FOR SETTING THE ALARM
    private void setAlarm(){
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_HOUR, 11);
        i.putExtra(AlarmClock.EXTRA_MINUTES, 12);
        i.putExtra(AlarmClock.EXTRA_MESSAGE, "Time to wake up!");
        i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        startActivity(i);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    // METHOD FOR CAPTURING IMAGE
    private void captureImage(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            ImageView img = findViewById(R.id.imageView);
            Bitmap bitmap = data.getParcelableExtra("data");
            img.setImageBitmap(bitmap);
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
