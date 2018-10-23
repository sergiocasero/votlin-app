package com.votlin.android.ui.adapter

import android.view.View
import com.votlin.android.R
import com.votlin.android.extensions.hideMe
import com.votlin.android.extensions.load
import com.votlin.android.extensions.showMe
import com.votlin.model.Speaker
import kotlinx.android.synthetic.main.item_speaker.view.*

class SpeakersAdapter(val onLinkedInClicked: (String) -> Unit = {}, val onTwitterClicked: (String) -> Unit = {}) :
        RootAdapter<Speaker>() {

    override val itemLayoutId: Int = R.layout.item_speaker

    override fun viewHolder(view: View): RootViewHolder<Speaker> =
            SpeakerViewHolder(itemView = view,
                    onLinkedInClicked = { onLinkedInClicked(items[it].linkedin) },
                    onTwitterClicked = { onTwitterClicked(items[it].twitter) })

    class SpeakerViewHolder(val onLinkedInClicked: (Int) -> Unit,
                            val onTwitterClicked: (Int) -> Unit,
                            itemView: View) : RootViewHolder<Speaker>(itemView = itemView) {

        init {
            itemView.linkedin.setOnClickListener { onLinkedInClicked(adapterPosition) }
            itemView.twitter.setOnClickListener { onTwitterClicked(adapterPosition) }
        }

        override fun bind(model: Speaker) {
            itemView.name.text = model.name
            itemView.description.text = model.bio
            itemView.image.load(url = model.photoUrl, circleCrop = true)

            if (model.linkedin.isNotEmpty() and model.twitter.isNotEmpty()) {
                itemView.linkedin.showMe()
                itemView.twitter.showMe()
                itemView.space.showMe()
            } else {
                itemView.space.hideMe()
                if (model.linkedin.isEmpty()) {
                    itemView.linkedin.hideMe()
                } else {
                    itemView.linkedin.showMe()
                }

                if (model.twitter.isEmpty()) {
                    itemView.twitter.hideMe()
                } else {
                    itemView.twitter.showMe()
                }
            }

        }
    }

}