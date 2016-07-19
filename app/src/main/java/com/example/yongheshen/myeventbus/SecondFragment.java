package com.example.yongheshen.myeventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by yonghe.shen on 16/7/19.
 */
public class SecondFragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View base = inflater.inflate(R.layout.fragment2,null);
        Button send = (Button) base.findViewById(R.id.second_send);
        send.setOnClickListener(this);
        return base;
    }

    @Override
    public void onClick(View view) {
        UserEvent userEvent = new UserEvent();
        userEvent.setUserName("shen");
        EventBus.getDefault().post(userEvent);
    }
}
