package net.muxi.huashiapp.ui.apartment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import net.muxi.huashiapp.R;
import net.muxi.huashiapp.common.base.ToolbarActivity;
import net.muxi.huashiapp.common.data.ApartmentData;
import net.muxi.huashiapp.common.db.HuaShiDao;
import net.muxi.huashiapp.net.CampusFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by december on 16/7/30.
 */
public class ApartmentActivity extends ToolbarActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    public static void start(Context context) {
        Intent starter = new Intent(context, ApartmentActivity.class);
        context.startActivity(starter);
    }


    private HuaShiDao dao;
    private List<ApartmentData> mApartDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment);
        ButterKnife.bind(this);
        dao = new HuaShiDao();
        //读取宿舍的信息
        mApartDatas = dao.loadApart();
        if (mApartDatas.size() > 0) {
            setupRecyclerView(mApartDatas);
        } else {
            showLoading();
        }
        setTitle("部门信息");
        CampusFactory.getRetrofitService().getApartment()
                //observeOn() 在主线程上面发送通知
                .observeOn(AndroidSchedulers.mainThread())
                //被观察者 在新的线程上面发送通知
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<ApartmentData>>() {
                    @Override
                    public void onCompleted() {
                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(List<ApartmentData> apartmentDataList) {
                        if (apartmentDataList.size() != mApartDatas.size()) {
                            setupRecyclerView(apartmentDataList);
                            dao.deleteApartData();
                            for (ApartmentData data : apartmentDataList) {
                                dao.insertApart(data);
                            }
                        }
                    }
                });
    }

    public void setupRecyclerView(List<ApartmentData> apartmentDataList) {
        ApartmentAdapter adapter = new ApartmentAdapter(apartmentDataList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
