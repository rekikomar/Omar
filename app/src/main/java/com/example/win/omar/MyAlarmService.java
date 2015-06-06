package com.example.win.omar;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyAlarmService extends Service {
	private CommentsDataSource datasource;
	
     private NotificationManager mManager;
    String tab ;
	Notification notification;
	String tab111;

     @Override
     public IBinder onBind(Intent arg0)
     {
       // TODO Auto-generated method stub
        return null;
     }

    @Override
    public void onCreate() 
    {
       // TODO Auto-generated method stub  
       super.onCreate();
    }

   @SuppressWarnings("static-access")
   @Override
   public void onStart(Intent intent, int startId)
   {
       super.onStart(intent, startId);
       datasource = new CommentsDataSource(this);
	   datasource.open();
	   String nomResultat;
       nomResultat=datasource.selection_Article();
       if(!nomResultat.equals("")) {
           Log.i("nomResultat", nomResultat);
           String str[] = nomResultat.split("//");
           tab = "";
           boolean b = false;
           for (int i = 0; i < str.length; i++) {
               String str1[] = str[i].split("/");

               if (Integer.parseInt(str1[7]) == 0) {
                   tab = tab + str1[3] + ", ";
                   b = true;
                   tab111 = tab.substring(0, tab.length() - 2);

               }
           }
           if (b == true) {
               mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
               Intent intent1 = new Intent(this.getApplicationContext(), NextActivity.class);
               intent1.putExtra("notifiedby", "il faut que vous mettre à jour le quantitue \n" + tab111);
               Notification notification = new Notification(R.drawable.icon, "Update Article !!!", System.currentTimeMillis());
               intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
               PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
               notification.flags |= Notification.FLAG_AUTO_CANCEL;
               notification.setLatestEventInfo(this.getApplicationContext(), "Gestion", " il faut que vous mettre à jour le quantitue \n" + tab111, pendingNotificationIntent);
               notification.vibrate = new long[]{0, 200, 100, 200, 100, 200};
               mManager.notify(0, notification);


           }

           datasource.close();
       }
    }
   

    @Override
    public void onDestroy() 
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}