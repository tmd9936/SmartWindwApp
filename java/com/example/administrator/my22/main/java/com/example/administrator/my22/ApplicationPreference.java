package com.example.administrator.my22;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class ApplicationPreference {

	public static final String PREF_NAME = "gigaiot.pref";

	public static final String PREF_GCM_REG_ID = "pref.gcm_regid";
	public static final String PREF_ACCOUNT_ID = "pref.account_id";
	public static final String PREF_ACCOUNT_MEMBER_SEQ = "pref.account_mbrseq";
	public static final String PREF_ACCESS_TOKEN = "pref.access_token";

	public static final String PREF_DEVICE_ID = "pref_device_id";
	public static final String PREF_TAG_ID = "pref_tag_id";
	public static final String PREF_EVENT_ID = "pref_event_id";


	public static ApplicationPreference applicationPref;

	SharedPreferences pref;
	SharedPreferences defaultPref;

	Editor editor;
	Editor defaultEditor;

	public static boolean isInitilized = false;

	public ApplicationPreference(Context context) {
		pref = context.getSharedPreferences(PREF_NAME, 0);
		defaultPref = PreferenceManager.getDefaultSharedPreferences(context);
		editor = pref.edit();
		defaultEditor = defaultPref.edit();
		isInitilized = true;
	}

	public static void init(Context context) {
		applicationPref = new ApplicationPreference(context);
	}

	public static ApplicationPreference getInstance() {
		return applicationPref;
	}

	public void setPrefGcmRegId(String data) {
		editor.putString(PREF_GCM_REG_ID, data);
		editor.commit();
	}

	public String getPrefGcmRegId() {
		return pref.getString(PREF_GCM_REG_ID, "");
	}

	public void setPrefAccountId(String data) {
		editor.putString(PREF_ACCOUNT_ID, data);
		editor.commit();
	}

	public String getPrefAccountId() {
		return pref.getString(PREF_ACCOUNT_ID, "");
	}

	public String getPrefAccountMbrSeq() {
		return pref.getString(PREF_ACCOUNT_MEMBER_SEQ, "");
	}

	public void setPrefAccountMbrSeq(String data) {
		editor.putString(PREF_ACCOUNT_MEMBER_SEQ, data);
		editor.commit();
	}


	public void setPrefAccessToken(String data) {
		editor.putString(PREF_ACCESS_TOKEN, data);
		editor.commit();
	}

	public String getPrefAccessToken() {
		return pref.getString(PREF_ACCESS_TOKEN, "");
	}

	public void setDeviceID(String data) {
		defaultEditor.putString(PREF_DEVICE_ID, data);
		defaultEditor.commit();
	}

	public String getDeviceID() {
		return defaultPref.getString(PREF_DEVICE_ID, "");
	}

	public void setTagID(String data) {
		defaultEditor.putString(PREF_TAG_ID, data);
		defaultEditor.commit();
	}

	public String getTagID() {
		return defaultPref.getString(PREF_TAG_ID, "");
	}

	public void setEventID(String data) {
		defaultEditor.putString(PREF_EVENT_ID, data);
		defaultEditor.commit();
	}

	public String getEventID() {
		return defaultPref.getString(PREF_EVENT_ID, "");
	}

}
