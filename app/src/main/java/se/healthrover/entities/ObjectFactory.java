package se.healthrover.entities;

import android.app.Activity;
import android.content.Intent;

import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;

import okhttp3.OkHttpClient;
import se.healthrover.car_service.CarManagement;
import se.healthrover.car_service.CarManagementImp;
import se.healthrover.conectivity.HealthRoverWebService;
import se.healthrover.conectivity.OkHttpWebService;
import se.healthrover.ui_activity_controller.UserInterfaceUtilities;
import se.healthrover.ui_activity_controller.error_handling.ActivityExceptionHandler;
import se.healthrover.ui_activity_controller.voice_control.RequestTask;

public class ObjectFactory {

    private static ObjectFactory instance = null;

    private ObjectFactory() {}

    public static ObjectFactory getInstance() {
        if (instance == null) {
            synchronized(ObjectFactory.class) {
                 instance = new ObjectFactory();
            }
        }
        return instance;
     }


    public CarManagement getCarManagement() {
        return new CarManagementImp();
    }

    public HealthRoverJoystick getHealthRoverJoystick(Activity activity) {
        return new HealthRoverJoystick(activity);
    }
    public HealthRoverWebService getWebService() {
        return new OkHttpWebService();
    }

    public ActivityExceptionHandler getExceptionHandler(Activity activity, HealthRoverCar healthRoverCar){
        return new ActivityExceptionHandler(activity, healthRoverCar);
    }

    public UserInterfaceUtilities getInterfaceUtilities(){
       return new UserInterfaceUtilities();
    }

    public RequestTask getRequestTask(Activity activity, SessionName session, SessionsClient sessionsClient, QueryInput queryInput){
       return new RequestTask(activity, session, sessionsClient , queryInput);
    }

    public OkHttpClient getOkHttpClient(){
        return new OkHttpClient();
    }

    public Intent getIntent(Activity oldActivity, Class<?> newActivity){
        return new Intent(oldActivity,newActivity);
    }
    public Intent getVoiceIntent(String recognizerIntent){
        return new Intent(recognizerIntent);
    }

}
