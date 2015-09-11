package tr.com.agem.arya;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;

import java.util.concurrent.ExecutionException;

import tr.com.agem.arya.gateway.AryaInterpreterHelper;
import tr.com.agem.arya.gateway.WebServiceConnectionAsyncTask;
import tr.com.agem.arya.interpreter.AlertController;
import tr.com.agem.arya.interpreter.components.AryaMain;
import tr.com.agem.arya.interpreter.components.AryaMenuItem;
import tr.com.agem.arya.interpreter.components.AryaNavBar;
import tr.com.agem.arya.interpreter.components.AryaWindow;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    private static AlertDialog alertDialog;
    private LinearLayout mainLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout) findViewById(R.id.scrollViewContentLayout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("arya");
        actionBar.hide();

        refresh();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //TODO  On Construction
        // Inflate the menu items for use in the action bar
        //MenuInflater inflater = getMenuInflater();
        menu.clear();

        AryaMenuItem mi = new AryaMenuItem(1,"V",true);
        menu.add("Test Menu");

        //inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void refresh() {
        // Prepare initial request
        AryaRequest request = new AryaRequest();
        request.setAction("master");
        request.setRequestType(RequestTypes.VIEW_ONLY);

        WebServiceConnectionAsyncTask connThread = new WebServiceConnectionAsyncTask( "http://192.168.1.106:8080/arya/rest/asya",request, getApplicationContext());

        String responseStr = null;
        try {
            responseStr = connThread.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (responseStr != null) {
            AryaResponse response = new AryaResponse();
            response.fromXMLString(responseStr);

            AryaMain main= new AryaMain(new AryaWindow(this, mainLayout),new AryaNavBar(this, mainLayout));
            AryaInterpreterHelper.interpretResponse(response, this, main);

        } else {
            AlertController.setAndShowPrimerAlert(this, "HATA!", "Sunucuyla Bağlantı Kurulamadı", "Tamam");
        }
    }

    public static AlertDialog getAlertDialog() {
        return alertDialog;
    }

    public static void setAlertDialog(AlertDialog alertDialog) {
        MainActivity.alertDialog = alertDialog;
    }

}