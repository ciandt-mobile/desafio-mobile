//
//  DetailView.swift
//  Movs
//
//  Created by Eduardo Pereira on 27/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class DetailView: UIView {
    
    let backgroundImage = UIImageView()
    private let activityControl:UIActivityIndicatorView = {
        let activityView = UIActivityIndicatorView()
        activityView.hidesWhenStopped = true
        activityView.backgroundColor = .clear
        activityView.style = UIActivityIndicatorView.Style.whiteLarge
        return activityView
    }()
    let artistCollection:MainCollectionView = {
        let layout = MainCollectionLayout()
        layout.sideItemAlpha = 1.0
        layout.sideItemScale = 1.0
        layout.beginOnMidle = false
        let collection = MainCollectionView(registerCell: { (collection) in
            collection.register(CastCellView.self, forCellWithReuseIdentifier:CastCellView.reuseIdentifier )
        },layout: layout)
        return collection
    }()
    let topImageView :UIImageView = {
        let view = UIImageView()
        view.contentMode = UIView.ContentMode.scaleAspectFill
        view.clipsToBounds = true
        return view
    }()
    let titleLabel = UILabel()
    let genreLabel:UILabel = {
        let label = UILabel()
        return label
    }()
    let overview :UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        return label
    }()
    let backButton:UIButton = {
        let button = UIButton()
        let image = UIImage(named: "back_button")?.withRenderingMode(.alwaysOriginal)
        button.setImage(image, for: .normal)
        return button
    }()

    let youtbeButton:UIButton = {
        let button = UIButton()
        button.setImage(UIImage(named: "play_button")?.withRenderingMode(.alwaysTemplate), for: .normal)
        button.tintColor = Color.scarlet
        return button
    }()
    private lazy var imageHeight = self.topImageView.heightAnchor.constraint(equalToConstant: max(UIScreen.main.bounds.height,UIScreen.main.bounds.width)*0.4)
    private lazy var imageWidth = self.topImageView.widthAnchor.constraint(equalToConstant: max(UIScreen.main.bounds.height,UIScreen.main.bounds.width)*0.4)
   
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        initViewCoding()
        activityControl.startAnimating()

    }
    func setup(viewModel:DetailViewModel){
            self.topImageView.image = viewModel.backdropImage
            self.titleLabel.attributedText = viewModel.title
            self.genreLabel.attributedText = viewModel.genre
            self.overview.attributedText = viewModel.overview
            self.backgroundImage.image = viewModel.backgroundImage
            if viewModel.youtubeURL == nil{
                self.youtbeButton.isHidden = true
            }else{
                self.youtbeButton.isHidden = false
            }
       
    }
    func reloadCollection(){
        self.artistCollection.reloadData()
    }
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutMarginsDidChange() {
      
        removeAllConstraints()
        let ratio = self.bounds.height/self.bounds.width
        if ratio < 1 {
            setUpLandscape()
        }else{
            setUpPortrait()
        }
        layoutIfNeeded()
        artistCollection.resizeCells(size: self.artistCollection.bounds.size)

    }
    //Tried to do with stack view,it workrd but cold not remove the constraints error from the console
    func setUpPortrait(){
        imageHeight.isActive = true
        imageWidth.isActive = false
        self.topImageView.anchor(top: self.topAnchor, leading: self.leadingAnchor, bottom: nil, trailing: self.trailingAnchor)
        self.titleLabel.anchor(top: topImageView.bottomAnchor, leading: self.leadingAnchor, bottom: nil, trailing: self.trailingAnchor)
        self.genreLabel.anchor(top: titleLabel.bottomAnchor, leading: self.leadingAnchor, bottom: nil, trailing: self.trailingAnchor)
        self.artistCollection.anchor(top: genreLabel.bottomAnchor, leading: self.leadingAnchor, bottom: nil, trailing: self.trailingAnchor)
         self.overview.anchor(top: artistCollection.bottomAnchor, leading: self.leadingAnchor, bottom: nil, trailing: self.trailingAnchor)
        self.youtbeButton.anchor(top: overview.bottomAnchor, leading: nil, bottom: nil, trailing: nil)
        backButton.anchor(top: self.topAnchor, leading: self.leadingAnchor, bottom: nil, trailing: nil,padding: UIEdgeInsets(top: layoutMargins.top*0.75, left: layoutMargins.left, bottom: 0, right: 0))
        youtbeButton.centerXAnchor.constraint(equalTo: overview.centerXAnchor).isActive = true
        activityControl.anchor(top: artistCollection.topAnchor, leading: artistCollection.leadingAnchor, bottom: artistCollection.bottomAnchor, trailing: artistCollection.trailingAnchor)
    }
    func setUpLandscape(){
        imageHeight.isActive = false
        imageWidth.isActive = true
       self.topImageView.anchor(top: self.topAnchor, leading: self.leadingAnchor, bottom: self.bottomAnchor, trailing: nil)
        self.titleLabel.anchor(top: self.topAnchor, leading: topImageView.trailingAnchor, bottom: nil, trailing: self.trailingAnchor)
        self.genreLabel.anchor(top: titleLabel.bottomAnchor, leading: topImageView.trailingAnchor, bottom: nil, trailing: self.trailingAnchor)
        self.artistCollection.anchor(top: self.genreLabel.bottomAnchor, leading: self.topImageView.trailingAnchor, bottom: nil, trailing: self.trailingAnchor)
        self.overview.anchor(top: artistCollection.bottomAnchor, leading: self.topImageView.trailingAnchor, bottom: nil, trailing: self.trailingAnchor)
        self.youtbeButton.anchor(top: overview.bottomAnchor, leading: nil, bottom: nil, trailing: nil)
          youtbeButton.centerXAnchor.constraint(equalTo: overview.centerXAnchor).isActive = true
        backButton.anchor(top: self.topAnchor, leading: self.leadingAnchor, bottom: nil, trailing: nil,padding: UIEdgeInsets(top: layoutMargins.top * 0.25, left: layoutMargins.left, bottom: 0, right: 0))
        activityControl.anchor(top: artistCollection.topAnchor, leading: artistCollection.leadingAnchor, bottom: artistCollection.bottomAnchor, trailing: artistCollection.trailingAnchor)
    }
    func removeConstraints(){
        self.topImageView.removeAllConstraints()
        self.titleLabel.removeAllConstraints()
        self.genreLabel.removeAllConstraints()
        self.artistCollection.removeAllConstraints()
        self.overview.removeAllConstraints()
        self.youtbeButton.removeAllConstraints()
        self.backButton.removeAllConstraints()
        self.activityControl.removeAllConstraints()
    }
    func stopRefresher(){
        DispatchQueue.main.async {
             self.activityControl.stopAnimating()
        }
       
    }


}
extension DetailView:ViewCoding{
    func buildViewHierarchy() {
         self.addSubview(backgroundImage)
        self.addSubview(topImageView)
        self.addSubview(titleLabel)
        self.addSubview(genreLabel)
        self.addSubview(artistCollection)
        self.addSubview(overview)
        self.addSubview(youtbeButton)
        self.addSubview(backButton)
        self.addSubview(activityControl)
        
    }
    
    func setUpConstraints() {
        backgroundImage.fillSuperview()
        //Could not find a way to remove size constraint so here i added only then
        artistCollection.anchor(top: nil, leading: nil, bottom: nil, trailing: nil,size:CGSize(width: 0, height: 200))
        youtbeButton.anchor(top: nil, leading: nil, bottom: nil, trailing: nil,size:CGSize(width: 50, height: 50))
        backButton.anchor(top: nil, leading: nil, bottom: nil, trailing: nil,size:CGSize(width: 50, height: 50))
        

    }
    
    
}
extension DetailView:Refresh{
    func removeRefresher() {
        DispatchQueue.main.async {
            self.activityControl.stopAnimating()
        }
    }
    
    func addRefresher() {
        DispatchQueue.main.async {
            self.activityControl.isHidden = true
            self.activityControl.startAnimating()
        }
        
    }
    
    
}

