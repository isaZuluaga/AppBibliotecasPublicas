package isabel.cl.appbibliotecaspublicas;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SendMailInterface extends AppCompatActivity {

   ImageButton goGmail;
    TextView libraryMail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail_interface);

        goGmail = (ImageButton) findViewById(R.id.gmailButton);
        libraryMail= (TextView) findViewById(R.id.libraryMail);



    }

    void openGmail (View v){

        PackageManager pm = getPackageManager();
        Intent i = pm.getLaunchIntentForPackage("com.google.android.gm");
        if (i != null){
            startActivity(i);
        }

    }

    void exitMain (View v){

        Intent intention = new Intent(this, MainInterface.class);
        startActivity(intention);

    }
}
