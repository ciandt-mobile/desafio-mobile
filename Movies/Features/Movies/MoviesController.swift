//
//  MoviesController.swift
//  Movies
//
//  Created by Piero Mattos on 27/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

class MoviesController: UICollectionViewController, UICollectionViewDelegateFlowLayout {

    // MARK: - Properties

    @IBOutlet weak var segmentedControl: UISegmentedControl!

    fileprivate var movies: [Movie] = []
    fileprivate var selectedMovie: Movie?
    fileprivate var state: State = .loading

    fileprivate var cellSize: CGSize = .zero
    fileprivate var detailsDismissalViews: (UIView, UIView)?
    fileprivate var screenSize: CGSize { return view.frame.inset(by: view.safeAreaInsets).size }

    // MARK: - Lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()

        setupCollectionView()
        loadMoviews()
        listenForDeviceOrientationChanges()
    }

    deinit {
        NotificationCenter.default.removeObserver(self)
    }

    // MARK: - Methods

    fileprivate func setupCollectionView() {
        cellSize = calculateCellSize(forScreenSize: screenSize)
        collectionView.register(UINib(nibName: "MovieCell", bundle: Bundle.main), forCellWithReuseIdentifier: "MovieCell")
    }

    fileprivate func listenForDeviceOrientationChanges() {
        UIDevice.current.beginGeneratingDeviceOrientationNotifications()
        _ = NotificationCenter.default.addObserver(forName: UIDevice.orientationDidChangeNotification, object: nil, queue: nil, using: { [unowned self] _ in
            let insetScreenSize = self.view.frame.inset(by: self.view.safeAreaInsets).size
            self.cellSize = self.calculateCellSize(forScreenSize: insetScreenSize)
            self.collectionView.collectionViewLayout.invalidateLayout()
        })
    }

    @IBAction fileprivate func loadMoviews() {
        state = .loading
        updateView()

        if segmentedControl.selectedSegmentIndex == 0 {
            Movie.fetchNowPlaying { [unowned self] movies, error in
                self.movies = movies ?? []
                self.state = error == nil ? .displayingResults : .error
                self.updateView()
            }
        } else {
            Movie.fetchUpcoming { [unowned self] movies, error in
                self.movies = movies ?? []
                self.state = error == nil ? .displayingResults : .error
                self.updateView()
            }
        }
    }

    private func updateView() {
        DispatchQueue.main.async { [unowned self] in
            switch self.state {
            case .loading:              self.collectionView.backgroundView = LoadingView.loadFromNib()
            case .error:                self.collectionView.backgroundView = ErrorView.loadFromNib()
            case .displayingResults:    self.collectionView.backgroundView = nil
            }
            self.collectionView.reloadData()
        }
    }

    // MARK: - Layout helper methods

    fileprivate func cellSizeForPortrait() -> CGSize {
        let width = screenSize.width
        return CGSize(width: width, height: width * 1.5)
    }

    fileprivate func cellSizeForLandscape() -> CGSize {
        let height = screenSize.height
        return CGSize(width: height * 2/3, height: height)
    }

    fileprivate func calculateCellSize(forScreenSize screenSize: CGSize) -> CGSize {
        var cellSize = UIDevice.current.isInPortraitOrientation ? cellSizeForPortrait() : cellSizeForLandscape()

        let paddedWidth = screenSize.width - 60
        let paddedHeight = screenSize.height - 90
        let containerSize = CGSize(width: paddedWidth, height: paddedHeight)
        cellSize.scaleToFit(inside: containerSize)

        return cellSize
    }

    fileprivate func contentInsetForPortrait() -> UIEdgeInsets {
        let insetY = floor((screenSize.height - cellSize.height)/2.0)
        let insetX = floor((screenSize.width - cellSize.width)/2.0)
        return UIEdgeInsets(top: insetY, left: insetX, bottom: insetY, right: insetX)
    }

    fileprivate func contentInsetForLandscape() -> UIEdgeInsets {
        let insetY = floor((screenSize.height - cellSize.height)/2.0)
        let insetX = floor(cellSize.width/4.0)
        return UIEdgeInsets(top: insetY, left: insetX, bottom: insetY, right: insetX)
    }

    fileprivate func lineSpacingForPortrait() -> CGFloat {
        return floor(view.frame.size.width - cellSize.width)
    }

    fileprivate func lineSpacingForLandscape() -> CGFloat {
        return floor(cellSize.width/4.0)
    }

    fileprivate func interItemSpacing() -> CGFloat {
        return floor(cellSize.width/4.0)
    }

    // MARK: - UICollectionViewDataSource

    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        switch state {
        case .displayingResults: return 1
        default: return 0
        }
    }

    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch state {
        case .displayingResults: return movies.count
        default: return 0
        }
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "MovieCell", for: indexPath) as? MovieCell
            else { fatalError("Could not dequeue MovieCell for collection view.") }
        cell.movie = movies[indexPath.row]
        return cell
    }

    // MARK: - UICollectionViewDelegate

    override func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        DispatchQueue.main.async { [weak self] in self?.collectionView.deselectItem(at: indexPath, animated: false) }

        selectedMovie = movies[indexPath.row]

        if UIDevice.current.isInPortraitOrientation {
            let cellContentView = collectionView.cellForItem(at: indexPath)!.contentView
            showDetailsAsFlippedPoster(cellContentView)
        } else {
            showDetailsAsModal()
        }
    }

    // MARK: - UICollectionViewDelegateFlowLayout

    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return cellSize
    }

    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIDevice.current.isInPortraitOrientation ? contentInsetForPortrait() : contentInsetForLandscape()
    }

    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return UIDevice.current.isInPortraitOrientation ? lineSpacingForPortrait() : lineSpacingForLandscape()
    }

    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return interItemSpacing()
    }

    // MARK: - UIScrollViewDelegate

    override func scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>) {
        if !UIDevice.current.isInPortraitOrientation { return }

        let screenWidth = cellSize.width + 2 * contentInsetForPortrait().left
        let originalXOffset = targetContentOffset.pointee.x

        let roundedIndex = round(originalXOffset / screenWidth)
        let desiredXOffset = roundedIndex * screenWidth
        let desiredYOffset = -collectionView.contentInset.top

        targetContentOffset.pointee = CGPoint(x: desiredXOffset, y: desiredYOffset)
    }

    // MARK: - Navigation and movie details

    fileprivate func showDetailsAsModal() {
        guard
            let selectedMovie = selectedMovie,
            let detailsNC = UIStoryboard(name: "MovieDetails", bundle: Bundle.main).instantiateInitialViewController() as? UINavigationController,
            let detailsVC = detailsNC.topViewController as? MovieDetailsController
            else { return }

        _ = detailsVC.view
        detailsVC.movie = selectedMovie

        DispatchQueue.main.async { [weak self] in
            self?.present(detailsNC, animated: true, completion: nil)
        }
    }

    fileprivate func showDetailsAsFlippedPoster(_ cellContentView: UIView) {
        guard
            let detailsView = Bundle.main.loadNibNamed("MovieDetailsView", owner: self, options: nil)?[0] as? MovieDetailsView
            else { return }

        detailsView.frame = cellContentView.frame
        detailsView.movie = selectedMovie
        detailsView.didTapPosterHandler = { [unowned self] in
            self.performSegue(withIdentifier: "showPoster", sender: self)
        }

        let dismissGesture = UITapGestureRecognizer(target: self, action: #selector(didDismissDetails))
        self.view.addGestureRecognizer(dismissGesture)

        collectionView.isScrollEnabled = false

        let transitionOptions: UIView.AnimationOptions = [.transitionFlipFromLeft, .curveEaseIn, .preferredFramesPerSecond60]
        UIView.transition(from: cellContentView, to: detailsView, duration: 0.4, options: transitionOptions) { [unowned self] _ in
            self.detailsDismissalViews = (cellContentView, detailsView)
        }
    }

    @objc func didDismissDetails() {
        guard
            let cellContentView = detailsDismissalViews?.0,
            let detailsView = detailsDismissalViews?.1
            else { return }

        let transitionOptions: UIView.AnimationOptions = [.transitionFlipFromRight, .curveEaseOut, .preferredFramesPerSecond60]
        UIView.transition(from: detailsView, to: cellContentView, duration: 0.4, options: transitionOptions) { [unowned self] _ in
            if let tapGestureRecognizer = self.view.gestureRecognizers?.first {
                self.view.removeGestureRecognizer(tapGestureRecognizer)
            }
            self.detailsDismissalViews = nil
            self.collectionView.isScrollEnabled = true
        }
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        guard
            segue.identifier == "showPoster",
            let movie = self.selectedMovie,
            let navigationController = segue.destination as? UINavigationController,
            let posterVC = navigationController.topViewController as? PosterController
            else { return }

        _ = posterVC.view
        posterVC.posterImageView.loadImage(movie.posterURL)
    }

    // MARK: - Enums

    enum State {
        case loading
        case error
        case displayingResults
    }
}
