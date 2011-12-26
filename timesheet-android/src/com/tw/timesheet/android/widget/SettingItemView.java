package com.tw.timesheet.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.widget.interfaces.ISettingItemView;

public class SettingItemView extends RelativeLayout implements ISettingItemView {

    public static final String ENUM_TEXT = "0";
    private TextView label;
    private TextView edit;
    private View content;
    private ImageView toggleImage;

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
        toggleImage = (ImageView) content.findViewById(R.id.setting_item_toggle_img);
    }

    private void bindData(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SettingItem);
        label.setText(typedArray.getString(R.styleable.SettingItem_labelText));

        if (ENUM_TEXT.equalsIgnoreCase(typedArray.getString(R.styleable.SettingItem_itemType))) {
            edit.setVisibility(View.VISIBLE);
            toggleImage.setVisibility(View.GONE);
        } else {
            edit.setVisibility(View.GONE);
            toggleImage.setVisibility(View.VISIBLE);
        }
        typedArray.recycle();
    }

    public void setIcon(int resId) {
        toggleImage.setImageResource(resId);
    }

    public void setText(String text) {
        edit.setText(text);
    }
}
