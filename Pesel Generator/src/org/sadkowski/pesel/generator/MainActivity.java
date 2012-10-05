package org.sadkowski.pesel.generator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	ProgressDialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
       	
    	OnClickListener onClicklistener = new OnClickListener(){  		
    		
            public void onClick(View v){    
            	/*Blokuje przycisk */
            	((Button)(findViewById(R.id.button1))).setEnabled(false);; 
            	
            	SeekBar sb = (SeekBar)findViewById(R.id.seekBar1);
            	DatePicker dp = (DatePicker)findViewById(R.id.datePicker1);
            	Spinner s = (Spinner)findViewById(R.id.spinner1);
            	s.getSelectedItemId();
            	/*Otwieram okno dialogowe które coś liczy */
            	dialog = new ProgressDialog(MainActivity.this);
           		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
           		dialog.setMax(sb.getProgress()+1);  
            	   
            	PeselAsync pa = new PeselAsync(dp.getYear(),dp.getMonth(),dp.getDayOfMonth(),PeselAsync.MEZCZYZNA,0,sb.getProgress()+1);
            	pa.execute("");
            	/*Pobieram wartości */
            	/* Wykonuję funkcję */
            	/* Zwracam wartość */
            	/*Odblokuje przycisk */
            	((Button)(findViewById(R.id.button1))).setEnabled(true);; 
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
          
    private class PeselAsync extends AsyncTask<String, Integer, String> {
    	    	
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
      		
    	@Override
     	 protected void onPreExecute() {
     	  // Analogicznie do metody onPostExecute, implenentujesz czynnosci do zrealizowania przed uruchomieniem wątku
    		
     		dialog.show();
    	}
    	
   	 @Override
   	 protected String doInBackground(String... params) {
	int coIleOdswiezac = 1+this.ilosc/100;
   		 
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
		
		
		if(i%coIleOdswiezac==0){
		publishProgress(coIleOdswiezac);
				
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
	dialog.dismiss();
   	  return ""/*null*/;
   	 }
   	 
   	@SuppressWarnings("unused")
	public String[] getPesels() {
		return pesels;
	}
   	
       
   	 @Override
   	 protected void onPostExecute(String result) {
   	  // Tutaj mozesz zaimplenentowac czynności, które powinny zostać zrealizowane po zakoczeniu operacji 		
   		//button.setEnabled(true);   		
   	 }
   	 
   	 @Override
   	 protected void onProgressUpdate(Integer... progress) {
   		 dialog.incrementProgressBy(progress[0]);
   		 }
   	}    
}