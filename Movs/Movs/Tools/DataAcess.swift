//
//  DataAcess.swift
//  Movs
//
//  Created by Eduardo Pereira on 26/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

protocol DataAcess:AnyObject{
    func getImage(path:String,_ fetch:@escaping(UIImage?)->())
    func getMovies(request:Request,page:Int,_ fetch: @escaping ([Movie]) -> ())
}
