package com.tw.timesheet.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tw.timesheet.android.R;

public class TitleBar extends RelativeLayout {

    private TextView textView;
    private ViewGroup content;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initUI();
        bindData(context, attrs);
    }

    private void initUI() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        content = (ViewGroup) inflater.inflate(R.layout.title_bar, this, false);
        this.addView(content);
        textView = (TextView) findViewById(R.id.title_bar_label);
    }

    private void bindData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        if (typedArray == null) {
            return;
        }
        String newLabelText = typedArray.getString(R.styleable.TitleBar_labelText);
        setLabelText(newLabelText);
        typedArray.recycle();
    }

    public void setLabelText(String labelText) {
        textView.setText(labelText);
        textView.setVisibility(View.VISIBLE);
    }
}
