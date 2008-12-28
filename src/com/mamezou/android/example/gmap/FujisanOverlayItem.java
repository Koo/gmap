package com.mamezou.android.example.gmap;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class FujisanOverlayItem extends OverlayItem {

	public FujisanOverlayItem(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
	}

}
