Rotate Layout
=============

Custom layout that can rotate it's view

[![Example](https://github.com/rongi/rotate-layout/raw/master/docs/screenshot5.png)](#Example)

Usage
=====

In your layout file add

```xml 
<com.github.rongi.rotate_layout.layout.RotateLayout
	xmlns:app="http://schemas.android.com/apk/res-auto"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	app:angle="90">	<!-- Specify rotate angle here -->

	<YourLayoutHere
		android:layout_width="wrap_content"
		android:layout_height="wrap_content">
	</YourLayoutHere>
</com.github.rongi.rotate_layout.layout.RotateLayout>
```

Voila! Your layout will be rotated 90 degrees.

Download
========

```groovy
compile 'rongi.rotate-layout:rotate-layout:3.0.0'
```

Features
========

1. Handles all touch events in correct way. You press the same button you touch!
2. Layout measures itself in a correct way. This means that if original view is 50x100, then 90 degree rotated it will measure itself as 100x50 and can fit in another layouts with this dimensions.
