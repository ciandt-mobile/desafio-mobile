
import UIKit

class AppButton: UIButton {
    
    @objc enum AppButtonType:Int {
        case Filled
        case Bordered
        case Delete
    }
    
    public override var isEnabled: Bool {
        didSet {
            if (isEnabled) {
                backgroundColor = referenceColor
                commonInit()
            } else {
                backgroundColor = disabledColor
                layer.borderColor = Colors.lightGray.cgColor
                layer.borderWidth = 0
                tintColor = Colors.lightGray
            }
        }
    }
    
    @IBInspectable
    public var appButtonType:Int = 0 {
        didSet {
            self.type = AppButtonType(rawValue: self.appButtonType)!;
            self.commonInit();
        }
    }
    
    public var referenceColor = Colors.yellow {
        didSet {
            commonInit()
        }
    }
    
    public var disabledColor = Colors.backgroundGray
    
    var type: AppButtonType = .Filled;
    
    init(_title:String, _frame:CGRect, _type:AppButtonType) {
        super.init(frame: _frame);
        self.type = _type;
        self.setTitle(_title, for: .normal);
        self.commonInit();
    }

    override init(frame: CGRect) {
        super.init(frame: frame);
        self.commonInit();
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder);
        self.commonInit();
    }
    
    func commonInit() {
        self.layer.cornerRadius = self.frame.height / 2
        switch self.type {
        case .Filled:
            backgroundColor = Colors.yellow
            tintColor = UIColor.white
            layer.borderWidth = 0
            break;
        case .Bordered:
            layer.borderColor = Colors.yellow.cgColor
            layer.borderWidth = 1
            backgroundColor = UIColor.white
            tintColor = Colors.yellow
            break;
        case .Delete:
            layer.borderColor = UIColor.red.cgColor;
            layer.borderWidth = 1;
            backgroundColor = UIColor.white;
            tintColor = UIColor.red;
            break;
        }
    }
}
