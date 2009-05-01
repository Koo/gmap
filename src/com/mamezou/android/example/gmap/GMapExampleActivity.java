package com.mamezou.android.example.gmap;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GMapExampleActivity extends MapActivity {
	private static final int VIEW_GROUP_ID = 1;

	private static final int SHOW_POINT_ID = 1;
	private static final int ZOOM_UP_ID = 2;
	private static final int ZOOM_DOWN_ID = 3;
	private static final int GOTO_FUJISAN_ID = 4;
	private static final int DISPLAY_FUJISAN_OVERLAY = 5;
	private static final int HIDE_FUJISAN_OVERLAY = 6;
	private static final int ADD_POINT_ID = 7;
	private static final int ADD_STAR_POINT_ID = 8;
	private static final int CLEAR_POINT_ID = 9;
	private static final int FLIP_SATELITE_ID = 10;
	private static final int FLIP_STREET_ID = 11;
	private static final int FLIP_TRAFFIC_ID = 12;

	private MapView mapView;
	private Overlay fujisanOverlay;
	private TextView positionTextView;
	private CheckPointOverlay checkPointOverlay;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mapView = (MapView) findViewById(R.id.map);
		positionTextView = (TextView) findViewById(R.id.positionTextView);

		fujisanOverlay = new FujisanOverlay();

		// ItemizedOverlayのセットアップ
		Drawable marker = getResources().getDrawable(R.drawable.attention);
		marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());  
		checkPointOverlay = new CheckPointOverlay(marker);
		mapView.getOverlays().add(checkPointOverlay);
		
		mapView.setBuiltInZoomControls(true);

		mapView.invalidate();
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
		menu.add(VIEW_GROUP_ID, ADD_POINT_ID, 6, R.string.add_point);
		menu.add(VIEW_GROUP_ID, ADD_STAR_POINT_ID, 7, R.string.add_star_point);
		menu.add(VIEW_GROUP_ID, CLEAR_POINT_ID, 8, R.string.clear_point);
		menu.add(VIEW_GROUP_ID, FLIP_SATELITE_ID, 9, R.string.flip_satellite);
		menu.add(VIEW_GROUP_ID, FLIP_STREET_ID, 10, R.string.flip_street);
		menu.add(VIEW_GROUP_ID, FLIP_TRAFFIC_ID, 11, R.string.flip_traffic);
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
		case ADD_POINT_ID:
			addPointDefault();
			break;
		case ADD_STAR_POINT_ID:
			addPointStar();
			break;
		case CLEAR_POINT_ID:
			clearPoint();
			break;
		case FLIP_SATELITE_ID:
			flipSatellite();
			break;
		case FLIP_STREET_ID:
			flipStreet();
			break;
		case FLIP_TRAFFIC_ID:
			flipTraffic();
			break;
		}
		return true;
	}

	private void displayFujisanLabel() {
		List<Overlay> overlays = mapView.getOverlays();
		if (!overlays.contains(fujisanOverlay)) {
			overlays.add(fujisanOverlay);
			mapView.invalidate();
		}
	}

	private void hideFujisanLabel() {
		List<Overlay> overlays = mapView.getOverlays();
		if (overlays.contains(fujisanOverlay)) {
			mapView.getOverlays().remove(fujisanOverlay);
			mapView.invalidate();
		}
	}

	private void addPointDefault() {
		GeoPoint point = mapView.getMapCenter();
		int no = checkPointOverlay.size();
		OverlayItem item = new OverlayItem(point, "title" + no, "snipet" + no);
		checkPointOverlay.addPoint(item);
		mapView.invalidate();
	}

	private void addPointStar() {
		GeoPoint point = mapView.getMapCenter();
		int no = checkPointOverlay.size();
		OverlayItem item = new OverlayItem(point, "title" + no, "snipet" + no);
		
		// 星マークを設定
		Drawable marker = getResources().getDrawable(R.drawable.star);
		marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());  
		item.setMarker(marker);

		checkPointOverlay.addPoint(item);
		mapView.invalidate();
	}
	private void clearPoint() {
		checkPointOverlay.clear();
		mapView.invalidate();
	}

	private void gotoFujisan() {
		// 北緯35度21分39秒
		double latitude = 35.0d + 21.0d / 60 + 39.0d / 3600;
		// 東経138度43分39秒
		double longitude = 138.0d + 43.0d / 60 + 39.0d / 3600;
		int latitudeE6 = (int) (latitude * 1E6);
		int longitudeE6 = (int) (longitude * 1E6);
		GeoPoint pos = new GeoPoint(latitudeE6, longitudeE6);
		mapView.getController().animateTo(pos);
	}

	private void zoomIn() {
		mapView.getController().zoomIn();
	}

	private void zoomOut() {
		mapView.getController().zoomOut();
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

	private void flipSatellite() {
		boolean nowSatellite = mapView.isSatellite();
		mapView.setSatellite(!nowSatellite);
	}

	private void flipTraffic() {
		boolean isTraffic = mapView.isTraffic();
		mapView.setTraffic(!isTraffic);
		
	}

	private void flipStreet() {
		boolean isStreet = mapView.isStreetView();
		mapView.setStreetView(!isStreet);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return true;
	}
}