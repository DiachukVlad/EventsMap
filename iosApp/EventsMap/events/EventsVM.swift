//
//  EventsVM.swift
//  EventsMap
//
//  Created by Vladyslav Diachuk on 02/09/2022.
//

import Foundation
import shared
import UIKit

class EventsVM: BaseVM, ObservableObject {
  private let vm: EventsViewModel
  
  @Published var events = [Event]()
  @Published var name: String = ""
  @Published var lat: Int64 = 0
  @Published var lon: Int64 = 0

  
  init() {
    vm = KoinHelper().eventsVM()
    super.init(vm)
    
    vm.observeEvents { newEvents in
      self.events.removeAll()
      self.events.append(contentsOf: newEvents)
    }
  }
}
