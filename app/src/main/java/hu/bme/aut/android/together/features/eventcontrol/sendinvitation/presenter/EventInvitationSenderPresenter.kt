package hu.bme.aut.android.together.features.eventcontrol.sendinvitation.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.features.eventcontrol.sendinvitation.interactor.EventInvitationSenderInteractor
import hu.bme.aut.android.together.model.domain.DomainEventShortInfo
import hu.bme.aut.android.together.model.presentation.EventShortInfo
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventInvitationSenderPresenter @Inject constructor(
    private val interactor: EventInvitationSenderInteractor
) {

    suspend fun loadEventDetails(eventId: Long) = withIOContext {
        interactor.getEventShortInfo(eventId).toEventShortInfo()
    }

    suspend fun sendEventInvitations(eventId: Long, userNameList: List<String>) = withIOContext {
        interactor.sendEventInvitationsAndGetResponse(eventId, userNameList)
    }

    private fun DomainEventShortInfo.toEventShortInfo(): EventShortInfo {
        return EventShortInfo(
            eventId,
            name,
            location,
            convertDateToRepresentedDateFormat(startDate),
            convertDateToRepresentedDateFormat(endDate),
            imageUrl
        )
    }

    private fun convertDateToRepresentedDateFormat(date: Date): String {
        return SimpleDateFormat("EEEE, MMM dd - HH:mm", Locale.ENGLISH).format(date)
    }
}