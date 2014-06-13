package com.vrdevs.appdrawerview.adapters;

import java.util.List;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.vrdevs.appdrawerview.R;
import com.vrdevs.appdrawerview.beans.ResolveInfoBean;

/**
 * Adapter that sets the icon and label of a ResolveInfoBean object.
 *@see ResolveInfo
 */
public class ResolveInfoAdapter extends BaseAdapter {

	private int ROW_COUNT = 5;
	private int COLUMN_COUNT = 4;
	List<ResolveInfoBean> resolveInfoBeanList;
	Activity context;
	private PackageManager pm;

	/**
	 * @param resolveInfoBeanList	List of ResolveInfoBean objects to be populated.
	 * @param context				Context of the Activity.
	 * @see ResolveInfoBean
	 */
	public ResolveInfoAdapter(List<ResolveInfoBean> resolveInfoBeanList,
			Activity context) {
		Log.d("ResolveBeans", resolveInfoBeanList.size()+"");
		this.resolveInfoBeanList = resolveInfoBeanList;
		this.context = context;
		pm = context.getPackageManager();
		if(resolveInfoBeanList.size() < 20) {
			int startingIndex = resolveInfoBeanList.size();
			int endingIndex = 19;
			for(int i = startingIndex; i <= endingIndex; i++) {
				resolveInfoBeanList.add(new ResolveInfoBean(true, null));
			}
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return resolveInfoBeanList.size();
	}

	@Override
	public ResolveInfoBean getItem(int position) {
		// TODO Auto-generated method stub
		return resolveInfoBeanList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ROW_COUNT = ((GridView)parent).getNumColumns();
		COLUMN_COUNT = Integer.parseInt(((GridView)parent).getTag().toString());
		
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		if (convertView == null)
			convertView = this.context.getLayoutInflater().inflate(
					R.layout.cell_app_info, null);
		ImageView iconImage = (ImageView) convertView
				.findViewById(R.id.imageViewAppIcon);
		ResolveInfo resolveInfo = resolveInfoBeanList.get(position)
				.getResolveInfo();
		if (!resolveInfoBeanList.get(position).isEmpty()) {
			iconImage.setImageDrawable(resolveInfo.loadIcon(pm));
			((TextView) convertView.findViewById(R.id.textViewAppName))
					.setText(resolveInfo.loadLabel(pm)
							.toString());
			convertView.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if(event.getAction() == MotionEvent.ACTION_DOWN) {
						v.setBackgroundColor(color.holo_blue_light);
					}
					if(event.getAction() == MotionEvent.ACTION_UP) {
						v.setBackgroundColor(color.transparent);
					}
					return false;
				}
			});
		} else {
			iconImage.setImageResource(android.R.color.transparent);
			((TextView) convertView.findViewById(R.id.textViewAppName))
					.setText("");
		}

		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		height = (int) ((dm.heightPixels - 100) / ROW_COUNT*0.75);
		width = dm.widthPixels / COLUMN_COUNT;
		android.widget.AbsListView.LayoutParams parms = new android.widget.AbsListView.LayoutParams(
				width, height);
		convertView.setLayoutParams(parms);
		return convertView;
	}

}
