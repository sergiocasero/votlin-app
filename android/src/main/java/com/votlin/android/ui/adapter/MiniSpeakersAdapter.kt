package com.votlin.android.ui.adapter

import android.view.View
import com.votlin.android.R
import com.votlin.android.extensions.load
import com.votlin.common.model.Speaker
import kotlinx.android.synthetic.main.item_speaker_mini.view.*

class MiniSpeakersAdapter(onItemClickListener: (Speaker) -> Unit) : RootAdapter<Speaker>(onItemClickListener = onItemClickListener) {

    override val itemLayoutId: Int = R.layout.item_speaker_mini

    override fun viewHolder(view: View): RootViewHolder<Speaker> = MiniSpeakerViewHolder(itemView = view)

    class MiniSpeakerViewHolder(itemView: View) : RootViewHolder<Speaker>(itemView = itemView) {

        override fun bind(model: Speaker) {
            itemView.name.text = model.name
            itemView.image.load(url = model.photoUrl, circleCrop = true)
        }
    }

}