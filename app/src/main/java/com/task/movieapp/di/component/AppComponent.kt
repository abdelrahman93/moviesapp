package com.task.movieapp.di.component

import android.content.Context
import com.task.movieapp.di.module.*
import com.task.movieapp.ui.movies.MoviesFragment
import dagger.BindsInstance
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        SchedulerModule::class,
        UsecaseModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(ingredientsFragment: MoviesFragment)

    @Named(SCHEDULER_MAIN_THREAD)
    fun getMainThread(): Scheduler

    @Named(SCHEDULER_IO)
    fun getIOThread(): Scheduler

}