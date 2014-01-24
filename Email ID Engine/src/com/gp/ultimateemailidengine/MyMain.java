package com.gp.ultimateemailidengine;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_main);
		
		Button pass_btn = (Button)findViewById(R.id.start_btn);
		//Button ask_btn = (Button)findViewById(R.id.ask_btn);
		
		pass_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), InputDataActivity.class);
				startActivity(nextScreen);
			}
			
		});
		/**
		ask_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), AskActivity.class);
				startActivity(nextScreen);
			}
			
		});
		*/
	}

}
