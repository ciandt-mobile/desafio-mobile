//
//  MovieFilterViewController.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 01/07/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import UIKit

protocol MovieFilterFlowDelegate {
    func didSelectFilters(selectedDates:[Date], selectedGenres:[GenreModel])
}

class MovieFilterViewController: BaseViewController {

    @IBOutlet weak var navigationBar: UINavigationBar!
    @IBOutlet weak var movieFilterTableView: UITableView!
    
    var years:[Date] = []
    var existingGenres:[Int] = []
    private var existingGenresDict:[Int:Bool] = [:]
    private var genres:[GenreModel] = []
    private var selectedGenres:[Int:Bool] = [:]
    private var selectedYears:[String:Date?] = [:]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupExistingGenresDict()
        setupNavigationBar()
        setupFilterTableView()
        getGenres()
        setupSelectedFilters()
    }
    
    private func setupExistingGenresDict() {
        for genreId in existingGenres {
            existingGenresDict[genreId] = true
        }
    }
    
    private func setupNavigationBar() {
        self.navigationBar.barTintColor = Colors.yellow
        self.navigationBar.tintColor = Colors.marineBlue
    }
    
    private func setupFilterTableView() {
        movieFilterTableView.delegate = self
        movieFilterTableView.dataSource = self
        movieFilterTableView.register(UINib(nibName: "MovieFilterTableViewCell", bundle: nil), forCellReuseIdentifier: CellReuse.MOVIE_FAVORITE_FILTER_CELL)
    }
    
    private func getGenres() {
        let provider = AppProvider()
        setRetryErrorBlock { self.getGenres() }
        showLoadingInView(withMessage: "Carregando informações do filme...")
        provider.makeRequest(.getGenreList, returnClass: GeneralGenreResponseModel.self, successCompletion: { (response) in
            self.genres = self.getFilteredGenres(response.getGenreModels())
            self.setNormalLayout()
            self.setupSelectedFilters()
            self.movieFilterTableView.reloadData()
        }) { (error) in
            self.showError(error)
        }
    }
    
    private func setupSelectedFilters() {
        for genre in genres {
            selectedGenres[genre.id] = true
        }
        for date in years {
            selectedYears["\(date.getYearValue())"] = date
        }
    }
    
    private func getFilteredGenres(_ allGenres:[GenreModel]) -> [GenreModel] {
        var returnGenres:[GenreModel] = []
        for genre in allGenres {
            if (existingGenresDict[genre.id] != nil) {
                returnGenres.append(genre)
            }
        }
        return returnGenres
    }
    
    @IBAction func closeButtonClicked(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
}


extension MovieFilterViewController: UITableViewDataSource, UITableViewDelegate {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if (section == 0) {
            return "Select by Date"
        } else if (section == 1) {
            return "Select by Genre"
        }
        return nil
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (section == 0) {
            return years.count
        } else if (section == 1) {
            return genres.count
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CellReuse.MOVIE_FAVORITE_FILTER_CELL) as? MovieFilterTableViewCell else {
            return UITableViewCell()
        }
        if (indexPath.section == 0) {
            if (years.indices.contains(indexPath.row)) {
                let yearString = "\(years[indexPath.row].getYearValue())"
                cell.setTitle(yearString)
                cell.setCheckImage(selectedYears[yearString] != nil)
            }
        } else if (indexPath.section == 1) {
            if (genres.indices.contains(indexPath.row)) {
                let thisGenre = genres[indexPath.row]
                cell.setTitle("\(thisGenre.name)")
                cell.setCheckImage(selectedGenres[thisGenre.id] ?? false)
            }
        }
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if (indexPath.section == 0) {
            if (years.indices.contains(indexPath.row)) {
                let yearString = "\(years[indexPath.row].getYearValue())"
                if (selectedYears[yearString] != nil) {
                    selectedYears[yearString] = nil
                } else {
                    selectedYears[yearString] = years[indexPath.row]
                }
            }
        } else if (indexPath.section == 1) {
            if (genres.indices.contains(indexPath.row)) {
                let thisGenre = genres[indexPath.row]
                selectedGenres[thisGenre.id] = !(selectedGenres[thisGenre.id] ?? false)
            }
        }
        tableView.reloadData()
    }
}
