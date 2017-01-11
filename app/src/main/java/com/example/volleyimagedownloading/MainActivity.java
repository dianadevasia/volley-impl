package com.example.volleyimagedownloading;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class MainActivity extends AppCompatActivity {

  private NetworkImageView imageView1;
  private NetworkImageView imageView2;
  private NetworkImageView imageView3;
  private ImageLoader imageLoader;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    imageView1 = (NetworkImageView) findViewById(R.id.imageView1);
    imageView2 = (NetworkImageView) findViewById(R.id.imageView2);
    imageView3 = (NetworkImageView) findViewById(R.id.imageView3);
    String url1 = "https://e2qonline.americanexpress.com/myca/shared/summary/cardasset/images/NUS000000011_240x152_STRAIGHT_96.jpg";
    String url2 = "http://www.horisonseminyak.com/wp-content/uploads/2014/08/Volley-resize.jpg";
    String url3 = "http://a.fssta.com/content/dam/fsdigital/fscom/Soccer/images/2016/01/24/012416-Soccer-Real-Betis-Alvaro-Cejudo-pi-ssm.vresize.330.186.high.40.jpg";
    loadImage(url1, imageView1, 80, 0);
    loadImage(url2, imageView2, 150, 0);
    loadImage(url3, imageView3, 200, 100);
  }

  private void loadImage(String url, NetworkImageView imageView){

    imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext()).getImageLoader();

    imageLoader.get(url,
        ImageLoader.getImageListener(imageView, R.drawable.offline_icon_big, android.R.drawable.ic_dialog_alert));

    imageView.setImageUrl(url, imageLoader);
  }

  private void loadImage(String url, NetworkImageView imageView, int w, int h){
    Resources r = getResources();
    if(w == 0 || h ==0){
      if(w==0){
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, r.getDisplayMetrics());
        imageView.setMaxHeight(height);
      }
      else{
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, r.getDisplayMetrics());
        imageView.setMaxWidth(width);
      }
      imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      imageView.setAdjustViewBounds(true);
    }
    else {
      int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, r.getDisplayMetrics());
      int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, r.getDisplayMetrics());
      imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }
      loadImage(url, imageView);
  }



}