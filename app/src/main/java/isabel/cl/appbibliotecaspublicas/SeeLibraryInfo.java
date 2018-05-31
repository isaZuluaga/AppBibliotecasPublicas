package isabel.cl.appbibliotecaspublicas;

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

public class SeeLibraryInfo extends AppCompatActivity {

    Spinner comboLibrary;
    TextView libraryName,libraryTel,libraryDir,libraryId,libraryCommune,libraryNeighborhood,librarySchedule;
    ArrayList<String> listLibrary;
    ArrayList<Library> libraryList;

    SQliteConnectionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_library_info);

        conn=new SQliteConnectionHelper(getApplicationContext(),"bd_libraries",null,1);

        comboLibrary= (Spinner) findViewById(R.id.comboLibrary);

        libraryId= (TextView) findViewById(R.id.libraryId);
        libraryName = (TextView) findViewById(R.id.libraryNAME);
        libraryTel= (TextView) findViewById(R.id.libraryTEL);
        libraryDir= (TextView) findViewById(R.id.libraryDir);
        libraryCommune= (TextView) findViewById(R.id.libraryCommune);
        libraryNeighborhood=(TextView) findViewById(R.id.libraryNeighborhood);
        librarySchedule=(TextView) findViewById(R.id.librarySchedule);



        consultLibrarylist();


        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listLibrary);

        comboLibrary.setAdapter(adaptador);

        comboLibrary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position!=0){
                    String nombre = Integer.toString(libraryList.get(position-1).getId());
                    libraryId.setText(nombre);
                    libraryName.setText(libraryList.get(position-1).getNombre());
                    libraryTel.setText(libraryList.get(position-1).getTelefono());
                    libraryDir.setText(libraryList.get(position-1).getDireccion());
                    libraryCommune.setText(libraryList.get(position-1).getComuna());
                    libraryNeighborhood.setText(libraryList.get(position-1).getBarrio());
                    librarySchedule.setText(libraryList.get(position-1).getHorario());

                }else{
                    libraryId.setText("");
                    libraryName.setText("");
                    libraryTel.setText("");
                    libraryDir.setText("");
                    libraryCommune.setText("");
                    libraryNeighborhood.setText("");
                    librarySchedule.setText("");

                }

                Toast.makeText(parent.getContext(), "Seleccionado: "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();



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
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilities.LIBRARY_TABLE,null);

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
        listLibrary=new ArrayList<String>();
        listLibrary.add("seleccione una biblioteca");

        for (int i=0;i<libraryList.size();i++){
            listLibrary.add(libraryList.get(i).getId()+" - "+libraryList.get(i).getNombre());
        }
    }


}
