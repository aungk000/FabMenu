package me.aungkooo.fabmenu;


import android.animation.Animator;
import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;


public class FabAnimation
{
    public static void hideFabMenuItem(final FloatingActionButton fab)
    {
        fab.animate().translationY(0).translationX(0).alpha(0)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(300)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        fab.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }

    public static void showFabMenuItem(FloatingActionButton fab, float y, float x, long duration)
    {
        // y -> upper == minus && lower == plus
        fab.animate().translationY(y).translationX(x).alpha(1)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(duration).setListener(null).start();
    }

    public static void hide(FloatingActionButton fab)
    {
        fab.animate().translationY(fab.getHeight() + fab.getPaddingBottom()).setDuration(500)
                .setInterpolator(new LinearInterpolator()).start();
    }

    public static void show(FloatingActionButton fab)
    {
        fab.animate().translationY(0).setDuration(500)
                .setInterpolator(new LinearInterpolator()).start();
    }

    public static void load(Context context, FloatingActionButton fab, @AnimRes int resId)
    {
        Animation animRotate = AnimationUtils.loadAnimation(context, resId);
        fab.startAnimation(animRotate);
    }
}
