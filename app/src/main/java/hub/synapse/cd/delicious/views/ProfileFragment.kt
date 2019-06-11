package hub.synapse.cd.delicious.views

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.constraint.R.attr.content
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hub.synapse.cd.delicious.R
import kotlinx.android.synthetic.main.profile_fragment_layout.*

class ProfileFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container?.context).inflate(R.layout.profile_fragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewSelectPhotoProfile.setOnClickListener {
            //choose an Image
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        btn_save_profile.setOnClickListener {
            Toast.makeText(activity, "Not yet use", Toast.LENGTH_LONG).show()
        }

    }

//    var selectPhotoUri: Uri? = null
//    //the method which help us to check the selected photo in the phone location
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
//            //proceed and check what the selected image was...
//            selectPhotoUri = data.data
//            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectPhotoUri)
//
//            selectphoto_imageview_profile.setImageBitmap(bitmap)
//
//            btn_select_profile_photo.alpha = 0f
//        }
//    }
}