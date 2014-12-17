package cn.vernon.preference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {
	private ListView mList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mList = (ListView) findViewById(R.id.list);
		mList.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Class<?> cls = null;
		switch(position) {
		case 0:
			cls = SettingActivity.class;
			break;
		case 1:
			cls = AdvancedSettingActivity.class;
			break;
		}
		
		if(cls != null) {
			startActivity(new Intent(MainActivity.this, cls));
		}
	}
}
