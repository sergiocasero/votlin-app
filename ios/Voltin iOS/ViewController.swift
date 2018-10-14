//
//  ViewController.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 13/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import UIKit
import ios

class TalksViewController: UIViewController, TalksView {
    
    private lazy var presenter: TalksPresenter = {
        TalksPresenter(
            errorHandler: IosErrorHandler(),
            view: self
        )
    }()
    
    override func viewDidLoad() {
    super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        presenter.initialize()
    }


    override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func showProgress() {
        //TODO: Show progress
    }
    
    func hideProgress() {
        //TODO: Hide progress
    }
    
    func showError(error: String) {
        //TODO: Show Error
    }
    
    func showMessage(message: String) {
        //TODO: Hide Error
    }
    
    func showTalks(talks: [Talk]) {
        // TODO: Show talks
    }


}
