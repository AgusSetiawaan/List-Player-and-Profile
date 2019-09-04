package com.agus.project.dicodingtest.presenter;

import android.content.Context;
import android.util.Log;

import com.agus.project.dicodingtest.model.PlayerResult;
import com.agus.project.dicodingtest.network.PlayersDataSource;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class DataPresenter {

    private CompositeDisposable composite = new CompositeDisposable();
    private View view;
    private Context context;

    public interface View{

        void onSuccessGetPlayers(PlayerResult playerResult);

        void onErrorGetPlayers(Throwable e);

        void onErrorConnection();

    }

    public DataPresenter(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void getEverything(Long idTeam){

        if(PlayersDataSource.isInternetOn(context)) {

            PlayersDataSource.getService().getPlayers(idTeam)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<PlayerResult>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            composite.add(d);
                        }

                        @Override
                        public void onNext(Response<PlayerResult> response) {
                            Log.e("ini response code nya", "" + response.code());
                            if (response.code() == 200) {
                                view.onSuccessGetPlayers(response.body());
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            view.onErrorGetPlayers(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        else{
            view.onErrorConnection();
        }

    }
}