package com.mamezou.android.example.gmap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class FujisanOverlay extends Overlay {

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		if (shadow) {
			// 富士山の山頂の位置
			GeoPoint p = new GeoPoint(35360833, 138727500);

			// 緯度、経度から、画面上の位置を取得する
			Point pos = mapView.getProjection().toPixels(p, null);

			// 影なので、少しズラす
			pos.x += 2;
			pos.y += 2;

			Paint paint;
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setARGB(255, 0, 0, 0);

			// 影の描画
			// 取得した画面上の位置に、描画を行う
			Rect rect = new Rect(pos.x - 5, pos.y - 5, pos.x + 5, pos.y + 5);
			canvas.drawRect(rect, paint);
			canvas.drawText("富士山です", pos.x + 3, pos.y, paint);
		} else {
			// 富士山の山頂の位置
			GeoPoint p = new GeoPoint(35360833, 138727500);

			// 緯度、経度から、画面上の位置を取得する
			Point pos = mapView.getProjection().toPixels(p, null);

			Paint paint;
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setARGB(255, 0, 255, 0);
			// 本体の描画
			// 取得した画面上の位置に、描画を行う
			Rect rect = new Rect(pos.x - 5, pos.y - 5, pos.x + 5, pos.y + 5);
			canvas.drawRect(rect, paint);
			canvas.drawText("富士山です", pos.x + 3, pos.y, paint);
		}
		super.draw(canvas, mapView, shadow);
	}

	@Override
	public boolean onTap(GeoPoint p, MapView mapView) {
		Log.d("FujisanOverlay", "point = " + p);
		return true;
	}
}
