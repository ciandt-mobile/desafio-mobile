//
//  PopularController.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Foundation
import UIKit


//MARK: - Protocols
protocol PopularGridViewModelDelegate: class{
    func refreshMovieData()
}


class PopularController: UIViewController {
    let screen = PopularView()
    let viewModel: PopularViewModel
    
    //MARK: - Inits
    init(apiAccess: APIClientInterface) {
        viewModel = PopularViewModel(apiAccess: apiAccess)
        super.init(nibName: nil, bundle: nil)
        viewModel.refreshDelegate = self
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    //MARK: - View cycle functions
    override func viewDidLoad(){
        self.view = screen
        
        screen.customSC.addTarget(self, action: #selector(loadUpcoming), for: UIControl.Event.valueChanged)
        navigationItem.titleView = screen.customSC
        
        screen.collectionView.delegate = self
        screen.collectionView.dataSource = self
        screen.loadRoll.startAnimating()
    }
    
}


//MARK: - View Model delegate
extension PopularController: PopularGridViewModelDelegate{
    func refreshMovieData() {
        DispatchQueue.main.async { [weak self] in
            self?.screen.loadGrid()
        }
    }
}



//MARK: - Transition
extension PopularController{
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let selectedMovie = viewModel.movies[indexPath.row]
        let detailsVC = DetailsController(selectedMovie: selectedMovie,apiAccess: viewModel.apiAccess)
        navigationController?.pushViewController(detailsVC, animated: true)
    }
    
    @objc func loadUpcoming(){
        if screen.customSC.selectedSegmentIndex == 0 {
            viewModel.resetMovies()
            screen.changeCollection()
            viewModel.loadMovies(type: viewModel.atualType)
            
        }else{
            viewModel.resetMovies()
            screen.changeCollection()
            viewModel.loadMovies(type: viewModel.atualType)
        }
    }
}



//MARK: - CollectionView DataSource
extension PopularController: UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.movies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: PopularViewCell.reuseIdentifier, for: indexPath) as! PopularViewCell
        cell.configure(withViewModel: viewModel.movies[indexPath.row])
        return cell
    }
}


//MARK: - CollectionView Layout
extension PopularController: UICollectionViewDelegate, UICollectionViewDelegateFlowLayout{
    // Loads more movies when the scrool gets near the end
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        if(indexPath.row > 15 && indexPath.row == viewModel.movies.count - 4){
            viewModel.loadMovies(type: viewModel.atualType)
        }
    }
    
    // Distance to the screen sides
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 10,left: 10,bottom: 10,right: 10)
    }
    // Set the size of the cells
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: 150, height: 300)
    }
}

