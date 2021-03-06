package hu.bme.aut.android.together.model.presentation.comparator

import androidx.recyclerview.widget.DiffUtil
import hu.bme.aut.android.together.model.presentation.EventQuestion

object EventQuestionComparator: DiffUtil.ItemCallback<EventQuestion>()  {
    override fun areItemsTheSame(oldItem: EventQuestion, newItem: EventQuestion): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: EventQuestion, newItem: EventQuestion): Boolean {
        return oldItem == newItem
    }
}