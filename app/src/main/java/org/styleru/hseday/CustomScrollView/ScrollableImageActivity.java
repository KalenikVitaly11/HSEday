package org.styleru.hseday.CustomScrollView;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

public class ScrollableImageActivity extends Activity {

    private float mx, my;
    private float curX, curY;

    private ScrollView vScroll;
    private HorizontalScrollView hScroll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.styleru.hseday.R.layout.fragment_map);

        //vScroll = (ScrollView) findViewById(org.styleru.hseday.R.id.v_scroll);
        //hScroll = (HorizontalScrollView) findViewById(org.styleru.hseday.R.id.h_scroll);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
                float curX, curY;

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mx = event.getX();
                        my = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        curX = event.getX();
                        curY = event.getY();
                        vScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                        hScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                        mx = curX;
                        my = curY;
                break;
            case MotionEvent.ACTION_UP:
                curX = event.getX();
                curY = event.getY();
                vScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                hScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                break;
        }

        return true;
    }

}