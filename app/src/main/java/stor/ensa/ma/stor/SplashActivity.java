package stor.ensa.ma.stor;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);

        logo.animate().rotation(160f).setDuration(2000);
        logo.animate().scaleX(0.25f).scaleY(2f).setDuration(3000);
        logo.animate().translationYBy(100f).setDuration(2000);
        logo.animate().alpha(0f).setDuration(8000);
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    Intent intent  = new Intent(SplashActivity.this, ListActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
