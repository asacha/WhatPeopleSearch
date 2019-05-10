package com.ascstb.whatpeoplesearch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ascstb.whatpeoplesearch.R
import com.ascstb.whatpeoplesearch.core.Navigation
import org.koin.android.ext.android.inject

class RootActivity : AppCompatActivity() {
    private val navigation by inject<Navigation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_activity_layout)

        navigation.setFragmentManager(supportFragmentManager)
        navigation.navigateToUserSelection()
    }
}
