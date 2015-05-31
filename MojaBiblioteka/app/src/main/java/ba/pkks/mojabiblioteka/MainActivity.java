package ba.pkks.mojabiblioteka;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Date;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView m_listview;
    private ArrayList<Knjiga> knjige;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_listview = (ListView) findViewById(R.id.id_list_view);

        knjige = new ArrayList<Knjiga>();

        dbHelper db = new dbHelper(this);
        knjige = db.getKnjige();

        ArrayAdapter<Knjiga> adapter = new ArrayAdapter<Knjiga>(this, android.R.layout.simple_list_item_1, (ArrayList)knjige);

        m_listview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void pozoviSearch(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void pozoviAdd(){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_search) {
            pozoviSearch();
            return true;
        }

        if (id == R.id.action_add){
            pozoviAdd();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
