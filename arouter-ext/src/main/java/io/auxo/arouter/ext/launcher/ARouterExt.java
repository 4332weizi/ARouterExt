package io.auxo.arouter.ext.launcher;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import io.auxo.arouter.ext.facade.PostcardExt;

public class ARouterExt {

    public static PostcardExt build(String path) {
        return new PostcardExt(ARouter.getInstance().build(path));
    }

    @Deprecated
    public static PostcardExt build(String path, String group) {
        return new PostcardExt(ARouter.getInstance().build(path, group));
    }

    public static PostcardExt build(Uri url) {
        return new PostcardExt(ARouter.getInstance().build(url));
    }

    public static Object navigation(Context mContext, Postcard postcard, int requestCode, NavigationCallback callback) {
        return ARouter.getInstance().navigation(mContext, postcard, requestCode, callback);
    }

    public static Object navigation(Fragment fragment, Postcard postcard, int requestCode, NavigationCallback callback) {
        return _ARouterExt.getInstance().navigation(fragment, postcard, requestCode, callback);
    }
}
