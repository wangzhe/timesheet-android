package com.tw.timesheet.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tw.timesheet.android.R;

public class WeekDayView extends RelativeLayout{

    private TextView title;
    private EditText value;
    private View itemView;

    public WeekDayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
        bindData(context, attrs);
    }

    private void initUI() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(R.layout.week_day_view, this, false);
        this.addView(itemView);

        title = (TextView) itemView.findViewById(R.id.week_day_text);
        value = (EditText) itemView.findViewById(R.id.week_day_value);
    }

    private void bindData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeekDayView);
        setTitleText(typedArray.getString(R.styleable.WeekDayView_labelText));
        typedArray.recycle();
    }

    private void setTitleText(String title) {
        this.title.setText(title);
    }


}
