/*
 * Copyright © 2017-2019 Harvard Pilgrim Health Care Institute (HPHCI) and its Contributors.
 * Copyright 2020 Google LLC
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * Funding Source: Food and Drug Administration (“Funding Agency”) effective 18 September 2014 as Contract no. HHSF22320140030I/HHSF22301006T (the “Prime Contract”).
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.harvard.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class ProgressDialogHelper {
  private ProgressDialog ringProgressDialog;

  /**
   * To show a progress dialog.
   *
   * @param context Activity context
   * @param title Title to display
   * @param msg Msg to display
   * @param cancelable True or False
   */
  public void showProgress(Context context, String title, String msg, boolean cancelable) {
    showProgressOnly(context, false);
  }

  /**
   * To show a progress dialog with title and msg.
   *
   * @param context activity context
   * @param title title to display null if don't need to show title
   * @param msg msg to display
   * @param cancelable is cancelable or not
   */
  public void showProgressWithText(Context context, String title, String msg, boolean cancelable) {
    if (ringProgressDialog != null) {
      return;
    }
    ringProgressDialog = new ProgressDialog(context);
    ringProgressDialog.setCancelable(cancelable);
    ringProgressDialog.setTitle(title);
    ringProgressDialog.setMessage(msg);
    ringProgressDialog.show();
  }

  /**
   * To show progress dialog without title nor msg.
   *
   * @param context activity context
   * @param cancelable is cancelable or not
   */
  private void showProgressOnly(Context context, boolean cancelable) {
    if (ringProgressDialog != null) {
      return;
    }
    ringProgressDialog = new ProgressDialog(context);
    ringProgressDialog
            .getWindow()
            .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    ringProgressDialog.setCancelable(cancelable);
    ringProgressDialog.show();
    ringProgressDialog.setContentView(new ProgressBar(context));
  }

  /**
   * To show a custom spinner.
   *
   * @param context activity context
   * @param id drawable resource of custom spinner
   * @param cancelable is cancelable or not
   */
  private void showCustomProgress(Context context, int id, boolean cancelable) {
    ringProgressDialog = new ProgressDialog(context);
    ringProgressDialog
        .getWindow()
        .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    ringProgressDialog.setCancelable(cancelable);
    ringProgressDialog.show();
    ProgressBar progressDialog = new ProgressBar(context);
    progressDialog.setIndeterminate(true);
    progressDialog.setIndeterminateDrawable(getdrawable(context, id));
    RelativeLayout relativeLayout = new RelativeLayout(context);
    RelativeLayout.LayoutParams layoutParams =
        new RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    RelativeLayout.LayoutParams layoutParamsprogress =
        new RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    layoutParamsprogress.addRule(RelativeLayout.CENTER_IN_PARENT);
    progressDialog.setLayoutParams(layoutParamsprogress);
    relativeLayout.setLayoutParams(layoutParams);
    relativeLayout.addView(progressDialog);
    ringProgressDialog.setContentView(relativeLayout);
  }

  /** only for swipe to refresh time. */
  public void showSwipeListCustomProgress(Context context, int id, boolean cancelable) {
    ringProgressDialog = new ProgressDialog(context);
    ringProgressDialog
        .getWindow()
        .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    ringProgressDialog.setCancelable(cancelable);
    ringProgressDialog.show();
    ProgressBar progressDialog = new ProgressBar(context);
    progressDialog.setIndeterminate(true);
    progressDialog.setIndeterminateDrawable(getdrawable(context, id));
    progressDialog
        .getIndeterminateDrawable()
        .setColorFilter(
            context.getResources().getColor(android.R.color.darker_gray),
            android.graphics.PorterDuff.Mode.SRC_IN);
    RelativeLayout relativeLayout = new RelativeLayout(context);
    RelativeLayout.LayoutParams layoutParams =
        new RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    RelativeLayout.LayoutParams layoutParamsprogress =
        new RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    layoutParamsprogress.addRule(RelativeLayout.CENTER_IN_PARENT);
    progressDialog.setLayoutParams(layoutParamsprogress);
    relativeLayout.setLayoutParams(layoutParams);
    relativeLayout.addView(progressDialog);
    ringProgressDialog.setContentView(relativeLayout);
  }

  /** dismiss a dialog. */
  public void dismissDialog() {
    if (ringProgressDialog != null && ringProgressDialog.isShowing()) {
      ringProgressDialog.dismiss();
      ringProgressDialog = null;
    }
  }

  public void updateMsg(String msg){
    if (ringProgressDialog != null && ringProgressDialog.isShowing()) {
      ringProgressDialog.setMessage(msg);
    }
  }

  private static Drawable getdrawable(Context context, int id) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      return context.getResources().getDrawable(id, context.getTheme());
    } else {
      return context.getResources().getDrawable(id);
    }
  }
}
