//
//  MovieOverviewCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class MovieOverviewCellBuilder: TableCellBuilder {
    
    private var movie: MovieDetail?
    
    init(properties: TableCellBuilderProperties, movie: MovieDetail?) {
        super.init(properties: properties, identifier: "MovieOverviewCell")
        self.movie = movie
    }
    
    override func getCell() -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier: self.identifier,
                                                      for: self.indexPath) as! MovieOverviewCell
        cell.setup(movie: movie)
        
        return cell
    }
    
    override func getHeight() -> CGFloat {
        return UITableView.automaticDimension
    }
}
