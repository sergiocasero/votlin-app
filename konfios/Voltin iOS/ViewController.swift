//
//  ViewController.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 13/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import UIKit
import konfios

class ViewController: UIViewController, PresenterView {
    
    private lazy var presenter: Shared = {
        Shared(
            view: self,
            platform: "iOS"
        )
    }()
    
    @IBOutlet weak var textv: UITextView!
    
    override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view, typically from a nib.
        presenter.doShomething()
    }


    override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
    }
    
    func showText(text: String) {
        textv.text = text
    }


}
