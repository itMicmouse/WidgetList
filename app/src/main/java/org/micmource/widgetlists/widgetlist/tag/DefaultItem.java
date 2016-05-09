package org.micmource.widgetlists.widgetlist.tag;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.micmource.widgetlists.widgetlist.R;
import org.micmource.widgetlists.widgetlist.point.BadgeView;

public class DefaultItem extends LinearLayout {

	private TextView tv_defaultinfo;
	private ImageView iv_deleteitem;
	private OnClick2DeleteListener listener;
	private BadgeView badge2;

	public DefaultItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public DefaultItem(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public DefaultItem(Context context) {
		this(context,null);
	}

	private void initView() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.default_item, this, true);
		tv_defaultinfo = (TextView) view.findViewById(R.id.tv_defaultinfo);
		iv_deleteitem = (ImageView) view.findViewById(R.id.iv_deleteitem);
		badge2 = new BadgeView(getContext(), iv_deleteitem);
		badge2.setText("1");
		badge2.setTextColor(Color.BLACK);
		badge2.setBadgeBackgroundColor(Color.RED);
		badge2.setTextSize(12);
		badge2.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge2.toggle();
			}
		});
		iv_deleteitem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DefaultItem.this.listener.deleteItem();
			}
		});
	}
	public void setText(String info){
		tv_defaultinfo.setText(info);
	}
	public String getText(){
		return tv_defaultinfo.getText().toString();
	}
	public interface OnClick2DeleteListener{
		public void deleteItem();
	}
	public void setOnClick2DeleteListener(OnClick2DeleteListener listener){
		this.listener=listener;
	}
}
