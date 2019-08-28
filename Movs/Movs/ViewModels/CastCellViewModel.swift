//
//  CastCellViewModel.swift
//  Movs
//
//  Created by Eduardo Pereira on 27/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class CastCellViewModel{
    var image:UIImage? = UIImage(named: "image_not_found")
    var title:NSMutableAttributedString = NSMutableAttributedString()
    private let cast:Cast
    init(cast:Cast,dataAcess:DataAcess,uiHandler:(()->Void)?){
      self.cast = cast
        dataAcess.getImage(path: cast.profile_path ?? "", width: 500) {[weak self] (image) in
            self?.image = image
            uiHandler?()
        }
        title.append(NSAttributedString(string: "\(cast.name ?? "")\n", attributes: Typography.title(Color.black, nil).attributes()))
        title.append(NSAttributedString(string: cast.character ?? "", attributes: Typography.title(Color.scarlet, nil).attributes()))
        
    }
   

}

