package hub.synapse.cd.delicious.utils

import android.content.Context
import android.widget.Toast
import hub.synapse.cd.delicious.model.ToastType
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by Michelo on 2019-07-12 at 05:28.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
class ShortcutMethods {
    companion object{
        /**
         * Allow to custom message to be set in a Toast
         */
        fun makeToastMessage(context: Context, strMessage: String, duration: ToastType){
            when(duration){
                ToastType.Long -> Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show()
                ToastType.Short -> Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * Allow to show the date in a certain format
         */
        fun showShortDate(dateStr: String): String {
            try {
                val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date = fmt.parse(dateStr)
                val fmtOut = SimpleDateFormat("MMM d, yyyy")
                return fmtOut.format(date)
            } catch (e: ParseException) {
                //We can log this exception in real app
                return ""
            }
        }


    }
}