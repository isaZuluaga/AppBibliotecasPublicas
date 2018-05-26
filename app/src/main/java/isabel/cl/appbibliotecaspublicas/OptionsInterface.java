package isabel.cl.appbibliotecaspublicas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OptionsInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_interface);
    }

    void goLibraryMap (View v)
    {
        Intent intention = new Intent(this, LibraryMapsActivity.class);
        startActivity(intention);

    }
}
