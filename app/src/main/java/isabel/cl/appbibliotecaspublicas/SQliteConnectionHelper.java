package isabel.cl.appbibliotecaspublicas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import isabel.cl.appbibliotecaspublicas.utilities.Utilities;

/**
 * Created by Isabel on 28/05/18.
 */
public class SQliteConnectionHelper extends SQLiteOpenHelper {



    public SQliteConnectionHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilities.CREATE_LIBRARY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS libraries");
        onCreate(db);


    }
}