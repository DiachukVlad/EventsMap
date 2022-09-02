//
//  ContentView.swift
//  EventsMap
//
//  Created by Vladyslav Diachuk on 01/09/2022.
//

import SwiftUI
import models
import sharedClient

struct ContentView: View {
  var vm = EventsViewModel(client: ClientExteKt.client)
    
  var body: some View {
    Text("Hello, world!")
      .padding()
      .onAppear{
        vm.onStart()
        
        vm.nameC.watch { name in
          print(name!)
        }
       
        vm.observeEvents { events in
          print(events)
        }
      }
      .onDisappear{
        vm.onClose()
      }
      .onTapGesture {
        print("Events = \(String(describing: vm.events.value as? [Event]))")
      }
  }
}

struct ContentView_Previews: PreviewProvider {
  static var previews: some View {
    ContentView()
  }
}
