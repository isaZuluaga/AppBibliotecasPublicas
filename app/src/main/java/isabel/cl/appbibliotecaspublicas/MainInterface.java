package isabel.cl.appbibliotecaspublicas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainInterface extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
    }

    void goOptions (View v)
    {
        Intent intention = new Intent(this, OptionsInterface.class);
        startActivity(intention);

    }
}
