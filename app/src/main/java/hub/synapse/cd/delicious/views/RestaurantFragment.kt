package hub.synapse.cd.delicious.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hub.synapse.cd.delicious.R

class RestaurantFragment: Fragment() {
    companion object {
        fun newInstance()=RestaurantFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container?.context).inflate(R.layout.restaurant_fragment_layout,container,false)

    }
}