package com.votlin.android.ui.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.votlin.android.R
import com.votlin.android.extensions.hideMe
import com.votlin.model.Talk
import com.votlin.model.Track
import kotlinx.android.synthetic.main.item_talk.view.*

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
                Track.DEVELOPMENT -> {
                    itemView.title.setTextColor(ContextCompat.getColor(itemView.context, R.color.dark_title))
                    R.color.track_development
                }
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
                val speakersAdapter = MiniSpeakersAdapter { onItemClickListener(adapterPosition) }
                speakersAdapter.addAll(model.speakers.toMutableList())

                itemView.speakers.adapter = speakersAdapter
                itemView.speakers.layoutManager = LinearLayoutManager(itemView.context)
            }
        }
    }
}