//
//  MovieBackdropCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class MovieBackdropCellBuilder: TableCellBuilder {
    
    private var movie: MovieDetail?
    
    init(properties: TableCellBuilderProperties, movie: MovieDetail?) {
        super.init(properties: properties, identifier: "MovieBackdropCell")
        self.movie = movie
    }
    
    override func getCell() -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier: self.identifier,
                                                      for: self.indexPath) as! MovieBackdropCell
        
        if let m = self.movie{
            cell.setup(movie: m)
        }
        
        return cell
    }
    
    override func getHeight() -> CGFloat {
        
        guard let m = movie, let bg = m.backdrop, !bg.isEmpty else { return 0 }
        
        let baseW: CGFloat = 500.0
        let baseH: CGFloat = 281.0
        
        let width: CGFloat = self.tableView.bounds.width
        let height: CGFloat = (width * baseH) / baseW
        
        return height
    }
}
