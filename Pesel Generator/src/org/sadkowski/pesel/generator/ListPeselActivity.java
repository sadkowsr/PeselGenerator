package org.sadkowski.pesel.generator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListPeselActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pesel);
        
        
        String[] elementy_listy = { "W³asna lista", "",""};
        ListView prosta_lista = (ListView) findViewById(R.id.listView1);
         
      
		ArrayAdapter<String> adapter_listy = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, elementy_listy);
        prosta_lista.setAdapter(adapter_listy);
    
        prosta_lista.setOnItemClickListener(new OnItemClickListener() {
        	 
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                long arg3) {
     
            	
            /*switch (pos) {
            case 0:
                Intent startActivityCustomList = new Intent(View_Main.this,
                    View_Custom_List.class);
                startActivity(startActivityCustomList);
                break;
            }*/
     
            }

	
     
        });
    
    
    
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_pesel, menu);
        return true;
    }
}
