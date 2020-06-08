package abbos2101.mat.ui

import abbos.DatabaseCreate.database.model.MyModel
import abbos.uzeu.database.DatabaseProvider
import abbos2101.mat.R
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter by lazy { MyAdapter() }
    private val database by lazy { DatabaseProvider.instance(this).databaseDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        rv.adapter = adapter
        edt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                search("${edt.mytext()}")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        search("")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_about)
            startActivity(Intent(this, InfoActivity::class.java))
        return true
    }

    private fun search(words: String) {
        adapter.setNewList(database.searchQuestion(words.toQuery()) as ArrayList<MyModel>, words)
    }

    private fun String.toQuery(): String = "%${this.replace(" ", "%")}%"

    private fun EditText.mytext() = this.text.toString().trim()//.replace("_", "\\_")
}