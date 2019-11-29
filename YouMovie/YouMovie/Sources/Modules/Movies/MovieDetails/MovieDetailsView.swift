//
//  MovieDetailsView.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit
import WebKit

class MovieDetailsView: BaseViewController {

    // MARK: - Viper Properties

    var presenter: MovieDetailsPresenterInputProtocol!

    // MARK: - Outlets

    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var stackView: UIStackView!

    @IBOutlet weak var backgropImageView: UIImageView!
    @IBOutlet weak var posterImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var subtitleLabel: UILabel!
    @IBOutlet weak var ratingView: CircularProgressView!
    @IBOutlet weak var playVideoButton: UIButton!
    @IBOutlet weak var overviewLabel: UILabel!

    @IBOutlet weak var durationLabel: UILabel!
    @IBOutlet weak var budgetLabel: UILabel!
    @IBOutlet weak var revenueLabel: UILabel!
    @IBOutlet weak var genresLabel: UILabel!

    // MARK: - Private Properties

    private var hasRatingViewAnimated: Bool = false

    // MARK: - Lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupUI()
        self.presenter.viewDidLoad()
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        self.animateRatingView()
    }

    // MARK: - Private Methods

    private func setupUI() {

        self.statusBarStyle = .lightContent
        self.setupStandaloneNavigationBar(style: .transparent, backAction: #selector(self.didTapBackButton))

        self.scrollView.delegate = self

        if let backdropImagePath = self.presenter.movie.backdropPathURL {
            let urlString = APIRoutes.MovieDetails.fetchImage(fromPath: backdropImagePath, size: .original)
            self.backgropImageView.downloadImage(fromURLString: urlString)
        }

        if let posterImagePath = self.presenter.movie.posterPathURL {
            let urlString = APIRoutes.MovieDetails.fetchImage(fromPath: posterImagePath, size: .w500)
            self.posterImageView.downloadImage(fromURLString: urlString)
        }

        self.titleLabel.text = self.presenter.movie.title
        self.titleLabel.setLineSpacing(value: 3.0)

        self.subtitleLabel.text = self.presenter.movie.releaseDate?.formattedString
        self.subtitleLabel.setLineSpacing(value: 3.0)

        let overview = self.presenter.movie.overview ?? "-"
        self.overviewLabel.text = !overview.isEmpty ? overview : "-"
        self.overviewLabel.setLineSpacing(value: 3.0)

        self.setupRatingView()
        self.setupInformations()
        self.playVideoButton.alpha = 0.0
    }

    private func setupRatingView() {

        self.ratingView.backgroundLayerColor = UIColor.Style.darkGreenBackground
        self.ratingView.progressBarUnfilledColor = UIColor.Style.darkGrayCustom

        if let voteAverage = self.presenter.movie.voteAverage,
            let formattedVote = Double(voteAverage * 10).roundedString {

            self.ratingView.text = "\(formattedVote)%"

            if voteAverage >= 7.0 {
                self.ratingView.progressBarUnfilledColor = UIColor.Style.darkGreen
                self.ratingView.progressBarFilledColor = UIColor.Style.lightGreen
            } else if voteAverage >= 4.0 {
                self.ratingView.progressBarUnfilledColor = UIColor.Style.darkYellow
                self.ratingView.progressBarFilledColor = UIColor.Style.lightYellow
            } else if voteAverage != 0.0 {
                self.ratingView.progressBarUnfilledColor = UIColor.Style.darkRed
                self.ratingView.progressBarFilledColor = UIColor.Style.lightRed
            }
        }
    }

    private func animateRatingView() {
        guard let voteAverage = self.presenter.movie.voteAverage,
            !self.hasRatingViewAnimated else { return }
        self.hasRatingViewAnimated = true
        self.ratingView.animate(withProgress: CGFloat(voteAverage / 10))
    }

    // MARK: - Actions

    @IBAction func didTapPlayVideoButton() {
        self.presenter.didTapPlayVideo()
    }

    @objc func didTapBackButton() {
        self.presenter.didTapClose()
    }
}

