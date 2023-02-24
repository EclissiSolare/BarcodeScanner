package com.example.progettoletturadati;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progettoletturadati.APIDir.DataModel;
import com.example.progettoletturadati.APIDir.RetrieveFeedTask;
import com.example.progettoletturadati.APIDir.Singleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActivityInsert extends AppCompatActivity {
    private EditText mEditTextLabelID;
    private EditText mEditTextItemID;
    // API URL
    private static final String EXTRA_PROFILENAME = "DWDataCapture1";
    // DataWedge Extras
    private static final String EXTRA_GET_VERSION_INFO = "com.symbol.datawedge.api.GET_VERSION_INFO";
    private static final String EXTRA_KEY_APPLICATION_NAME = "com.symbol.datawedge.api.APPLICATION_NAME";
    private static final String EXTRA_KEY_NOTIFICATION_TYPE = "com.symbol.datawedge.api.NOTIFICATION_TYPE";
    private static final String EXTRA_SOFT_SCAN_TRIGGER = "com.symbol.datawedge.api.SOFT_SCAN_TRIGGER";
    private static final String EXTRA_RESULT_NOTIFICATION = "com.symbol.datawedge.api.NOTIFICATION";
    private static final String EXTRA_REGISTER_NOTIFICATION = "com.symbol.datawedge.api.REGISTER_FOR_NOTIFICATION";
    private static final String EXTRA_UNREGISTER_NOTIFICATION = "com.symbol.datawedge.api.UNREGISTER_FOR_NOTIFICATION";
    private static final String EXTRA_SET_CONFIG = "com.symbol.datawedge.api.SET_CONFIG";
    private static final String EXTRA_RESULT_NOTIFICATION_TYPE = "NOTIFICATION_TYPE";
    private static final String EXTRA_KEY_VALUE_SCANNER_STATUS = "SCANNER_STATUS";
    private static final String EXTRA_KEY_VALUE_PROFILE_SWITCH = "PROFILE_SWITCH";
    private static final String EXTRA_KEY_VALUE_CONFIGURATION_UPDATE = "CONFIGURATION_UPDATE";
    private static final String EXTRA_KEY_VALUE_NOTIFICATION_STATUS = "STATUS";
    private static final String EXTRA_SEND_RESULT = "SEND_RESULT";
    private static final String EXTRA_EMPTY = "";
    private static final String EXTRA_RESULT = "RESULT";
    private static final String EXTRA_RESULT_INFO = "RESULT_INFO";
    private static final String EXTRA_COMMAND = "COMMAND";
    // DataWedge Actions
    private static final String ACTION_DATAWEDGE = "com.symbol.datawedge.api.ACTION";
    private static final String ACTION_RESULT_NOTIFICATION = "com.symbol.datawedge.api.NOTIFICATION_ACTION";
    private static final String ACTION_RESULT = "com.symbol.datawedge.api.RESULT_ACTION";
    // private variables
    private Boolean bRequestSendResult = false;
    final String LOG_TAG = "DataCapture1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Bundle profileConfig = new Bundle();
        profileConfig.putString("PROFILE_NAME", EXTRA_PROFILENAME);
        profileConfig.putString("PROFILE_ENABLED", "true");
        profileConfig.putString("CONFIG_MODE", "UPDATE");  // Update specified settings in profile
        // PLUGIN_CONFIG bundle properties
        Bundle barcodeConfig = new Bundle();
        barcodeConfig.putString("PLUGIN_NAME", "BARCODE");
        barcodeConfig.putString("RESET_CONFIG", "true");
        // PARAM_LIST bundle properties
        Bundle barcodeProps = new Bundle();
        barcodeProps.putString("scanner_selection", "auto");
        barcodeProps.putString("scanner_input_enabled", "true");
        barcodeProps.putString("decoder_code128", "true");
        barcodeProps.putString("decoder_code39", "false");
        barcodeProps.putString("decoder_ean13", "true");
        barcodeProps.putString("decoder_upca", "false");
        barcodeProps.putString("decoder_datamatrix", "false");
        barcodeProps.putString("decoder_qrcode", "false");
        // Bundle "barcodeProps" within bundle "barcodeConfig"
        barcodeConfig.putBundle("PARAM_LIST", barcodeProps);
        // Place "barcodeConfig" bundle within main "profileConfig" bundle
        profileConfig.putBundle("PLUGIN_CONFIG", barcodeConfig);
        // Create APP_LIST bundle to associate app with profile
        Bundle appConfig = new Bundle();
        appConfig.putString("PACKAGE_NAME", getPackageName());
        appConfig.putStringArray("ACTIVITY_LIST", new String[]{"*"});
        profileConfig.putParcelableArray("APP_LIST", new Bundle[]{appConfig});
        sendDataWedgeIntentWithExtra(ACTION_DATAWEDGE, EXTRA_SET_CONFIG, profileConfig);
        Bundle b = new Bundle();
        b.putString(EXTRA_KEY_APPLICATION_NAME, getPackageName());
        b.putString(EXTRA_KEY_NOTIFICATION_TYPE, "SCANNER_STATUS");     // register for changes in scanner status
        sendDataWedgeIntentWithExtra(ACTION_DATAWEDGE, EXTRA_REGISTER_NOTIFICATION, b);
        registerReceivers();
        // Get DataWedge version
        // Use GET_VERSION_INFO: http://techdocs.zebra.com/datawedge/latest/guide/api/getversioninfo/
        sendDataWedgeIntentWithExtra(ACTION_DATAWEDGE, EXTRA_GET_VERSION_INFO, EXTRA_EMPTY);    // must be called after registering BroadcastReceiver
        setupEditTextListener();
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.insert);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        return true;
                    case R.id.ean:
                        finish();
                        startActivity(new Intent(getApplicationContext(),ActivityEAN.class));
                        return true;
                    case R.id.labels:
                        finish();
                        startActivity(new Intent(getApplicationContext(),ActivityLabels.class));
                        return true;
                    case R.id.info:
                        finish();
                        startActivity(new Intent(getApplicationContext(),ActivityInfo.class));
                        return true;
                    case R.id.insert:
                        return true;
                }
                return false;
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Matching");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.turchese)));

    }
    // Toggle soft scan trigger from UI onClick() event
    public void ToggleSoftScanTrigger (View view){
        sendDataWedgeIntentWithExtra(ACTION_DATAWEDGE, EXTRA_SOFT_SCAN_TRIGGER, "TOGGLE_SCANNING");
    }
    private void registerReceivers() {

        Log.d(LOG_TAG, "registerReceivers()");

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_RESULT_NOTIFICATION);   // for notification result
        filter.addAction(ACTION_RESULT);                // for error code result
        filter.addCategory(Intent.CATEGORY_DEFAULT);    // needed to get version info
        // register to received broadcasts via DataWedge scanning
        filter.addAction(getResources().getString(R.string.activity_intent_filter_action));
        filter.addAction(getResources().getString(R.string.activity_action_from_service));
        registerReceiver(myBroadcastReceiver, filter);
    }
    // Unregister scanner status notification
    public void unRegisterScannerStatus() {
        Log.d(LOG_TAG, "unRegisterScannerStatus()");
        Bundle b = new Bundle();
        b.putString(EXTRA_KEY_APPLICATION_NAME, getPackageName());
        b.putString(EXTRA_KEY_NOTIFICATION_TYPE, EXTRA_KEY_VALUE_SCANNER_STATUS);
        Intent i = new Intent();
        i.setAction(ACTION);
        i.putExtra(EXTRA_UNREGISTER_NOTIFICATION, b);
        this.sendBroadcast(i);
    }
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Bundle b = intent.getExtras();

            Log.d(LOG_TAG, "DataWedge Action:" + action);

            if (action.equals(getResources().getString(R.string.activity_intent_filter_action))) {
                //  Received a barcode scan
                try {
                    displayScanResult(intent, "via Broadcast");
                }
                catch (Exception e) {
                    //  Catch error if the UI does not exist when we receive the broadcast...
                }
            }
            else if (action.equals(ACTION_RESULT))
            {
                // Register to receive the result code
                if ((intent.hasExtra(EXTRA_RESULT)) && (intent.hasExtra(EXTRA_COMMAND)))
                {
                    String command = intent.getStringExtra(EXTRA_COMMAND);
                    String result = intent.getStringExtra(EXTRA_RESULT);
                    String info = "";

                    if (intent.hasExtra(EXTRA_RESULT_INFO))
                    {
                        Bundle result_info = intent.getBundleExtra(EXTRA_RESULT_INFO);
                        Set<String> keys = result_info.keySet();
                        for (String key : keys) {
                            Object object = result_info.get(key);
                            if (object instanceof String) {
                                info += key + ": " + object + "\n";
                            } else if (object instanceof String[]) {
                                String[] codes = (String[]) object;
                                for (String code : codes) {
                                    info += key + ": " + code + "\n";
                                }
                            }
                        }
                        Log.d(LOG_TAG, "Command: "+command+"\n" +
                                "Result: " +result+"\n" +
                                "Result Info: " + info + "\n");
                        Toast.makeText(getApplicationContext(), "Error Resulted. Command:" + command + "\nResult: " + result + "\nResult Info: " +info, Toast.LENGTH_LONG).show();
                    }
                }
            }
            // Register for scanner change notification
            else if (action.equals(ACTION_RESULT_NOTIFICATION))
            {
                if (intent.hasExtra(EXTRA_RESULT_NOTIFICATION))
                {
                    Bundle extras = intent.getBundleExtra(EXTRA_RESULT_NOTIFICATION);
                    String notificationType = extras.getString(EXTRA_RESULT_NOTIFICATION_TYPE);

                    if (notificationType != null)
                    {
                        switch (notificationType) {
                            case EXTRA_KEY_VALUE_SCANNER_STATUS:
                                // Change in scanner status occurred
                                String displayScannerStatusText = extras.getString(EXTRA_KEY_VALUE_NOTIFICATION_STATUS);
                                //Toast.makeText(getApplicationContext(), displayScannerStatusText, Toast.LENGTH_SHORT).show();
                                final TextView lblScannerStatus = (TextView) findViewById(R.id.txtInsertStatus);
                                lblScannerStatus.setText(displayScannerStatusText);
                                break;
                            case EXTRA_KEY_VALUE_PROFILE_SWITCH:
                                // Received change in profile
                                // For future enhancement
                                break;
                            case EXTRA_KEY_VALUE_CONFIGURATION_UPDATE:
                                // Configuration change occurred
                                // For future enhancement
                                break;
                        }
                    }
                }
            }
        }
    };
    private void setupEditTextListener() {
        mEditTextItemID = findViewById(R.id.editTextInsertEan);
        mEditTextLabelID=findViewById(R.id.editTextInsertLabel);
        mEditTextItemID.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String a = v.getText().toString();
                addData(a);
                Singleton.getInstance().setDatoEAN(a);
                return true;
            }
            return false;
        });
        mEditTextLabelID.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String a = v.getText().toString();
                addData(a);
                Singleton.getInstance().setDatoLabels(a);
                return true;
            }
            return false;
        });
    }
    //mostra a schermo i codici scannerizzati
    private void displayScanResult(Intent initiatingIntent, String howDataReceived) {
        // store decoded data
        String decodedData = initiatingIntent.getStringExtra(getResources().getString(R.string.datawedge_intent_key_data));
        // store decoder type
        addData(decodedData);
        Singleton.getInstance().setDatoEAN(decodedData);
    }
    public void onClickInsert(View view) throws Exception {
        String itemID = mEditTextItemID.getText().toString();
        String labelID = mEditTextLabelID.getText().toString();

        // Call the API to insert the new label and item IDs
        insertDataintoAPI(labelID,itemID);
    }
    private Set<String> mData = new HashSet<>();
    //metodo richiamato per aggiungere dentro l'arraylist
    public void addData(String data1){
        mEditTextItemID = findViewById(R.id.editTextInsertEan);
        mEditTextLabelID=findViewById(R.id.editTextInsertLabel);
        String key = data1;
        DataModel dataModel = new DataModel();
        if (!mData.contains(key)){
            mData.add(key);
            dataModel.setData1(data1);
        }

    }
    private void sendDataWedgeIntentWithExtra(String action, String extraKey, Bundle extras) {
        Intent dwIntent = new Intent();
        dwIntent.setAction(action);
        dwIntent.putExtra(extraKey, extras);
        if (bRequestSendResult)
            dwIntent.putExtra(EXTRA_SEND_RESULT, "true");
        this.sendBroadcast(dwIntent);
    }
    private void sendDataWedgeIntentWithExtra(String action, String extraKey, String extraValue) {
        Intent dwIntent = new Intent();
        dwIntent.setAction(action);
        dwIntent.putExtra(extraKey, extraValue);
        if (bRequestSendResult)
            dwIntent.putExtra(EXTRA_SEND_RESULT, "true");
        this.sendBroadcast(dwIntent);
    }
    protected void onResume() {
        super.onResume();
        registerReceivers();
    }
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcastReceiver);
        unRegisterScannerStatus();
    }
    protected void onDestroy()
    {
        super.onDestroy();
    }
    protected void onStart()
    {
        super.onStart();
    }
    protected void onStop()
    {
        super.onStop();
    }

    public void insertDataintoAPI(String labelId, String itemId) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    OkHttpClient client = new OkHttpClient().newBuilder().build();
                    MediaType mediaType = MediaType.parse("application/json");
                    String json = "[{\"labelId\":\"" + params[0] + "\",\"registrationCode\":null,\"items\":[{\"itemId\":\"" + params[1] + "\"}]}]";
                    RequestBody body = RequestBody.create(mediaType, json);
                    Request request = new Request.Builder()
                            .url("https://api-eu.vusion.io/vlink-pro/v1/stores/retex_it.vlab/labels/matchings")
                            .method("POST", body)
                            .addHeader("Ocp-Apim-Subscription-Key", "396b91329ae14428b67048372c7d9ab7")
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response = client.newCall(request).execute();
                    return response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    Toast.makeText(ActivityInsert.this, "Inserimento riuscito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ActivityInsert.this, "Error occurred while calling API", Toast.LENGTH_LONG).show();
                }
            }
        }.execute(labelId, itemId);
    }



}
