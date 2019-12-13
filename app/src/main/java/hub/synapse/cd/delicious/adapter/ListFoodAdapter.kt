package hub.synapse.cd.delicious.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.xwray.groupie.ViewHolder
import hub.synapse.cd.delicious.R

class ListFoodAdapter (private val getContext : Context, private val listFoodItem : ArrayList<FoodList>):
    ArrayAdapter<FoodList>(getContext, 0, listFoodItem){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var listLayout = convertView
        val holder : ViewHolder

        if (listLayout == null){
            val inflateList = (getContext as Activity).layoutInflater
            listLayout = inflateList!!.inflate(R.layout.food_list, parent, false)
            holder = ViewHolder()
            holder.mTextViewTitle = listLayout!!.findViewById(R.id.texte_titre)
            holder.mTextViewDescription = listLayout!!.findViewById(R.id.texte_description)
            holder.mImageListView = listLayout!!.findViewById(R.id.imageViewFoodList)

            listLayout.setTag(holder)

        } else {
            holder = listLayout.getTag() as ViewHolder
        }

        val listItem = listFoodItem [position]
        holder.mTextViewTitle!!.setText(listItem.mFoodListTextTitle)
        holder.mTextViewDescription!!.setText(listItem.mFoodListTextDescription)
        holder.mImageListView!!.setImageResource(listItem.mFoodListImage)

        return listLayout
    }

    class ViewHolder{
        internal var mTextViewTitle : TextView? = null
        internal var mTextViewDescription : TextView? = null
        internal var mImageListView : ImageView? = null
    }
}