//
//  DetailsSnapshots.swift
//  DesafioMobileTests
//
//  Created by Eric Winston on 8/31/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Quick
import Nimble
import Nimble_Snapshots

@testable import DesafioMobile

class DetailsSnapshots: QuickSpec{
    
    override func spec() {
        let cast = CastMock()
        
        describe("Details Screen Visual check") {
            it("Should look like this"){
                let frame = UIScreen.main.bounds
                let view =  DetailsView(frame: frame)
                
                view.imageView.backgroundColor = .red
                view.verticalContainer.backgroundColor = .blue
                view.castView.backgroundColor = .green
                view.descLabel.backgroundColor = .yellow
                
                expect(view) == snapshot("DetailView")
            }
        }
        
        describe("CastViewCell Visual check") {
            it("Should look like this"){
                let cell = CastViewCell()
                
                cell.configure(withViewModel: cast.actors[0], actorImage: UIImage())
                cell.frame = CGRect(x: 0, y: 0, width: 150, height: 300)
                
                expect(cell) == snapshot("CastView")
            }
        }
    }
}
