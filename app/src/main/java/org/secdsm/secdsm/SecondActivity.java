package org.secdsm.secdsm;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import helpers.MqttHelper;


public class SecondActivity extends AppCompatActivity {

    MqttHelper mqttHelper;
    TextView dataReceived;
    TextView mqttTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        dataReceived = findViewById(R.id.dataReceived);
        mqttTopic = findViewById(R.id.mqttTopic);

        startMqtt();


    }
    private void startMqtt() {
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug", topic);
                Log.w("Debug", mqttMessage.toString());


                dataReceived.setText(mqttMessage.toString());
                mqttTopic.setText(topic);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });


    }

}
