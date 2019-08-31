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
    func refreshCastView()
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
        
        
        screen.castView.delegate = self
        screen.castView.dataSource = self
        
        viewModel.getData(completion: { [weak self] in
            self?.screen.castView.reloadData()
        })
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        navigationController?.navigationBar.setBackgroundImage(nil, for: .default)
        navigationController?.navigationBar.shadowImage = nil
        navigationController?.navigationBar.isTranslucent = false
        navigationController?.view.backgroundColor = UsedColors.gray.color
    }
}


//MARK: - Protocol Methods
extension DetailsController: DetailsLoadDelegate{
    
    func refreshScreen() {
        DispatchQueue.main.async { [weak self] in
            self?.loadData()
        }
    }
    
    func refreshCastView(){
        DispatchQueue.main.async { [weak self] in
            self?.screen.castView.reloadData()
        }
    }
    
    func loadData(){
        screen.configure(detailedMovie: viewModel.movie, movieYear: viewModel.movieYear, genreNames: viewModel.detailsGenres(), runtime: viewModel.runtime)
    }
}


//MARK: - Collection DataSource
extension DetailsController: UICollectionViewDataSource{
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
       return viewModel.castImages.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
       let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CastViewCell.reuseIdentifier, for: indexPath) as! CastViewCell
       cell.configure(withViewModel: viewModel.movieCast[indexPath.row], actorImage: viewModel.castImages[indexPath.row])
       return cell
    } 
}


//MARK: - CollectionView Layout
extension DetailsController: UICollectionViewDelegate, UICollectionViewDelegateFlowLayout{
    // Distance to the screen sides
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 0,left: 10,bottom: 0,right: 10)
    }
    
    // Set the size of the cells
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        let width = (collectionView.frame.size.width - 12 * 3)/3
        let height = collectionView.frame.size.height
        
        return CGSize(width: width, height: height)
    }
    
    override func viewLayoutMarginsDidChange() {
        
        super.viewLayoutMarginsDidChange()
        
        let flow = screen.castView.collectionViewLayout as? UICollectionViewFlowLayout
        let width = (screen.castView.frame.size.width - 12 * 3)/3
        let height = screen.castView.frame.size.height
        
        if (width != 0 && height != 0){
            flow?.itemSize = CGSize(width: width, height: height)
        }
    }
    
}

