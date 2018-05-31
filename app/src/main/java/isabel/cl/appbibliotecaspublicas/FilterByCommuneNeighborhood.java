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

import java.util.ArrayList;

import isabel.cl.appbibliotecaspublicas.entity.Library;
import isabel.cl.appbibliotecaspublicas.utilities.Utilities;

public class FilterByCommuneNeighborhood extends AppCompatActivity {

    Spinner comboCommune;
    Spinner comboNeighborhood;
    ArrayList<Library> libraryList;
    ArrayList<String> communeList;
    ArrayList<String> neighborhoodList;

    SQliteConnectionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_by_commune_neighborhood);

        conn=new SQliteConnectionHelper(getApplicationContext(),"bd_libraries",null,1);

        comboCommune= (Spinner) findViewById(R.id.comboComune);
        comboNeighborhood= (Spinner) findViewById(R.id.comboNeighborhood);


        consultLibrarylist();

        ArrayAdapter<CharSequence> adaptadorcomuna=new ArrayAdapter(this,android.R.layout.simple_spinner_item,communeList);

        comboCommune.setAdapter(adaptadorcomuna);

        ArrayAdapter<CharSequence> adaptadorbarrio=new ArrayAdapter(this,android.R.layout.simple_spinner_item,neighborhoodList);

        comboNeighborhood.setAdapter(adaptadorbarrio);

        comboCommune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position!=0){

                    String comuna =""+libraryList.get(position-1).getComuna();

                    Intent intention = new Intent(FilterByCommuneNeighborhood.this, LibraryInformationInterface.class);

                    //Bundle bundle = new Bundle();
                    //bundle.putString("tipobiblioteca",tipo);
                    intention.putExtra("comunabiblioteca",comuna);
                    startActivity(intention);


                }else{
                    //paramType.setText("");

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        comboNeighborhood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){

                    String barrio =""+libraryList.get(position-1).getBarrio();

                    Intent intention = new Intent(FilterByCommuneNeighborhood.this, LibraryInformationInterface.class);

                    //Bundle bundle = new Bundle();
                    //bundle.putString("tipobiblioteca",tipo);
                    intention.putExtra("barriobiblioteca",barrio);
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
        communeList=new ArrayList<String>();
        neighborhoodList=new ArrayList<String>();
        communeList.add("seleccione una comuna");
        neighborhoodList.add("seleccione un barrio");

        for (int i=0;i<libraryList.size();i++){
            communeList.add(libraryList.get(i).getComuna());
            neighborhoodList.add(libraryList.get(i).getBarrio());
        }
    }
}
