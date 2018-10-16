//
//  AllViewController.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 16/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import UIKit
import ios

class AllViewController: UIViewController {

    @IBOutlet weak var talkListController: TalksListViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "all" {
            let connectionTalkToList = segue.destination as? TalksListViewController
            talkListController = connectionTalkToList
            talkListController.setTrack(track: Track.all)
        }
    }
    
}
