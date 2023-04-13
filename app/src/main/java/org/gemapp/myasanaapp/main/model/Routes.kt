package org.gemapp.myasanaapp.main.model

sealed class Routes(val route: String) {
    object Screen1: Routes("sport")
    object Screen2: Routes("food")
    object Screen3: Routes("settings")
    object AddNewItem: Routes("addnew")
    object Main: Routes("main")
}