package com.lecai.quwen.MainActivity.Fragment.Task.Assemble.MemberInformation;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lecai.quwen.AndroidRX.RxBus;
import com.lecai.quwen.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.MyView.CircleImage;
import com.lecai.quwen.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MemberAdapter extends BaseAdapter implements Callback {
    private Context context;
    private List<MemberBean> list;
    private TextView id,name;

    public MemberAdapter(Context context) {
        this.context = context;
    }

    public List<MemberBean> getList() {
        return list;
    }

    public void setList(List<MemberBean> list) {
        this.list = list;
        notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        return list == null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_act_member_information,parent,false);
//        if(position == 0){
//            ImageView highest = view.findViewById(R.id.item_act_member_highest);
//            highest.setVisibility(View.VISIBLE);
//        }
        name = view.findViewById(R.id.item_act_member_name);
        id = view.findViewById(R.id.item_act_member_id);
        name.setText(list.get(position).getM_name());
        CircleImage headimg = view.findViewById(R.id.item_act_member_headimg);
        headimg.setImageURL(list.get(position).getM_headimg());
        id.setText(list.get(position).getM_uid());
        return view;
    }

    public void addItem(MemberBean item){
        list.add(item);
        notifyDataSetChanged();
    }

    private void getMemberInfo(String u_unionid) throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/user/user_info";
        JSONObject json_post = new JSONObject();
        json_post.put("u_unionid", u_unionid);
        PostServerHeaderJ(url,json_post,"");
    }

    public void PostServerHeaderJ(String url, JSONObject jsonObject, final String Rxid){
        OkHttpClient okhttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;"),jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",MyApplication.getInstance().getUser().getToken())
                .post(requestBody).build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(this);
    }

    private void HandAllUser(String data) throws JSONException {
        JSONObject json_data = new JSONObject(data);
        if(json_data.getInt("return_code") == 1){
            JSONObject json_user_data = json_data.getJSONObject("data");
            String u_unionid = json_user_data.getString("u_unionid");
            String uid = json_user_data.getString("uid");
            String name = json_user_data.getString("name");
            String headimg = json_user_data.getString("headimg");
            int gold = json_user_data.getInt("gold");
            Log.i("WXEntryActivity_TAG",name);
        }
    }

    private void getUser(String m_unionid) throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/user/user_info";
        JSONObject json_post = new JSONObject();
        json_post.put("u_unionid",m_unionid);
        Client.getInstance().PostServer(url,json_post,Rxid.GET_TEAM_ALL_USER);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.i("WXEntryActivity_TAG","网络连接失败");
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String res = response.body().string();
        if(response.isSuccessful()){
            if(res!=null) {
                try {
                    HandAllUser(res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
