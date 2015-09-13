package tr.com.agem.arya.interpreter.components;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/**
 * Created by volkan on 11.09.2015.
 */
public class AryaMenuItem implements MenuItem {

    int itemId;
    int groupId;
    int order;
    CharSequence title;
    CharSequence titleCondensed;
    boolean checkable;
    boolean checked;
    boolean enable;
    boolean disable;
    boolean visible;
    Drawable icon;
    Intent intent;

    public AryaMenuItem(int itemId, CharSequence title, boolean enable) {
        this.itemId = itemId;
        this.title = title;
        this.enable = enable;
    }

    public AryaMenuItem() {
    }



    @Override
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public MenuItem setTitle(CharSequence charSequence) {
        this.title=charSequence;
        return this;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public CharSequence getTitle() {
        return title;
    }

    @Override
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.titleCondensed=charSequence;
        return this;
    }


    @Override
    public MenuItem setTitle(int i) {
        return null;
    }

    @Override
    public CharSequence getTitleCondensed() {
        return titleCondensed;
    }

    @Override
    public MenuItem setIcon(Drawable drawable) {
        this.icon=drawable;
        return this;
    }

    @Override
    public MenuItem setIcon(int i) {
        return null;
    }

    @Override
    public Drawable getIcon() {
        return this.icon;
    }

    @Override
    public MenuItem setIntent(Intent intent) {
        this.intent=intent;
        return this;
    }

    @Override
    public Intent getIntent() {
        return this.intent;
    }

    @Override
    public MenuItem setShortcut(char c, char c1) {
        return null;
    }

    @Override
    public MenuItem setNumericShortcut(char c) {
        return null;
    }

    @Override
    public char getNumericShortcut() {
        return 0;
    }

    @Override
    public MenuItem setAlphabeticShortcut(char c) {
        return null;
    }

    @Override
    public char getAlphabeticShortcut() {
        return 0;
    }

    @Override
    public MenuItem setCheckable(boolean b) {
        this.checkable=b;
        return this;
    }


    @Override
    public boolean isCheckable() {
        return checkable;
    }

    @Override
    public MenuItem setChecked(boolean b) {
        this.checked=b;
        return this;
    }


    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public MenuItem setVisible(boolean b) {
        this.visible=b;
        return this;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public MenuItem setEnabled(boolean b) {
        this.enable=b;
        return this;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    @Override
    public boolean hasSubMenu() {
        return false;
    }

    @Override
    public SubMenu getSubMenu() {
        return null;
    }

    @Override
    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        return null;
    }

    @Override
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override
    public void setShowAsAction(int i) {

    }

    @Override
    public MenuItem setShowAsActionFlags(int i) {
        return null;
    }

    @Override
    public MenuItem setActionView(View view) {
        return null;
    }

    @Override
    public MenuItem setActionView(int i) {
        return null;
    }

    @Override
    public View getActionView() {
        return null;
    }

    @Override
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        return null;
    }

    @Override
    public ActionProvider getActionProvider() {
        return null;
    }

    @Override
    public boolean expandActionView() {
        return false;
    }

    @Override
    public boolean collapseActionView() {
        return false;
    }

    @Override
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override
    public MenuItem setOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
        return null;
    }
}
