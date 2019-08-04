import Foundation
import UIKit

extension UIViewController {
    
    func displayLoading(onView : UIView) -> UIView {
        
        let loadingView = UIView.init(frame: onView.bounds)
        loadingView.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 0.3)
        
        let activityIndicator = UIActivityIndicatorView.init(style: .whiteLarge)
        activityIndicator.startAnimating()
        activityIndicator.center = loadingView.center
        
        DispatchQueue.main.async {
            loadingView.addSubview(activityIndicator)
            onView.addSubview(loadingView)
        }
        
        return loadingView
    }
    
    func removeLoading(_ loadingView :UIView) {
        DispatchQueue.main.async {
            loadingView.removeFromSuperview()
        }
    }
    
}
