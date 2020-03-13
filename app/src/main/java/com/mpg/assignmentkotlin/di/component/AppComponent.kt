package com.mpg.assignmentkotlin.di.component

import android.app.Application
import com.mpg.assignmentkotlin.di.module.ApplicationModule
import com.mpg.assignmentkotlin.di.module.NetworkModule
import com.mpg.assignmentkotlin.presentation.view.activities.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface AppComponent {
    fun inject(app: MainActivity)


    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

}
