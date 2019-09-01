//
//  MovieSpec.swift
//  DesafioMobileTests
//
//  Created by Eric Winston on 9/1/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Foundation
import Quick
import Nimble
import Nimble_Snapshots

@testable import DesafioMobile

class MovieSpec: QuickSpec{
    
    override func spec() {
        
        let apiMock = APIMock()
        let mock = MovieMock()
        let viewModel = MovieCellViewModel(movie: mock.movie, apiAccess: apiMock)
        
        
        describe("Check if is loading the movie image") {
            it("has to call the function"){
                viewModel.loadImage(path: "", completion: {_ in })
                expect(apiMock.hasDowloadImage).to(beTrue())
            }
        }
        
        describe("Check if it is formating the date") {
            it("has to call the function"){
                let test = viewModel.formateMovieDate(movieDate: "2019-07-09")
                expect(test) == "09/07/2019"
            }
        }
        
    }
    
}

