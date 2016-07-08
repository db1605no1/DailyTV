package com.example.dailytv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dailytv.beans.Program;
import com.example.dailytv.beans.User;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 荒原中的歌声 on 2016/7/7.
 */
public class RegisterActivity extends MyActivity{

    @Bind(R.id.register_toolbar)
    Toolbar registerToolbar;
    @Bind(R.id.username1)
    EditText username1;
    @Bind(R.id.password1)
    EditText password1;
    @Bind(R.id.register2)
    Button register2;
    @Bind(R.id.register_icon)
    ImageView registerIcon;
    public   final static  int TAKEPHOTO=2;
      public   final static  int LOCALIMG=1;
     private SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
    private Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_activty);
        ButterKnife.bind(this);
        setSupportActionBar(registerToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void register2(View view){
        String name = username1.getText().toString();
        final String pwd = password1.getText().toString();
        final User user = new User();
        List<Program> collects = new ArrayList<>();
        user.setName(name);
        user.setPwd(pwd);
        user.setCollects(collects);
        //首先在Bmob段查询用户名是否已经存在
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("name", name);
        query.findObjects(this, new FindListener<User>(){
            @Override
            public void onSuccess(List<User> list){
                if(list.size() == 0){
                    //用户名不存在，可以注册
                    saveUser(user);
                }else if(list.size() == 1){
                    Toast.makeText(RegisterActivity.this, "该用户名已存在！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int i, String s){
                Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUser(User user){
        user.save(this, new SaveListener(){
            @Override
            public void onSuccess(){
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s){
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
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

    public void localImg(View view){
               //从本地相册中取图片
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT) ;
        startActivityForResult(i,LOCALIMG);




    }

    public void camera(View view){


        //执行拍照前，先判断储存卡是否存在
           String   hasSdcard=Environment.getExternalStorageState();
        if(hasSdcard.equals(Environment.MEDIA_MOUNTED)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "IMG" + format.format(new Date()) + ".png");
            uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent,TAKEPHOTO);
        }   else{
            Toast.makeText(this,"内存卡不存在", Toast.LENGTH_LONG).show();}

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.e("RegisterActivity", "resultCode:" + resultCode);
        if(requestCode==TAKEPHOTO&&resultCode==RESULT_OK){
            Log.e("RegisterActivity", "ahhahaaaaaaaaaaaa");
                 registerIcon.setImageURI(uri);
        }
       else if(requestCode==LOCALIMG&&resultCode==RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            registerIcon.setImageBitmap(bitmap);

        }


    }
}
