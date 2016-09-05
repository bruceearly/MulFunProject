package com.ebest.mijia.heapcleartest.Fragments;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.SubscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ebest.mijia.heapcleartest.R;

/**
 * Created by mijia on 16/8/3.
 */
public class TextViewSpannableFragment extends Fragment {

    TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_textspan, container, false);
        tv = (TextView) v.findViewById(R.id.textspan_tv);
        tv.setText("00Hou00Min\r\nTime in outlet");
        setTextAppreanceSpan();
        ValueAnimator animator = ValueAnimator.ofInt(0, tv.getText().toString().length());
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                setForegroundColorSpan(value);
            }
        });
        animator.start();
        return v;
    }

    private void setForegroundColorSpan(int index) {
        String spantext = tv.getText().toString();
        SpannableString spannableString = new SpannableString(spantext);
        spannableString.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, index, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);
    }

    private void setTextAppreanceSpan() {
        String spantext = tv.getText().toString();
        SpannableString spannableString = new SpannableString(spantext);
        spannableString.setSpan(new TextAppearanceSpan(getActivity(), android.R.style.TextAppearance_Large), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(getActivity(), android.R.style.TextAppearance_Large), 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);
    }

    private void setSubscriptSpan() {
        String spantext = tv.getText().toString();
        SpannableString spannableString = new SpannableString(spantext);
        spannableString.setSpan(new SubscriptSpan(), 2, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new SubscriptSpan(), 7, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
