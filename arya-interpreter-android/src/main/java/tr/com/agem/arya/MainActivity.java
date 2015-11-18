package tr.com.agem.arya;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.concurrent.ExecutionException;

import tr.com.agem.arya.gateway.AryaInterpreterHelper;
import tr.com.agem.arya.gateway.WebServiceConnectionAsyncTask;
import tr.com.agem.arya.interpreter.AlertController;
import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ElementFunctions;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;

import tr.com.agem.core.utils.AryaUtils;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    public static String inetAddr = "192.168.1.216"; // if you use AVD(emulator) just change it to 10.0.2.2 statically or if you use GENYMOTION set it to 10.0.3.2 .

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
        final ScrollView mScrollView = (ScrollView) findViewById(R.id.scrollView);
        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_view);
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if(mScrollView.getScrollY()==0){
                    mSwipeRefreshLayout.setEnabled(true);
                }
                else{
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        refresh(mainLayout);	//TODO I just called refresh() function for simplicity, It should be implemented with more appropriate post function.
                    }
                }, 1000);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("arya");
        actionBar.hide();

        refresh(mainLayout);
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

    public void refresh(View v) {

        // Prepare initial request
        AryaRequest request = new AryaRequest();
        // Menu
        AryaRequest requestMenu = new AryaRequest();
        requestMenu.setAction("menu");
        requestMenu.setRequestType(RequestTypes.VIEW_ONLY);
        WebServiceConnectionAsyncTask connThreadMenu = new WebServiceConnectionAsyncTask("http://"+inetAddr+":8080/arya/rest/asya",requestMenu, getApplicationContext());
        String responseMenuStr=null;
        try {
            responseMenuStr = connThreadMenu.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(AryaInterpreterHelper.getjSessionId() == null || ElementFunctions.getLastPage() == null) {
            request.setAction("login");
            request.setRequestType(RequestTypes.VIEW_ONLY);

        }
        else {
            request.setAction(ElementFunctions.getLastPage());
            request.setRequestType(RequestTypes.valueOf(ElementFunctions.getReqType()));
        }


        WebServiceConnectionAsyncTask connThread = new WebServiceConnectionAsyncTask("http://"+inetAddr+":8080/arya/rest/asya",request, getApplicationContext());

        String responseStr = null;
        try {
            responseStr = connThread.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (responseStr != null && responseMenuStr !=null) {
            AryaResponse response = new AryaResponse();
            response.fromXMLString(responseStr);
            AryaResponse responseMenu = new AryaResponse();
            responseMenu.fromXMLString(responseMenuStr);

            main = new AryaMain(this,mainLayout);
            AryaInterpreterHelper.interpretResponseMenu(responseMenu, main);
            if(request.getAction().equals("login")) {
                AryaInterpreterHelper.interpretResponse(response,true, main);
            }
            else{
                AryaInterpreterHelper.interpretResponse(response,false, main);
            }

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