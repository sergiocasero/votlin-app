//
//  TalksListViewController.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 16/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import UIKit
import ios

class TalksListViewController: UIViewController, UICollectionViewDataSource, UICollectionViewDelegate, TalksListView {
    
    private lazy var presenter : TalksListPresenter = TalksListPresenter(view: self, errorHandler: IosErrorHandler())
    
    var track: Track = Track.all
    
    var talks: [Talk] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.initialize()
        presenter.onViewVisible()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func setTrack(track: Track){
        self.track = track
    }
   
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return talks.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! TalkCollectionViewCell
        
        let talk = self.talks[indexPath.row]
        cell.talkName.text = talk.name
        cell.talkDescription.text = ""
        
        return cell
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
        self.talks = talks
    }
    
    func goToTalkScreen(id: Int32) {
    
    }

}
