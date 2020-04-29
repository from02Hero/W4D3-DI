package com.example.w4d3_di.inject

import android.app.Application
import android.content.Context
import com.example.w4d3_di.db.AppDatabase
import com.example.w4d3_di.model.local.WordDAO
import com.example.w4d3_di.model.network.UrbanRepositoryDagger
import com.example.w4d3_di.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Now, classes annotated with @Singleton will be scoped to AppComponent
@Singleton
// Definition of a Dagger component that adds info from the StorageModule to the graph
@Component(modules = [NetworkModule::class, AppModule::class, RoomModule::class])
interface AppComponent {
    /***
     * Use @BindsInstance for objects that are constructed outside of the graph
     * (e.g. instances of Context).
     */
    // Factory to create instances of the AppComponent
//    @Component.Factory
//    interface Factory {
//        // With @BindsInstance, the Context passed in will be available in the graph
//        fun create(@BindsInstance context: Context): AppComponent
//    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)

    fun wordDAO(): WordDAO

    fun demoDatabase(): AppDatabase

    fun urbanRepository(): UrbanRepositoryDagger

    fun application(): Application
}