package me.aungkooo.fabmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


public class FabMenu extends FrameLayout implements View.OnClickListener
{
    private boolean isActive;
    private OnFabMenuItemClickListener fabMenuItemClickListener;

    private FloatingActionButton[] fabList;
    private FloatingActionButton fabMain;

    private int itemCount, position;

    public FabMenu(@NonNull Context context) {
        super(context);
    }

    public FabMenu(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        if(attrs != null)
        {
            TypedArray typedArray = context.getTheme()
                    .obtainStyledAttributes(attrs, R.styleable.FabMenu, 0, 0);

            if(typedArray != null)
            {
                try {
                    itemCount = typedArray.getInteger(R.styleable.FabMenu_itemCount, 1);
                    position = typedArray.getInteger(R.styleable.FabMenu_position, 1);
                }
                finally {
                    typedArray.recycle();
                }
            }
        }
    }

    public FabMenu(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FabMenu(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FabMenu setFabList(FloatingActionButton[] fabList) {
        this.fabList = fabList;
        return this;
    }

    public FabMenu setFabMain(FloatingActionButton fabMain) {
        this.fabMain = fabMain;

        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(isActive)
                {
                    hideFabMenu();
                }
                else {
                    showFabMenu();
                }
            }
        });

        return this;
    }

    public FloatingActionButton getFabMain() {
        return fabMain;
    }

    public void setFabMenuItemClickListener(OnFabMenuItemClickListener fabMenuItemClickListener) {
        this.fabMenuItemClickListener = fabMenuItemClickListener;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void showFabMenu()
    {
        dimParent(true);

        for(int i = 0, l = fabList.length; i < l; i++)
        {
            fabList[i].setVisibility(View.VISIBLE);
            fabList[i].setOnClickListener(this);

            if(position == 0)
            {
                if(itemCount == 0)
                {
                    switch (i)
                    {
                        case 0:
                            FabAnimation.showFabMenuItem(fabList[i], -75, 225, 150);
                            break;

                        case 1:
                            FabAnimation.showFabMenuItem(fabList[i], -225, 100, 350);
                            break;
                    }
                }
                else {
                    switch (i)
                    {
                        case 0:
                            FabAnimation.showFabMenuItem(fabList[i], -20, 250, 150);
                            break;

                        case 1:
                            FabAnimation.showFabMenuItem(fabList[i], -175, 175, 350);
                            break;

                        case 2:
                            FabAnimation.showFabMenuItem(fabList[i], -250, 20, 550);
                            break;
                    }
                }
            }
            else {
                if(itemCount == 0)
                {
                    switch (i)
                    {
                        case 0:
                            FabAnimation.showFabMenuItem(fabList[i], -75, -225, 150);
                            break;

                        case 1:
                            FabAnimation.showFabMenuItem(fabList[i], -225, -100, 350);
                            break;
                    }
                }
                else {
                    switch (i)
                    {
                        case 0:
                            FabAnimation.showFabMenuItem(fabList[i], -20, -250, 150);
                            break;

                        case 1:
                            FabAnimation.showFabMenuItem(fabList[i], -175, -175, 350);
                            break;

                        case 2:
                            FabAnimation.showFabMenuItem(fabList[i], -250, -20, 550);
                            break;
                    }
                }
            }
        }

        isActive = true;
        FabAnimation.load(getContext(), fabMain, R.anim.rotate_forward);
    }

    public void hideFabMenu()
    {
        dimParent(false);

        for(final FloatingActionButton each: fabList)
        {
            each.setOnClickListener(null);

            FabAnimation.hideFabMenuItem(each);
        }

        isActive = false;
        FabAnimation.load(getContext(), fabMain, R.anim.rotate_backward);
    }

    private void dimParent(boolean dim)
    {
        if(dim)
        {
            this.setBackgroundColor(Color.argb(64, 0, 0,0));
            this.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideFabMenu();
                }
            });
        }
        else {
            this.setBackgroundColor(0);
            this.setOnClickListener(null);
            this.setClickable(false);
        }
    }

    @Override
    public void onClick(View view)
    {
        fabMenuItemClickListener.onFabMenuItemClick(view);
    }
}
