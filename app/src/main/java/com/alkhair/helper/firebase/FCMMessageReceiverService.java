//package com.alkhair.helper.firebase;
//
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.text.Html;
//
//import androidx.core.app.NotificationCompat;
//
//import com.alkhair.R;
//import com.alkhair.Splash_View;
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//
//import java.io.InputStream;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static com.alkhair.helper.ApplicationConstant.BUNDLE_KEY_FROM_NOTIFICATION;
//import static com.alkhair.helper.ApplicationConstant.BUNDLE_KEY_NOTIFICATION_ID;
//import static com.alkhair.helper.ApplicationConstant.BUNDLE_KEY_NOTIFICATION_TYPE;
//
///**
// * Created by Hossam on 11/19/2020.
// */
//public class FCMMessageReceiverService extends FirebaseMessagingService {
//
//    private String NOTIFICATION_CHANNEL_DEFAULT = "default";
//    private String NOTIFICATION_CHANNEL_ABANDONED_CART = "abandonedCart";
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//
////        Log.d(ApplicationConstant.TAG, "FCMMessageReceiverService onMessageReceived : " + remoteMessage);
////        Log.d(ApplicationConstant.TAG, "FCMMessageReceiverService onMessageReceived: data " + remoteMessage.getData());
//
//        try {
//            Map<String, String> data = remoteMessage.getData();
//            if (data.size() > 0) {
//                String notificationTitle = data.get("title");
//                String notificationMessage = data.get("message");
//                String notificationType = data.get("type");
//                String notificationId = data.get("id");
//
//
//                Intent intent = new Intent(this, Splash_View.class);
//                switch (notificationType) {
//
//                    case "taxon":
////                        intent = new Intent(this, OrderDetailActivity.class);
//                        intent.putExtra("type", data.get("type"));
//                        intent.putExtra("taxon", data.get("taxon"));
//                        intent.putExtra("image", data.get("image"));
//                        intent.putExtra("en_US", data.get("en_US"));
//                        intent.putExtra("ar_EG", data.get("ar_EG"));
////                        Log.d("asdasdqwe", data.get("type"));
////                        Log.d("asdasdqwe", data.get("taxon"));
//                        break;
//
//                    default:
//                        break;
//                }
//
//                if (intent != null) {
//                    intent.putExtra(BUNDLE_KEY_NOTIFICATION_TYPE, notificationType);
//                    intent.putExtra(BUNDLE_KEY_FROM_NOTIFICATION, "");
//                    intent.putExtra(BUNDLE_KEY_NOTIFICATION_ID, notificationId);
//                    sendNotification(notificationTitle, notificationMessage, data.get("banner_url") == null || data.get("banner_url").isEmpty() ? "" : data.get("banner_url"), Integer.parseInt(notificationId), intent);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onNewToken(String token) {
////        Log.d("asdasdqwe", token);
//        // sending token to server here
//    }
//
//    public static String convertUTF8ToString(String s) {
//        String out = null;
//        try {
//            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
//        } catch (java.io.UnsupportedEncodingException e) {
//            return null;
//        }
//        return out;
//    }
//
//    // convert internal Java String format to UTF-8
//    public static String convertStringToUTF8(String s) {
//        String out = null;
//        try {
//            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
//        } catch (java.io.UnsupportedEncodingException e) {
//            return null;
//        }
//        return out;
//    }
//
//    private void sendNotification(String notificationTitle, String notificationContent, String bannerUrl, int notificationId, Intent intent) {
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        addNotificationChannels();
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_DEFAULT)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(Html.fromHtml(notificationTitle))
//                .setContentText(Html.fromHtml(notificationContent))
//                .setLargeIcon(icon)
//                .setAutoCancel(true)
//                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent)
//                .setPriority(NotificationCompat.PRIORITY_HIGH);
//
//        NotificationCompat.BigPictureStyle notificationBigPictureStyle = new NotificationCompat.BigPictureStyle();
//        try {
//            if (!bannerUrl.isEmpty()) {
//                Bitmap remote_picture = BitmapFactory.decodeStream((InputStream) new URL(bannerUrl).getContent());
//                notificationBigPictureStyle.bigPicture(remote_picture);
//                notificationBuilder.setStyle(notificationBigPictureStyle);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
//    }
//
//    public void addNotificationChannels() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_DEFAULT, "All Notifications", NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(notificationChannel);
//
//            NotificationChannel abandonedCartNotificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ABANDONED_CART, "Abandoned Cart", NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(abandonedCartNotificationChannel);
//
//            List<NotificationChannel> channels = new ArrayList<>();
//            channels.add(notificationChannel);
//
//            notificationManager.createNotificationChannels(channels);
//        }
//    }
//}
