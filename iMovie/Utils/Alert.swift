import Foundation
import SwiftMessages
import UIKit

class Alert {
    
    enum AlertType {
        case success, error, warning
    }
    
    public static func show(title : String = "Attention", message : String, type : AlertType) {
        
        let view = MessageView.viewFromNib(layout: .cardView)
        var config = SwiftMessages.Config()
        config.duration = .seconds(seconds: 3)
        view.configureTheme(getTheme(type))
        view.configureDropShadow()
        view.configureContent(title: title, body: message, iconImage: getImage(type))
        view.button?.isHidden = true
        
        view.layoutMarginAdditions = UIEdgeInsets(top: 20, left: 20, bottom: 20, right: 20)
        (view.backgroundView as? CornerRoundingView)?.cornerRadius = 10
        SwiftMessages.show(config: config, view: view)
        
    }
    
    private static func getImage(_ type : AlertType) -> UIImage {
        switch type {
        case AlertType.success:
            return UIImage(named: "success")!
        case AlertType.error:
            return UIImage(named: "error")!
        case AlertType.warning:
            return UIImage(named: "danger.png")!
        }
    }
    
    private static func getTheme(_ type : AlertType) -> Theme {
        switch type {
        case AlertType.success:
            return Theme.success
        case AlertType.error:
            return Theme.error
        case AlertType.warning:
            return Theme.warning
        }
    }
    
}
