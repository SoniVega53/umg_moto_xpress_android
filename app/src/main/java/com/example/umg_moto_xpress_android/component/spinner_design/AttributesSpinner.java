package com.example.umg_moto_xpress_android.component.spinner_design;

import java.util.ArrayList;
import java.util.List;

public class AttributesSpinner {
    private String textItemSelect;
    private int positionSelect;
    private int heightDialog = 0;
    private boolean selectItem;
    private boolean openMenu = false;
    private String title = "";
    private boolean isTitle = true;
    private List<String> options = new ArrayList<>();
    private final int spaceMenu = 10;

    public AttributesSpinner() {
    }

    public void resetAttributes() {
        selectItem = false;
        textItemSelect = "";
        positionSelect = -1;
    }

    public AttributesSpinner(int heightDialog, boolean isTitle, List<String> options) {
        this.heightDialog = heightDialog;
        this.isTitle = isTitle;
        this.options = options;
    }

    public String getTextItemSelect() {
        return textItemSelect;
    }

    public void setTextItemSelect(String textItemSelect) {
        this.textItemSelect = textItemSelect;
    }

    public int getPositionSelect() {
        return positionSelect;
    }

    public void setPositionSelect(int positionSelect) {
        this.positionSelect = positionSelect;
    }

    public int getHeightDialog() {
        return heightDialog;
    }

    public void setHeightDialog(int heightDialog) {
        this.heightDialog = heightDialog;
    }

    public boolean isSelectItem() {
        return selectItem;
    }

    public void setSelectItem(boolean selectItem) {
        this.selectItem = selectItem;
    }

    public boolean isOpenMenu() {
        return openMenu;
    }

    public void setOpenMenu(boolean openMenu) {
        this.openMenu = openMenu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getSpaceMenu() {
        return spaceMenu;
    }
}
