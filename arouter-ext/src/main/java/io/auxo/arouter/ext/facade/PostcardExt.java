package io.auxo.arouter.ext.facade;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.template.IProvider;

import java.io.Serializable;
import java.util.ArrayList;

import io.auxo.arouter.ext.launcher.ARouterExt;

/**
 * @author Victor Chiu
 */

public class PostcardExt {

    private Postcard postcard;

    public PostcardExt(Postcard postcard) {
        this.postcard = postcard;
    }

    public Bundle getOptionsBundle() {
        return postcard.getOptionsBundle();
    }

    public int getEnterAnim() {
        return postcard.getEnterAnim();
    }

    public int getExitAnim() {
        return postcard.getExitAnim();
    }

    public IProvider getProvider() {
        return postcard.getProvider();
    }

    public Postcard setProvider(IProvider provider) {
        return postcard.setProvider(provider);
    }

    public boolean isGreenChannel() {
        return postcard.isGreenChannel();
    }

    public Object getTag() {
        return postcard.getTag();
    }

    public Postcard setTag(Object tag) {
        return postcard.setTag(tag);
    }

    public Bundle getExtras() {
        return postcard.getExtras();
    }

    public int getTimeout() {
        return postcard.getTimeout();
    }

    public Postcard setTimeout(int timeout) {
        return postcard.setTimeout(timeout);
    }

    public Uri getUri() {
        return postcard.getUri();
    }

    public Postcard setUri(Uri uri) {
        return postcard.setUri(uri);
    }

    public Object navigation() {
        return navigation(null);
    }

    public Object navigation(Context context) {
        return navigation(context, null);
    }

    public Object navigation(Context context, NavigationCallback callback) {
        return postcard.navigation(context, callback);
    }

    public void navigation(Activity mContext, int requestCode) {
        navigation(mContext, requestCode, null);
    }

    public void navigation(Activity mContext, int requestCode, NavigationCallback callback) {
        postcard.navigation(mContext, requestCode, callback);
    }

    public void navigation(Fragment fragment, int requestCode) {
        navigation(fragment, requestCode, null);
    }

    public void navigation(Fragment fragment, int requestCode, NavigationCallback callback) {
        ARouterExt.navigation(fragment, postcard, requestCode, callback);
    }

    public PostcardExt greenChannel() {
        postcard.greenChannel();
        return this;
    }

    public PostcardExt with(Bundle bundle) {
        postcard.with(bundle);
        return this;
    }

    public PostcardExt withFlags(@Postcard.FlagInt int flag) {
        postcard.withFlags(flag);
        return this;
    }

    public int getFlags() {
        return postcard.getFlags();
    }

    public PostcardExt withObject(@Nullable String key, @Nullable Object value) {
        postcard.withObject(key, value);
        return this;
    }

    public PostcardExt withString(@Nullable String key, @Nullable String value) {
        postcard.withString(key, value);
        return this;
    }

    public PostcardExt withBoolean(@Nullable String key, boolean value) {
        postcard.withBoolean(key, value);
        return this;
    }

    public PostcardExt withShort(@Nullable String key, short value) {
        postcard.withShort(key, value);
        return this;
    }

    public PostcardExt withInt(@Nullable String key, int value) {
        postcard.withInt(key, value);
        return this;
    }

    public PostcardExt withLong(@Nullable String key, long value) {
        postcard.withLong(key, value);
        return this;
    }

    public PostcardExt withDouble(@Nullable String key, double value) {
        postcard.withDouble(key, value);
        return this;
    }

    public PostcardExt withByte(@Nullable String key, byte value) {
        postcard.withByte(key, value);
        return this;
    }

    public PostcardExt withChar(@Nullable String key, char value) {
        postcard.withChar(key, value);
        return this;
    }

    public PostcardExt withFloat(@Nullable String key, float value) {
        postcard.withFloat(key, value);
        return this;
    }

    public PostcardExt withCharSequence(@Nullable String key, @Nullable CharSequence value) {
        postcard.withCharSequence(key, value);
        return this;
    }

    public PostcardExt withParcelable(@Nullable String key, @Nullable Parcelable value) {
        postcard.withParcelable(key, value);
        return this;
    }

    public PostcardExt withParcelableArray(@Nullable String key, @Nullable Parcelable[] value) {
        postcard.withParcelableArray(key, value);
        return this;
    }

    public PostcardExt withParcelableArrayList(@Nullable String key, @Nullable ArrayList<? extends Parcelable> value) {
        postcard.withParcelableArrayList(key, value);
        return this;
    }

    public PostcardExt withSparseParcelableArray(@Nullable String key, @Nullable SparseArray<? extends Parcelable> value) {
        postcard.withSparseParcelableArray(key, value);
        return this;
    }

    public PostcardExt withIntegerArrayList(@Nullable String key, @Nullable ArrayList<Integer> value) {
        postcard.withIntegerArrayList(key, value);
        return this;
    }

    public PostcardExt withStringArrayList(@Nullable String key, @Nullable ArrayList<String> value) {
        postcard.withStringArrayList(key, value);
        return this;
    }

    public PostcardExt withCharSequenceArrayList(@Nullable String key, @Nullable ArrayList<CharSequence> value) {
        postcard.withCharSequenceArrayList(key, value);
        return this;
    }

    public PostcardExt withSerializable(@Nullable String key, @Nullable Serializable value) {
        postcard.withSerializable(key, value);
        return this;
    }

    public PostcardExt withByteArray(@Nullable String key, @Nullable byte[] value) {
        postcard.withByteArray(key, value);
        return this;
    }

    public PostcardExt withShortArray(@Nullable String key, @Nullable short[] value) {
        postcard.withShortArray(key, value);
        return this;
    }

    public PostcardExt withCharArray(@Nullable String key, @Nullable char[] value) {
        postcard.withCharArray(key, value);
        return this;
    }

    public PostcardExt withFloatArray(@Nullable String key, @Nullable float[] value) {
        postcard.withFloatArray(key, value);
        return this;
    }

    public PostcardExt withCharSequenceArray(@Nullable String key, @Nullable CharSequence[] value) {
        postcard.withCharSequenceArray(key, value);
        return this;
    }

    public PostcardExt withBundle(@Nullable String key, @Nullable Bundle value) {
        postcard.withBundle(key, value);
        return this;
    }

    public PostcardExt withTransition(int enterAnim, int exitAnim) {
        postcard.withTransition(enterAnim, exitAnim);
        return this;
    }

    @RequiresApi(16)
    public PostcardExt withOptionsCompat(ActivityOptionsCompat compat) {
        postcard.withOptionsCompat(compat);
        return this;
    }

}
