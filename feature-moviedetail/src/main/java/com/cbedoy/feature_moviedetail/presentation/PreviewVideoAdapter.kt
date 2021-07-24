package com.cbedoy.feature_moviedetail.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cbedoy.feature_moviedetail.R
import com.cbedoy.feature_moviedetail.databinding.ViewHolderPreviewBinding
import com.cbedoy.feature_moviedetail.presentation.model.PreviewVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class PreviewVideoAdapter : RecyclerView.Adapter<PreviewVideoViewHolder>() {

    var dataSource: List<PreviewVideo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewVideoViewHolder {
        return PreviewVideoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.view_holder_preview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PreviewVideoViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}

class PreviewVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(previewVideo: PreviewVideo) {
        with(ViewHolderPreviewBinding.bind(itemView)) {
            titleView.text = previewVideo.title
            previewView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(previewVideo.youtubeId, 0f)
                }
            })
        }
    }
}