// MARK: - MovieDetailsPresenterOutputProtocol

extension MovieDetailsView: MovieDetailsPresenterOutputProtocol {

    // MARK: - Internal Methods

    func updateUI() {
        self.setupInformations()
        self.setupCastCrewView()
        self.setupRecommendationsView()
        self.playVideoButton.alpha = self.presenter.hasVideo ? 1.0 : 0.0
        self.view.layoutIfNeeded()

    }

    // MARK: - Private Methods

    private func setupInformations() {

        let Messages = MessagesUtil.MovieDetails.self
        let titleFont: UIFont = .systemFont(ofSize: 14.0, weight: .semibold)
        let contentFont: UIFont = .systemFont(ofSize: 14.0, weight: .regular)
        
        let titleColor: UIColor = UIColor.Style.darkGrayAdaptative
        let contentColor: UIColor = UIColor.Style.darkGrayAdaptative
        
        let duration = self.presenter.movie.runtime?.durationFormatted ?? "-"
        let durationTitle = Messages.durationTitle.attributed(with: titleFont, color: titleColor)
        let durationValue = duration.attributed(with: contentFont, color: contentColor)
        self.durationLabel.attributedText = durationTitle.concatene(to: durationValue)

        let budget = self.presenter.movie.budget?.formattedCurrency ?? "-"
        let budgetTitle = Messages.budgetTitle.attributed(with: titleFont, color: titleColor)
        let budgetValue = budget.attributed(with: contentFont, color: contentColor)
        self.budgetLabel.attributedText = budgetTitle.concatene(to: budgetValue)

        let revenue = self.presenter.movie.revenue?.formattedCurrency ?? "-"
        let revenueTitle = Messages.revenueTitle.attributed(with: titleFont, color: titleColor)
        let revenueValue = revenue.attributed(with: contentFont, color: contentColor)
        self.revenueLabel.attributedText = revenueTitle.concatene(to: revenueValue)

        let genres = self.presenter.movie.genres?.joined(separator: ", ") ?? "-"
        let genresTitle = Messages.genresTitle.attributed(with: titleFont, color: titleColor)
        let genresValue = genres.attributed(with: contentFont, color: contentColor)
        self.genresLabel.attributedText = genresTitle.concatene(to: genresValue)
    }

    private func setupCastCrewView() {

        let cast = self.presenter.movie.cast ?? []
        let crew = self.presenter.movie.crew ?? []

        guard !cast.isEmpty || !crew.isEmpty else { return }

        let castCrewView = CastCrewView.instantiate()
        self.stackView.addArrangedSubview(castCrewView)
        castCrewView.setupUI(with: cast, and: crew)
    }

    private func setupRecommendationsView() {

        guard let recommendations = self.presenter.movie.recommendations,
            !recommendations.isEmpty else { return }

        let recommendationsView = RecommendationsView.instantiate()
        recommendationsView.delegate = self

        self.stackView.addArrangedSubview(recommendationsView)
        recommendationsView.setupUI(with: recommendations)
    }
}

// MARK: - UIScrollViewDelegate

extension MovieDetailsView: UIScrollViewDelegate {

    // MARK: - Internal Methods

    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        
        let limitHeight: CGFloat = UIApplication.shared.statusBarFrame.height + 94.0

        if self.backgropImageView.frame.height < limitHeight {
            self.statusBarStyle = .default
            self.setupStandaloneNavigationBar(title: self.presenter.movie.title, style: .light)
        } else {
            self.statusBarStyle = .lightContent
            self.setupStandaloneNavigationBar(style: .transparent)
        }
    }
}

// MARK: - RecommendationsViewDelegate

extension MovieDetailsView: RecommendationsViewDelegate {

    // MARK: - Internal Methods

    func recommendationsView(_ recommendationsView: RecommendationsView, didSelect recommendation: MovieEntity) {
        self.presenter.didSelectRecommendation(recommendation)
    }
}

