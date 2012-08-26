package org.sadkowski.pesel.generator;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	private static String NAME = MainActivity.class.getName();
	Pesel pesel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pesel=new Pesel();
        
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void clicked(View v){
    	try {
			pesel.generatePesels(1987, 07, 14, false, 5000);
			EditText et = (EditText)findViewById(R.id.editText1);
			et.setText(pesel.getPesels()[0]);
			Log.d(NAME,"TEST");
			Pesel.main(null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return;
    }

    
}
