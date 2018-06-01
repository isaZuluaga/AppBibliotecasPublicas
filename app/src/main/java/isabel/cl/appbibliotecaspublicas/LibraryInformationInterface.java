package isabel.cl.appbibliotecaspublicas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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

public class LibraryInformationInterface extends AppCompatActivity {

    Spinner comboFilterResult;
    ArrayList<Library> libraryList;
    ArrayList<String> resultList;
    TextView param1Receiver,param2Receiver,libraryID,libraryNAME,libraryTEL,libraryADD,libraryMAIL,libraryLINK;
    Bundle libraryType,librarySchedule,libraryCommune,libraryNeighborhood;





    SQliteConnectionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_information_interface);

        conn=new SQliteConnectionHelper(getApplicationContext(),"bd_libraries",null,1);

        comboFilterResult = (Spinner) findViewById(R.id.comboFilterResult);

        libraryID = (TextView) findViewById(R.id.libraryID);
        libraryADD= (TextView) findViewById(R.id.libraryADD);
        libraryTEL = (TextView) findViewById(R.id.libraryTEL);
        libraryNAME = (TextView) findViewById(R.id.libraryNAME);
        libraryMAIL = (TextView) findViewById(R.id.libraryMAIL);
        libraryLINK = (TextView) findViewById(R.id.libraryLINK);

        param1Receiver = (TextView) findViewById(R.id.typeReceiver);
        param2Receiver = (TextView) findViewById(R.id.scheduleReceiver);


        libraryType = getIntent().getExtras();
        librarySchedule=getIntent().getExtras();

        libraryCommune=getIntent().getExtras();
        libraryNeighborhood=getIntent().getExtras();

        //ponemos los campos de los parametros vacios
        param1Receiver.setText("");
        param2Receiver.setText("");


        String typeParameter=null;
                typeParameter= libraryType.getString("tipobiblioteca");


        String scheduleParameter=null;
                scheduleParameter= librarySchedule.getString("horariobiblioteca");


        String communeParameter=null;
        communeParameter= libraryCommune.getString("comunabiblioteca");


        String neighborhoodParameter=null;
        neighborhoodParameter= libraryNeighborhood.getString("barriobiblioteca");



        if(scheduleParameter!=null) {
            param2Receiver.setText(scheduleParameter);
            consultLibrarylistBySchedule();
        }
        if(typeParameter!=null){
            param1Receiver.setText(typeParameter);
        consultLibrarylistByType();
        }
        if(communeParameter!=null){
            param1Receiver.setText(communeParameter);
            consultLibrarylistByCommune();
        }
        if (neighborhoodParameter!=null){
            param2Receiver.setText(neighborhoodParameter);
            consultLibrarylistByNeighborhood();
        }

        ArrayAdapter<CharSequence> adaptadorFiltro=new ArrayAdapter(this,android.R.layout.simple_spinner_item,resultList);
        comboFilterResult.setAdapter(adaptadorFiltro);

        comboFilterResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position!=0){
                    String nombre = Integer.toString(libraryList.get(position-1).getId());
                    libraryID.setText(nombre);
                    libraryNAME.setText(libraryList.get(position-1).getNombre());
                    libraryTEL.setText(libraryList.get(position-1).getTelefono());
                    libraryADD.setText(libraryList.get(position-1).getDireccion());
                    libraryMAIL.setText(libraryList.get(position-1).getCorreo());
                    libraryLINK.setText(libraryList.get(position-1).getLink());



                }else{
                    libraryID.setText("");
                    libraryNAME.setText("");
                    libraryTEL.setText("");
                    libraryADD.setText("");
                    libraryMAIL.setText("");
                    libraryLINK.setText("");



                }

                Toast.makeText(parent.getContext(), "Seleccionado: "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void consultLibrarylistByCommune() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Library library = null;
        //crea instancia a la lista librarylist
        libraryList=new ArrayList<Library>();

        String commune = param1Receiver.getText().toString();


        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilities.LIBRARY_TABLE + " WHERE " + Utilities.COMMUNE_FIELD + "=?", new String[] {commune});


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

    private void consultLibrarylistByNeighborhood() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Library library = null;
        //crea instancia a la lista librarylist
        libraryList=new ArrayList<Library>();

        String neighborhood = param2Receiver.getText().toString();


        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilities.LIBRARY_TABLE + " WHERE " + Utilities.NEIGHBORHOOD_FIELD + "=?", new String[] {neighborhood});


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

    private void consultLibrarylistByType() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Library library = null;
        //crea instancia a la lista librarylist
        libraryList=new ArrayList<Library>();

        String type = param1Receiver.getText().toString();


        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilities.LIBRARY_TABLE + " WHERE " + Utilities.TYPE_FIELD + "=?", new String[] {type});


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

    private void consultLibrarylistBySchedule() {

        SQLiteDatabase db=conn.getReadableDatabase();

        Library library = null;
        //crea instancia a la lista librarylist
        libraryList=new ArrayList<Library>();

        String schedule = param2Receiver.getText().toString();


        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilities.LIBRARY_TABLE + " WHERE " + Utilities.SCHEDULE_FIELD + "=?", new String[] {schedule});


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
        resultList=new ArrayList<String>();
        resultList.add("seleccione una biblioteca del filtro");

        for (int i=0;i<libraryList.size();i++){
            resultList.add(libraryList.get(i).getId()+" - "+libraryList.get(i).getNombre());
        }
    }

    void goLink (View v){

        //String libraryUrl = "http://google.com/";
        String libraryUrl = libraryLINK.getText().toString();

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(libraryUrl));
        startActivity(i);
    }

    void doExit (View v){
        Intent intention = new Intent(this, MainInterface.class);
        startActivity(intention);


    }
    void doMail (View v){


        Intent intention = new Intent(this, SendMailInterface.class);
        startActivity(intention);

    }





}
