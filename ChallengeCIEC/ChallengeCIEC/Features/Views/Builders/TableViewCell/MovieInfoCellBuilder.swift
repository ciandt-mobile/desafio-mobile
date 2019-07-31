//
//  MovieInfoCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class MovieInfoCellBuilder: TableCellBuilder {
    
    private var movie: MovieDetail?
    
    init(properties: TableCellBuilderProperties, movie: MovieDetail?) {
        super.init(properties: properties, identifier: "MovieInfoCell")
        self.movie = movie
    }
    
    override func getCell() -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier: self.identifier,
                                                      for: self.indexPath) as! MovieInfoCell
        cell.setup(movie: self.movie)
        
        return cell
    }
    
    override func getHeight() -> CGFloat {
        return UITableView.automaticDimension
    }
}
