//
//  BusinessViewController.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 16/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import UIKit
import ios

class BusinessViewController: UIViewController {

    @IBOutlet weak var businessTalksViewController: TalksListViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "business" {
            let connectionTalkToList = segue.destination as? TalksListViewController
            businessTalksViewController = connectionTalkToList
            businessTalksViewController.setTrack(track: Track.business)
        }
    }
    
}
