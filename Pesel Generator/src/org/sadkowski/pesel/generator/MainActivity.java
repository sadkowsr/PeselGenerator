package org.sadkowski.pesel.generator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	private static String NAME = MainActivity.class.getName();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void clicked(View v){
    	try {
			Log.d(NAME,"TEST");
			new WymagajacyWatek().execute("");
			//Pesel.main(null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return;
    }
    
    private class WymagajacyWatek extends AsyncTask<String, Integer, String> {
    	Pesel pesel;
    	EditText et;
    	Button button1;
    	int rok,miesiac,dzien,ilosc;
    	Toast toast;
    	
   	 @Override
   	 protected String doInBackground(String... params) {
   		pesel = new Pesel();
  		try{
   		pesel.generatePesels(1987, 07, 14, false, 0, 5000);
   		
   		publishProgress(100);
   		}
   		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
   		
   		}
   	  return null;
   	 }
   	 
   	 @Override
   	 protected void onPostExecute(String result) {
   	  // Tutaj mozesz zaimplenentowac czynności, które powinny zostać zrealizowane po zakoczeniu operacji
   		
	et.setText(pesel.getPesels()[0]);
   	button1.setEnabled(true);
	et.setEnabled(true);
   	 }
   	 
   	 @Override
   	 protected void onPreExecute() {
   	  // Analogicznie do metody onPostExecute, implenentujesz czynnosci do zrealizowania przed uruchomieniem wątku
   		et = (EditText)findViewById(R.id.editText1);
   		button1 = (Button)findViewById(R.id.button1);
   		
   		et.setEnabled(false);
   		button1.setEnabled(false);
   	 }
   	 
   	 @Override
   	 protected void onProgressUpdate(Integer... progress) {
   		
   		 //et.setText("Pracuję:"+progress[0].toString()+"%");
   		toast = Toast.makeText(getApplicationContext(),Integer.toString(progress[0]),Toast.LENGTH_SHORT);
        toast.show();
   	  // Natomiast metoda onProgressUpdate umozliwia aktualizwanie watku głównej podczas działana naszego WymagającegoWatku
   	  }
   	}
   	 

    
}
