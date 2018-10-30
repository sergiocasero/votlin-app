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
    
    @IBOutlet weak var collectionView: UICollectionView!
    
    private lazy var presenter : TalksListPresenter = TalksListPresenter(
        executor: IosExecutor(),
        repository: CommonRepository(local: IosLocalDataSource(), remote: CommonRemoteDataSource()),
        view: self,
        errorHandler: IosErrorHandler())
    
    
    var track: Track = Track.all
    
    var talks: [Talk] = []
    
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
   
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return talks.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! TalkCollectionViewCell
        
        let talk = self.talks[indexPath.row]
        cell.talkName.text = talk.name
        switch talk.track {
        case Track.business:
            cell.backgroundColor = UIColor(red:0.29, green:0.53, blue:0.92, alpha:1.0)
            cell.talkName.textColor = UIColor(red:1.00, green:1.00, blue:1.00, alpha:1.0)
            cell.talkDescription.textColor = UIColor(red:1.00, green:1.00, blue:1.00, alpha:1.0)
        case Track.development:
            cell.backgroundColor = UIColor(red:0.95, green:0.76, blue:0.18, alpha:1.0)
            cell.talkName.textColor = UIColor(red:0.00, green:0.00, blue:0.00, alpha:1.0)
            cell.talkDescription.textColor = UIColor(red:0.00, green:0.00, blue:0.00, alpha:1.0)
        case Track.maker:
            cell.backgroundColor = UIColor(red:0.85, green:0.19, blue:0.23, alpha:1.0)
            cell.talkName.textColor = UIColor(red:1.00, green:1.00, blue:1.00, alpha:1.0)
            cell.talkDescription.textColor = UIColor(red:1.00, green:1.00, blue:1.00, alpha:1.0)
        default:
            cell.backgroundColor = UIColor(red:0.48, green:0.42, blue:1.00, alpha:1.0)
            cell.talkName.textColor = UIColor(red:1.00, green:1.00, blue:1.00, alpha:1.0)
            cell.talkDescription.textColor = UIColor(red:1.00, green:1.00, blue:1.00, alpha:1.0)
        }
        
        cell.talkName.sizeToFit()
        cell.talkName.setNeedsDisplay()
        cell.talkDescription.sizeToFit()
        
        let speakers = talk.speakers.map{$0.name}.joined(separator: ", ")
        cell.talkDescription.text = speakers
        
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
        var talkInfo = track.name + "\n"
        for talk in talks {
            talkInfo += "Name: " + talk.name + "\n"
        }
        print(talkInfo)
        collectionView.reloadData()
    }
    
    func goToTalkScreen(id: Int32) {
    
    }

}
