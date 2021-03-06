package com.ascstb.whatpeoplesearch.presentation.players


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.ascstb.whatpeoplesearch.R
import com.ascstb.whatpeoplesearch.databinding.PlayerSelectionLayoutBinding
import com.ascstb.whatpeoplesearch.core.Game
import com.ascstb.whatpeoplesearch.model.Player
import kotlinx.android.synthetic.main.player_selection_layout.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerSelectionView : Fragment() {
    private val viewModel by viewModel<PlayerSelectionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = PlayerSelectionLayoutBinding.inflate(inflater, container, false).root.also { layout ->
        //region config Number Picker
        layout.usersNumber.minValue = 1
        layout.usersNumber.maxValue = 8
        layout.usersNumber.setOnValueChangedListener { _, _, newVal ->
            viewModel.users = newVal
            updateUsersList(layout)
        }
        //endregion

        //region on Confirm
        layout.btnConfirm.setOnClickListener {
            val players: MutableList<Player> = mutableListOf()
            layout.playersLayout.children.forEach { child ->
                if (child !is EditText) return@forEach

                val playerName = if(child.text.trim().isEmpty()) child.hint else child.text.trim()
                players.add(Player(name = playerName.toString()))
            }

            Game.players = players
            viewModel.onConfirm()
        }
        //endregion

        updateUsersList(layout)
    }

    private fun updateUsersList(layout: View) {
        layout.playersLayout.removeAllViews()

        for (i: Int in 0 until viewModel.users) {
            layout.playersLayout.addView(
                EditText(this.context).apply {
                    hint = context.getString(R.string.user_name_hint, (i + 1).toString())
                    setSingleLine(true)
                    inputType = EditorInfo.TYPE_TEXT_VARIATION_PERSON_NAME
                    imeOptions = EditorInfo.IME_ACTION_NEXT
                    setTextAppearance(R.style.GeneralInput)
                }
            )
        }
    }
}
