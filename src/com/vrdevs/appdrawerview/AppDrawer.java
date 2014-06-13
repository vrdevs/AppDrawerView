package com.vrdevs.appdrawerview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.vrdevs.appdrawerview.adapters.DrawerPagerAdapter;
import com.vrdevs.appdrawerview.utils.DepthPageTransformer;

/**
 * This View is a complete Appdrawer.
 */
public class AppDrawer extends RelativeLayout {

	private Activity context;

	public AppDrawer(Activity context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.activity_app_drawer, this, true);
		initContent();
	}

	public AppDrawer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = (Activity) context;
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.activity_app_drawer, this, true);
		initContent();
	}

	public AppDrawer(Context context, AttributeSet attrs, int defStyle) {
		// TODO Auto-generated constructor stub
		super(context, attrs, defStyle);
		this.context = (Activity) context;
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.activity_app_drawer, this, true);
		initContent();
	}

	public AppDrawer(Context context, AttributeSet attrs) {
		// TODO Auto-generated constructor stub
		super(context, attrs);
		this.context = (Activity) context;
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.activity_app_drawer, this, true);
		initContent();
	}

	/**
	 * Generates a List of ResolveInfos of all Activities that handle
	 * ACTION_MAIN of category CATEGORY_LAUNCHER.
	 * 
	 * @param context
	 *            Context of the Activity.
	 * @return List of ResolveInfos of all launch intents.
	 */
	public List<ResolveInfo> getResolveInfoList(Application context) {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		final PackageManager manager = context.getPackageManager();
		return (ArrayList<ResolveInfo>) manager.queryIntentActivities(
				mainIntent, 0);
	}

	/**
	 * Initializes the appdrawer with ResolveInfo objects that handle
	 * ACTION_MAIN of category CATEGORY_LAUNCHER.
	 * 
	 * @see ResolveInfo
	 */
	public void initContent() {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

		final PackageManager manager = context.getPackageManager();
		ArrayList<ResolveInfo> resolveInfoList = (ArrayList<ResolveInfo>) manager
				.queryIntentActivities(mainIntent, 0);
		Collections.sort(resolveInfoList, new Comparator<ResolveInfo>() {

			@Override
			public int compare(ResolveInfo lhs, ResolveInfo rhs) {
				// TODO Auto-generated method stub
				return lhs
						.loadLabel(context.getPackageManager())
						.toString()
						.compareToIgnoreCase(
								rhs.loadLabel(context.getPackageManager())
										.toString());
			}
		});
		DrawerPagerAdapter adapter = new DrawerPagerAdapter(context,
				resolveInfoList);
		((ViewPager) findViewById(R.id.pager)).setAdapter(adapter);
		((ViewPager) findViewById(R.id.pager)).setPageTransformer(true,
				new DepthPageTransformer());
		((ViewPager) findViewById(R.id.pager)).invalidate();
	}
}
