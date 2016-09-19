package org.micmource.widgetlists.widgetlist.tag;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.micmource.widgetlists.widgetlist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoorActivity extends Activity {

	private String[] mVals = new String[] { "bingo", "googol", "apple", "bingoogolapple", "helloworld", "tiant", "tiant", "tiant" };
	@BindView(R.id.flowlayout)
	BGAFlowLayout mFlowLayout;
	@BindView(R.id.et_main_tag)
	EditText mTagEt;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		flowLayout();

	}

	private void flowLayout() {
		setContentView(R.layout.activity_door);
		ButterKnife.bind(this);
		initData();
		mTagEt.setOnEditorActionListener(new EditText.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				String tag = mTagEt.getText().toString().trim();
				if (!TextUtils.isEmpty(tag)) {
					final DefaultItem label = getLabel(tag);
					label.setOnClick2DeleteListener(new DefaultItem.OnClick2DeleteListener() {

						@Override
						public void deleteItem() {
							mFlowLayout.removeView(label);
						}
					});

					mFlowLayout.addView4original(label);
				}
				mTagEt.setText("");
				return false;
			}
		});
	}

	public void initData() {
		for (int i = 0; i < mVals.length; i++) {
			mFlowLayout.addView4original(getLabel(mVals[i]));
		}
	}




	private DefaultItem getLabel(String text) {
		final DefaultItem item = new DefaultItem(DoorActivity.this);
		item.setText(text);
		item.setOnClick2DeleteListener(new DefaultItem.OnClick2DeleteListener() {

			@Override
			public void deleteItem() {
				mFlowLayout.removeView(item);
			}
		});
		return item;
	}
}
