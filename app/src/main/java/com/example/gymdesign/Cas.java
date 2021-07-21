package com.example.gymdesign;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class Cas extends AppCompatActivity {

    String test;
    private Button button2;
    private TextView textView8;
    private Button button3;
    private double ShrambaCifra;
    private Handler mHandler = new Handler();
    private LineGraphSeries<DataPoint> series;
    private double lastXPoint = -1;
    String shramba1;
    private EditText payloadText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cas_template);




        button3 = (Button) findViewById(R.id.button3);
        button2 = (Button) findViewById(R.id.button3);
        textView8 = (TextView) findViewById(R.id.textView8);
        payloadText = (EditText) findViewById(R.id.editText);


        String clientId = MqttClient.generateClientId();
        final MqttAndroidClient client =
                new MqttAndroidClient(this.getApplicationContext(),  "tcp://io.adafruit.com:1883",
                        clientId);

        try {

            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
            options.setUserName("mirko123");
            options.setPassword("8c8aad0f4baf4becac17b30921d20777".toCharArray());

            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {


                    Toast.makeText(Cas.this, "This is my Toast message!", Toast.LENGTH_LONG).show();
                    String topic = "mirko123/feeds/cas";
                    int qos = 1;
                    try {
                        IMqttToken subToken = client.subscribe(topic, qos);
                        subToken.setActionCallback(new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                Toast.makeText(Cas.this, "SUBSCRIBED!", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken,
                                                  Throwable exception) {
                                Toast.makeText(Cas.this, "napaka!", Toast.LENGTH_LONG).show();

                            }
                        });
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(Cas.this, "napaka!", Toast.LENGTH_LONG).show();


                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {



                test =  new String(message.getPayload());
                String[] separated = test.split(" ");

                textView8.setText(test);

                shramba1 = separated[0].replace("\"", "");

                ShrambaCifra = Double.parseDouble(shramba1);



                // Toast.makeText(Temperatura.this, new String(message.getPayload()) + ShrambaCifra, Toast.LENGTH_SHORT).show();
                Toast.makeText(Cas.this , shramba1, Toast.LENGTH_SHORT).show();
                test =  new String(message.getPayload());



            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = "mirko123/feeds/MejaCas";
                String payload = payloadText.getText().toString();
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                    Toast.makeText(Cas.this, "ALALLA!", Toast.LENGTH_LONG).show();
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }
            }
        });


    }



    }



