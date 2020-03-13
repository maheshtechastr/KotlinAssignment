package com.mpg.assignmentkotlin.app

import android.app.Application
import com.mpg.assignmentkotlin.di.component.AppComponent
import com.mpg.assignmentkotlin.di.component.DaggerAppComponent


class TodoDemoApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

}