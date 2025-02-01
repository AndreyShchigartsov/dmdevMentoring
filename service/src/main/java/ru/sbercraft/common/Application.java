package ru.sbercraft.common;

import ru.sbercraft.common.controllers.NavigationController;

public class Application {
    public static void main(String[] args) {
        NavigationController navigationController = new NavigationController();
        navigationController.getNavigation();
    }
}
