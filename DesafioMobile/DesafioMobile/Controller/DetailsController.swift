//
//  DetailsController.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import UIKit


protocol DetailsLoadDelegate: class{
    func refreshScreen()
}

class DetailsController: UIViewController {
    let screen = DetailsView()
    let viewModel: DetailsViewModel
    
    // MARK: - Inits
    init(selectedMovie: PresentableMovieInterface, apiAccess: APIClientInterface) {
        viewModel = DetailsViewModel(movie: selectedMovie,apiAccess: apiAccess)
        super.init(nibName: nil, bundle: nil)
        viewModel.loadDelegate = self
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    
    // MARK: - View cycle functions
    override func viewDidLoad() {
        self.view = screen
        navigationController?.navigationBar.setBackgroundImage(UIImage(), for: .default)
        navigationController?.navigationBar.shadowImage = UIImage()
        navigationController?.navigationBar.isTranslucent = true
        navigationController?.view.backgroundColor = .clear
        viewModel.getData()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        navigationController?.navigationBar.setBackgroundImage(nil, for: .default)
        navigationController?.navigationBar.shadowImage = nil
        navigationController?.navigationBar.isTranslucent = false
        navigationController?.view.backgroundColor = UsedColors.gray.color
    }
}


extension DetailsController: DetailsLoadDelegate{
    func refreshScreen() {
        DispatchQueue.main.async { [weak self] in
            self?.loadData()
        }
    }
    
    func loadData(){
        screen.configure(detailedMovie: viewModel.movie, movieYear: viewModel.movieYear, genreNames: viewModel.detailsGenres(), runtime: viewModel.runtime)
    }
}
