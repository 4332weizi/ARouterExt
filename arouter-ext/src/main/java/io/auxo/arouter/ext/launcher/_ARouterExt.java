package io.auxo.arouter.ext.launcher;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.exception.NoRouteFoundException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.facade.service.InterceptorService;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.Consts;
import com.alibaba.android.arouter.utils.DefaultLogger;

/**
 * @author Victor Chiu
 */
public class _ARouterExt {

    static ILogger logger = new DefaultLogger(Consts.TAG);

    private static InterceptorService interceptorService;

    private static _ARouterExt instance;

    private _ARouterExt() {
        interceptorService = (InterceptorService) ARouter.getInstance().build("/arouter/service/interceptor").navigation();
    }

    protected static _ARouterExt getInstance() {
        if (instance == null) {
            synchronized (_ARouterExt.class) {
                if (instance == null) {
                    instance = new _ARouterExt();
                }
            }
        }
        return instance;
    }

    public Object navigation(final Fragment fragment, final Postcard postcard, final int requestCode, final NavigationCallback callback) {

        boolean found = completion(fragment.getContext(), postcard);

        if (found) {
            if (callback != null) {
                callback.onFound(postcard);
            }
        } else {
            if (null != callback) {
                callback.onLost(postcard);
            } else {    // No callback for this invoke, then we use the global degrade service.
                DegradeService degradeService = ARouter.getInstance().navigation(DegradeService.class);
                if (null != degradeService) {
                    degradeService.onLost(fragment.getActivity(), postcard);
                }
            }
        }

        if (!postcard.isGreenChannel()) {   // It must be run in async thread, maybe interceptor cost too mush time made ANR.
            interceptorService.doInterceptions(postcard, new InterceptorCallback() {
                /**
                 * Continue process
                 *
                 * @param postcard route meta
                 */
                @Override
                public void onContinue(Postcard postcard) {
                    _navigation(fragment, postcard, requestCode, callback);
                }

                /**
                 * Interrupt process, pipeline will be destory when this method called.
                 *
                 * @param exception Reson of interrupt.
                 */
                @Override
                public void onInterrupt(Throwable exception) {
                    if (null != callback) {
                        callback.onInterrupt(postcard);
                    }

                    logger.info(Consts.TAG, "Navigation failed, termination by interceptor : " + exception.getMessage());
                }
            });
        } else {
            return _navigation(fragment, postcard, requestCode, callback);
        }

        return null;
    }

    private boolean completion(Context context, Postcard postcard) {
        try {
            LogisticsCenter.completion(postcard);
            return true;
        } catch (NoRouteFoundException ex) {
            logger.warning(Consts.TAG, ex.getMessage());

            if (ARouter.debuggable()) { // Show friendly tips for user.
                Toast.makeText(context, "There's no route matched!\n" +
                        " Path = [" + postcard.getPath() + "]\n" +
                        " Group = [" + postcard.getGroup() + "]", Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }

    private Object _navigation(final Fragment fragment, final Postcard postcard, final int requestCode, final NavigationCallback callback) {
        switch (postcard.getType()) {
            case ACTIVITY:
                // Build intent
                final Intent intent = new Intent(fragment.getActivity(), postcard.getDestination());
                intent.putExtras(postcard.getExtras());

                // Set flags.
                int flags = postcard.getFlags();
                if (-1 != flags) {
                    intent.setFlags(flags);
                }

                // Navigation in main looper.
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (requestCode > 0) {  // Need start for result
                            fragment.startActivityForResult(intent, requestCode, postcard.getOptionsBundle());
                        } else {
                            fragment.startActivity(intent, postcard.getOptionsBundle());
                        }

                        if ((0 != postcard.getEnterAnim() || 0 != postcard.getExitAnim())) {
                            fragment.getActivity().overridePendingTransition(postcard.getEnterAnim(), postcard.getExitAnim());
                        }

                        // Navigation over.
                        if (null != callback) {
                            callback.onArrival(postcard);
                        }
                    }
                });
                break;
            default:
                return null;
        }

        return null;
    }
}
