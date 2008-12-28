package com.mamezou.android.example.gmap;

import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class GMapExampleActivity extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return true;
	}
}