package tr.com.agem.arya.gateway;

import android.content.Context;
import android.os.AsyncTask;


public class WebServiceConnectionAsyncTask extends AsyncTask<String,Void,String>
{
    private static final String TAG = "WebServiceConnection";
    AryaRequest request;
    String url;
    Context context;

    public WebServiceConnectionAsyncTask(String url, AryaRequest request, Context context) {
        this.request = request;
        this.url = url;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String responseStr = AryaInterpreterHelper.callUrl(url, request);
        return responseStr;
    }

    @Override
    protected void onPostExecute(String responseStr)
    {
        super.onPostExecute(responseStr);
    }

}