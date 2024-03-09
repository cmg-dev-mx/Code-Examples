package mx.dev.shellcore.android.profilecard.ui.route

sealed class ProfileCardBaseRoute(val route: String) {
    data object Users : ProfileCardBaseRoute("users")
}