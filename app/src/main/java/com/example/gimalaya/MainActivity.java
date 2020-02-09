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
    protected void onCreate(Bundle savedInstanceState) {  //子类重写了父类的onCreate方法，在活动启动时该方法会被调用
        super.onCreate(savedInstanceState);  //调用父类的onCreate方法
        setContentView(R.layout.activity_main);   //由于是继承自父类，因此子类也继承了父类的setContentView方法，这里是调用自己的setContentView方法
        Map<String, String> map = new HashMap<>();  //集合map(映射)，key与value一一对应，且key不可以重复，通过key访问value
        CommonRequest.getCategories(map, new IDataCallBack<CategoryList>() {
            @Override
            public void onSuccess(CategoryList categoryList) {  //这个函数先这么理解:传入了一个数据结构map，最后回调到onSuccess函数
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
