//
//  MovsTests.swift
//  MovsTests
//
//  Created by Eduardo Pereira on 28/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import Foundation
import Quick
import Nimble
import Nimble_Snapshots


@testable import Movs

class MovsSpec: QuickSpec {
    override func spec(){
        testUI()
        testAPI()
    }
    func testAPI(){
        describe("The movieDB"){
            let api = MovieAPI()
            context("want to return data"){
               
                it("should request popular"){
                    var error:Error? = NetworkError.movieNotFound("")
                    api.movieRequest(mode: Request.popular, page: 1, onComplete: { (result, errorResponse) in
                        error = errorResponse
                    })
                    expect(error).toEventually(beNil())
                }
             
            }
        }
    }
    func testUI(){
        let acessMock = DataAcessMock()
        describe("Main screen"){
            let homeController = HomeController(dataAcess: acessMock)
            _ = UINavigationController(rootViewController: homeController)
            homeController.viewDidLoad()
            context("User want to see movies"){
                it("Should have right view"){
                    
                    let view = homeController.homeView
                    view.collection.resizeCells(size: homeController.view.bounds.size)
                    expect(view) == snapshot()
                }
                context("User want to go details"){
                    acessMock.getMovies(request: Request.popular, page: 1, { (result) in
                        if let result = result?.first {
                            
                            let detailController = DetailController(model: result, dataAcess: acessMock)
                            detailController.viewDidLoad()
                            expect(detailController.viewModel).toNot(beNil())
                            homeController.navigationController?.pushViewController(detailController, animated: false)
                            let view = detailController.detailView
                            it("Shoul have right View"){
                                view.layoutMarginsDidChange()
                                expect(view) == snapshot()
                                
                            }
                        }else{
                            fail("check acess mock")
                        }
                        
                    })
                    
                }
                
                
            }
        }
    }

}
