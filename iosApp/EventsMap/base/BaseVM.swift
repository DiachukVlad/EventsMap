//
//  BaseVM.swift
//  EventsMap
//
//  Created by Vladyslav Diachuk on 02/09/2022.
//

import Foundation
import shared

class BaseVM {
  private let vm: BaseViewModel
  
  init(_ vm: BaseViewModel) {
    self.vm = vm
  }
  
  func onStart() {
    vm.onStart()
  }
  
  func onClose() {
    vm.onClose()
  }
}
