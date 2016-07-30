package com.example.dailytv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dailytv.beans.User;
import com.example.dailytv.utils.Constant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class MyAccountActivity extends AppCompatActivity{

    @Bind(R.id.account_toolbar)
    Toolbar accountToolbar;
    @Bind(R.id.account_icon)
    ImageView accountIcon;
    @Bind(R.id.local_img)
    ImageButton localImg;
    @Bind(R.id.camera)
    ImageButton camera;
    @Bind(R.id.account_send)
    Button accountSend;
    private String absolutePath;
    private SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
    private Uri uri;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accunt);
        ButterKnife.bind(this);
        setSupportActionBar(accountToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        objectId = getIntent().getStringExtra("objectId");
        absolutePath= getIntent().getStringExtra("absolutePath");
        if(absolutePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(absolutePath);
            accountIcon.setImageBitmap(bitmap);
        }

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void localImg(View view){
        //从本地相册中取图片
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i, Constant.LOCALIMG);
    }

    public void camera(View view){
        //执行拍照前，先判断储存卡是否存在
        String hasSdcard = Environment.getExternalStorageState();
        if(hasSdcard.equals(Environment.MEDIA_MOUNTED)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "IMG" + format.format(new Date()) + ".png");
            uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, Constant.TAKEPHOTO);
        }else{
            Toast.makeText(this, "内存卡不存在", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == Constant.TAKEPHOTO && resultCode == RESULT_OK){
            accountIcon.setImageURI(uri);
        }else if(requestCode == Constant.LOCALIMG && resultCode == RESULT_OK){
            uri = (Uri) data.getData();
            accountIcon.setImageURI(uri);
        }
    }

    public void changeIcon(View view){
        if(uri!=null){
            File file = null;
            try{
                InputStream is = getContentResolver().openInputStream(uri);
                BitmapFactory.Options options = new BitmapFactory.Options();
                //二次采样压缩图片
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(is, null, options);
                int height = options.outHeight;
                int width = options.outWidth;
                int containerHeight = accountIcon.getHeight();
                int containerWidth = accountIcon.getWidth();
                int xSample = height / containerHeight;
                int ySample = width / containerWidth;
                int sample = xSample < ySample ? xSample : ySample;
                sample = sample > 1 ? sample : 1;
                is = getContentResolver().openInputStream(uri);
                options.inJustDecodeBounds = false;
                options.inSampleSize = sample;
                Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
                if(bitmap != null){
                    //  Intent intent=new Intent("com.android.camera.action.CROP");
                    file = new File(getCacheDir() + File.separator + "IMG" + format.format(new Date()) + "avatar.jpg");
                    FileOutputStream os = new FileOutputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    byte[] b = bos.toByteArray();
                    os.write(b);
                    os.flush();
                    os.close();
                }
                absolutePath = file.getAbsolutePath();
                final BmobFile bmobFile = new BmobFile(file);
                bmobFile.uploadblock(this, new UploadFileListener(){
                    @Override
                    public void onProgress(Integer value){
                        Log.e("MyAccountActivity", "value:" + value);
                    }

                    @Override
                    public void onSuccess(){
                        User user = new User();
                        user.setIcon(bmobFile);
                        update(MyAccountActivity.this, user, objectId);
                    }

                    @Override
                    public void onFailure(int i, String s){
                        Log.e("MyAccountActivity", s);
                    }
                });
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "请选择图片", Toast.LENGTH_SHORT).show();
        }
    }

    private void update(final Context context, User user, String objectId){
        user.update(context, objectId, new UpdateListener(){
            @Override
            public void onSuccess(){
                Log.e("MyAccountActivity", "hahahhahaha");
                Intent intent = new Intent();
                intent.putExtra("absolutePath", absolutePath);
                MyAccountActivity.this.setResult(RESULT_OK, intent);
                Toast.makeText(context, "头像上传成功！", Toast.LENGTH_SHORT).show();
                MyAccountActivity.this.finish();
            }

            @Override
            public void onFailure(int i, String s){
                Log.e("MyAccountActivity", s);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}