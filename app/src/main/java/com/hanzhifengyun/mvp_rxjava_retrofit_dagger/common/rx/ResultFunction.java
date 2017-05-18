package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.rx;


import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.BaseModel;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.exception.MyRuntimeException;

import io.reactivex.functions.Function;


public class ResultFunction<T> implements Function<BaseModel<T>, T> {


    @Override
    public T apply(BaseModel<T> baseModel) throws Exception {
        if (baseModel == null) {
            throw new MyRuntimeException("获取数据为空");
        }
        if (!baseModel.isSuccessful()) {
            throw new MyRuntimeException(baseModel.getError().getMessage());
        }

        T t = baseModel.getResult();
        if (t == null) {
            t = (T) "";
        }
        return t;
    }
}
