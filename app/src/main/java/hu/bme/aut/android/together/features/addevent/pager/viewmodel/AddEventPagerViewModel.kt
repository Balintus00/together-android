package hu.bme.aut.android.together.features.addevent.pager.viewmodel

import androidx.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.model.presentation.AddableEvent
import javax.inject.Inject

@HiltViewModel
class AddEventPagerViewModel @Inject constructor() :
    RainbowCakeViewModel<AddEventPagerState>(Loaded) {

    val addableEvent: MutableLiveData<AddableEvent> = MutableLiveData(
        AddableEvent(
            "",
            isPrivate = false,
            isMaximumParticipantCountRuleSet = false,
            0,
            isJoinRequestAutoAcceptAllowed = false,
            category = ""
        )
    )

}