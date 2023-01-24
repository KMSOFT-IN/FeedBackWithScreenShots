# FeedBackWithScreenShots

[![](https://jitpack.io/v/KMSOFT-IN/FeedBackWithScreenShots.svg)](https://jitpack.io/#KMSOFT-IN/FeedBackWithScreenShots)


How to Use:
  - view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                new CaptureScreenShots().handleTouch(HomeActivity.this,motionEvent,view);
                return true;
            }
        });
