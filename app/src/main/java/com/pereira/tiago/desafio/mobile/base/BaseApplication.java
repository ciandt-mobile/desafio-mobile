package com.pereira.tiago.desafio.mobile.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.pereira.tiago.desafio.mobile.databasemodels.DaoMaster;
import com.pereira.tiago.desafio.mobile.databasemodels.DaoSession;
import com.pereira.tiago.desafio.mobile.singletons.SingletonMovie;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

public class BaseApplication extends Application {

    public static final boolean ENCRYPTED = false;
    public static final String databaseName = "desafio-mobile";
    public static Context context;

    private DaoSession daoSession;

    public BaseApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? databaseName+"-encrypted" : databaseName+"-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;

        try {
            SingletonMovie.initialize(daoSession);
        } catch (Exception e) {
            Log.e("MOBILE", e.getMessage());
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }

    @Override
    protected void attachBaseContext(Context base) {
        context = base;
        super.attachBaseContext(base);
    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
