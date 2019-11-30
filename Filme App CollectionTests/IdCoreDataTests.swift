//
//  CollectionViewControllerTests.swift
//  Filme App CollectionTests
//
//  Created by Miguel Fernandes Lopes on 29/11/19.
//  Copyright © 2019 Miguel Fernandes Lopes. All rights reserved.
//

import XCTest
import CoreData
@testable import Filme_App_Collection

class IdCoreDataTests: XCTestCase {

    // MARK: - Properties
    
    private var idCoreData: [Int] = []
    private var existe = false
    private var Verifica: DescricaoViewController?
    
    override func setUp() {
        super.setUp()
        idCoreData = [105486,162489,251234]
        print(idCoreData)
    }

    override func tearDown() {
        idCoreData = []
    }

    func testExample() {
        for idCoreData in (Verifica?.self.dadosID)! {
            if idCoreData == Verifica?.self.dados_json[0].id {
                existe = true
            }
        }
        if Verifica?.verifID() == existe {
            print("Erro, nao existe")
        }
    }

    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }

}
