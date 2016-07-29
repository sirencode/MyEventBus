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
import org.greenrobot.eventbus.Subscribe;

import rx.Observable;

/**
 * Created by yonghe.shen on 16/7/18.
 */
public class FirstFragment extends Fragment implements View.OnClickListener{

    private Observable<UserEvent> rxbus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment, container, false);
        Button send = (Button) baseView.findViewById(R.id.send);
        send.setOnClickListener(this);
        return baseView;
    }

    @Override
    public void onClick(View view) {
        System.out.println("start post rxbus::"+System.currentTimeMillis());
        UserEvent userEvent = new UserEvent();
        for (int i = 0;i<1001;i++){
            userEvent.setUserName("rxbus shen"+i);
            userEvent.setId(i);
            RxBus.get().post("shen", userEvent);
        }
    }

    //定义处理接收方法，主线程
    @Subscribe
    public void onEventMainThread(UserEvent event) {
        if (event.getId() == 1000) {
            System.out.println("finish post eventbus::"+System.currentTimeMillis());
            Toast.makeText(getActivity(), "from second" + event.getUserName(), Toast.LENGTH_SHORT).show();
        }
    }

//    /**
//     * 和发送者统一线程
//     * @param event
//     */
//    @Subscribe
//    public void onEvent(UserEvent event){
//        Toast.makeText(getActivity(), event.getUserName(), Toast.LENGTH_SHORT).show();
//    }
//
//    /**
//     * 如果事件是在UI线程中发布出来的，那么onEventBackground就会在子线程中运行，如果事件本来就是子线程中发布出来的
//     * ，那么onEventBackground函数直接在该子线程中执行
//     * @param event
//     */
//    @Subscribe
//    public void onEventBackground(UserEvent event){
//        Toast.makeText(getActivity(), event.getUserName(), Toast.LENGTH_SHORT).show();
//    }
//
//    /**
//     * 使用这个函数作为订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程在执行onEventAsync。
//     * @param event
//     */
//    @Subscribe
//    public void onEventAsync(UserEvent event){
//        Toast.makeText(getActivity(), event.getUserName(), Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
