//
//  MoviesView.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit
import BetterSegmentedControl

class MoviesView: BaseViewController, Feedbackable {

    // MARK: - Viper Properties

    var presenter: MoviesPresenterInputProtocol!

    // MARK: - Outlets

    @IBOutlet weak var collectionView: UICollectionView!

    // MARK: - Private Properties

    private var refreshControl = UIRefreshControl()
    private var currentOrientation: UIDeviceOrientation = .unknown
    private var didChangeOrientationObserver: Any?
    
    // MARK: - Inits
    
    deinit {
        guard let didChangeOrientationObserver = self.didChangeOrientationObserver else { return }
        NotificationCenter.default.removeObserver(didChangeOrientationObserver)
    }

    // MARK: - Lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupSearchController()
        self.setupCollectionView()
        self.setupOrientationRecognizer()
        self.fetchMovies(from: .popular)
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.statusBarStyle = .default
        self.currentOrientation = UIDevice.current.orientation
        self.setupSegmentedControl()
        self.setupBaseNavigationBar(title: self.presenter.title, isTranslucent: true, isLargeTitle: true)
    }

    // MARK: - Private Methods

    private func setupSegmentedControl() {

        let titles: [String] = [MessagesUtil.Movies.popularTitle,
                                MessagesUtil.Movies.topRatedTitle,
                                MessagesUtil.Movies.upcomingTitle]

        let segments = LabelSegment.segments(withTitles: titles,
                                             normalFont: .systemFont(ofSize: 14.0, weight: .medium),
                                             normalTextColor: UIColor.Style.darkGrayAdaptative,
                                             selectedFont: .systemFont(ofSize: 14.0, weight: .bold),
                                             selectedTextColor: UIColor.Style.whiteAdaptative)
        
        let cornerRadius: CGFloat = self.currentOrientation.isPortrait ? 20.0 : 16.0
        let options: [BetterSegmentedControlOption] = [.backgroundColor(.clear),
                                                       .indicatorViewBackgroundColor(UIColor.Style.blackAdaptative),
                                                       .cornerRadius(cornerRadius)]

        let segmentedControl = BetterSegmentedControl(frame: CGRect(x: 0.0, y: 0.0, width: 300.0, height: 40.0),
                                                      segments: segments,
                                                      index: self.presenter.currentSection.rawValue,
                                                      options: options)
        segmentedControl.addTarget(self, action: #selector(self.didChangeMoviesSection), for: .valueChanged)
        self.navigationItem.titleView = segmentedControl
    }

    private func setupSearchController() {

        let search = UISearchController(searchResultsController: nil)
        search.searchBar.delegate = self
        search.searchBar.tintColor = UIColor.Style.blackAdaptative
        search.obscuresBackgroundDuringPresentation = false
        self.navigationItem.searchController = search
        self.navigationItem.hidesSearchBarWhenScrolling = true
    }

    private func setupCollectionView() {

        self.collectionView.dataSource = self
        self.collectionView.delegate = self

        self.refreshControl.tintColor = .gray
        self.refreshControl.addTarget(self, action: #selector(self.didPullToRefresh), for: .valueChanged)
        self.collectionView.refreshControl = self.refreshControl

        MovieCollectionViewCell.registerOn(self.collectionView)
        LoadingCollectionReusableView.registerOn(self.collectionView,
                                                 forSupplementaryViewOfKind: UICollectionView.elementKindSectionFooter)
    }

    private func fetchMovies(from section: MoviesSectionType) {

        UIView.animate(withDuration: 0.2, animations: {
            self.collectionView.alpha = 0.0
        }) { _ in
            self.view.showActivityIndicator(isLarge: true, color: .darkGray)
            self.collectionView.setContentOffset(.zero, animated: false)
            self.presenter.fetchMovies(from: section)
            self.navigationItem.title = self.presenter.title
        }
    }
    
    private func setupOrientationRecognizer() {
        
        let notificationCenter = NotificationCenter.default
        self.didChangeOrientationObserver = notificationCenter.addObserver(forName: UIDevice.orientationDidChangeNotification,
                                                                           object: nil,
                                                                           queue: .main) { [weak self] _ in
                                                                            self?.shouldLayoutCollectionCells()
        }
    }
    
    private func shouldLayoutCollectionCells() {
        
        let newOrientation = UIDevice.current.orientation
        guard newOrientation != self.currentOrientation else { return }
        
        if newOrientation.isPortrait && newOrientation != .portraitUpsideDown {
            self.currentOrientation = newOrientation
            self.collectionView.reloadData()
            self.setupSegmentedControl()
        } else if newOrientation.isLandscape {
            self.currentOrientation = newOrientation
            self.collectionView.reloadData()
            self.setupSegmentedControl()
        }
    }

    // MARK: - Actions

    @objc func didPullToRefresh() {
        guard !self.presenter.shouldSearchMovie else {
            self.refreshControl.endRefreshing()
            return
        }
        self.presenter.reloadMovies()
    }

    @objc func didChangeMoviesSection(_ control: BetterSegmentedControl) {
        guard let section = MoviesSectionType(rawValue: control.index) else { return }
        self.fetchMovies(from: section)
    }
}

// MARK: - MoviesPresenterOutputProtocol

extension MoviesView: MoviesPresenterOutputProtocol {

    // MARK: - Internal Methods

    func reloadMovies() {
        self.collectionView.alpha = 1.0
        self.collectionView.reloadData()
        self.view.hideActivityIndicator()
        self.refreshControl.endRefreshing()
    }

    func addNewMovies() {

        var indexPaths: [IndexPath] = []

        for (index, movie) in self.presenter.movies.enumerated() where movie.isNew {
            indexPaths.append(IndexPath(item: index, section: 0))
        }

        self.collectionView.performBatchUpdates({
            self.collectionView.insertItems(at: indexPaths)
        })
    }

    func didFailedMovies(localizedError: String) {

        let okAction = UIAlertAction(title: MessagesUtil.General.ok, style: .default)
        self.showAlert(withTitle: MessagesUtil.General.oops,
                       message: localizedError,
                       actions: [okAction]) { [weak self] in
            self?.reloadMovies()
        }
    }
}

// MARK: - UICollectionViewDataSource

extension MoviesView: UICollectionViewDataSource {

    // MARK: - Internal Methods

    func collectionView(_ collectionView: UICollectionView,
                        numberOfItemsInSection section: Int) -> Int {
        return self.presenter.movies.count
    }

    func collectionView(_ collectionView: UICollectionView,
                        cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: MovieCollectionViewCell.reuseIdentifier, for: indexPath) as? MovieCollectionViewCell else { return UICollectionViewCell() }
        self.presenter.movies[indexPath.item].isNew = false
        cell.setupUI(with: self.presenter.movies[indexPath.item])
        return cell
    }

    func collectionView(_ collectionView: UICollectionView,
                        viewForSupplementaryElementOfKind kind: String,
                        at indexPath: IndexPath) -> UICollectionReusableView {
        guard let footerView = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: LoadingCollectionReusableView.reuseIdentifier, for: indexPath) as? LoadingCollectionReusableView, kind == UICollectionView.elementKindSectionFooter else { return UICollectionReusableView() }
        return footerView
    }
}

