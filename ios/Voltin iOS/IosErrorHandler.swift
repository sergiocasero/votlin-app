//
//  IosErrorHandler.swift
//  Voltin iOS
//
//  Created by Daniel Llanos Muñoz on 14/10/2018.
//  Copyright © 2018 votlin. All rights reserved.
//

import Foundation
import ios

class IosErrorHandler: NSObject, ErrorHandler {
    func convert(error: ios.Error) -> String {
        return ""
    }
}

class IosLocalDataSource :NSObject,LocalDataSource {
    
    private var delegate: UserDefaults = UserDefaults.standard
    
    func getFavoriteTalks() -> [Talk] {
        return delegate.object(forKey: "Talk") as! [Talk]
    }
    
    func saveRate(rate: Rate) {
        delegate.set(rate.value, forKey: "Rate")
    }
    
    func saveTalk(talk: Talk) {
        delegate.set(talk, forKey: "Talk")
    }
    
    func getRate(talkId: Int32) -> Int32 {
        return Int32(delegate.integer(forKey: "Rate"))
    }
}

class IosExecutor: NSObject, Executor {
    var main: Kotlinx_coroutines_core_nativeCoroutineDispatcher = UI()
}

public class UI: Kotlinx_coroutines_core_nativeCoroutineDispatcher {
    override public func dispatch(context: KotlinCoroutineContext, block: Kotlinx_coroutines_core_nativeRunnable) {
        DispatchQueue.main.async {
            block.run()
        }
    }
}
