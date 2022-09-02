//
//  ContentView.swift
//  EventsMap
//
//  Created by Vladyslav Diachuk on 01/09/2022.
//

import SwiftUI

struct EventsScreen: View {
  @StateObject var vm = EventsVM()
    
  var body: some View {
    VStack {
      EventsList()
    }
    .environmentObject(vm)
    .onAppear{
      vm.onStart()
    }
    .onDisappear{
      vm.onClose()
    }
  }
}


struct EventsScreen_Previews: PreviewProvider {
  static var previews: some View {
    EventsScreen()
  }
}
