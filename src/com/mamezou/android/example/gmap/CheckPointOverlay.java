package com.mamezou.android.example.gmap;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class CheckPointOverlay extends ItemizedOverlay<OverlayItem>{

	private List<OverlayItem> items = new ArrayList<OverlayItem>();

	private OverlayItem selectedItem;

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
		selectedItem = null;
		populate();
	}

	@Override
	protected boolean onTap(int index) {
		selectedItem = items.get(index);
		return false;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// CheckPointOverlayのマーカ描画
		super.draw(canvas, mapView, shadow);

		// タップされた点だけテキストを描画
		if (!shadow && selectedItem != null) {
			GeoPoint p = selectedItem.getPoint();
			Point pos = mapView.getProjection().toPixels(p, null);

			Paint paint;
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setARGB(255, 255, 0, 0);

			// 取得した画面上の位置に、描画を行う
			String title = selectedItem.getTitle();
			canvas.drawText(title, pos.x, pos.y + 20, paint);
			
			String snipet = selectedItem.getSnippet();
			canvas.drawText(snipet, pos.x, pos.y + 33, paint);
		}
	}
}
