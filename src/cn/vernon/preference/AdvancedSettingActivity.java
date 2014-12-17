package cn.vernon.preference;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @since 2014-12-17
 * @author huailiang
 * 
 * 如果是在Fragment中运用
 * 重写OnCreate()方法 在里面添加addPreferencesFromResources()方法
 * 然后在OnCreateView()方法中 inflate.inflate(R.layout.xxx) xxx为主布局文件
 */
public class AdvancedSettingActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 注意先后顺序
		addPreferencesFromResource(R.xml.prefs_other);
		setContentView(R.layout.activity_advancedsetting);
	}
}
