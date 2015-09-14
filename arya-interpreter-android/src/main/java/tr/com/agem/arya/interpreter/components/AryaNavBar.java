package tr.com.agem.arya.interpreter.components;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

import tr.com.agem.arya.MainActivity;
import tr.com.agem.arya.interpreter.parser.IAryaMenu;
import tr.com.agem.core.utils.AryaUtils;

public class AryaNavBar extends ActionBarActivity {


    private AryaMenuBar menuBar = null;


    public AryaNavBar(MainActivity mainActivity, LinearLayout mainLayout) {
        ActionBar actionBar = ((ActionBarActivity) mainLayout.getContext()).getSupportActionBar();
        actionBar.show();
    }

    public Menu fillMenuOptions(Menu menu) {

        ArrayList<IAryaMenu> menuItemList = menuBar.getMenuItems();

        if(AryaUtils.isNotEmpty(menuItemList))
        for (int i=0; i<menuItemList.size();i++){
            IAryaMenu aryaMenuItem = menuItemList.get(i);

            MenuItem
                    item = menu.add(aryaMenuItem.getLabel());
                    item.setOnMenuItemClickListener(aryaMenuItem.getOnMenuItemClickListener());

        }

        return menu;
    }

    public AryaMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(AryaMenuBar menuBar) {
        this.menuBar = menuBar;
    }
}
