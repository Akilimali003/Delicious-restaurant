package hub.synapse.cd.delicious.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hub.synapse.cd.delicious.R
import kotlinx.android.synthetic.main.addresses_fragment_layout.*

class AboutFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container?.context).inflate(R.layout.about_fragment_layout,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Test to implement some methods in the fragments
//        toast("Yes this is a Toast! ")
    }

    private fun toast(msg: String?){
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }
}