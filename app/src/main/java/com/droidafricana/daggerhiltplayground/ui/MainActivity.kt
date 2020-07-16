package com.droidafricana.daggerhiltplayground.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droidafricana.daggerhiltplayground.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        //Injection happens here just like with normal Dagger impl
        super.onCreate(savedInstanceState)
        //This has to be done after the call to super
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)

    }
}



















