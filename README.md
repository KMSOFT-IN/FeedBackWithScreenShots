# FeedBackWithScreenShots

[![](https://jitpack.io/v/KMSOFT-IN/FeedBackWithScreenShots.svg)](https://jitpack.io/#KMSOFT-IN/FeedBackWithScreenShots)


How to Use:

**Step 1. Add the JitPack repository to your build file**

Add it in your root build.gradle at the end of repositories:

```sh
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
  
Step 2. Add the dependency

```sh
dependencies {
	        implementation 'com.github.KMSOFT-IN:FeedBackWithScreenShots:1.0.6'
	}
```

after add dependencies add code like this :
```sh
view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                new CaptureScreenShots().handleTouch(HomeActivity.this,motionEvent,view);
                return true;
            }
        });
```
