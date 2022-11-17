package br.edu.ifsp.ads.pdm.mycontacts.model.dao

import androidx.room.*
import br.edu.ifsp.ads.pdm.mycontacts.model.entity.Contact

@Dao
interface ContactRoomDao {
    companion object Constant{
        const val CONTACT_DATABASE_FILE = "contacts_room"
        const val CONTACT_TABLE = "contact"
        const val ID_COLUMN = "id"
        const val NAME_COLUMN = "name"

    }

    //o inteiro que será retornado é o id de quem foi inserido
    @Insert
    fun createContact(contact: Contact)
    //retorna um contado do banco de acordo com o id passado, lembrando que pode ser retornado null
    @Query("SELECT * FROM $CONTACT_TABLE WHERE $ID_COLUMN = :id")
    fun retrieveContact(id: Int): Contact?
    //retorna todos os contatos da lista
    @Query("SELECT * FROM $CONTACT_TABLE ORDER BY $NAME_COLUMN")
    fun retrieveContacts():MutableList<Contact>
    //atualiza um contato, e retorna q quantidade de registros que foram alterados
    @Update
    fun updateContact(contact: Contact): Int
    //retorna a quantidade de registros que foram deletados
    @Delete
    fun deleteContact(id: Contact): Int
}