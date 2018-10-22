package com.votlin.android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import com.votlin.android.R
import com.votlin.android.extensions.hideMe
import com.votlin.android.extensions.load
import com.votlin.model.Talk
import com.votlin.model.Track
import kotlinx.android.synthetic.main.item_talk.view.*
import kotlinx.android.synthetic.main.view_speaker.view.*

class TalksAdapter(onItemClick: (Talk) -> Unit) : RootAdapter<Talk>(onItemClickListener = onItemClick) {

    override val itemLayoutId: Int = R.layout.item_talk

    override fun viewHolder(view: View): RootViewHolder<Talk> {
        val viewHolder = TalkViewHolder(view)
        viewHolder.setIsRecyclable(false)
        return viewHolder
    }

    class TalkViewHolder(itemView: View) : RootViewHolder<Talk>(itemView = itemView) {

        override fun bind(model: Talk) {
            itemView.title.text = model.name

            val color = when (model.track) {
                Track.BUSINESS -> R.color.track_business
                Track.DEVELOPMENT -> R.color.track_development
                Track.MAKER -> R.color.track_maker
                Track.ALL -> {
                    itemView.setOnClickListener {}
                    R.color.track_all
                }

            }

            itemView.title.setBackgroundResource(color)

            if (model.speakers.isEmpty()) {
                itemView.speakers.hideMe()
            } else {
                model.speakers.forEach { speaker ->
                    val view = LayoutInflater.from(itemView.context).inflate(R.layout.view_speaker, null, false)
                    view.name.text = speaker.name
                    view.image.load(speaker.photoUrl)
                    itemView.speakers.addView(view)
                }
            }
        }
    }
}