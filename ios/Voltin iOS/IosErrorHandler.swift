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



class IosLocalDataSource: NSObject, LocalDataSource {
    func getFavoriteTalks() -> [Talk] {
        fatalError("getFavoriteTalks() has not been implemented")
    }

    func saveRate(rate: Rate) {

    }

    func saveTalk(talk: Talk) {

    }
}

class IosExecutor: NSObject, Executor {
    var new: Kotlinx_coroutines_core_nativeCoroutineDispatcher = UI()
    var main: Kotlinx_coroutines_core_nativeCoroutineDispatcher = UI()
}

public class UI: Kotlinx_coroutines_core_nativeCoroutineDispatcher {
    override public func dispatch(context: KotlinCoroutineContext, block: Kotlinx_coroutines_core_nativeRunnable) {
        DispatchQueue.main.async {
            block.run()
        }
    }
}

class IosRemoteDataSource: NSObject, RemoteDataSource {
    
}
