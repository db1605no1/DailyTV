package com.example.dailytv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dailytv.beans.User;
import com.example.dailytv.utils.Constant;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends MyActivity{

    @Bind(R.id.toolbarlogin)
    Toolbar toolbarlogin;
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.register1)
    Button register1;
    private String  objectId;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarlogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void login1(View view){
        final String name = username.getText().toString();
        final String pwd = password.getText().toString();
        //非空判断
        if(!TextUtils.isEmpty(name) &&! TextUtils.isEmpty(pwd)){
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("name", name);
            query.findObjects(this, new FindListener<User>(){
                @Override
                public void onSuccess(List<User> list){
                    if(list.size() == 0){
                        Toast.makeText(LoginActivity.this, "用户名不存在，请先注册！", Toast.LENGTH_SHORT).show();
                    }else if(list.size() == 1){
                        User user = list.get(0);
                     objectId=user.getObjectId();
                        String pwd1 = user.getPwd();
                        if(pwd1.equals(pwd)){
                            BmobFile bmobFile = user.getIcon();
                            //从Bomb下载图片
                            Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                            if(bmobFile!=null){
                                final File saveFile = new File(Environment.getExternalStorageDirectory(), bmobFile.getFilename());
                                Log.e("LoginActivity", "Environment.getExternalStorageDirectory():" + Environment.getExternalStorageDirectory());
                                bmobFile.download(LoginActivity.this, saveFile, new DownloadFileListener(){

                                    @Override
                                    public void onProgress(Integer progress, long total){
                                        Log.e("LoginActivity", "progress:" + progress);
                                    }

                                    @Override
                                    public void onSuccess(String s){
                                        String absolutePath = saveFile.getAbsolutePath();
                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("name", name);
                                        bundle.putString("pwd", pwd);
                                        Log.e("LoginActivity", absolutePath);
                                        bundle.putString("absolutePath", absolutePath);
                                        bundle.putString("objectId",objectId);
                                        intent.putExtras(bundle);
                                        LoginActivity.this.setResult(RESULT_OK, intent);
                                        LoginActivity.this.finish();
                                    }

                                    @Override
                                    public void onFailure(int i, String s){
                                    }
                                });
                            }else{
                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                bundle.putString("name", name);
                                bundle.putString("objectId",objectId);
                                intent.putExtras(bundle);

                                LoginActivity.this.setResult(RESULT_OK, intent);
                                LoginActivity.this.finish();


                            }




                        }else{
                            Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onError(int i, String s){
                    Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
        }
    }


    private  File  downloadFile(BmobFile file){



        File saveFile=new File(Environment.getExternalStorageDirectory(),file.getFilename());
        Log.e("LoginActivity", "Environment.getExternalStorageDirectory():" + Environment.getExternalStorageDirectory());
        file.download(this, saveFile, new DownloadFileListener(){
            @Override
            public void onSuccess(String s){
            }

            @Override
            public void onFailure(int i, String s){
            }
        });

return saveFile;
    }
    public void register1(View view){
Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, Constant.REGISTER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){


        if(requestCode==Constant.REGISTER && resultCode==RESULT_OK){

            Bundle b=data.getExtras();

            final String name = b.getString("name");
            final  String pwd= b.getString("pwd");
          //根据name得到objectId
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("name", name);
            query.findObjects(this, new FindListener<User>(){
                @Override
                public void onSuccess(List<User> list){
                    User user0 = list.get(0);
                   objectId = user0.getObjectId();
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("pwd", pwd);
                    bundle.putString("objectId",objectId);
                    intent.putExtras(bundle);
                    LoginActivity.this.setResult(RESULT_OK, intent);
                    LoginActivity.this.finish();
                }

                @Override
                public void onError(int i, String s){
                }
            });







        }
    }
}