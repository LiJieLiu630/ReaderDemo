package com.liulije.readerdemo.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.liulije.readerdemo.R;

/**
 * 事件名称 MeijieIndia
 * 类描述:
 * 创建人：韩爽
 * 创建时间：2016/1/14 16:12
 * 备注：
 * 修改人：韩爽
 * 修改时间：2016/1/14 16:12
 * 修改备注：
 */
public class MyMenuUtil {

    public static SwipeMenuCreator addMenus(final Context mContext, final String[] menus, final int[] bgColors, final int width, final int textSize) {

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                for (int i = 0; i < menus.length; i++) {
                    // create "delete" item
                    SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
                    // set item background
                    deleteItem.setBackground(new ColorDrawable(mContext.getResources().getColor(bgColors[i])));
                    // set item width
                    deleteItem.setWidth(dp2px(mContext, width));
                    // set item title
                    deleteItem.setTitle(menus[i]);
                    // set item title fontsize
                    deleteItem.setTitleSize(textSize);
                    // set item title font color
                    deleteItem.setTitleColor(Color.WHITE);
                    // add to menu
                    menu.addMenuItem(deleteItem);
                }
            }
        };
        return creator;
    }

    public static int dp2px(Context mContext, int dip) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * 带图片的删除
     *
     * @param mContext
     * @param bgColors
     * @return
     */

    public static SwipeMenuCreator addMenusImg(final Context mContext, final int bgColors) {

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
                // set item background
                deleteItem.setBackground(new ColorDrawable(bgColors));
                // set item width
                deleteItem.setWidth(dp2px(mContext, 60));
                //set item icon
                deleteItem.setIcon(R.mipmap.delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        return creator;
    }
}
