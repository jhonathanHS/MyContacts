package br.edu.ifsp.ads.pdm.mycontacts.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.getSystemService
import br.edu.ifsp.ads.pdm.mycontacts.R
import br.edu.ifsp.ads.pdm.mycontacts.databinding.TileContactBinding
import br.edu.ifsp.ads.pdm.mycontacts.model.Contact

class ContactAdapter (
    context: Context,
    val contactList: MutableList<Contact>
): ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList){
    private lateinit var tcb: TileContactBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contact = contactList[position]
        val contactTileView = convertView

        if (contactTileView == null){
            //Inflo uma nova tela
            tcb = TileContactBinding.inflate(context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            parent,
            false)

            contactTileView = tcb.root
            val tileContactHolder = TileContactHolder(tcb.nameTv, tcb.emailTv)
            contactTileView.tag = tileContactHolder
        }
        with(contactTileView.tag as TileContactHolder) {
            tcb.nameTv.text = contact.name
            tcb.emailTv.text = contact.email
        }
//        val view = (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
//            R.layout.tile_contact,
//            parent,
//            false
//        )
        return contactTileView
    }
}