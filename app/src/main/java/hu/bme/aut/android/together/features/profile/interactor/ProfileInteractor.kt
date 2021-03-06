package hu.bme.aut.android.together.features.profile.interactor

import hu.bme.aut.android.together.model.domain.DomainProfileData
import hu.bme.aut.android.together.network.NetworkDataSource
import hu.bme.aut.android.together.persistence.repository.ProfileRepository
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val profileRepository: ProfileRepository
) {

    fun getProfileDataById(id: Long) : DomainProfileData {
        return networkDataSource.getUserProfileById(id)?.let { profileData ->
            profileRepository.saveProfileData(profileData)
            profileData
        } ?: profileRepository.loadProfileData(id)
    }

}