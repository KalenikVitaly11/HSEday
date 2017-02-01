package com.example.hseday;

import android.view.View;

/**
 * Created by Виталий on 31.01.2017.
 */

public interface ItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}