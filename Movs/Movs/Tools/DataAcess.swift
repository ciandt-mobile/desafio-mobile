//
//  DataAcess.swift
//  Movs
//
//  Created by Eduardo Pereira on 26/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

protocol DataAcess:AnyObject{
    var genres:[Genre]{get set}
    func getImage(path:String,width:Int,_ fetch:@escaping(UIImage?)->())
    func getMovies(request:Request,page:Int,_ fetch: @escaping ([Movie]) -> ())
    func getDuration(id: Int, _ fetch: @escaping (Int?) -> ())
    func getCast(id:String,_ fetch: @escaping ([Cast]) ->())
}
