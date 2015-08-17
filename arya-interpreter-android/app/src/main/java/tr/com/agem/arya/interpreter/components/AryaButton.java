package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import tr.com.agem.arya.gateway.*;
import tr.com.agem.arya.interpreter.AlertController;

public class AryaButton extends Button implements IAryaComponent {

    public AryaButton(final Context context, XmlPullParser parser, final LinearLayout window) {
        super(context);
        this.setBackgroundResource(android.R.drawable.btn_default);
        this.setText(parser.getAttributeValue(null, "label"));
        this.setHeight(100);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AryaRequest request = new AryaRequest();
                request.setAction("myAction");
                request.setRequestType("D");
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("username", "alio");
                request.setParams(params);

                String url = "http://10.0.2.2:8080/arya/rest/arya";
                WebServiceConnectionAsyncTask connectionThread = new WebServiceConnectionAsyncTask(url, request, context);

                String responseStr = null;
                try {
                    responseStr = connectionThread.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(responseStr != null){
                    AryaResponse response = new AryaResponse();
                    response.fromXMLString(responseStr);
                    EditText newTextbox = new EditText(getContext());
                    newTextbox.setBackgroundResource(android.R.drawable.edit_text);
                    newTextbox.setHint(response.getData());
                    newTextbox.setTextColor(Color.parseColor("#FF000000"));
                    newTextbox.setHintTextColor(Color.parseColor("#FF999999"));
                    newTextbox.setHeight(150);
                    window.addView(newTextbox, window.getChildCount() - 1);
                }else {
                    AlertController.setAndShowPrimerAlert(context, "HATA!", "Sunucuyla Bağlantı Kurulamadı", "Tamam");
                }
            }
        });
        window.addView(this);
    }
}
