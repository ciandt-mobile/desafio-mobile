//
//  TelaCollectionViewCell.swift
//  Filme App Collection
//
//  Created by Miguel Fernandes Lopes on 27/11/19.
//  Copyright © 2019 Miguel Fernandes Lopes. All rights reserved.
//

import UIKit
import Kingfisher

class TelaCollectionViewCell: UICollectionViewCell {

    @IBOutlet private weak var tituloLabel: UILabel!
    @IBOutlet private weak var imagemView: UIImageView!
    @IBOutlet private weak var dtLancamento: UILabel!
    
    override func prepareForReuse() {
        super.prepareForReuse()
        imagemView.kf.cancelDownloadTask()
        imagemView.image = nil
    }
    
    func setupCell(title: String, image: Resource, dateLancemto: String){
        tituloLabel.text = title
        imagemView.kf.setImage(with: image)
        dtLancamento.text = formateDate(dateString: dateLancemto)
    }

    private func formateDate(dateString: String) -> String {
        let dateFormatterGet = DateFormatter()
        dateFormatterGet.dateFormat = "yyyy-MM-dd"
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd/MM/yy"
        let date = dateFormatterGet.date(from: dateString)
        return dateFormatter.string(from: date!)
    }
    
}
