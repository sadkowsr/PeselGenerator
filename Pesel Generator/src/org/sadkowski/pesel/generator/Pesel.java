package org.sadkowski.pesel.generator;
import android.util.Log;
import android.os.SystemClock;
/**
 * @author Rafal Sadkowski, rafal.sadkowski@gmail.com, http://sadkowski.org
 * 
 */
public class Pesel {
	
	public static boolean KOBIETA = true;
	public static boolean MEZCZYZNA = false;
	private static int PESEL_LENGHT = 11;
	/**
	 * @param args
	 */
	String[] pesels;
	private static String NAME = Pesel.class.getName();
	public static void main(String[] args) {
		
		Pesel pesel = new Pesel();
		double x=0;
		int z=100;
		double min=1000000000,max=0,tmp=0;
		
			for(int i=0;i<z;i++){
				try {
				tmp=pesel.generatePesels(2010, 07, 14, Pesel.MEZCZYZNA, 5000);
				} catch (Exception e) {
					Log.d("Wystąpił błąd w trakcie wykonywania funkcji generacji nr pesel 'generatePesels:'","Treść błędu: "+e);
				}
			if(tmp>max)
				max=tmp;
			if(tmp<min)
				min=tmp;
			x+=tmp;}
	
		Log.d(NAME,"Srednio:"+x*10/(10*z)+" ms");
		Log.d(NAME,"Minimum:"+min*10/10+" ms");
		Log.d(NAME,"Maksimum"+max*10/10+" ms");
		
	}

	public long generatePesels(int year, int mounth, int day, boolean plec,
			int ilosc) throws Exception {
		long start = SystemClock.elapsedRealtime();//System.nanoTime();
				Log.d("Policzyłem:","Start: "+start);
				
				pesels = new String[ilosc];
		int[] peselCurrent = new int[PESEL_LENGHT];
		char[] peselPrint = new char[PESEL_LENGHT];
		int r;
		ilosc *= 2;
		int plecNr = 0;
		if (plec == Pesel.MEZCZYZNA)
			plecNr++;
		int validMonthNr;
		int validNumber;
		peselCurrent[0] = year / 10 % 10;
		peselCurrent[1] = year % 10;

		if (year < 1800) {
			throw new Exception(
					"The date is before 1800. Please enter older date");

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

			throw new Exception(
					"The date is after 2300. Please enter earlier date");
		}
		peselCurrent[3] = mounth % 10;
		peselCurrent[4] = day / 10;
		peselCurrent[5] = day % 10;

		//generate a validNumber
		validMonthNr = peselCurrent[0] + peselCurrent[4] + 3 * (peselCurrent[1] + peselCurrent[5]) + 7 * peselCurrent[2] + 9 * peselCurrent[3];	
		for(int l=0;l<6;l++){
			peselPrint[l]=(char)(peselCurrent[l]+48);		
		}
		for (int i = 0 + plecNr; i < ilosc; i = i + 2) {
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
		}
		long end = //System.nanoTime();
		 SystemClock.elapsedRealtime();
		Log.d("Policzyłem","Koniec: "+end);
		return (end-start);
	}

	public String[] getPesels() {
		return pesels;
	}

}
