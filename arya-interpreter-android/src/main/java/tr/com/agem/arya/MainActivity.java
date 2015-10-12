package tr.com.agem.arya;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.ExecutionException;

import tr.com.agem.arya.gateway.AryaInterpreterHelper;
import tr.com.agem.arya.gateway.WebServiceConnectionAsyncTask;
import tr.com.agem.arya.interpreter.AlertController;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.utils.AryaUtils;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    private static AlertDialog alertDialog;
    private LinearLayout mainLayout;
    private AryaMain main;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //for (Wrapped android.os.NetworkOnMainThreadException) exception abaout threads wich use network
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout) findViewById(R.id.scrollViewContentLayout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("arya");
        actionBar.hide();



        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.clear();
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(AryaUtils.isNotEmpty(main))
        if(AryaUtils.isNotEmpty(main.getAryaNavBar())){
            menu = main.getAryaNavBar().fillMenuOptions(menu);
        }

        return true;
    }

    public void refresh() {
        // Prepare initial request
        AryaRequest request = new AryaRequest();
        request.setAction("login");
        request.setRequestType(RequestTypes.VIEW_ONLY);

        //request.setAction("genel.duyuru.list");
        //request.setRequestType(RequestTypes.ALL);


        WebServiceConnectionAsyncTask connThread = new WebServiceConnectionAsyncTask("http://192.168.1.211:8080/arya/rest/asya",request, getApplicationContext());

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


            main = new AryaMain(this,mainLayout);

            AryaInterpreterHelper.interpretResponse(response, main);

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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}