//
//  ContentView.swift
//  EventsMap
//
//  Created by Vladyslav Diachuk on 01/09/2022.
//

import SwiftUI
import shared

struct HomeScreen: View {
  @StateObject var vm = HomeVM()
    
  var body: some View {
      HomeView(consultancies: vm.consultancies)
        .onAppear{
          vm.onStart()
        }
        .onDisappear{
          vm.onClose()
        }
  }
}


struct HomeView: View {
    
    var consultancies: [Consultancy]
    
    var body: some View {
        VStack {
            ForEach(consultancies, id: \Consultancy.name) { consultancy in
                Text(consultancy.name)
            }
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView(consultancies: [
            Consultancy(name: "Vlad Industries"),
            Consultancy(name: "Taras Industries"),
            Consultancy(name: "Anime Industries"),
        ])
    }
}
