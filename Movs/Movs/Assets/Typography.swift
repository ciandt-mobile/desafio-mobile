//
//  Typography.swift
//  Movs
//
//  Created by Eduardo Pereira on 31/07/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

enum Typography {
    case title(UIColor,CGFloat?)
    case description(UIColor,CGFloat?)

    
    func attributes() ->[NSAttributedString.Key : Any]{
        switch self {
        case .title(let color,let size):
            return [NSAttributedString.Key.font:UIFont(name:"Silentina Movie", size: size ?? 15) ?? UIFont.preferredFont(forTextStyle: .title1),NSAttributedString.Key.foregroundColor:color]
        case .description(let color,let size):
            return [NSAttributedString.Key.font:UIFont(name:"Silentina Film", size: size ?? 15) ?? UIFont.preferredFont(forTextStyle: .title1),NSAttributedString.Key.foregroundColor:color]
        }
       
        
    }
}
