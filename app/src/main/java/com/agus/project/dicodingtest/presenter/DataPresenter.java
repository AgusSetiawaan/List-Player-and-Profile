package com.agus.project.dicodingtest.presenter;

import com.agus.project.dicodingtest.model.PlayerResult;
import com.agus.project.dicodingtest.network.PlayersDataSource;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DataPresenter {

    private CompositeDisposable composite = new CompositeDisposable();
    private View view;

    public interface View{

        void onSuccessGetPlayers(PlayerResult playerResult);

        void onErrorGetPlayers(Throwable e);

    }

    public DataPresenter(View view) {
        this.view = view;
    }

    public void getEverything(Long idTeam){

        PlayersDataSource.getService().getPlayers(idTeam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayerResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        composite.add(d);
                    }

                    @Override
                    public void onNext(PlayerResult playerResult) {
                        view.onSuccessGetPlayers(playerResult);
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
}