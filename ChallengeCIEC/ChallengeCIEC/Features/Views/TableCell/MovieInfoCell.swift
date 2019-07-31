//
//  MovieInfoCell.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import UIKit



class MovieInfoCell: UITableViewCell {

    @IBOutlet weak var labelTitle: UILabel!
    @IBOutlet weak var labelInfo: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setup(movie: MovieDetail?) {
        if let m = movie {
            self.labelTitle.text = title(movie: m)
            self.labelInfo.text = info(movie: m)
        }
    }
}

extension MovieInfoCell {
    
    func title(movie: MovieDetail) -> String {
        if let title = movie.title {
            return title
        } else {
            return ""
        }
    }
    
    func info(movie: MovieDetail) -> String {
        var text = ""
        let runtime = self.runtime(movie: movie)
        let genres = self.genres(movie: movie)
        
        if runtime != "" {
            text = runtime
        }
        
        if genres != "" {
            if text != "" {
                text = text + " | " + genres
            } else {
                text = genres
            }
        }
        
        return text
    }
    
    func runtime(movie: MovieDetail) -> String {
        var text = ""
        
        if let runtime = movie.runtime {
            text = "\(runtime)m"
        }
        
        return text
    }
    
    func genres(movie: MovieDetail) -> String {
        var text = ""
        
        if let genres = movie.genres, genres.count > 0 {
            
            for (i, genre) in genres.enumerated() {
                if let name = genre.name {
                    if i == genres.count - 1 {
                        text = text + name + "."
                    } else {
                        text = text + name + ", "
                    }
                }
            }
        }
        
        return text
    }
}
