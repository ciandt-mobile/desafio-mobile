//
//  ColorScheme.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import UIKit
import ChameleonFramework

enum UsedColors {
    case black
    case gray
    case white
    case purple
    case gold
    
    var color: UIColor{
        switch self {
        case .black:
            return UIColor.flatBlack
            
        case .gray:
            return UIColor.flatGray
            
        case .white:
            return UIColor.flatWhiteDark
            
        case .purple:
            return UIColor.flatPurple
            
        case .gold:
            return UIColor.flatYellowDark
        }
    }
}
