Rotate Layout
=============

Android layout that can rotate it's child

Usage
=====

1. Add to you project as Android library
2. In you layout file add

```xml 
<com.github.rongi.rotate_layout.layout.RotateLayout
  app="http://schemas.android.com/apk/res-auto"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content" >

	<YourLayoutHere
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		app:layout_angle="90">
	</YourLayoutHere>
</com.github.rongi.rotate_layout.layout.RotateLayout>
```

