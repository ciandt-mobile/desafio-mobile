import Foundation
import UIKit
import SnapKit

class MovieListView: UIView {
    
    let cellId = "movieCellId"
    var collectionView : UICollectionView!
    private var viewFilter : UIView!
    var barButtonFilter : UIBarButtonItem!
    var movieFilterChangeDelegate : MovieFilterChange!
    var context : UIViewController!
    
    func render(_ context : UIViewController, movieFilterChange : MovieFilterChange) {
        
        self.frame = UIScreen.main.bounds
        self.movieFilterChangeDelegate = movieFilterChange
        self.context = context
        
        barButtonFilter = UIBarButtonItem(image: UIImage(named: "new"), style: .plain, target: self, action: #selector(showFilter(_:)))
        context.navigationItem.title = "Popular Movies"
        context.navigationItem.rightBarButtonItem = barButtonFilter
        context.navigationController?.navigationBar.prefersLargeTitles = true
        context.navigationController?.navigationBar.barTintColor = UIColor(red: 12 / 255, green: 12 / 255, blue: 12 / 255, alpha: 1)
        context.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
        context.navigationController?.navigationBar.largeTitleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
        context.navigationController?.navigationBar.tintColor = .red
        
        let layoutCollectionView: UICollectionViewFlowLayout = UICollectionViewFlowLayout()
        layoutCollectionView.sectionInset = UIEdgeInsets(top: 10, left: 10, bottom: 10, right: 10)
        layoutCollectionView.itemSize = CGSize(width: 100, height: 160)
        
        collectionView = UICollectionView(frame: self.frame, collectionViewLayout: layoutCollectionView)
        collectionView.backgroundColor = UIColor(red: 20 / 255, green: 20 / 255, blue: 20 / 255, alpha: 1)
        collectionView.register(MovieListCell.self, forCellWithReuseIdentifier: cellId)
        
        addSubview(collectionView)
        collectionView.snp.makeConstraints { (make) in
            make.height.width.equalToSuperview()
        }
        
    }
    
    func updateNavigationBar(_ filter : MovieFilter) {
        if (filter == MovieFilter.popular) {
            context.navigationItem.title = "Popular Movies"
            barButtonFilter.image = UIImage(named: "new")
            context.navigationController?.navigationBar.tintColor = .red
        } else {
            context.navigationItem.title = "Upcoming Movies"
            barButtonFilter.image = UIImage(named: "star")
            context.navigationController?.navigationBar.tintColor = .yellow
        }
    }
    
    @objc func showFilter(_ sender : Any) {
        movieFilterChangeDelegate.updateMovieList()
    }
}
