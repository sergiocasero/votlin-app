//
//  TalksListViewController.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 16/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import UIKit
import ios

class TalksListViewController: UIViewController {
    
    var track: Track = Track.all
    
    @IBOutlet weak var test: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        test.text = track.name
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func setTrack(track: Track){
        self.track = track
    }

}
