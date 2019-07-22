//
//  UpcomingMoviesViewController.swift
//  desafiomobile
//
//  Created by Erasmo Oliveira on 21/07/19.
//  Copyright © 2019 Erasmo Oliveira. All rights reserved.
//

import UIKit
import SVProgressHUD

class UpcomingMoviesViewController: UIViewController {

    // MARK :- Outlets
    @IBOutlet weak var collectionView: UICollectionView!
    
    // MARK :- properties
    var presenter: UpcomingMoviesPresenter? = UpcomingMoviesPresenter()
    
    // MARK :- life cycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        collectionView.delegate = self
        collectionView.dataSource = self
        MovieCell.registerNib(for: collectionView)
        
        presenter?.delegate = self
        presenter?.viewDidLoad()
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
    }

}

extension UpcomingMoviesViewController : UICollectionViewDelegate, UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return presenter?.numberOfItemsInSection() ?? 0
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = MovieCell.dequeueCell(from: collectionView, for: indexPath)
        cell.config(with: presenter?.movies[indexPath.row])
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
        let vc = MovieDetailViewController()
        vc.movie = presenter?.movies[indexPath.row]
        vc.modalPresentationStyle = .overCurrentContext
        present(vc, animated: true, completion: nil)
        
    }
    
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        
        let numberOfSection = presenter?.numberOfItemsInSection() ?? 0
        
        if numberOfSection > 0 {
            if indexPath.row == ( numberOfSection - 1 ) {
                presenter?.callNextPage()
            }
        }
        
    }

}

extension UpcomingMoviesViewController : UpcomingMoviesProtocol {
    
    func reloadView() {
        collectionView.reloadData()
        SVProgressHUD.dismiss()
    }
    
}
