//
//  PopularGridSpec.swift
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

class PopularGridSpec: QuickSpec{
    
    override func spec() {
        
        let apiMock = APIMock()
        let movieMock = PresentableMovieMock()
        let controller = PopularController(apiAccess: apiMock)
        let viewModel = PopularViewModel(apiAccess: apiMock)
        
        describe("Check if the view is loading") {
            it("view has to be equal to equal to the PopularView"){
                controller.viewDidLoad()
                expect(controller.view) == controller.screen
            }
        }
        
        describe("Check if the view is loading the movies") {
            it("has to enter the function FetchData"){
                viewModel.loadMovies(type: "")
                expect(apiMock.hasFetchedData).to(beTrue())
            }
        }
        
        describe("Check if the function is reseting the movie array") {
            it("has to enter the function and reset the array"){
                viewModel.movies.append(movieMock)
                viewModel.resetMovies()
                expect(viewModel.movies).to(beEmpty())
            }
        }
    }
    
}

