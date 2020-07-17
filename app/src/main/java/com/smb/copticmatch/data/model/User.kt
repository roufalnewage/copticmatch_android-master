package com.smb.copticmatch.data.model

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */
import androidx.annotation.NonNull
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
  data class User(

        @field:SerializedName("speciality")
        var name: String? = null,

        @NonNull
        @field:SerializedName("spec_id")
        var id: Int = 0

)