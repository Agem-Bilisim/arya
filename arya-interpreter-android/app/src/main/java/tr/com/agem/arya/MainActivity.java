package tr.com.agem.arya;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.concurrent.ExecutionException;

import tr.com.agem.arya.gateway.*;
import tr.com.agem.arya.interpreter.*;



public class MainActivity extends Activity
{
    private static final String TAG = "MainActivity";

    private static AlertDialog alertDialog;
//    private static ProgressDialog progDialog;
    private LinearLayout mainLayout;
    private Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout) findViewById(R.id.scrollViewContentLayout);

        refreshButton = (Button)findViewById(R.id.button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainLayout.removeAllViews();
                mainLayout.addView(refreshButton);

                AryaRequest request = new AryaRequest();
                request.setAction("master");
                request.setRequestType(RequestTypes.VIEW_ONLY);
                String url = "http://192.168.1.191:8080/arya/rest/hello/";
                WebServiceConnectionAsyncTask connectionThread = new WebServiceConnectionAsyncTask(url, request, getApplicationContext());
                try {
                    onGetEnd(connectionThread.execute().get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void onGetEnd(String result)
    {
        if(result != null){
            AryaResponse response = new AryaResponse();
            response.fromXMLString(result);
            AryaInterpreter.handleViewResponse(response, mainLayout, this);
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

    /*
    public static ProgressDialog getProgDialog() {
        return progDialog;
    }

    public static void setProgDialog(ProgressDialog progDialog) {
        MainActivity.progDialog = progDialog;
    }
    */

}
