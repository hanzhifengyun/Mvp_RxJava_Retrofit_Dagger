package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.rx;


import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.BaseModel;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.exception.UnauthorizedException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class CheckCookieFunction<T> implements Function<BaseModel<T>, ObservableSource<BaseModel<T>>> {


    @Override
    public ObservableSource<BaseModel<T>> apply(BaseModel<T> baseModel) throws Exception {
        if (baseModel.isUnauthorized()) {
            return Observable.error(new UnauthorizedException());
        }
        return Observable.just(baseModel);
    }
}
