package abbos2101.fj.ui

import abbos.DatabaseCreate.database.model.MyModel
import abbos.uzeu.database.DatabaseProvider
import abbos2101.fj.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
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
        Toast.makeText(this, "Madina Rahmatova uchun maxsus!", Toast.LENGTH_LONG).show()
    }

    private fun search(words: String) {
        adapter.setNewList(database.searchQuestion(words.toQuery()) as ArrayList<MyModel>, words)
    }

    private fun String.toQuery(): String {
        var query = "%"
        val words = this.trim().toLowerCase().split(" ")
        words.forEach {
            if (it.trim().isNotEmpty())
                query += "$it%"
        }
        return query
    }

    private fun EditText.mytext() = this.text.toString().trim().replace("_", "\\_")
}