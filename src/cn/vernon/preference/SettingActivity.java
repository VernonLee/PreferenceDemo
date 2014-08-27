package cn.vernon.preference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
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
	private CheckBoxPreference cbpWifi; 
	private CheckBoxPreference cbpBlueTooth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref_setting);
		
		wifiKey = getResources().getString(R.string.setting_wifi_key);
		blueToothKey = getResources().getString(R.string.setting_bluetooth_key);
		
		cbpWifi = (CheckBoxPreference)findPreference(wifiKey);
		cbpWifi.setOnPreferenceClickListener(this);
		cbpBlueTooth = (CheckBoxPreference)findPreference(blueToothKey);
		cbpBlueTooth.setOnPreferenceClickListener(this);
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
