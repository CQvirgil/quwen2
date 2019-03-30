package com.lecai.quwen.MainActivity.Fragment.Task.Mentor.BindingMaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lecai.quwen.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class BindingMasterAdapter extends BaseAdapter {
    private Context context;
    private boolean isadd = false;
    private List<MasterBean> list;

    public boolean isIsadd() {
        return isadd;
    }

    public List<MasterBean> getList() {
        return list;
    }

    public void setList(List<MasterBean> list) {
        this.list = list;
    }

    public void setIsadd(boolean isadd) {
        this.isadd = isadd;
    }

    public BindingMasterAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_act_binding_master,parent,false);
        Button btn = view.findViewById(R.id.item_act_binding_master_btn);
        if(isadd){
            btn.setText("添加");
        }
        TextView text_name = view.findViewById(R.id.item_act_binding_master_name);
        text_name.setText(list.get(position).getName());
        Button btn_binding_master = view.findViewById(R.id.item_act_binding_master_btn);
        btn_binding_master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sub_unionid = MyApplication.getInstance().getUser().getU_unionid();
                String dom_unionid = list.get(position).getU_unionid();
                String sub_name = MyApplication.getInstance().getUser().getName();
                String text = "爸爸";
                try {
                    disciple_apply(sub_unionid,dom_unionid,sub_name,text);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private void disciple_apply(String sub_unionid,String dom_unionid,String sub_name,String text) throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/disciple/disciple_apply";
        JSONObject json_post = new JSONObject();
        json_post.put("sub_unionid",sub_unionid);
        json_post.put("dom_unionid",dom_unionid);
        json_post.put("sub_name",sub_name);
        json_post.put("text",text);
        Client.getInstance().PostServer(url,json_post,Rxid.BINDING_MASTER);
    }
}
