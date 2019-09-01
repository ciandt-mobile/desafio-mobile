//
//  DetailSpec.swift
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

class DetailsSpec: QuickSpec{
    
    override func spec() {
        
        let apiMock = APIMock()
        let mock = PresentableMovieMock()
        let genreMock = GenreMock()
        let controller = DetailsController(selectedMovie: mock, apiAccess: apiMock)
        let viewModel = DetailsViewModel(movie: mock, apiAccess: apiMock)
        
        describe("Check if the view is loading") {
            it("view has to be equal to equal to the PopularView"){
                controller.viewDidLoad()
                expect(controller.view) == controller.screen
            }
        }
        
        describe("Check if is detailing the genres") {
            it("has to return a formated string"){
                viewModel.movieGenres = genreMock.genres
                expect(viewModel.detailsGenres()) == " Action, Adventure"
            }
        }
        
        describe("Check if is accessing the api") {
            it("has to enter the function"){
                viewModel.getData(completion: {})
                expect(apiMock.hasFetchedData).to(beTrue())
            }
        }
    }
}
