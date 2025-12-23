package com.sssmobile.helpers.deeplink

import androidx.navigation.NavController

fun deeplinkToRouteStack(deeplink: String): List<String> {
    return deeplink
        .split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
}

fun NavController.navigateWithStack(
    stack: List<String>,
    clearBackStack: Boolean = true
) {
    if (stack.isEmpty()) return

    if (clearBackStack) {
        navigate(stack.first()) {
            popUpTo(graph.startDestinationId) {
                inclusive = true
            }
        }
    } else {
        navigate(stack.first())
    }

    stack.drop(1).forEach { route ->
        navigate(route)
    }
}
