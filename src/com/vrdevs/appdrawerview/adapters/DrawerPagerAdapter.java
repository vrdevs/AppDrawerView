package com.vrdevs.appdrawerview.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

import com.vrdevs.appdrawerview.R;
import com.vrdevs.appdrawerview.beans.ResolveInfoBean;

/**
 * This Adapter handles each page in the appdrawer's ViewPager.
 * @author Ramesh V.
 */
public class DrawerPagerAdapter extends PagerAdapter {

	Activity context;
	ArrayList<ResolveInfo> resolveInfoList;

	public DrawerPagerAdapter(Activity context,
			ArrayList<ResolveInfo> resolveInfoList) {
		super();
		this.context = context;
		this.resolveInfoList = resolveInfoList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count;
		count = resolveInfoList.size() / 20;
		if (resolveInfoList.size() % 20 != 0)
			count++;
		return count;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		View page = context.getLayoutInflater()
				.inflate(R.layout.gridpage, null);
		GridView gridView = (GridView) page.findViewById(R.id.gridViewPage);
		int rowCount = Integer.parseInt(gridView.getTag().toString());
		int columnCount = gridView.getNumColumns();
		gridView.setSelector(android.R.color.transparent);
		int pageCount = getCount();
		int startingIndex = position * 20;
		int endingIndex = startingIndex + 20;
		if (endingIndex >= resolveInfoList.size()) {
			endingIndex = resolveInfoList.size();
		}
		final List<ResolveInfo> resolveInfoList = this.resolveInfoList.subList(
				startingIndex, endingIndex);
		Log.d("ResolveInfoSize", "" + this.resolveInfoList.size());
		ArrayList<ResolveInfoBean> itemList = new ArrayList<ResolveInfoBean>();
		for (ResolveInfo resolveInfo : resolveInfoList) {
			itemList.add(new ResolveInfoBean(false, resolveInfo));
		}
		gridView.setAdapter(new ResolveInfoAdapter(itemList, context));
		gridView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					return false;
				}
				return false;
			}

		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				Animation animation = AnimationUtils.loadAnimation(context,
						R.anim.icon_wobble);
				new Timer().schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						ResolveInfoBean resolveInfoBean = ((ResolveInfoBean) parent
								.getAdapter().getItem(position));
						if (resolveInfoBean.isEmpty())
							return;
						ActivityInfo activity = resolveInfoBean
								.getResolveInfo().activityInfo;
						ComponentName name = new ComponentName(
								activity.applicationInfo.packageName,
								activity.name);
						Intent i = new Intent(Intent.ACTION_MAIN);

						i.addCategory(Intent.CATEGORY_LAUNCHER);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
						i.setComponent(name);
						context.startActivity(i);

					}
				}, 100);
				view.findViewById(R.id.imageViewAppIcon).startAnimation(
						animation);
			}
		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// try {
				// // LauncherUtils.saveShortcut(context,
				// // resolveInfoList.get(position), 0);
				// } catch (JSONException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				return false;
			}
		});
		// ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
		// layoutParams.height = 100;
		gridView.setGravity(Gravity.CENTER);
		// gridView.setLayoutParams(layoutParams);
		gridView.invalidate();
		((ViewPager) container).addView(page);

		return page;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView((View) object);
	}

}
