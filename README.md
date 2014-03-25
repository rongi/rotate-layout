Rotate Layout
=============

Android layout that can rotate it's child

Usage
=====

1. Add to you project as Android library
2. In your layout file add

```xml 
<com.github.rongi.rotate_layout.layout.RotateLayout
	app="http://schemas.android.com/apk/res-auto"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content" >

	<YourLayoutHere
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		app:layout_angle="90">	<!-- Specify rotate angle here -->
	</YourLayoutHere>
</com.github.rongi.rotate_layout.layout.RotateLayout>
```

Voila! Your layout will be rotated 90 degrees.

Features
========

1. Handles all touch events in correct way. You press the same button you touch!
2. Layout measures itself in a correct way. This means that if not-rotated view is 50x100, then 90 degree rotated it will measure itself as 100x50 and can fit in another layouts with this dimensions.
3. Supports only power of 90 degrees rotation angles atm
