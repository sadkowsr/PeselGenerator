package org.sadkowski.pesel.generator;

//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
import android.app.Activity;
//import android.content.ClipData;
import android.os.Bundle;
//import android.text.ClipboardManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListPeselActivity extends Activity {
	String[] elements;
	 
	  
	
/*
	@SuppressLint("NewApi") private void schowek11(){
		android.content.ClipboardManager clipboard =  (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
		android.content.ClipData clip = android.content.ClipData.newPlainText("label", "Text to Copy");
        clipboard.setPrimaryClip(clip); 
	}
	
	*/
	
	private void copyToClipboard4(String nr){
		android.text.ClipboardManager clipboard = (android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE); 
		clipboard.setText(nr);
		
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_pesel);

		Bundle extras = getIntent().getExtras();
		elements=null; 
		if(extras !=null){
			elements=	extras.getStringArray("pesels");}
		else{
			Log.d("error","Problem with delievered argument between activities");
			return;
		}
		ListView listPesels = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adapter_listy = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, elements);
		listPesels.setAdapter(adapter_listy);
		
		
		//int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		//if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
		//   schowek11();  
		///} else{
		
		//}
	
		
		//Toast.makeText(getApplicationContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show();
		
		
		
		listPesels.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				copyToClipboard4(""+elements[pos]);
				Toast toast = Toast.makeText(getApplicationContext(), 
						"Skopiowano do schowka: "+elements[pos], Toast.LENGTH_LONG);
				toast.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_list_pesel, menu);
		return true;
	}
}