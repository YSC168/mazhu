package code.skit.vip;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.graphics.PixelFormat;
import android.view.WindowManager;
import android.content.Intent;

public class logo extends AppCompatActivity {


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
       // getWindow().setFormat(PixelFormat.RGBA_8888);
  //      getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		
        setContentView(R.layout.logo);

        //Display the current version number


        new Handler().postDelayed(new Runnable() {
				public void run() {
					/* Create an Intent that will start the Main WordPress Activity. */
					Intent mainIntent = new Intent(logo.this, MainActivity.class);
					startActivity(mainIntent);
					finish();
				}
			}, 2900); //2900 for release

    }
}


