package nantian.com.volleydeom;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import nantian.com.volleydeom.callback.CallbackUI;
import nantian.com.volleydeom.comunicationCore.VolleyCore;

public class MainActivity extends AppCompatActivity implements CallbackUI, View.OnClickListener {

    /**
     * Volley请求队列引用
     **/
    private RequestQueue queue = null;

    public static MainActivity instance = null;

    private Button post, get, download;

    private ImageView img = null;

    private static Snackbar snackbar = null;

    private static View view = null;

    private VolleyCore volleyCore = null;

    private ProgressBar progressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    /**
     * 必要的初始化操作
     **/
    private void init() {
        /**对Volley请求队列的实力化**/
        queue = Volley.newRequestQueue(this);

        /**初始化当前类引用**/
        instance = MainActivity.this;

        findUI();

        volleyCore = VolleyCore.getInstance(this, queue);
    }

    /**
     * 请求的网络数据对ui的更新在此处进行
     **/
    @Override
    public void upDataUI(Object responseData, String flag) {

        switch (flag) {
            case "get": {

                String response_msg = (String) responseData;
                setSnackbar(response_msg);

            }
            break;
            case "post": {

                String response_msg = (String) responseData;
                setSnackbar(response_msg);
            }
            break;
            case "img": {

                Bitmap bitmap = (Bitmap) responseData;
                img.setImageBitmap(bitmap);
            }
            break;

        }


    }

    /**
     * 对界面控件的初始化
     **/

    private void findUI() {

        post = (Button) findViewById(R.id.post);
        get = (Button) findViewById(R.id.get);
        download = (Button) findViewById(R.id.download);
        img = (ImageView) findViewById(R.id.img);
        post.setOnClickListener(this);
        get.setOnClickListener(this);
        download.setOnClickListener(this);
        view = findViewById(R.id.main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
            startProgressBar();
        switch (v.getId()) {
            case R.id.post: {

                setSnackbar("post");
                Map<String, String> map = new HashMap<>();
                map.put("name", "xcy");
                map.put("age", "25");

                volleyCore.post("http://192.168.1.106:8080/ser", map);

            }
            break;
            case R.id.get: {
                setSnackbar("get");
                volleyCore.get("http://192.168.1.106:8080/ser?name=xcy&age=25");
            }
            break;
            case R.id.download: {
                setSnackbar("down");
                volleyCore.downloadImg("http://ww1.sinaimg.cn/mw690/c2c699f3jw1esmfey04hsj20dw08owf2.jpg");
            }
            break;


        }

    }


    /**
     * 设置snackbar
     **/

    public static void setSnackbar(String msg) {
        if (snackbar == null) {
            snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        snackbar.setText(msg);
        snackbar.show();

    }


    /**
     * 显示环形加载条
     **/

    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 关闭环形加载条
     **/
    public void cancelProgressBar() {

        progressBar.setVisibility(View.GONE);
    }


}
