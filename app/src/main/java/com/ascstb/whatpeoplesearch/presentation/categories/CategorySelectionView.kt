package com.ascstb.whatpeoplesearch.presentation.categories


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.ascstb.whatpeoplesearch.databinding.CategorySelectionLayoutBinding
import com.ascstb.whatpeoplesearch.model.Category
import com.ascstb.whatpeoplesearch.core.Game
import kotlinx.android.synthetic.main.category_selection_layout.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CategorySelectionView : Fragment() {
    private val viewModel by viewModel<CategorySelectionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = CategorySelectionLayoutBinding.inflate(inflater, container, false).root.also { layout ->
        viewModel.userName.observe(this, Observer { userName ->
            Timber.d("CategorySelectionView_TAG: onCreateView: userName: $userName")
        })

        viewModel.userName.postValue(Game.currentPlayer.name)

        layout.btnCulture.setOnClickListener { viewModel.onConfirm(Category.CULTURE) }
        layout.btnPeople.setOnClickListener { viewModel.onConfirm(Category.PEOPLE) }
        layout.btnNames.setOnClickListener { viewModel.onConfirm(Category.NAMES) }
        layout.btnQuestions.setOnClickListener { viewModel.onConfirm(Category.QUESTIONS) }
    }
}
