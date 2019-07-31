//
//  MovieDetailStatusCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class MovieDetailStatusCellBuilder: TableCellBuilder {
    
    private var status: MovieDetailTableBuilderState = .Loading
    private var error: String?
    
    init(properties: TableCellBuilderProperties, status: MovieDetailTableBuilderState, error: String?) {
        super.init(properties: properties, identifier: "MovieDetailStatusCell")
        self.status = status
        self.error = error
    }
    
    override func getCell() -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier: self.identifier,
                                                      for: self.indexPath) as! MovieDetailStatusCell
        
        cell.setup(status: status, error: error)
        
        return cell
    }
    
    override func getHeight() -> CGFloat {
        return 80.0
    }
}
