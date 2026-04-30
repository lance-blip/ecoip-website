package za.co.quikle.lexio.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Onboarding : Screen("onboarding")
    data object Home : Screen("home")
    data object Chat : Screen("chat?conversationId={conversationId}") {
        fun createRoute(conversationId: String? = null): String {
            return if (conversationId != null) {
                "chat?conversationId=$conversationId"
            } else {
                "chat"
            }
        }
    }
    data object Scenario : Screen("scenario")
    data object Library : Screen("library")
    data object LibraryDetail : Screen("library_detail/{categoryId}") {
        fun createRoute(categoryId: String): String {
            return "library_detail/$categoryId"
        }
    }
    data object Profile : Screen("profile")
    data object Settings : Screen("settings")
    data object Premium : Screen("premium")
}
