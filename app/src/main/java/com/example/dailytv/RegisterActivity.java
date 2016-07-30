package com.example.dailytv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dailytv.beans.Program;
import com.example.dailytv.beans.User;

import java.util.ArrayList;
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_activty);
        ButterKnife.bind(this);
        setSupportActionBar(registerToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void register2(View view){
        final String name = username1.getText().toString();
        final String pwd = password1.getText().toString();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd) ){
            final User user = new User();
            List<Program> collects = new ArrayList<>();
            user.setName(name);
            user.setPwd(pwd);
            user.setCollects(collects);
            //首先在Bmob端查询用户名是否已经存在
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("name", name);
            query.findObjects(this, new FindListener<User>(){
                @Override
                public void onSuccess(List<User> list){

                    if(list.size() == 0){
                        //用户名不存在，可以注册
                        savaUserInfo(user,name,pwd);
                    }else if(list.size() == 1){
                        Toast.makeText(RegisterActivity.this, "该用户名已存在！", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(int i, String s){
                    Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
        }
    }

    protected void savaUserInfo(User user, final String name,final String pwd){
        user.save(RegisterActivity.this, new SaveListener(){
            @Override
            public void onSuccess(){
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("pwd", pwd);
                intent.putExtras(bundle);
                RegisterActivity.this.setResult(RESULT_OK, intent);
                RegisterActivity.this.finish();
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


}
