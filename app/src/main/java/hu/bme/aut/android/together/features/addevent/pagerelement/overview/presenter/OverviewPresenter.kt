package hu.bme.aut.android.together.features.addevent.pagerelement.overview.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.features.addevent.pagerelement.overview.interactor.OverviewInteractor
import hu.bme.aut.android.together.model.domain.DomainAddableEvent
import hu.bme.aut.android.together.model.domain.DomainUploadResponse
import hu.bme.aut.android.together.model.presentation.AddableEvent
import hu.bme.aut.android.together.model.presentation.UploadResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class OverviewPresenter @Inject constructor(
    private val interactor: OverviewInteractor
) {

    suspend fun uploadEvent(event: AddableEvent) = withIOContext {
        interactor.uploadEvent(event.toDomainAddableEvent()).toUploadResponse()
    }

    private fun AddableEvent.toDomainAddableEvent() : DomainAddableEvent {
        return DomainAddableEvent(
            title,
            isPrivate,
            isMaximumParticipantCountRuleSet,
            maximumParticipantCount,
            isJoinRequestAutoAcceptAllowed,
            category,
            SimpleDateFormat("yyyy.MM.dd. HH:mm", Locale.ENGLISH).run { parse("$startDate $startTime") }!!,
            SimpleDateFormat("yyyy.MM.dd. HH:mm", Locale.ENGLISH).run { parse("$endDate $endTime") }!!,
            location,
            description
        )
    }

    private fun DomainUploadResponse.toUploadResponse(): UploadResponse {
        return UploadResponse(wasSuccessful, errorMessage)
    }

}