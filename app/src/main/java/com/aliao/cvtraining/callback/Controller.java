package com.aliao.cvtraining.callback;

/**
 * Created by 丽双 on 2015/9/8.
 */
public class Controller {

    public ICallBack iCallBack;

    public Controller(ICallBack iCallBack) {
        this.iCallBack = iCallBack;
    }

    public void begin(){
        iCallBack.run();
    }

    public void setCallBack(ICallBack callBack){
        iCallBack = callBack;
    }
}
