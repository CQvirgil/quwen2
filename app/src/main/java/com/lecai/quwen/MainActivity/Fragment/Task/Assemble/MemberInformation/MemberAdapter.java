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
import com.lecai.quwen.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.functions.Consumer;

public class MemberAdapter extends BaseAdapter implements Consumer {
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
        RxBus.getInstance().subscribe(String.class,this);
        name = view.findViewById(R.id.item_act_member_name);
        id = view.findViewById(R.id.item_act_member_id);
        name.setText(list.get(position).getM_name());
        return view;
    }

    public void addItem(MemberBean item){
        list.add(item);
        notifyDataSetChanged();
    }

    @Override
    public void accept(Object o) throws Exception {
        String object = (String) o;
        String rxid = object.substring(0,5);
        String data = object.substring(5);
        if(rxid.equals(Rxid.GET_TEAM_ALL_USER)){
            HandAllUser(data);
        }
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
            Log.i("WXEntryActivity_TAG",uid);
            notifyDataSetChanged();
        }
    }

    private void getUser(String m_unionid) throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/user/user_info";
        JSONObject json_post = new JSONObject();
        json_post.put("u_unionid",m_unionid);
        Client.getInstance().PostServer(url,json_post,Rxid.GET_TEAM_ALL_USER);
    }
}
