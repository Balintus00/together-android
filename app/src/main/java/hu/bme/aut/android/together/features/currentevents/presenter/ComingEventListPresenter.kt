package hu.bme.aut.android.together.features.currentevents.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.features.currentevents.interactor.EventListInteractor
import hu.bme.aut.android.together.model.presentation.EventShortInfo
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ComingEventListPresenter @Inject constructor(
    private val eventListInteractor: EventListInteractor
) {

    suspend fun loadPastEventShortInfoByProfileId(profileId: Long) = withIOContext {
        eventListInteractor.getComingEventShortInfoByProfileId(profileId)
            .map { domainShortInfoModel ->
                EventShortInfo(
                    domainShortInfoModel.eventId,
                    domainShortInfoModel.name,
                    domainShortInfoModel.location,
                    convertDateToRepresentedDateFormat(domainShortInfoModel.startDate),
                    convertDateToRepresentedDateFormat(domainShortInfoModel.endDate),
                    domainShortInfoModel.imageUrl
                )
            }
    }

    private fun convertDateToRepresentedDateFormat(date: Date): String {
        return SimpleDateFormat("EEEE, MMM dd - HH:mm", Locale.ENGLISH).format(date)
    }

}