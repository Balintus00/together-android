package hu.bme.aut.android.together.features.eventcontrol.details.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.features.eventcontrol.details.interactor.EventDetailsInteractor
import hu.bme.aut.android.together.model.domain.DomainEventDetails
import hu.bme.aut.android.together.model.presentation.EventDetails
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventDetailsPresenter @Inject constructor(
    private val interactor: EventDetailsInteractor
) {

    suspend fun getEventDetailsById(eventId: Long) = withIOContext {
        interactor.getEventDetailsByEventId(eventId).toEventDetails()
    }

    private fun DomainEventDetails.toEventDetails(): EventDetails {
        return EventDetails(
            id,
            title,
            imageUrl,
            category,
            convertDateToRepresentedDateFormat(startDate),
            convertDateToRepresentedDateFormat(endDate),
            location,
            description,
            isParticipantCountLimited,
            maxParticipantCount,
            currentParticipantCount,
            isPrivate,
            isParticipant,
            isOrganiser
        )
    }

    private fun convertDateToRepresentedDateFormat(date: Date): String {
        return SimpleDateFormat("EEEE, MMM dd - HH:mm", Locale.ENGLISH).format(date)
    }

}