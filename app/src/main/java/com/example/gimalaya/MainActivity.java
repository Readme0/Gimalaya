package com.example.gimalaya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.gimalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.category.Category;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> map = new HashMap<>();
        CommonRequest.getCategories(map, new IDataCallBack<CategoryList>() {
            @Override
            public void onSuccess(CategoryList categoryList) {
                List<Category> categories = categoryList.getCategories();
                if (categories != null) {
                    int size = categories.size();
                    Log.d(TAG,"categories size -- " + size);  //alt+enter创建tag,create constant field 'TAG' -- MainActivity
                    for(Category category : categories){
                        //Log.d(TAG,"category -- " + category.getCategoryName());
                        LogUtil.d(TAG, "category -- " + category.getCategoryName());  //用自己写的LogUtil类来过滤log
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                //Log.e(TAG,"erro code -- " + i + "erro msg -- " + s);
                LogUtil.d(TAG, "erro code -- " + i + "erro msg -- " + s);
            }
        });
    }
}
