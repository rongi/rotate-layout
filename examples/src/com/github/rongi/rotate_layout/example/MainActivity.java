package com.github.rongi.rotate_layout.example;

import android.app.Activity;
import android.os.Bundle;

import com.github.rongi.rotate_layout.layout.RotateLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

	@Bind(R.id.form1_container) RotateLayout form1RotateLayout;
	@Bind(R.id.form2_container) RotateLayout form2RotateLayout;
	@Bind(R.id.form3_container) RotateLayout form3RotateLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}

	/**
	 * Clicking on a form will rotate it
	 */
	@OnClick({R.id.form1_container, R.id.form2_container, R.id.form3_container}) void onForm1ContainerClick(RotateLayout rotateLayout) {
		int newAngle = rotateLayout.getAngle() + 360 + 120;
		rotateLayout.setAngle(newAngle);
	}

}
