package com.lyh.utils.widget;

import android.graphics.Rect;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 *  @项目名： IwaaKF-III 
 *  @包名： com.szyh.ikf3.widget
 *  @文件名: ItemDecoration
 *  @创建者: kilin
 *  @创建时间: 2017/5/10 10:35
 */

public class ItemDecoration
        extends RecyclerView.ItemDecoration {
    int mSpace;
    boolean addBottom;

    @Override
    public void getItemOffsets(Rect outRect,
                               View view,
                               RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        if (layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL){
            outRect.right = mSpace;
            if (addBottom){
                outRect.bottom = 15;
            }
        }else {
            outRect.right = mSpace;
            outRect.bottom = mSpace;
        }

    }

    public ItemDecoration(int space, boolean flag) {
        this.mSpace = space;
        this.addBottom = flag;
    }
}
