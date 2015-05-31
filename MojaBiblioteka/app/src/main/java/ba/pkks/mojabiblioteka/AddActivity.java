package ba.pkks.mojabiblioteka;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.sql.Date;


public class AddActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    public static java.sql.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        java.sql.Date datum = new Date(year, month, day);

        return datum;
    }

    public void dodajKnjigu(View view){
        dbHelper dbhelper = new dbHelper(this);
        String naslov = ((EditText)findViewById(R.id.editText_naslov)).getText().toString();
        String autor = ((EditText)findViewById(R.id.editText_autor)).getText().toString();
        String ISBN = ((EditText)findViewById(R.id.editText_ISBN)).getText().toString();
        Date datum = new Date(getDateFromDatePicker((DatePicker) findViewById(R.id.datePicker)).getTime());
        String brojStranica = ((EditText) findViewById(R.id.editText_stranice)).getText().toString();
        int stranice = 0;
        String opis = ((EditText)findViewById(R.id.editText_opis)).getText().toString();

        boolean validacija = true;
        if(naslov.equals("")) {
            ((EditText)findViewById(R.id.editText_naslov)).setError("Unesite naslov!");
            validacija = false;
        }
        else {
            ((EditText)findViewById(R.id.editText_naslov)).setError(null);
            validacija = true;
        }

        if(autor.equals("")) {
            ((EditText)findViewById(R.id.editText_naslov)).setError("Unesite ime autora!");
            validacija = false;
        }
        else {
            ((EditText)findViewById(R.id.editText_autor)).setError(null);
            validacija = true;
        }



        if(brojStranica.equals("")) {
            ((EditText)findViewById(R.id.editText_stranice)).setError("Unesite broj stranica!");
            validacija = false;
        }
        else {
            ((EditText)findViewById(R.id.editText_stranice)).setError(null);
            stranice = Integer.parseInt(brojStranica);
            validacija = true;
        }

        if(opis.equals("")){
            ((EditText)findViewById(R.id.editText_opis)).setError("Unesite naslov!");
            validacija = false;
        }
        else {
            ((EditText)findViewById(R.id.editText_opis)).setError(null);
            validacija = true;
        }

        if(validacija) {
            Knjiga k = new Knjiga(naslov, autor, ISBN, datum, stranice, opis, false);
            dbhelper.insertKnjige(k);
            NavUtils.navigateUpFromSameTask(this);
        }
    }
}
