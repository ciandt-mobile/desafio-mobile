//
//  MovieDetailTableBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

enum MovieDetailTableBuilderState: Int {
    case Loading, Error, Success
}

protocol MovieDetailTableBuilderProtocol {
    
}

class MovieDetailTableBuilder: NSObject {
    
    var tableView: UITableView?
    var delegate: MovieDetailTableBuilderProtocol?
    
    private var state: MovieDetailTableBuilderState
    private var movie: MovieDetail?
    private var credits: MovieCredits?
    private var error: String?
    
    init(state: MovieDetailTableBuilderState, movie: MovieDetail?, credits: MovieCredits?) {
        self.state = state
        self.movie = movie
        self.credits = credits
    }
    
    init(state: MovieDetailTableBuilderState, error: String?) {
        self.state = state
        self.error = error
    }
}

extension MovieDetailTableBuilder: UITableViewDataSource, UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        if state == .Success {
            return 4
        } else {
            return 1
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        let properties = TableCellBuilderProperties(tableView: tableView,
                                                    indexPath: indexPath,
                                                    state: self.state.rawValue)
        if state == .Success {
            switch indexPath.row {
            case 0:
                return MovieBackdropCellBuilder(properties: properties, movie: self.movie).height
            case 1:
                return MovieInfoCellBuilder(properties: properties, movie: self.movie).height
            case 2:
                return MovieActorsCellBuilder(properties: properties, credits: self.credits).height
            case 3:
                return MovieOverviewCellBuilder(properties: properties, movie: self.movie).height
            default:
                return 0.0
            }
        } else {
            return MovieDetailStatusCellBuilder(properties: properties, status: self.state, error: self.error).height
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let properties = TableCellBuilderProperties(tableView: tableView,
                                                    indexPath: indexPath,
                                                    state: self.state.rawValue)
        
        if state == .Success {
         
            switch indexPath.row {
            case 0:
                return MovieBackdropCellBuilder(properties: properties, movie: self.movie).cell
            case 1:
                return MovieInfoCellBuilder(properties: properties, movie: self.movie).cell
            case 2:
                return MovieActorsCellBuilder(properties: properties, credits: self.credits).cell
            case 3:
                return MovieOverviewCellBuilder(properties: properties, movie: self.movie).cell
            default:
                return UITableViewCell()
            }
        } else {
            return MovieDetailStatusCellBuilder(properties: properties, status: self.state, error: self.error).cell
            
        }
    }
}
