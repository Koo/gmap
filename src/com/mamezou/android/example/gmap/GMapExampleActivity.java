package com.mamezou.android.example.gmap;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class GMapExampleActivity extends MapActivity {
	private static final int VIEW_GROUP_ID = 1;

	private static final int SHOW_POINT_ID = 1;
	private static final int ZOOM_UP_ID = 2;
	private static final int ZOOM_DOWN_ID = 3;
	private static final int GOTO_FUJISAN_ID = 4;
	private static final int DISPLAY_FUJISAN_OVERLAY = 5;
	private static final int HIDE_FUJISAN_OVERLAY = 6;

	private MapView mapView;
	// private OverlayController overlayController;
	private Overlay fujisanOverlay;
	private TextView positionTextView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mapView = (MapView) findViewById(R.id.map);
		positionTextView = (TextView) findViewById(R.id.positionTextView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(VIEW_GROUP_ID, SHOW_POINT_ID, 0, R.string.show_point);
		menu.add(VIEW_GROUP_ID, ZOOM_UP_ID, 1, R.string.zoom_up);
		menu.add(VIEW_GROUP_ID, ZOOM_DOWN_ID, 2, R.string.zoom_down);
		menu.add(VIEW_GROUP_ID, GOTO_FUJISAN_ID, 3, R.string.goto_fujisan);
		menu.add(VIEW_GROUP_ID, DISPLAY_FUJISAN_OVERLAY, 4,
				R.string.display_fujisan);
		menu.add(VIEW_GROUP_ID, HIDE_FUJISAN_OVERLAY, 5, R.string.hide_fujisan);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		switch (item.getItemId()) {
		case SHOW_POINT_ID:
			showPoint();
			break;
		case ZOOM_UP_ID:
			zoomIn();
			break;
		case ZOOM_DOWN_ID:
			zoomOut();
			break;
		case GOTO_FUJISAN_ID:
			gotoFujisan();
			break;
		case DISPLAY_FUJISAN_OVERLAY:
			displayFujisanLabel();
			break;
		case HIDE_FUJISAN_OVERLAY:
			hideFujisanLabel();
			break;
		}
		return true;
	}

	private void displayFujisanLabel() {
		// TODO 実装
//		overlayController.add(fujisanOverlay, true);
		mapView.invalidate();
	}

	private void hideFujisanLabel() {
		// TODO 実装
//		overlayController.clear();
		mapView.invalidate();
	}

	private void gotoFujisan() {
		// 北緯35度21分39秒
		double latitude = 35.0d + 21.0d / 60 + 39.0d / 3600;
		// 東経138度43分39秒
		double longitude = 138.0d + 43.0d / 60 + 39.0d / 3600;
		int latitudeE6 = (int) (latitude * 1E6);
		int longitudeE6 = (int) (longitude * 1E6);
		mapView.getController().animateTo(new GeoPoint(latitudeE6, longitudeE6));
	}

	private static final int ZOOM_STEP = 1;

	private void zoomIn() {
		int currentZoomLevel = mapView.getZoomLevel();
		mapView.getController().setZoom(currentZoomLevel + ZOOM_STEP);
//		mapView.getController().zoomIn();
	}

	private void zoomOut() {
		int currentZoomLevel = mapView.getZoomLevel();
		mapView.getController().setZoom(currentZoomLevel - ZOOM_STEP);
//		mapView.getController().zoomOut();
	}

	private void showPoint() {
		StringBuilder sb = new StringBuilder();
		GeoPoint p = mapView.getMapCenter();
		sb.append("緯度 = ");
		sb.append(((double) p.getLatitudeE6()) / 1E6);
		sb.append("   ");
		sb.append("経度 = ");
		sb.append(((double) p.getLongitudeE6()) / 1E6);
		positionTextView.setText(sb.toString());
	}

//	private class FujisanOverlay extends Overlay {
//
//		@Override
//		public void draw(Canvas canvas, PixelCalculator calculator,
//				boolean shadow) {
//
//			if (shadow) {
//				// 富士山の山頂の位置
//				Point p = new Point(35360833, 138727500);
//
//				// 緯度、経度から、画面上の位置を取得する
//				int[] pos = new int[2];
//				calculator.getPointXY(p, pos);
//				// 影の分描画位置をずらす
//				pos[0] += 2;
//				pos[1] += 2;
//
//				Paint paint;
//				paint = new Paint();
//				paint.setAntiAlias(true);
//				paint.setARGB(255, 0, 0, 0);
//
//				// 影の描画
//				// 取得した画面上の位置に、描画を行う
//				Rect rect = new Rect(pos[0] - 5, pos[1] - 5, pos[0] + 5,
//						pos[1] + 5);
//				canvas.drawRect(rect, paint);
//				canvas.drawText("富士山です", pos[0] + 3, pos[1], paint);
//			} else {
//				// 富士山の山頂の位置
//				Point p = new Point(35360833, 138727500);
//
//				// 緯度、経度から、画面上の位置を取得する
//				int[] pos = new int[2];
//				calculator.getPointXY(p, pos);
//
//				Paint paint;
//				paint = new Paint();
//				paint.setAntiAlias(true);
//				paint.setARGB(255, 0, 255, 0);
//				// 本体の描画
//				// 取得した画面上の位置に、描画を行う
//				Rect rect = new Rect(pos[0] - 5, pos[1] - 5, pos[0] + 5,
//						pos[1] + 5);
//				canvas.drawRect(rect, paint);
//				canvas.drawText("富士山です", pos[0] + 3, pos[1], paint);
//			}
//		}
//	}

	@Override
	protected boolean isRouteDisplayed() {
		return true;
	}
}