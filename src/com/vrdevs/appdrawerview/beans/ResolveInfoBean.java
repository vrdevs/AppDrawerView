package com.vrdevs.appdrawerview.beans;

import android.content.pm.ResolveInfo;

/**
 * Bean that contains information about a ResolveInfo object. Represents an
 * application that is displayed in the appdrawer and homescreen shortcuts.
 */
public class ResolveInfoBean {

	private boolean isEmpty;
	private ResolveInfo resolveInfo;

	public ResolveInfoBean(boolean isEmpty, ResolveInfo resolveInfo) {
		super();
		this.isEmpty = isEmpty;
		this.resolveInfo = resolveInfo;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public ResolveInfo getResolveInfo() {
		return resolveInfo;
	}

	public void setResolveInfo(ResolveInfo resolveInfo) {
		this.resolveInfo = resolveInfo;
	}

}
