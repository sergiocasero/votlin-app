import react.dom.div
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    window.onload = {
        render(document.getElementById("root")!!) {
            div("container") {
                +"Hello from react!!"
            }
        }
    }
}