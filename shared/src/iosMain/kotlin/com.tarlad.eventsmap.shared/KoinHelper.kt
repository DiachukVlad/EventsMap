package com.tarlad.eventsmap.shared

import com.tarlad.eventsmap.shared.consultancy.HomeViewModel
import com.tarlad.eventsmap.shared.di.sharedModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(sharedModule)
    }
}
class KoinHelper: KoinComponent {
    private val eventsViewModel: HomeViewModel by inject()
    fun homeVM(): HomeViewModel = eventsViewModel
}