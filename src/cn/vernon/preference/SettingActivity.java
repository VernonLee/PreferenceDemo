package cn.vernon.preference;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author huailiang
 * @time 上午11:56:03
 * @describe 设置界面
 */
public class SettingActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref_setting);
	}
}
