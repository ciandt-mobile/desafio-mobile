import Foundation
import UIKit
import PINRemoteImage

class MovieListCell: UICollectionViewCell {
    
    var movie : Movie? {
        didSet {
            if let currentMovie = movie {
                labelTitle.text = currentMovie.title
                labelReleaseDate.text = DateFormat.format(date: currentMovie.releaseDate, format: "dd/MM/yy") 
                thumbnail.pin_setImage(from: URL(string: "\(Constants.BASE_URL_IMG)w500\(currentMovie.posterPath)"))
            }
        }
    }
    
    private let thumbnail : UIImageView = {
       let imageView = UIImageView()
        return imageView
    }()

    private let labelTitle : UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 10)
        label.numberOfLines = 3
        label.textAlignment = .center
        label.textColor = .white
        return label
    }()
    
    private let labelReleaseDate : UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 10)
        label.textAlignment = .center
        label.textColor = .white
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
                
        let nameContainer = UIView()
        nameContainer.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 0.6)
        
        let releaseDateContainer = UIView()
        releaseDateContainer.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 0.6)
        
        addSubview(thumbnail)
        thumbnail.snp.makeConstraints { (make) in
            make.height.width.equalToSuperview()
        }
        
        addSubview(nameContainer)
        nameContainer.snp.makeConstraints { (make) in
            make.left.bottom.right.equalToSuperview()
        }
        
        nameContainer.addSubview(labelTitle)
        labelTitle.snp.makeConstraints { (make) in
            make.left.bottom.right.top.equalToSuperview()
        }
        
        addSubview(releaseDateContainer)
        releaseDateContainer.snp.makeConstraints { (make) in
            make.left.top.right.equalToSuperview()
        }
        
        releaseDateContainer.addSubview(labelReleaseDate)
        labelReleaseDate.snp.makeConstraints { (make) in
            make.left.bottom.right.top.equalToSuperview()
        }
        
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
