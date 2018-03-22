package com.ltg263.happy_dzh.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ltg263.happy_dzh.R;

/**
 * 作者： litongge
 * 时间： 2017/3/5 20:27
 * 邮箱；ltg263@126.com
 * 描述：dialog,退出框，调用的方法,直接调用这个方法，第二个参数，直接重写接口，接口里面的方法，写你要执行的操作！
 */
public class Dialog_Exit {
    private Dialog dialog;



    /**
     * 单个按钮，不设置监听
     * @param context
     * @param title
     */
    public static void showDialogNoListen(Context context, String title) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_exit, null);
        TextView bt_ok = (TextView) view.findViewById(R.id.bt_confirm);
        TextView suanle = (TextView) view.findViewById(R.id.bt_suanle);
        suanle.setVisibility(View.GONE);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(title);
        suanle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog5.dismiss();
            }
        });
        bt_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog5.dismiss();
            }
        });

        dialog5.setContentView(view);
        dialog5.show();
    }
    /**
     * 单个按钮，设置监听
     * @param context
     * @param title
     */
    public static void showDialogOneBtn(Context context, String title, final DialogConfirm dialogConfirm) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        dialog5.setCancelable(false);
        dialog5.setCanceledOnTouchOutside(false);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_exit, null);
        TextView bt_ok = (TextView) view.findViewById(R.id.bt_confirm);
        TextView suanle = (TextView) view.findViewById(R.id.bt_suanle);
        suanle.setVisibility(View.GONE);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(title);
        suanle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog5.dismiss();
            }
        });
        bt_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog5.dismiss();
                if (dialogConfirm != null) {
                    dialogConfirm.confirm();
                }
            }
        });

        dialog5.setContentView(view);
        dialog5.show();
    }
    public interface DialogConfirm {
        void confirm();
    }
}
