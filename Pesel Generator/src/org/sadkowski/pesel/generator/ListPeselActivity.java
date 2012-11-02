package org.sadkowski.pesel.generator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ListPeselActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pesel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_pesel, menu);
        return true;
    }
}
