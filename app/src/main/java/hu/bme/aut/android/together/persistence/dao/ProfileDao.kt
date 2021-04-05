package hu.bme.aut.android.together.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hu.bme.aut.android.together.model.persistence.PersistedProfileData

@Dao
interface ProfileDao {

    @Query("SELECT * FROM persistedprofiledata WHERE id = :id")
    fun getProfileDataById(id: Long): PersistedProfileData

    @Insert
    fun insertProfileData(profile: PersistedProfileData)
}