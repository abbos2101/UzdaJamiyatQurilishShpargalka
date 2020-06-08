package abbos2101.mat.ui

import abbos2101.mat.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}