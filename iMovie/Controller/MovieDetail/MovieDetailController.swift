import Foundation
import UIKit

class MovieDetailController: UIViewController, HasCustomView, UICollectionViewDelegate, UICollectionViewDataSource {
    
    typealias CustomView = MovieDetailView
    var movie : Movie!
    var castList : [Cast] = []
    
    override func loadView() {
        let movieDetailView = MovieDetailView()
        view = movieDetailView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        customView.render()
        
        customView.labelName.text = movie.title
        customView.labelOverview.text = movie.overview
        if let backdropPath = movie.backdropPath {
            customView.posterImage.pin_setImage(from: URL(string: "\(Constants.BASE_URL_IMG)original\(backdropPath)"))
        } else {
            customView.posterImage.image = UIImage(named: "not-found")
            customView.posterImage.contentMode = UIView.ContentMode.scaleToFill
        }
        
        var infoText = DateFormat.format(date: movie.releaseDate, format: "yyyy")
        if let runtime = movie.runtime {
            infoText = "\(infoText) | \(runtime)m"
        }
        
        if let genres = movie.genres {
            for i in 0..<genres.count {
                let genre = genres[i]
                if (i == 0) {
                    infoText = "\(infoText) | \(genre.name)"
                } else {
                    infoText = "\(infoText) \(genre.name)"
                }
                
                if (i != genres.count - 1) {
                    infoText = "\(infoText),"
                }
            }
        }
        
        customView.collectionView.delegate = self
        customView.collectionView.dataSource = self
        castList = movie.cast ?? []
        customView.labelInfo.text = infoText
        customView.collectionView.reloadData()
    }
    
    override func viewDidLayoutSubviews() {
        customView.scrollView.resizeScrollViewToContent()
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        print(castList.count)
        return castList.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: customView.cellId, for: indexPath) as! CastListCell
        let cast = castList[indexPath.row]
        cell.cast = cast
        return cell
    }
}
