package com.example.parentalnotice.presentation.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parentalnotice.data.model.response.NoticeResponseModel
import com.example.parentalnotice.databinding.ItemNoticeBinding

class NoticeAdapter(private val noticeAdapterCallback: NoticeAdapterCallback) :
    ListAdapter<NoticeResponseModel, NoticeAdapter.NoticeViewHolder>(
        NoticeAdapterDiffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        return NoticeViewHolder(
            ItemNoticeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(getItem(position), noticeAdapterCallback)
    }

    class NoticeViewHolder(
        private val itemNoticeBinding: ItemNoticeBinding
    ) : RecyclerView.ViewHolder(itemNoticeBinding.root) {

        fun bind(
            noticeResponseModel: NoticeResponseModel,
            noticeAdapterCallback: NoticeAdapterCallback
        ) {
            itemNoticeBinding.tvNumber.text = "Notice number ${noticeResponseModel.id}"

            itemNoticeBinding.tvTitle.text = noticeResponseModel.title

            itemNoticeBinding.lytCard.setOnClickListener {
                noticeAdapterCallback.onNoticeClicked(adapterPosition, noticeResponseModel)
            }
        }
    }


    object NoticeAdapterDiffUtil : DiffUtil.ItemCallback<NoticeResponseModel>() {
        override fun areItemsTheSame(
            oldItem: NoticeResponseModel,
            newItem: NoticeResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NoticeResponseModel,
            newItem: NoticeResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface NoticeAdapterCallback {
        fun onNoticeClicked(position: Int, noticeResponseModel: NoticeResponseModel)
    }
}