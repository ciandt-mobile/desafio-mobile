//
//  DetailController.swift
//  Movs
//
//  Created by Eduardo Pereira on 27/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class DetailController: UIViewController {
    var viewModel:DetailViewModel!
    let detailView = DetailView()
    override func viewDidLoad() {
        initViewCoding()
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        viewModel.configureNavBar(navController: self.navigationController)
    }
    init(model:Movie,dataAcess:DataAcess){
        super.init(nibName: nil, bundle: nil)
        viewModel = DetailViewModel(model: model, dataAcess: dataAcess,uiHandler: {
            DispatchQueue.main.async {
                [weak self] in
                guard let self = self else{
                    return
                }
                self.detailView.setup(viewModel: self.viewModel)
                self.detailView.reloadCollection()
            }
          
            
        } )
        
        detailView.artistCollection.delegate = viewModel
        detailView.artistCollection.dataSource = viewModel
        
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
extension DetailController:ViewCoding{
    func buildViewHierarchy() {
        self.view.addSubview(detailView)
    }
    
    func setUpConstraints() {
        detailView.fillSuperview()
    }
    
    
}