// MARK: - UICollectionViewDelegateFlowLayout

extension MoviesView: UICollectionViewDelegateFlowLayout {

    // MARK: - Private Properties

    private var insetForSections: UIEdgeInsets {
        return UIEdgeInsets(top: 15.0, left: 15.0, bottom: 15.0, right: 15.0)
    }

    private var margins: CGFloat {
        return 15.0
    }

    // MARK: - Internal Methods

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        insetForSectionAt section: Int) -> UIEdgeInsets {
        return self.insetForSections
    }

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return self.margins
    }

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return self.margins
    }

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        if self.currentOrientation.isLandscape {
            let padding = self.insetForSections.left + self.insetForSections.right + self.margins
            let width = (self.view.bounds.size.height - padding) / 3
            let ratio: CGFloat = 1.7
            let height = width * ratio
            return CGSize(width: width, height: height)
        } else {
            let padding = self.insetForSections.left + self.insetForSections.right + self.margins
            let width = (self.view.bounds.size.width - padding) / 2
            let ratio: CGFloat = 1.7
            let height = width * ratio
            return CGSize(width: width, height: height)
        }
    }

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        referenceSizeForFooterInSection section: Int) -> CGSize {
        let footerSize = CGSize(width: self.view.frame.size.width, height: 40.0)
        return !self.presenter.shouldFetchNextPageMovies ? .zero : footerSize
    }
}

// MARK: - UICollectionViewDelegate

extension MoviesView: UICollectionViewDelegate {

    // MARK: - Internal Methods

    func collectionView(_ collectionView: UICollectionView,
                        willDisplay cell: UICollectionViewCell,
                        forItemAt indexPath: IndexPath) {

        if !self.presenter.shouldSearchMovie {
            cell.alpha = 0.0
            cell.transform = CGAffineTransform(scaleX: 0.5, y: 0.5)

            UIView.animate(withDuration: 0.4, delay: 0.0, options: .allowUserInteraction, animations: {
                cell.alpha = 1.0
                cell.transform = .identity
            })
        }

        let lastRowIndex = collectionView.numberOfItems(inSection: indexPath.section) - 1

        if lastRowIndex == indexPath.row && self.presenter.shouldFetchNextPageMovies {
            self.presenter.fetchNextPageMovies()
        }
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.10) {
            guard self.presenter.movies.indices.contains(indexPath.item) else { return }
            self.presenter.didSelectMovie(self.presenter.movies[indexPath.item])
        }
    }

    func collectionView(_ collectionView: UICollectionView, didHighlightItemAt indexPath: IndexPath) {
        if let cell = collectionView.cellForItem(at: indexPath) {
            UIView.animate(withDuration: 0.4,
                           delay: 0,
                           usingSpringWithDamping: 1.0,
                           initialSpringVelocity: 0.2,
                           options: .allowUserInteraction,
                           animations: {
                cell.transform = CGAffineTransform(scaleX: 0.95, y: 0.95)
            })
        }
    }

    func collectionView(_ collectionView: UICollectionView, didUnhighlightItemAt indexPath: IndexPath) {
        if let cell = collectionView.cellForItem(at: indexPath) {
            UIView.animate(withDuration: 0.4,
                           delay: 0.05,
                           usingSpringWithDamping: 1.0,
                           initialSpringVelocity: 0.2,
                           options: .allowUserInteraction,
                           animations: {
                cell.transform = .identity
            })
        }
    }
}

// MARK: - UISearchBarDelegate

extension MoviesView: UISearchBarDelegate {

    // MARK: - Internal Methods

    func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
        self.presenter.shouldSearchMovie = true
    }

    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        self.presenter.searchMovie(byTitle: searchText)
    }

    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        self.presenter.shouldSearchMovie = false
    }
}
