package com.gp.ultimateemailidengine;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_main);
		
		Button pass_btn = (Button)findViewById(R.id.start_btn);
		pass_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), InputDataActivity.class);
				startActivity(nextScreen);
			}
			
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_main, menu);
		return true;
	}

}
