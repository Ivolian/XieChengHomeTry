package lib2;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;


public class ZoomAnim {

    public static void addAnimFor(final View view, final float zoomScale, final int duration, AfterEndAnimFinish afterEndAnimFinish) {

        new ZoomAnim(view, zoomScale, duration, afterEndAnimFinish);
    }

    // =========  ZoomState ==========

    enum State {
        startAnimBegin,
        startAnimFinish,
        endAnimBegin,
        endAnimFinish
    }

    // =========  Final ==========

    private static final float START_SCALE = 1f;

    private static final float SOME_VALUE = 0.5f;

    // =========  Field ==========

    private State state = State.endAnimFinish;

    private boolean keyUp = true;

    private float x;

    private float y;

    private boolean swipe = false;

    // =========  Constructor ==========

    private ZoomAnim(final View view, final float zoomScale, final int duration, final AfterEndAnimFinish afterEndAnimFinish) {

        // =========  Generate Anim ==========

        final ScaleAnimation startAnim = new ScaleAnimation(START_SCALE, zoomScale, START_SCALE, zoomScale,
                Animation.RELATIVE_TO_SELF, SOME_VALUE, Animation.RELATIVE_TO_SELF, SOME_VALUE
        );
        startAnim.setDuration(duration);
        startAnim.setFillAfter(true);

        final ScaleAnimation endAnim = new ScaleAnimation(zoomScale, START_SCALE, zoomScale, START_SCALE,
                Animation.RELATIVE_TO_SELF, SOME_VALUE, Animation.RELATIVE_TO_SELF, SOME_VALUE
        );
        endAnim.setDuration(duration);
        endAnim.setFillAfter(true);

        // =========  SetAnimationListener ==========

        startAnim.setAnimationListener(new Animation.AnimationListener() {

                                           @Override
                                           public void onAnimationStart(Animation animation) {

                                               state = State.startAnimBegin;
                                           }

                                           @Override
                                           public void onAnimationEnd(Animation animation) {

                                               state = State.startAnimFinish;
                                               if (keyUp) {
                                                   view.startAnimation(endAnim);
                                               }
                                           }

                                           @Override
                                           public void onAnimationRepeat(Animation animation) {

                                           }
                                       }
        );

        endAnim.setAnimationListener(new Animation.AnimationListener() {

                                         @Override
                                         public void onAnimationStart(Animation animation) {

                                             state = State.endAnimBegin;
                                         }

                                         @Override
                                         public void onAnimationEnd(Animation animation) {

                                             state = State.endAnimFinish;
                                             if (!swipe) {
                                                 afterEndAnimFinish.doSomething();
                                             }
                                         }

                                         @Override
                                         public void onAnimationRepeat(Animation animation) {

                                         }
                                     }
        );

        // =========  SetOnTouchListener ==========

        view.setOnTouchListener(new View.OnTouchListener() {

                                    @Override
                                    public boolean onTouch(View view, MotionEvent event) {

                                        switch (event.getAction()) {
                                            case MotionEvent.ACTION_DOWN:
                                                swipe = false;
                                                keyUp = false;
                                                x = event.getX();
                                                y = event.getY();
                                                if (state == State.endAnimFinish) {
                                                    view.startAnimation(startAnim);
                                                }
                                                break;
                                            case MotionEvent.ACTION_UP:
                                                keyUp = true;
                                                if (state == State.startAnimFinish) {
                                                    view.startAnimation(endAnim);
                                                }
                                                break;
                                            case MotionEvent.ACTION_MOVE:
                                                if (state == State.startAnimFinish) {
                                                    float distance = (x - event.getX()) * (x - event.getX()) + (y - event.getY()) * (y - event.getY());
                                                    if (distance > 1000) {
                                                        swipe = true;
                                                        view.startAnimation(endAnim);
                                                    }
                                                }
                                                break;
                                        }

                                        return true;
                                    }
                                }
        );
    }

}
