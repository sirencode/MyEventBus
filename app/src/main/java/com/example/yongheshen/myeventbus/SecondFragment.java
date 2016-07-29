package com.example.yongheshen.myeventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by yonghe.shen on 16/7/19.
 */
public class SecondFragment extends Fragment implements View.OnClickListener{

    private Observable<UserEvent> rxbus;

    @Override
    public void onStart() {
        RxBus.get().register("remote",UserEvent.class);
        rxbus = RxBus.get().register("shen",UserEvent.class);
        rxbus.subscribe(new Action1<UserEvent>() {
            @Override
            public void call(UserEvent userEvent) {
                if (userEvent.getId() == 1000) {
                    System.out.println("finish post rxbus::"+System.currentTimeMillis());
                    Toast.makeText(getActivity(), "tag is shen from first rxbus" + userEvent.getUserName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        super.onStart();
    }

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
        switch (view.getId()){
            case R.id.second_send:
                System.out.println("start post eventbus::"+System.currentTimeMillis());
                UserEvent userEvent = new UserEvent();
                for (int i =0;i<1001;i++){
                    userEvent.setUserName("EventBus::second"+i);
                    userEvent.setId(i);
                    EventBus.getDefault().post(userEvent);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onStop() {
        RxBus.get().unregister("shen",rxbus);
        super.onStop();
    }
}
