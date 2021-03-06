package com.scglab.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scglab.common.adapter.ImageRenderer;
import com.scglab.common.adapter.LabelItem;
import com.scglab.common.adapter.LabelRenderer;
import com.scglab.common.adapter.UrlItem;
import com.scglab.common.listadapter.FlexAdapter;
import com.scglab.common.listadapter.RendererFactory;

/**
 * Created by shj on 2017. 9. 21..
 */
public class MultipleTypeSampleActivity extends AppCompatActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		//rendererFactory
		RendererFactory rendererFactory = new RendererFactory();
		rendererFactory.put(LabelRenderer.class, R.layout.renderer_label);
		rendererFactory.put(ImageRenderer.class, R.layout.renderer_image);

		//adapter
		FlexAdapter flexAdapter = new FlexAdapter(rendererFactory);
		flexAdapter.setOnItemSwipe(new FlexAdapter.OnItemSwipeHandler() {
			@Override
			public int onSwipe(Object item) {
				if (item instanceof UrlItem) return FlexAdapter.OnItemSwipeHandler.RESTORE_ITEM;
				else return FlexAdapter.OnItemSwipeHandler.REMOVE_ITEM;
			}
		});
		flexAdapter.setItemSwipe(true);

		//recyclerView
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(flexAdapter);

		//add items
		for (int index = 0; index < 50; index++) {
			flexAdapter.addItem(new LabelItem(String.valueOf(index)));
		}
		flexAdapter.addItem(1, new UrlItem("https://www.android.com/static/2016/img/logo-android-green_2x.png"));
		flexAdapter.addItem(21, new UrlItem("https://developer.android.com/images/brand/Android_Robot_200.png"));
		flexAdapter.addItem(41, new UrlItem("https://play.google.com/intl/en_us/badges/images/play-logo-2x.png"));
	}
}
