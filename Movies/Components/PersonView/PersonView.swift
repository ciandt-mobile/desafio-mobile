//
//  PersonView.swift
//  Movies
//
//  Created by Piero Mattos on 28/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

protocol PersonRepresentable {
    var name: String { get }
    var role: String { get }
    var profilePictureURL: URL? { get }
}

class PersonView: UIStackView {

    // MARK: - Properties

    @IBOutlet weak var profileImageView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var roleLabel: UILabel!

    var person: PersonRepresentable? {
        didSet {
            updateView()
        }
    }

    // MARK: - Methods

    private func updateView() {
        DispatchQueue.main.async { [weak self] in
            guard
                let strongSelf = self,
                let person = strongSelf.person,
                let placeholderImage = UIImage(named: "Person placeholder")
                else { return }

            if let profilePictureURL = person.profilePictureURL {
                strongSelf.profileImageView.loadImage(profilePictureURL, placeholder: placeholderImage)
            } else {
                strongSelf.profileImageView.image = placeholderImage
            }

            strongSelf.profileImageView.layer.cornerRadius = strongSelf.profileImageView.frame.size.height / 2.0
            strongSelf.profileImageView.clipsToBounds = true

            strongSelf.nameLabel.text = person.name
            strongSelf.roleLabel.text = person.role
        }
    }

    static func forPerson(_ person: PersonRepresentable) -> PersonView {
        guard
            let personView = Bundle.main.loadNibNamed("PersonView", owner: nil, options: nil)?[0] as? PersonView
            else { return PersonView() }
        personView.person = person
        return personView
    }
}
