package com.example.farrell

import android.content.Context
import android.content.pm.PackageManager
import android.telephony.SmsManager
import androidx.core.content.ContextCompat
import android.Manifest
import android.app.Activity
import android.os.Build
import android.telephony.SubscriptionManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

//fun sendSms(context: Context, phoneNumber: String, message: String): String {
//    if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
//        try {
//            val smsManager = SmsManager.getDefault()
//            smsManager.sendTextMessage("+48722183087", null, message, null, null)
//            return "Wiadomość wysłana"
//        } catch (e: Exception) {
//            return "Nie udało się, ${e.message}"
//        }
//    } else {
//        return "Brak pozwolenia na wysłanie SMS"
//    }
//}

//fun sendSms(context: Context, phoneNumber: String, message: String): String {
//    if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
//        return try {
//            val smsManager = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//                context.getSystemService(SmsManager::class.java)
//            } else {
//                SmsManager.getDefault()
//            }
//
//            if (smsManager == null) {
//                return "Nie można uzyskać dostępu do SmsManager"
//            }
//
//            smsManager.sendTextMessage("+48$phoneNumber", null, message, null, null)
//            "SMS wysłany pomyślnie"
//        } catch (e: Exception) {
//            "Błąd przy wysyłaniu SMS: ${e.message}"
//        }
//    } else {
//        return "Brak uprawnień do wysyłania SMS"
//    }
//}

//fun sendSms(context: Context, phoneNumber: String, message: String): String {
//    if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
//        try {
//            // on below line initializing sms manager.
//            val smsManager: SmsManager = SmsManager.getDefault()
//            // on below line sending sms
//            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
//
//            return "Wiadomość wysłana"
//        } catch (e: Exception) {
//            // on below line handling error and
//            // displaying toast message.
//            return "Nie udało się, ${e.message}"
//        }
//    } else {
//        return "Brak pozwolenia na wysłanie SMS"
//    }
//}

//fun sendSms(context: Context, phoneNumber: String, message: String): String {
//    if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS)
//        == PackageManager.PERMISSION_GRANTED
//    ) {
//        return try {
//            val smsManager = context.getSystemService(SmsManager::class.java)
//
//            val formattedNumber = if (phoneNumber.startsWith("+")) {
//                phoneNumber
//            } else {
//                "+48$phoneNumber"
//            }
//
//            smsManager.sendTextMessage(formattedNumber, null, message, null, null)
//            "Wiadomość wysłana"
//        } catch (e: Exception) {
//            "Nie udało się wysłać wiadomości: ${e.message}"
//        }
//    } else {
//        return "Brak pozwolenia na wysłanie SMS"
//    }
//}

//fun sendSms(context: Context, phoneNumber: String, message: String): String {
//    if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
//        return try {
//
//            val subscriptionId = SubscriptionManager.getDefaultSubscriptionId()
//            val smsManager = SmsManager.getSmsManagerForSubscriptionId(subscriptionId)
//
//            val formattedNumber = if (phoneNumber.startsWith("+")) {
//                phoneNumber
//            } else {
//                "+48$phoneNumber"
//            }
//            smsManager.sendTextMessage(formattedNumber, null, message, null, null)
//            "Wiadomość wysłana"
//        } catch (e: Exception) {
//            "Nie udało się, ${e.message}"
//        }
//    } else {
//        return "Brak pozwolenia na wysyłanie SMS"
//    }
//}

//fun sendSms(context: Context, phoneNumber: String, message: String): String {
//    if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
//        return try {
//            val subscriptionManager = context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager
//            val activeSubs = subscriptionManager.activeSubscriptionInfoList
//            for (sub in activeSubs) {
//                Log.d("SMS", "Active SIM: ${sub.subscriptionId}, Slot: ${sub.simSlotIndex}, Carrier: ${sub.carrierName}")
//            }
//            val subId = activeSubs[0].subscriptionId  // lub wybierz konkretną SIM 2
//            val smsManager = SmsManager.getSmsManagerForSubscriptionId(subId)
//
//
//            val formattedNumber = if (phoneNumber.startsWith("+")) {
//                phoneNumber
//            } else {
//                "+48$phoneNumber"
//            }
//            smsManager.sendTextMessage(formattedNumber, null, message, null, null)
//            "Wiadomość wysłana"
//        } catch (e: Exception) {
//            "Nie udało się, ${e.message}"
//        }
//    } else {
//        return "Brak pozwolenia na wysyłanie SMS"
//    }
//}

//fun requestSmsPermission(activity: Activity) {
//    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS)
//        != PackageManager.PERMISSION_GRANTED
//    ) {
//        ActivityCompat.requestPermissions(
//            activity,
//            arrayOf(Manifest.permission.SEND_SMS),
//            1001 // dowolny kod żądania
//        )
//    }
//}

//@RequiresApi(Build.VERSION_CODES.S)
fun sendSms(context: Context, phoneNumber: String, message: String): String {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
        return try {
            val smsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                context.getSystemService<SmsManager>(SmsManager::class.java).createForSubscriptionId(1)
            } else {
                SmsManager.getDefault()
            }
//            val smsManager = context.getSystemService<SmsManager>(SmsManager::class.java).createForSubscriptionId(1)

            val formattedNumber = if (phoneNumber.startsWith("+")) {
                phoneNumber
            } else {
                "+48$phoneNumber"
            }
            smsManager.sendTextMessage(formattedNumber, null, message, null, null)
            "Wiadomość wysłana"
        } catch (e: Exception) {
            "Nie udało się, ${e.message}"
        }
    } else {
        return "Brak pozwolenia na wysyłanie SMS"
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetPermissions(context: Context){
    val multiplePermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_PHONE_NUMBERS
        )
    )
    DisposableEffect(key1 = multiplePermissions) {
        multiplePermissions.launchMultiplePermissionRequest()
        onDispose { }
    }
}