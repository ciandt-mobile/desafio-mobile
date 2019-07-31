//
//  TableCellBuilder.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import Foundation
import UIKit

class TableCellBuilder {
    
    private(set) var identifier: String
    private(set) var tableView: UITableView
    private(set) var indexPath: IndexPath
    private(set) var state: Int
    
    var cell: UITableViewCell { get { return self.getCell() }}
    var height: CGFloat { get { return self.getHeight() }}
    
    init(properties: TableCellBuilderProperties, identifier: String) {
        self.tableView = properties.tableView
        self.indexPath = properties.indexPath
        self.state = properties.state
        self.identifier = identifier
        
        self.register()
    }
    
    private func register() {
        self.tableView.register(UINib(nibName: self.identifier, bundle: nil),
                                forCellReuseIdentifier: self.identifier)
    }
    
    func getCell() -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier: self.identifier, for: self.indexPath)
        return cell
    }
    
    func getHeight() -> CGFloat {
        return 20.0
    }
}

struct TableCellBuilderProperties {
    var tableView: UITableView
    var indexPath: IndexPath
    var state: Int
}
