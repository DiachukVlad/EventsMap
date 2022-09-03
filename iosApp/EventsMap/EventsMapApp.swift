//
//  EventsMapApp.swift
//  EventsMap
//
//  Created by Vladyslav Diachuk on 01/09/2022.
//

import SwiftUI
import shared

@main
struct EventsMapApp: App {
  init(){
    KoinHelperKt.doInitKoin()
  }
  
  var body: some Scene {
    WindowGroup {
      EventsScreen()
    }
  }
}
