Rotate Layout
=============

A custom layout that can rotate it's view

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
implementation 'rongi.rotate-layout:rotate-layout:3.0.0'
```

Features
========

1. The rotated view receives correct touch events.
2. The bounding box is also rotated. This means that if the view was 100x50px before the rotation, then after 90 degrees rotation it will be 50x100px and can fit into another layout with this dimensions.

