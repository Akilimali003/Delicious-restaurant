package hub.synapse.cd.delicious.model

import android.support.v4.app.Fragment

/**
 * Created by Michelo on 2019-07-11 at 13:34.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
interface ISharedFragment {
    fun openFragment(fragment: Fragment, fragment_id: Int)
}