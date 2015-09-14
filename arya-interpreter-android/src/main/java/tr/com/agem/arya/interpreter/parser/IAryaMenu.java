package tr.com.agem.arya.interpreter.parser;

import android.support.v7.widget.ActionMenuView;
import android.view.MenuItem;

/**
 * Created by volkan on 14.09.2015.
 */
public interface IAryaMenu {

    String getLabel();
    void setLabel(String label);
    MenuItem.OnMenuItemClickListener getOnMenuItemClickListener();



}
