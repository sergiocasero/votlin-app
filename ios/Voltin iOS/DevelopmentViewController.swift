//
//  DevelopmentViewController.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 16/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import UIKit
import ios

class DevelopmentViewController: UIViewController {

    
    @IBOutlet weak var developmentTalksViewController: TalksListViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "development" {
            let connectionTalkToList = segue.destination as? TalksListViewController
            developmentTalksViewController = connectionTalkToList
            developmentTalksViewController.setTrack(track: Track.development)
        }
    }
    

}
