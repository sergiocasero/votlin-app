import com.votlin.presentation.Shared
import react.dom.div
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {

    val firstSharedClass = Shared()

    window.onload = {
        render(document.getElementById("root")!!) {
            div("container") {
                +"Hello from react!! ${firstSharedClass.text}"
            }
        }
    }
}