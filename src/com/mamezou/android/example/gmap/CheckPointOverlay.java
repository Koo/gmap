package com.mamezou.android.example.gmap;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class CheckPointOverlay extends ItemizedOverlay<OverlayItem>{

	private List<OverlayItem> items = new ArrayList<OverlayItem>();

	public CheckPointOverlay(Drawable d) {
		super(d);
		// 初期追加時にエラーが発生しないように、populateを実行
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return items.get(i);
	}

	@Override
	public int size() {
		return items.size();
	}

	public void addPoint(OverlayItem item) {
		items.add(item);
		populate();
	}

	public void clear() {
		items.clear();
		populate();
	}
}
