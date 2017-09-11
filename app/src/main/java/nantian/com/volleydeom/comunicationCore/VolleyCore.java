package nantian.com.volleydeom.comunicationCore;

import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import nantian.com.volleydeom.callback.CallbackUI;

/**
 * Created by xiaochunyuan on 17/9/8.
 * <p>
 * Volley请求的核心类
 */

public class VolleyCore {

    public static String GET = "get";
    public static String POST = "post";
    public static String IMG = "img";
    /**
     * 回调页面引用
     **/
    private CallbackUI callbackUI = null;

    private static VolleyCore volleyCore = null;
    /**
     * 请求队列
     **/
    private RequestQueue queue = null;

    private VolleyCore(CallbackUI callbackUI, RequestQueue queue) {
        this.callbackUI = callbackUI;

    }


    /**
     * 单列化
     **/

    public static VolleyCore getInstance(CallbackUI callbackUI, RequestQueue queue) {

        if (volleyCore == null) {

            volleyCore = new VolleyCore(callbackUI, queue);

        }
        return volleyCore;
    }


    /**
     * Get请求
     **/

    public void get(String url) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /**请求成功回调界面**/


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        request.setTag(GET);
        queue.add(request);


    }

    /**
     * Post请求
     **/

    public void post(String url, final Map<String, String> map) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return map;

            }
        };

        request.setTag(POST);
        queue.add(request);

    }


    /**
     * 第一个参数:URL
     * 第二个参数:请求成功响应回调
     * 第三个参数:最大宽
     * 第四个参数:最大高
     * 第五个参数:是否缩放
     * 第六个参数:图片质量
     * 第七给参数:请求失败响应回调
     * **/

    public void downloadImg(String url)
    {
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {



            }
        }, 0, 0, null, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        request.setTag(IMG);
        queue.add(request);


    }




}
