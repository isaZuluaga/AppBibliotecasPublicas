package isabel.cl.appbibliotecaspublicas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FilterOptionsInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_options_interface);
    }

    void goFilterByCB (View v)
    {
        Intent intention = new Intent(this, FilterByCommuneNeighborhood.class);
        startActivity(intention);

    }

    void goFilterByTH (View v)
    {
        Intent intention = new Intent(this, FilterByTypeSchedule.class);
        startActivity(intention);

    }
}
