package org.robolectric.shadows;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.TestRunners;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(TestRunners.WithDefaults.class)
public class ShadowAnimationSetTest {
  final Animation.AnimationListener moveListener = mock(Animation.AnimationListener.class);
  final Animation.AnimationListener spinListener = mock(Animation.AnimationListener.class);

  @Test @Ignore("Needs additional work")
  public void start_shouldRunAnimation() {
    final AnimationSet set = new AnimationSet(true);

    final Animation move = new TranslateAnimation(0, 100, 0, 100);
    move.setDuration(1000);
    move.setAnimationListener(moveListener);

    final Animation spin = new RotateAnimation(0, 360);
    spin.setDuration(1000);
    spin.setStartOffset(1000);
    spin.setAnimationListener(spinListener);

    set.start();

    verify(moveListener).onAnimationStart(move);

    Robolectric.flushForegroundScheduler();

    verify(moveListener).onAnimationEnd(move);
  }
}
