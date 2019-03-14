import com.votlin.client.presentation.app.app
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    window.onload = {
        render(document.getElementById("app")) {
            app()
        }
    }
}