package cn.vernon.preference;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @author huailiang
 * @time 上午11:56:03
 * @describe 设置界面
 */
public class SettingActivity extends PreferenceActivity implements OnPreferenceClickListener {
	
	private String wifiKey;
	private String blueToothKey;
	private String dateKey;
	private String recoverKey;
	private CheckBoxPreference cbpWifi; 
	private CheckBoxPreference cbpBlueTooth;
	private MultiSelectListPreference mslpDate;
	private Preference prefRecover;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref_setting);
		
		wifiKey = getResources().getString(R.string.setting_wifi_key);
		blueToothKey = getResources().getString(R.string.setting_bluetooth_key);
		dateKey = getResources().getString(R.string.setting_date_key);
		recoverKey = getResources().getString(R.string.setting_recover_key);
		
		cbpWifi = (CheckBoxPreference)findPreference(wifiKey);
		cbpWifi.setOnPreferenceClickListener(this);
		cbpBlueTooth = (CheckBoxPreference)findPreference(blueToothKey);
		cbpBlueTooth.setOnPreferenceClickListener(this);
		mslpDate = (MultiSelectListPreference)findPreference(dateKey);
		prefRecover = (Preference) findPreference(recoverKey);
		prefRecover.setOnPreferenceClickListener(this);
		
		// =============日期设置模拟开始=============== //
		String value = "星期一,星期二,星期三";
		JSONObject dataJson = null;
		try {
			dataJson = new JSONObject();
			dataJson.put("aeraTime", value);

			Set<String> defaultValue = new HashSet<String>();
			String jsonValue[] = dataJson.getString("aeraTime").split(",");
			if (jsonValue != null) {
				// 设置选中的值
				for (String temp : jsonValue) {
					defaultValue.add(temp);
				}
				mslpDate.setValues(defaultValue);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		// 选中的值
		Set<String> checkedValue = mslpDate.getValues();
		for(String item : checkedValue) {
			Log.i("SETTING-CURRENT-DATE",item.toString());
		}
		// =============日期设置模拟结束=============== //
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean wifiState = pref.getBoolean(wifiKey, false);
		boolean blueToothState = pref.getBoolean(blueToothKey, false);
		
		Log.d("WIFI-STATE", String.valueOf(wifiState));
		Log.d("BLUETOOTH-STATE", String.valueOf(blueToothState));	
		
		String preferenceKey = preference.getKey();
		if(preferenceKey.equals(wifiKey)) {
			if(wifiState) {
				showToast("wifi已打开");
			} else {
				showToast("wifi已关闭");
			}
		} else if(preferenceKey.equals(blueToothKey)) {
			if(blueToothState) {
				showToast("蓝牙已打开");
			} else {
				showToast("蓝牙已关闭");
			}
		} else if(preferenceKey.equals(recoverKey)) {
			showToast("recovered");
		}
		
		return true;
	}
	
	private void showToast(final String info) {
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(getApplicationContext(), info, 
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
