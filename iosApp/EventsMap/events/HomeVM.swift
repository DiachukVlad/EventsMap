//
//  EventsVM.swift
//  EventsMap
//
//  Created by Vladyslav Diachuk on 02/09/2022.
//

import Foundation
import shared
import UIKit

class HomeVM: BaseVM, ObservableObject {
    private let vm: HomeViewModel
    
    @Published var consultancies = [Consultancy]()
    
    init() {
        vm = KoinHelper().homeVM()
        super.init(vm)
        vm.observeState(callback: { consultancies in
            DispatchQueue.main.async {
                self.consultancies.removeAll()
                self.consultancies.append(contentsOf: consultancies)
            }
        })
    }
}
