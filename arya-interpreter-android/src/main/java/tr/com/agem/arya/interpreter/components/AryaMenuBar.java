package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.view.MenuItem;

import org.xml.sax.Attributes;

import java.util.ArrayList;

import tr.com.agem.arya.interpreter.parser.IAryaMenu;
import tr.com.agem.core.interpreter.IAryaComponent;



public class AryaMenuBar implements IAryaMenu,IAryaComponent {

    private ArrayList<IAryaMenu> menuItems;

    public AryaMenuBar(Context context, Attributes attributes, AryaWindow window) {

        menuItems =new ArrayList<>();

    }


    public ArrayList<IAryaMenu> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<IAryaMenu> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public void setComponentParent(Object o) {

        if(o instanceof AryaNavBar){
            AryaNavBar navBar = (AryaNavBar) o;
            navBar.setMenuBar(this);
        }

    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public void setLabel(String label) {
    }

    @Override
    public MenuItem.OnMenuItemClickListener getOnMenuItemClickListener() {
        return null;
    }

    public void addItem(IAryaMenu menuItem) {
        menuItems.add(menuItem);
    }

    @Override
    public void setComponentId(String s) {

    }

    @Override
    public String getComponentId() {
        return null;
    }

    @Override
    public void setComponentClassName(String s) {

    }

    @Override
    public String getComponentClassName() {
        return null;
    }

    @Override
    public void setComponentValue(String s) {

    }

    @Override
    public String getComponentValue() {
        return null;
    }

    @Override
    public void setComponentAttribute(String s) {

    }

    @Override
    public String getComponentAttribute() {
        return null;
    }

    @Override
    public String validate() {
        return null;
    }


}
