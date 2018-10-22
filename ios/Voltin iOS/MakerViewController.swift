//
//  MakerViewController.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 16/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import UIKit
import ios

class MakerViewController: UIViewController {

    @IBOutlet weak var makerTalksViewController: TalksListViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "maker" {
            let connectionTalkToList = segue.destination as? TalksListViewController
            makerTalksViewController = connectionTalkToList
            makerTalksViewController.setTrack(track: Track.maker)
        }
    }
    

}
