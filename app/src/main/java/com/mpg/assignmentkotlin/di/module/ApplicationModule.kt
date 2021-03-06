package com.mpg.assignmentkotlin.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.mpg.assignmentkotlin.app.TodoDemoApp
import com.mpg.assignmentkotlin.presentation.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

//    @Provides
//    fun provideApplication(todoDemoApp: TodoDemoApp): TodoDemoApp = todoDemoApp

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

}