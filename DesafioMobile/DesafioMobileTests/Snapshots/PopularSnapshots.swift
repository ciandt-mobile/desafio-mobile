//
//  PopularSnapshots.swift
//  DesafioMobileTests
//
//  Created by Eric Winston on 8/31/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Quick
import Nimble
import Nimble_Snapshots

@testable import DesafioMobile

class PopularSnapshots: QuickSpec{
    
    override func spec() {
        
        let movie = MovieMock()
        
        describe("Popular Grid Visual check") {
            it("Should look like this"){
                let frame = UIScreen.main.bounds
                let view =  PopularView(frame: frame)
                
                view.collectionView.backgroundColor = .white
                
                expect(view) == snapshot("PopularView")
            }
        }
        
        describe("Popular Grid Cell Visual check") {
            it("Should look like this"){
                let cell = PopularViewCell()
                
                cell.configure(withViewModel: movie)
                cell.frame = CGRect(x: 0, y: 0, width: 150, height: 300)
                let view =  cell
                
                expect(view) == snapshot("PopularGridCell")
            }
        }
    }
}
