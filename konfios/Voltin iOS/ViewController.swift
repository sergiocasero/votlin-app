//
//  ViewController.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 13/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import UIKit
import konfios

class ViewController: UIViewController {
    
    
    @IBOutlet weak var textv: UITextView!
    
    public lazy var shared = { Shared() }()

    override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view, typically from a nib.
        textv.text = shared.text
    }


    override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
    }



}
