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
protocol MovieGridViewModelDelegate: class{
    func refreshMovieData()
}


class PopularController: UIViewController {
    let screen = PopularView()
    let viewModel: PopularViewModel
    
    //MARK: - Inits
    init(apiAccess: APIClientInterface) {
        viewModel = PopularViewModel(apiAccess: apiAccess)
        super.init(nibName: nil, bundle: nil)
        viewModel.refresh = self
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    //MARK: - View cycle functions
    override func viewDidLoad(){
        self.view = screen
        
        navigationItem.title = "Populares"
        navigationItem.titleView = screen.customSC
        
        screen.gridView.delegate = self
        screen.gridView.dataSource = self
        screen.loadRoll.startAnimating()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        screen.gridView.reloadData()
    }
}


//MARK: - View Model delegate
extension PopularController: MovieGridViewModelDelegate{
    func refreshMovieData() {
        DispatchQueue.main.async { [weak self] in
            self?.screen.gridView.reloadData()
            self?.screen.loadRoll.stopAnimating()
            self?.screen.loadRoll.isHidden = true
            self?.screen.loadingLabel.isHidden = true
            self?.screen.gridView.isHidden = false
        }
    }
}


//MARK: - Transition
extension PopularController{
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
     
    }
}

//MARK: - CollectionView DataSource
extension PopularController: UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.movies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: PopularViewCell.reuseIdentifier, for: indexPath) as! PopularViewCell
        let movie = viewModel.movies[indexPath.row]
        cell.configure(withViewModel: movie)
        return cell
    }
}


//MARK: - CollectionView Layout
extension PopularController: UICollectionViewDelegate, UICollectionViewDelegateFlowLayout{
    // Loads more movies when the scrool gets near the end
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        if(indexPath.row == viewModel.movies.count - 5){
            viewModel.loadMovies()
        }
    }
    
    // Distance to the screen sides
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 10,left: 3,bottom: 10,right: 3)
    }
    // Set the size of the cells
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: 115, height: 230)
    }
}

