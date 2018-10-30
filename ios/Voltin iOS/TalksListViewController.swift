//
//  TalksListViewController.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 16/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import UIKit
import ios

class TalksListViewController: UIViewController, TalksListView {
    
    private lazy var presenter : TalksListPresenter = TalksListPresenter(
        executor: IosExecutor(),
        repository: CommonRepository(local: IosLocalDataSource(), remote: CommonRemoteDataSource()),
        view: self,
        errorHandler: IosErrorHandler())
    
    
    var track: Track = Track.all
    
    @IBOutlet weak var test: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.initialize()
        presenter.onTrackChanged(track: track)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func setTrack(track: Track){
        self.track = track
    }
    
    func showProgress() {
    
    }
    
    func hideProgress() {
    
    }
    
    func showError(error: String) {
    
    }
    
    func showMessage(message: String) {
        
    }
    
    func getTrack() -> Track {
        return track
    }
    
    func showTalks(talks: [Talk]) {
        var talkInfo = track.name + "\n"
        for talk in talks {
            talkInfo += "Name: " + talk.name + "\n"
        }
        test.text = talkInfo
    }
    
    func goToTalkScreen(id: Int32) {
    
    }

}
