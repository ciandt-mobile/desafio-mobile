//
//  MovieActorsCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 30/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class MovieActorsCellBuilder: TableCellBuilder {
    
    private var credits: MovieCredits?
    
    init(properties: TableCellBuilderProperties, credits: MovieCredits?) {
        super.init(properties: properties, identifier: "MovieActorsCell")
        self.credits = credits
    }
    
    override func getCell() -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier: self.identifier,
                                                      for: self.indexPath) as! MovieActorsCell
        
        if let c = self.credits, let cast = c.cast, cast.count > 0 {
            cell.setup(credits: c)
        }
        
        return cell
    }
    
    override func getHeight() -> CGFloat {
        if let c = self.credits, let cast = c.cast, cast.count > 0 {
            return 180.0
        } else {
            return 0.0
        }
    }
}
