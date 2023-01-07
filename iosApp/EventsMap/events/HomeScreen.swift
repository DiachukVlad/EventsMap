//
//  ContentView.swift
//  EventsMap
//
//  Created by Vladyslav Diachuk on 01/09/2022.
//

import SwiftUI

struct HomeScreen: View {
  @StateObject var vm = HomeVM()
    
  var body: some View {
    VStack {
        Text("qwe")
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


struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
