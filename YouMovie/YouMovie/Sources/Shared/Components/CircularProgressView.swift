//
//  CircularProgressView.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

@IBDesignable
class CircularProgressView: UIView {

    // MARK: - Internal Properties

    @IBInspectable var text: String = "NR" {
        didSet {
            self.setupUI()
        }
    }

    @IBInspectable var textColor: UIColor = .white {
        didSet {
            self.setupUI()
        }
    }

    @IBInspectable var progressBarUnfilledColor: UIColor = .lightGray {
        didSet {
            self.setupUI()
        }
    }

    @IBInspectable var progressBarFilledColor: UIColor = .darkGray {
        didSet {
            self.setupUI()
        }
    }

    @IBInspectable var backgroundLayerColor: UIColor = .black {
        didSet {
            self.setupUI()
        }
    }

    // MARK: - Private Properties

    private var backgroundLayer: CAShapeLayer!
    private var progressLayer: CAShapeLayer!
    private var trackLayer: CAShapeLayer!
    private var percentageLabel: UILabel = UILabel()

    // MARK: - Lifecycle

    override func prepareForInterfaceBuilder() {
        super.prepareForInterfaceBuilder()
        self.setupUI()
    }
    
    // MARK: - Internal Methods

    /// Method used to animate circular progress bar
    ///
    /// - Parameter progress: The value of this property must be in the range 0.0 to 1.0.
    func animate(withProgress progress: CGFloat = 0.0) {

        let basicAnimation = CABasicAnimation(keyPath: "strokeEnd")
        basicAnimation.toValue = progress
        basicAnimation.duration = 1.0
        basicAnimation.fillMode = .forwards
        basicAnimation.isRemovedOnCompletion = false
        basicAnimation.timingFunction = CAMediaTimingFunction(name: .easeInEaseOut)

        self.progressLayer.add(basicAnimation, forKey: "progressAnimation")
    }

    // MARK: - Private Methods

    private func setupUI() {

        self.backgroundColor = .clear
        self.layer.cornerRadius = self.frame.size.width / 2

        self.backgroundLayer = self.createCircleShapeLayer(withStrokeColor: self.backgroundLayerColor,
                                                           fillColor: .clear)
        self.backgroundLayer.lineWidth = 10.0
        self.layer.addSublayer(self.backgroundLayer)

        self.trackLayer = self.createCircleShapeLayer(withStrokeColor: self.progressBarUnfilledColor,
                                                      fillColor: self.backgroundLayerColor)
        self.trackLayer.lineWidth = 5.0
        self.layer.addSublayer(self.trackLayer)

        self.progressLayer = self.createCircleShapeLayer(withStrokeColor: self.progressBarFilledColor,
                                                         fillColor: .clear)
        self.progressLayer.lineWidth = 5.0
        self.progressLayer.transform = CATransform3DMakeRotation(-.pi / 2.0, 0.0, 0.0, 1.0)
        self.progressLayer.strokeEnd = 0.0
        self.layer.addSublayer(self.progressLayer)

        self.percentageLabel.textAlignment = .center
        self.percentageLabel.text = self.text
        self.percentageLabel.textColor = self.textColor
        self.percentageLabel.font = .systemFont(ofSize: 12.0, weight: .semibold)
        self.percentageLabel.frame = self.bounds
        self.addSubview(self.percentageLabel)

        self.layoutIfNeeded()
        self.clipsToBounds = false
    }

    private func createCircleShapeLayer(withStrokeColor strokeColor: UIColor, fillColor: UIColor) -> CAShapeLayer {

        let layer = CAShapeLayer()
        let circularPath = UIBezierPath(arcCenter: CGPoint(x: self.frame.size.width / 2, y: self.frame.size.height / 2),
                                        radius: (self.frame.width - 1.5) / 2,
                                        startAngle: 0.0,
                                        endAngle: 2 * .pi,
                                        clockwise: true)

        layer.path = circularPath.cgPath
        layer.strokeColor = strokeColor.cgColor
        layer.fillColor = fillColor.cgColor
        layer.lineCap = .round
        layer.position = self.center
        layer.frame = self.bounds

        return layer
    }
}
