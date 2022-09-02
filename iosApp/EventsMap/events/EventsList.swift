//
//  EventsList.swift
//  EventsMap
//
//  Created by Vladyslav Diachuk on 02/09/2022.
//

import Foundation
import SwiftUI

struct EventsList: View {
  @EnvironmentObject var vm: EventsVM
  
  var body: some View {
    VStack {
      List(vm.events) { event in
        Text("\(event)")
      }
    }
  }
}
