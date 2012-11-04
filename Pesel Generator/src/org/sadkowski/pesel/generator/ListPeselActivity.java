package org.sadkowski.pesel.generator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListPeselActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pesel);
        
        
        String[] elementy_listy = { "W³asna lista", "",""};
        ListView prosta_lista = (ListView) findViewById(R.id.listView1);
         
        @SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayAdapter adapter_listy = new ArrayAdapter(this,
            android.R.layout.simple_list_item_1, elementy_listy);
        prosta_lista.setAdapter(adapter_listy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_pesel, menu);
        return true;
    }
}
