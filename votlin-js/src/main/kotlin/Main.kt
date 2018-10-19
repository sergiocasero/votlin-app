import com.votlin.client.presentation.app.app
import kotlinext.js.requireAll
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    window.onload = {
        render(document.getElementById("app")) {
            app()
        }
    }


    //val firstSharedClass = Shared()
    // window.onload = {
    //     render(document.getElementById("root")!!) {
    //         div("container") {
//
    //             val talks = mutableListOf<Talk>()
//
    //             GlobalScope.launch(Dispatchers.Default) {
    //                 // val talks = getAllTalks(CommonRepository(remote = JsRemoteDataSource(), local = JsLocalDataSource()))
    //                 // +"Hello $talks"
    //                 val talksResponse = getAllTalks(repository = CommonRepository(remote = JsRemoteDataSource(), local = JsLocalDataSource()))
    //                 talksResponse.forEach {
    //                     println(it.name)
    //                 }
    //             }
//
    //         }
    //     }
    // }

    // requireAll(kotlinext.js.require.context("src", true, js("/\\.css$/")))
}