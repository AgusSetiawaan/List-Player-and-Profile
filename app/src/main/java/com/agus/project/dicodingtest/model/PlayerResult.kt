package com.agus.project.dicodingtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlayerResult(
        @SerializedName("player")
        @Expose
        val player: List<PlayerData>? = null)

data class PlayerData(
        @SerializedName("idPlayer")
        @Expose
        val idPlayer: String,
        @SerializedName("idTeam")
        @Expose
        val idTeam: String,
        @SerializedName("strPlayer")
        @Expose
        val strPlayer: String,
        @SerializedName("strTeam")
        @Expose
        val strTeam: String,
        @SerializedName("dateBorn")
        @Expose
        val dateBorn: String,
        @SerializedName("strNumber")
        @Expose
        val strNumber: String,
        @SerializedName("dateSigned")
        @Expose
        val dateSigned: String,
        @SerializedName("strWage")
        @Expose
        val strWage: String,
        @SerializedName("strDescriptionEN")
        @Expose
        val strDescriptionEN: String,
        @SerializedName("strCutout")
        @Expose
        val strCutout: String,
        @SerializedName("strNationality")
        @Expose
        val strNationality: String,
        @SerializedName("strPosition")
        @Expose
        val strPosition: String,
        @SerializedName("strHeight")
        @Expose
        val strHeight: String,
        @SerializedName("strWeight")
        @Expose
        val strWeight: String,
        @SerializedName("strThumb")
        @Expose
        val strThumb: String

)
