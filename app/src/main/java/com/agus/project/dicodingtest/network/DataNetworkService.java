package com.agus.project.dicodingtest.network;

import com.agus.project.dicodingtest.model.PlayerResult;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataNetworkService {

    @GET("lookup_all_players.php")
    Observable<PlayerResult> getPlayers(@Query("id") Long idTeam);
}
