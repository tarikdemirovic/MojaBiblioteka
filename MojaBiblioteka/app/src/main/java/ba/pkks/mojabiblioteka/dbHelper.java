package ba.pkks.mojabiblioteka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by tarik on 5/30/15.
 */
public class dbHelper extends SQLiteOpenHelper {
    static final String dbName = "knjigeDB";
    static final String tableName = "knjige";
    static final int version = 1;

    //kolone
    static final String colID = "id";
    static final String colNaslov = "naslov";
    static final String colAutor = "autor";
    static final String colISBN = "isbn";
    static final String colDatum = "datum";
    static final String colStranice = "stranice";
    static final String colOpis = "opis";
    static final String colStatus = "status";

    public dbHelper(Context context){
        super(context, dbName, null, version);
    }

    public void onCreate(SQLiteDatabase db){
        //napravi novu
        db.execSQL("CREATE TABLE " + tableName + "(" + colID + " INTEGER PRIMARY KEY, "
                + colNaslov + " TEXT, " + colAutor + " TEXT, " + colISBN + " TEXT, " + colDatum + " INTEGER, "
                + colStranice + " INTEGER, " + colOpis + " TEXT, " + colStatus + " INTEGER)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
        //dropaj i napravi novu
        db.execSQL("DROP TABLE IF EXISTS " + dbName);

        onCreate(db);
    }


    public boolean insertKnjige(Knjiga k){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(colNaslov, k.getNaslov());
        contentValues.put(colAutor, k.getAutor());
        contentValues.put(colISBN, k.getISBN());
        contentValues.put(colDatum, k.getDatumObjave().getTime());
        contentValues.put(colStranice, k.getBrojStranica());
        contentValues.put(colOpis, k.getOpis());
        if(k.isProcitana())
            contentValues.put(colStatus, 1);
        else
            contentValues.put(colStatus, 0);
        db.insert(tableName, null, contentValues);
        return true;
    }

    public ArrayList<Knjiga> getKnjige(){
        ArrayList<Knjiga> knjige = new ArrayList<Knjiga>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + tableName, null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String naslov = res.getString(res.getColumnIndex(colNaslov));
            String autor = res.getString(res.getColumnIndex(colAutor));
            String ISBN = res.getString(res.getColumnIndex(colISBN));
            Date datum = new Date(res.getLong(res.getColumnIndex(colDatum)));
            int stranice = res.getInt(res.getColumnIndex(colStranice));
            String opis = res.getString(res.getColumnIndex(colOpis));
            boolean procitana;
            if(res.getInt(res.getColumnIndex(colStatus)) == 0)
                procitana = false;
            else procitana = true;
            Knjiga k = new Knjiga(naslov, autor, ISBN, datum, stranice, opis, procitana);
            knjige.add(k);
            res.moveToNext();
        }
        return knjige;
    }
}
