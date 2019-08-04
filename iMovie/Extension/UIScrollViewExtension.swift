import UIKit


extension UIScrollView {
    
    func resizeScrollViewToContent() {
        
        var contentRect = CGRect.zero
        for view in self.subviews {
            contentRect = contentRect.union(view.frame)
        }
        
        self.contentSize = contentRect.size
    }
    
}
