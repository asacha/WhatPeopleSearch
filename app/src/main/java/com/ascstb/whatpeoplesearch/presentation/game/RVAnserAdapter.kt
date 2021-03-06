package com.ascstb.whatpeoplesearch.presentation.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ascstb.whatpeoplesearch.R
import com.ascstb.whatpeoplesearch.model.GoogleAnswer

class RVAnswerAdapter(
    private var answerList: MutableList<GoogleAnswer>
) : RecyclerView.Adapter<RVAnswerAdapter.ViewHolder>() {
    var type: AnswerViewType = AnswerViewType.HIDDEN

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvHidden: TextView? = null
        var tvAnswer: TextView? = null
        var tvPoints: TextView? = null

        init {
            tvHidden = itemView.findViewById(R.id.tvHidden)
            tvAnswer = itemView.findViewById(R.id.tvAnswer)
            tvPoints = itemView.findViewById(R.id.tvPoints)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (answerList[position].uncovered) {
            return AnswerViewType.SHOWN.typeId
        } else {
            return AnswerViewType.HIDDEN.typeId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = when (viewType) {
            AnswerViewType.SHOWN.typeId -> {
                type = AnswerViewType.SHOWN
                R.layout.answer_item_layout
            }
            else -> {
                type = AnswerViewType.HIDDEN
                R.layout.hidden_answer_item_layout
            }
        }
        return ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val answer = answerList[position]
        holder.tvAnswer?.text = answer.answer
        holder.tvPoints?.text = answer.points.toString()
        holder.tvHidden?.text = (answer.position).toString()
    }

    override fun getItemCount(): Int = answerList.size

    fun updateAnswers(newAnswers: List<GoogleAnswer>) {
        answerList.clear()
        answerList.addAll(newAnswers)
        notifyDataSetChanged()
    }

    enum class AnswerViewType(val typeId: Int) {
        SHOWN(1),
        HIDDEN(2)
    }
}