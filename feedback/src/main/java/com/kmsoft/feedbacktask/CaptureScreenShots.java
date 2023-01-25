package com.kmsoft.feedbacktask;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.FileUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class CaptureScreenShots {

    private static final int NONE = 0;
    private static final int SWIPE = 1;
    private static boolean isSwipe;
    private static float startY, stopY;
    // We will only detect a swipe if the difference is at least 100 pixels
    private static final int TRESHOLD = 50;

    public void handleTouch(Context context,MotionEvent m, View view){
        //Number of touches
        int pointerCount = m.getPointerCount();
        if(pointerCount == 2) {

            switch (m.getAction() & MotionEvent.ACTION_MASK)
            {
                case MotionEvent.ACTION_POINTER_DOWN:
                    // This happens when you touch the screen with two fingers
                    isSwipe = true;
                    // You can also use event.getY(1) or the average of the two
                    startY = m.getY(0);
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    // This happens when you release the second finger
                    isSwipe = false;
                    if(Math.abs(startY - stopY) > TRESHOLD)
                    {
                        System.out.println("startY : " + startY);
                        System.out.println("stopY : " + stopY);
                        if(startY > stopY)
                        {
                            // Swipe up
                        }
                        else
                        {
                            //Swipe down
                            screenShot(context,view);
                            System.out.println("SWIPE DOWN");
                        }
                    }
                    break;

                case MotionEvent.ACTION_MOVE:
                    if(isSwipe)
                    {
                        stopY = m.getY(0);
                    }
                    break;
            }
        }
    }

    public void screenShot(Context context, View view) {
        int randomNumber = Math.abs(new Random(1).nextInt());

        try {

            // create bitmap screen capture
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                    view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);

            ContextWrapper cw = new ContextWrapper(context);
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
//            File imageFile = new File(directory, "ScreenShots_" + randomNumber + ".jpg");
            File imageFile = File.createTempFile("ScreenShots_" + randomNumber , ".jpg", context.getExternalCacheDir());
            System.out.println("$$$$$$$$$$ " + imageFile.getPath());

//            if (!imageFile.exists()) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(imageFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
//            }

            Intent intent = new Intent(context,SendActivity.class);
            intent.putExtra("ImageFIle",imageFile);
            context.startActivity(intent);

        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }
}
