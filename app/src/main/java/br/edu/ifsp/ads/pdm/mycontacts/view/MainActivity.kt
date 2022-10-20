package br.edu.ifsp.ads.pdm.mycontacts.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifsp.ads.pdm.mycontacts.R
import br.edu.ifsp.ads.pdm.mycontacts.databinding.ActivityMainBinding
import br.edu.ifsp.ads.pdm.mycontacts.model.Constant.EXTRA_CONTACT
import br.edu.ifsp.ads.pdm.mycontacts.model.Contact

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Data source
    private val contactList: MutableList<Contact> = mutableListOf()

    //Adapter
    private val contactAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            contactList.map { it.toString() }
        )
    }

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        fillContactsList()
        amb.contactsLv.adapter = contactAdapter

        carl = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ){
            result ->
            if (result.resultCode == RESULT_OK){
                val contact = result.data?.getParcelableExtra<Contact>(EXTRA_CONTACT)
                contact?.let { _contact ->
                    contactList.add(_contact)
                    contactAdapter.add(_contact.toString())
                    contactAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addContactMi -> {
                carl.launch(Intent(this, ContactActivity::class.java))
                true
            }
            else -> {
                false
            }
        }
    }

    private fun fillContactsList() {
        for (i in 1..10) {
            contactList.add(
                Contact(
                    id = i,
                    name = "Nome $i",
                    address = "Endereço $i",
                    phone = "Telefone $i",
                    email = "Email $i",
                )
            )
        }
    }
}