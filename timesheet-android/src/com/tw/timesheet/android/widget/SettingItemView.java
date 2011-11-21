package com.tw.timesheet.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.widget.interfaces.ISettingItemView;

public class SettingItemView extends RelativeLayout implements ISettingItemView {

    private TextView label;
    private TextView edit;
    private View content;

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        bindData(attrs);
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        content = inflater.inflate(R.layout.setting_item, this, false);
        this.addView(content);

        label = (TextView) content.findViewById(R.id.setting_item_label);
        edit = (TextView) content.findViewById(R.id.setting_item_edit_text);
    }

    private void bindData(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SettingItem);
        label.setText(typedArray.getString(R.styleable.SettingItem_labelText));
        typedArray.recycle();
    }

}
