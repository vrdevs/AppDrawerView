package com.vrdevs.appdrawerview.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class AppDrawerUtils {
//	public static Drawable getIconDrawable(Activity context,ResolveInfo resolveInfo) {
//		String themePackage = ThemeHandler.getCurrentTheme(context);
//		Resources themeResources = null;
//		PackageManager pm = context.getPackageManager();
//		String resName = resolveInfo.activityInfo.name.toLowerCase().replace(
//				".", "_");
//
//		try {
//			themeResources = pm.getResourcesForApplication(themePackage);
//		} catch (NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			themeResources = context.getResources();
//		}
//
//		Drawable drawable;
//		try {
//			drawable = themeResources.getDrawable(themeResources.getIdentifier(
//					resName, "drawable", themePackage));
//		} catch (Exception e) {
//			drawable = resolveInfo.loadIcon(pm);
//		}
//		return drawable;
//
//	}
}
