package tr.com.agem.arya.interpreter.components;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tr.com.agem.arya.R;
import tr.com.agem.core.interpreter.IAryaComponent;

/**
 * Created by volkan on 09.09.2015.
 */
public class AryaNavBar extends ActionBarActivity { //TODO IAryaMenu Interface implement edilecek
    //TODO menu itemleri view den mi extend ediyor?
    //TODO 2 component olur: menu ve menuitem(iç içe yapı)


    public AryaNavBar(Context context, LinearLayout parent){

        ActionBar actionBar = ((ActionBarActivity)context).getSupportActionBar();

        actionBar.show();



    }




}
