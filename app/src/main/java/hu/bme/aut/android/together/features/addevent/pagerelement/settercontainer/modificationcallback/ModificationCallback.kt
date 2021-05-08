package hu.bme.aut.android.together.features.addevent.pagerelement.settercontainer.modificationcallback

interface ModificationCallback {

    fun getCurrentEventTitle(): String

    fun modifyEventTitle(newTitle: String)

    fun isEventInCurrentlyPrivateMode(): Boolean

    fun changeEventPrivateMode(isPrivate: Boolean)

    fun isMaxParticipantCountRuleSet(): Boolean

    fun changeMaxParticipantCountRule(isMaxParticipantCountRuleSet: Boolean)

    fun getMaxParticipantCount(): Int

    fun setMaxParticipantCount(maxParticipantCount: Int)

    fun changeJoinRequestAutoAcceptRule(isJoinRequestAutoAcceptAllowed: Boolean)

    fun isJoinRequestAutoAcceptAllowed(): Boolean

    fun getCategory(): String

    fun changeCategory(newCategory: String)

}