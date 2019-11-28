//
//  BaseViewController.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

class BaseViewController: UIViewController {

    // MARK: - Definitions

    enum StandaloneNavigationBarStyle {
        case transparent
        case light
    }

    // MARK: - Internal Properties

    override var preferredStatusBarStyle: UIStatusBarStyle { return self.statusBarStyle }

    var statusBarStyle: UIStatusBarStyle = .default {
        didSet {
            guard self.statusBarStyle != oldValue else { return }
            self.setNeedsStatusBarAppearanceUpdate()
        }
    }

    var standaloneNavigationBar: UINavigationBar?
    var standaloneNavigationBarStyle: StandaloneNavigationBarStyle?

    // MARK: - Internal Methods
    
    func setupBaseNavigationBar(title: String? = nil,
                                titleColor: UIColor = UIColor.Style.blackAdaptative,
                                tintColor: UIColor = UIColor.Style.blackAdaptative,
                                barStyle: UIBarStyle = .default,
                                barColor: UIColor? = nil,
                                isTranslucent: Bool = false,
                                isLargeTitle: Bool = false) {

        guard let navigationController = self.navigationController else { return }

        navigationController.setNavigationBarHidden(false, animated: true)
        navigationController.navigationBar.titleTextAttributes = [.foregroundColor: titleColor]
        navigationController.navigationBar.tintColor = tintColor
        navigationController.navigationBar.barStyle = barStyle
        navigationController.navigationBar.barTintColor = barColor
        navigationController.navigationBar.isTranslucent = isTranslucent
        navigationController.navigationBar.prefersLargeTitles = isLargeTitle
        navigationController.navigationItem.largeTitleDisplayMode = .automatic
        navigationController.navigationBar.largeTitleTextAttributes = [.foregroundColor: titleColor]

        if let title = title {
            self.navigationItem.title = title
        }
    }

    func setupStandaloneNavigationBar(title: String? = nil,
                                      style: StandaloneNavigationBarStyle,
                                      backAction: Selector? = nil) {

        guard self.standaloneNavigationBarStyle != style else { return }

        self.navigationController?.setNavigationBarHidden(true, animated: true)
        self.navigationController?.interactivePopGestureRecognizer?.delegate = nil

        let navigationBar = self.standaloneNavigationBar ?? UINavigationBar()
        navigationBar.isTranslucent = true

        switch style {
        case .transparent:

            UIView.animate(withDuration: 0.3) {
                navigationBar.setBackgroundImage(UIImage(), for: .default)
                navigationBar.shadowImage = UIImage()
                navigationBar.barTintColor = .clear
                navigationBar.titleTextAttributes = [.foregroundColor: UIColor.white]
                navigationBar.layoutIfNeeded()
            }
            navigationBar.tintColor = .white
        case .light:

            UIView.animate(withDuration: 0.3) {
                navigationBar.setBackgroundImage(nil, for: .default)
                navigationBar.shadowImage = nil
                navigationBar.barTintColor = nil
                navigationBar.titleTextAttributes = [.foregroundColor: UIColor.Style.blackAdaptative]
                navigationBar.layoutIfNeeded()
            }
            navigationBar.tintColor = UIColor.Style.blackAdaptative
        }

        navigationBar.topItem?.title = title
        self.standaloneNavigationBarStyle = style

        guard self.standaloneNavigationBar == nil ||
            self.view.subviews.contains(where: { $0.isKind(of: UINavigationBar.self) }) else { return }
        self.standaloneNavigationBar = navigationBar
        self.standaloneNavigationBar?.delegate = self
        self.view.addSubview(navigationBar)

        navigationBar.translatesAutoresizingMaskIntoConstraints = false
        navigationBar.leftAnchor.constraint(equalTo: self.view.leftAnchor).isActive = true
        navigationBar.rightAnchor.constraint(equalTo: self.view.rightAnchor).isActive = true
        navigationBar.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor).isActive = true
        navigationBar.layoutIfNeeded()

        guard let backAction = backAction else { return }
        let backNavigationItem = UINavigationItem()
        let backButton = UIButton(frame: .zero)

        backButton.setImage(UIImage(named: "icon-back-arrow"), for: .normal)
        backButton.addTarget(self, action: backAction, for: .touchUpInside)

        backNavigationItem.setLeftBarButton(UIBarButtonItem(customView: backButton), animated: true)
        navigationBar.pushItem(backNavigationItem, animated: true)
    }

    func dismissKeyboard() {
        self.view.endEditing(true)
    }
}

// MARK: - UINavigationBarDelegate

extension BaseViewController: UINavigationBarDelegate {

    // MARK: - Internal Methods

    func position(for bar: UIBarPositioning) -> UIBarPosition {
        return .topAttached
    }
}
