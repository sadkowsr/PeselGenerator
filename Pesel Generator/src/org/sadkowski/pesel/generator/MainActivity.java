package org.sadkowski.pesel.generator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static String NAME = MainActivity.class.getName();
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	OnClickListener onClicklistener = new OnClickListener(){  		
    		
            public void onClick(View v){
                /*Blokuje przycisk */
            	/*Otwieram okno dialogowe które coś liczy */
            	/*Pobieram wartości */
            	/* Wykonuję funkcję */
            	/* Zwracam wartość */
            	/* Otwieram nową Intecję z klasą przekazując jej wartości*/
            }};
  
            OnSeekBarChangeListener onSeekBarChangeListener = new OnSeekBarChangeListener(){
            	SeekBar sb;
            	TextView tv;
			
            	public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
		                tv.setText(String.valueOf(sb.getProgress()+1));
				}

				public void onStartTrackingTouch(SeekBar seekBar) {	
					sb = (SeekBar)findViewById(R.id.seekBar1);
	                tv = (TextView)findViewById(R.id.textView4);                
				}

				public void onStopTrackingTouch(SeekBar seekBar) {
					
				}
            };
            
            
            /*START*/
    	super.onCreate(savedInstanceState);       
    	setContentView(R.layout.aaaa);
    	
    	TextView tv = (TextView)findViewById(R.id.textView4);
        tv.setText(new String("1"));
     
       Button button = (Button)findViewById(R.id.button1);        
       button.setOnClickListener(onClicklistener);

       SeekBar sb = (SeekBar)findViewById(R.id.seekBar1);
       sb.setOnSeekBarChangeListener(onSeekBarChangeListener);
       
       DatePicker dp = (DatePicker)findViewById(R.id.datePicker1);
       dp.updateDate(dp.getYear()-18, dp.getMonth(), dp.getDayOfMonth());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void clicked(View v){
			Log.d(NAME,"TEST");
			new PeselAsync(1987,7,14,PeselAsync.MEZCZYZNA,0,1000).execute("");
    }
    
       
    private class PeselAsync extends AsyncTask<String, Integer, String> {
    	EditText et;
    	Button button1;
    	
    	int year, mounth, day;
    	boolean plec;
    	int poczatek,ilosc;
    
    	PeselAsync(int year, int mounth, int day, boolean plec, int poczatek, int ilosc){
    	this.year=year;
    	this.mounth=mounth;
    	this.day=day;
    	this.plec=plec;
    	this.poczatek=poczatek;
    	this.ilosc=ilosc;
    }
    	
    	@SuppressWarnings("unused")
		public static final boolean KOBIETA = true;
    	public static final boolean MEZCZYZNA = false;
    	private static final int PESEL_LENGHT = 11;
    	String[] pesels;
    	//public final String NAMES = Pesel.class.getName();
    	
    	
   	 @Override
   	 protected String doInBackground(String... params) {
    		long start = SystemClock.elapsedRealtime();//System.nanoTime();
			Log.d("Policzyłem:","Start: "+start);
			
			pesels = new String[ilosc];
	int[] peselCurrent = new int[PESEL_LENGHT];
	char[] peselPrint = new char[PESEL_LENGHT];
	int r;
	ilosc *= 2;
	poczatek*=2;
	int plecNr = 0;
	if (plec == Pesel.MEZCZYZNA)
		plecNr++;
	int validMonthNr;
	int validNumber;
	peselCurrent[0] = year / 10 % 10;
	peselCurrent[1] = year % 10;

	if (year < 1800) {
		System.err.println("The date is before 1800. Please enter older date");
		//throw new Exception(
			//	"The date is before 1800. Please enter older date");

	} else if (year < 1900) {
		peselCurrent[2] = mounth / 10 + 8;

	} else if (year < 2000) {
		peselCurrent[2] = mounth / 10;
	} else if (year < 2100) {
		peselCurrent[2] = mounth / 10 + 2;
	} else if (year < 2200) {
		peselCurrent[2] = mounth / 10 + 4;
	} else if (year < 2300) {
		peselCurrent[2] = mounth / 10 + 6;
	} else {
		System.err.println("The date is after 2300. Please enter earlier date");
//		throw new Exception(
	//			"The date is after 2300. Please enter earlier date");
	}
	peselCurrent[3] = mounth % 10;
	peselCurrent[4] = day / 10;
	peselCurrent[5] = day % 10;

	//generate a validNumber
	validMonthNr = peselCurrent[0] + peselCurrent[4] + 3 * (peselCurrent[1] + peselCurrent[5]) + 7 * peselCurrent[2] + 9 * peselCurrent[3];	
	for(int l=0;l<6;l++){
		peselPrint[l]=(char)(peselCurrent[l]+48);		
	}
	
	for (int i = poczatek + plecNr; i < ilosc; i = i + 2) {
		peselCurrent[6] = i / 1000;
		r = i % 1000;
		peselCurrent[7] = r / 100;
		r = r % 100;
		peselCurrent[8] = r / 10;
		peselCurrent[9] = r % 10;
			
		validNumber =  peselCurrent[8] + 3	* peselCurrent[9] + 7 * peselCurrent[6]	+ 9 * peselCurrent[7] + validMonthNr;
		validNumber %= 10;
		if (validNumber == 0)
			peselPrint[10] = 48;
		else
			peselPrint[10] = (char)(/*10-valid+48)*/58 - validNumber); 
		//end generate a valideNumber
		
		for(int l=6;l<10;l++){
			peselPrint[l]=(char)(peselCurrent[l]+48);
		}
		this.pesels[(i - plecNr)/2] = String.valueOf(peselPrint);
		publishProgress((int)(1+100*i/(ilosc-poczatek)));
	}
	long end = //System.nanoTime();
	 SystemClock.elapsedRealtime();
	Log.d("Policzyłem","Koniec: "+end);
   	  return ""/*null*/;
   	 }
   	 
   	@SuppressWarnings("unused")
	public String[] getPesels() {
		return pesels;
	}
   	
   	 @Override
   	 protected void onPostExecute(String result) {
   	  // Tutaj mozesz zaimplenentowac czynności, które powinny zostać zrealizowane po zakoczeniu operacji 		
	//et.setText(getPesels()[0]);
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
   		
   		TextView tv=(TextView)findViewById(R.id.textView3);
   		tv.setText("PPPPP");
   	 }
   	 
   	 @Override
   	 protected void onProgressUpdate(Integer... progress) {
   		 et.setText("Pracuję:"+progress[0].toString()+"%");
   			  }
   	}    
}
