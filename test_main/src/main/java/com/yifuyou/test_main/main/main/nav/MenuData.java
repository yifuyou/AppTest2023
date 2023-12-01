package com.yifuyou.test_main.main.main.nav;

import com.yifuyou.test_main.main.main.FragmentItemName;
import com.yifuyou.test_main.main.main.fragment.community.CommunityFragment;
import com.yifuyou.test_main.main.main.fragment.find.FindFragment;
import com.yifuyou.test_main.main.main.fragment.home.HomeFragment;
import com.yifuyou.test_main.main.main.fragment.user.UserFragment;
import com.yifuyou.test_main.main.main.utls.CommandUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * info
 *
 * @author Administrator
 * @since 2023/11/25
 */
public class MenuData {
    private static final String TAG = "MenuData";

    private static final List<MenuData> MENU_DATA = new ArrayList<>();

    // 启动页
    private static final int startIndex = 0;

    public static int getStartIndex() {
        return startIndex;
    }

    public static List<MenuData> getMenuData() {
        String[] fragmentNames = {
                HomeFragment.class.getName(),
                FindFragment.class.getName(),
                CommunityFragment.class.getName(),
                UserFragment.class.getName()
        };

        String[] titleName = {
                FragmentItemName.首页.name(),
                FragmentItemName.发现.name(),
                FragmentItemName.通知.name(),
                FragmentItemName.我的.name()
        };

        int[] icons = {
                CommandUtil.getDrawableId("main_home"),
                CommandUtil.getDrawableId("main_notify"),
                CommandUtil.getDrawableId("main_community"),
                CommandUtil.getDrawableId("main_user")
        };

        for (int i = 0; i < fragmentNames.length; i++) {
            MENU_DATA.add(new MenuData(0x998+i, 1, titleName[i], icons[i], fragmentNames[i]));
        }
        return MENU_DATA;
    }

    private int id;

    private int groupId = 1;

    private String title;

    private int icon;

    private String fragmentClassName;

    public MenuData(int id, int groupId, String title, int icon, String fragmentClassName) {
        this.id = id;
        this.groupId = groupId;
        this.title = title;
        this.icon = icon;
        this.fragmentClassName = fragmentClassName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFragmentClassName() {
        return fragmentClassName;
    }

    public void setFragmentClassName(String fragmentClassName) {
        this.fragmentClassName = fragmentClassName;
    }
}
