/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Just for learn.
 */

package com.yifuyou.test_main.main.main.nav;

import java.lang.ref.WeakReference;
import java.util.List;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.IdRes;
import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

/**
 * navigation 工具类
 *
 * @author Administrator
 * @since 2023/11/26
 */
public class MenuUtil {
    private static final String TAG = "MenuUtil";

    private static final List<MenuData> DATA = MenuData.getMenuData();

    public static void setNavMenu(AppCompatActivity parent, BottomNavigationView navigationView,
        @IdRes int contentLayout, @NavigationRes int defaultNavMenu, MenuActions actions) {
        Menu navigationViewMenu = navigationView.getMenu();
        NavHostFragment navHostFragment =
            (NavHostFragment)parent.getSupportFragmentManager().findFragmentById(contentLayout);
        NavController navController = null;
        if (navHostFragment == null) {
            Log.i(TAG, "onCreate: navHostFragment null");
            return;
        }

        navController = navHostFragment.getNavController();

        NavGraph graph = null;
        if (defaultNavMenu > 0) {
            graph = navController.getNavInflater().inflate(defaultNavMenu);

            if (DATA.size() > 0) {
                graph.clear();
            }
        } else {
            graph = new NavGraphNavigator(navController.getNavigatorProvider()).createDestination();
        }


        for (int i = 0; i < DATA.size(); i++) {
            MenuData menuData = DATA.get(i);

            navigationViewMenu.add(menuData.getGroupId(), menuData.getId(), Menu.NONE, menuData.getTitle());
            navigationViewMenu.getItem(i).setIcon(menuData.getIcon());
            FragmentNavigator navigator =
                new FragmentNavigator(parent, parent.getSupportFragmentManager(), defaultNavMenu);
            FragmentNavigator.Destination destination =
                navigator.createDestination().setClassName(menuData.getFragmentClassName());
            destination.setId(menuData.getId());
            graph.addDestination(destination);
        }
        graph.setStartDestination(DATA.get(MenuData.getStartIndex()).getId());
        navController.setGraph(graph);
        NavigationUI.setupWithNavController(navigationView, navController);
        final WeakReference<BottomNavigationView> navigationViewWeakReference =
            new WeakReference<>(navigationView);

        final NavController finalNavController = navController;
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                BottomNavigationView view = navigationViewWeakReference.get();
                if (view == null) {
                    finalNavController.removeOnDestinationChangedListener(this);
                    return;
                }

                for (int i = 0; i < DATA.size(); i++) {
                    if (DATA.get(i).getId() == view.getSelectedItemId()) {
                        actions.onPageChange(i);
                        return;
                    }
                }
            }
        });

    }

}
