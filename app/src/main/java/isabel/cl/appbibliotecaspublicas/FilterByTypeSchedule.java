package isabel.cl.appbibliotecaspublicas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import isabel.cl.appbibliotecaspublicas.entity.Library;
import isabel.cl.appbibliotecaspublicas.utilities.Utilities;

public class FilterByTypeSchedule extends AppCompatActivity {

    Spinner comboType;
    Spinner comboSchedule;
    ArrayList<Library> libraryList;
    ArrayList<String> typeList;
    ArrayList<String> scheduleList;
    //TextView paramType;


    SQliteConnectionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_by_type_schedule);

        conn=new SQliteConnectionHelper(getApplicationContext(),"bd_libraries",null,1);

        comboType= (Spinner) findViewById(R.id.comboType);
        comboSchedule= (Spinner) findViewById(R.id.comboSchedule);
        //paramType= (TextView) findViewById(R.id.paramTipo);


        consultLibrarylist();

        ArrayAdapter<CharSequence> adaptadortipo=new ArrayAdapter(this,android.R.layout.simple_spinner_item,typeList);

        ArrayAdapter<CharSequence> adaptadorhorario=new ArrayAdapter(this,android.R.layout.simple_spinner_item,scheduleList);

        comboSchedule.setAdapter(adaptadorhorario);

        comboType.setAdapter(adaptadortipo);

        comboType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){

                    String tipo =""+libraryList.get(position-1).getTipo();

                    Intent intention = new Intent(FilterByTypeSchedule.this, LibraryInformationInterface.class);

                    //Bundle bundle = new Bundle();
                    //bundle.putString("tipobiblioteca",tipo);
                    intention.putExtra("tipobiblioteca",tipo);
                    startActivity(intention);


                }else{
                    //paramType.setText("");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        comboSchedule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position!=0){

                    String schedule =""+libraryList.get(position-1).getHorario();

                    Intent intention = new Intent(FilterByTypeSchedule.this, LibraryInformationInterface.class);

                    intention.putExtra("horariobiblioteca",schedule);
                    startActivity(intention);


                }else{
                    //paramType.setText("");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void consultLibrarylist() {

        SQLiteDatabase db=conn.getReadableDatabase();

        Library library = null;
        //crea instancia a la lista librarylist
        libraryList=new ArrayList<Library>();

        //select * from libraries
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilities.LIBRARY_TABLE,null);

        while (cursor.moveToNext()){
            library=new Library();
            library.setId(cursor.getInt(0));
            library.setNombre(cursor.getString(1));
            library.setTelefono(cursor.getString(2));
            library.setDireccion(cursor.getString(3));
            library.setCorreo(cursor.getString(4));
            library.setLink(cursor.getString(5));
            library.setTipo(cursor.getString(6));
            library.setHorario(cursor.getString(7));
            library.setBarrio(cursor.getString(8));
            library.setComuna(cursor.getString(9));




            libraryList.add(library);
        }

        obtainList();
    }

    private void obtainList() {
        typeList=new ArrayList<String>();
        scheduleList=new ArrayList<String>();
        typeList.add("seleccione un tipo");
        scheduleList.add("seleccione un horario");

        for (int i=0;i<libraryList.size();i++){
            typeList.add(libraryList.get(i).getTipo());
            scheduleList.add(libraryList.get(i).getHorario());
        }

    }

    void doExit (View v){

        Intent intention = new Intent(this, FilterOptionsInterface.class);
        startActivity(intention);

    }
}
