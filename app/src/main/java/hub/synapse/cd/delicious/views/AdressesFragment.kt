package hub.synapse.cd.delicious.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hub.synapse.cd.delicious.R
import kotlinx.android.synthetic.main.addresses_fragment_layout.*

class AdressesFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        //Put all the code here



        return LayoutInflater.from(container?.context).inflate(R.layout.addresses_fragment_layout,container,false)
    }

//    private fun onExplorerClick(){
//        tv_explorer_kivu_restaurant.setOnClickListener {
//            Toast.makeText(this, "Yes", Toast.LENGTH_LONG).show()
//        }
//    }
}