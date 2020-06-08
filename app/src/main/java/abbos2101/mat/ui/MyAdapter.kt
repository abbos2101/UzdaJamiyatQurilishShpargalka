package abbos2101.mat.ui

import abbos.DatabaseCreate.database.model.MyModel
import abbos2101.mat.R
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var list: ArrayList<MyModel>? = null
    private var words: String? = null

    class ViewHolder : RecyclerView.ViewHolder {
        var tv_q: TextView? = null
        var tv_a: TextView? = null

        constructor(itemView: View) : super(itemView) {
            this.tv_q = itemView.findViewById(R.id.tv_q)
            this.tv_a = itemView.findViewById(R.id.tv_a)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = this.list?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = this.list?.get(position)
        var q = setQ(model?.lowerquestion.toString(), this.words ?: "")
        holder.tv_q?.setText(Html.fromHtml("${model?._id}. $q"))//"${model?._id}. ${model?.question}"))
        holder.tv_a?.setText(model?.answer)
    }

    fun setNewList(newList: ArrayList<MyModel>, newWords: String) {
        this.list = newList
        this.words = newWords
        this.notifyDataSetChanged()
    }

    private fun setQ(text: String, words: String): String? {
        var s = text
        var x = text.toLowerCase()
        val a = words.toClearPlace().split(" ")
        var mn = 0
        for (i in 0..a.size - 1) {
            val mx = mn + x.substring(mn).indexOf(a[i])
            s = (s.substring(0, mx) + "<a href=\"\">" + s.substring(mx, mx + a[i].length)
                    + "</a>" + s.substring(mx + a[i].length, s.length))
            x = s.toLowerCase()
            mn = mx + 18
        }
        return s
    }

    private fun String.toClearPlace(): String {
        var query = ""
        val words = this.trim().toLowerCase().split(" ")
        words.forEach {
            if (it.trim().isNotEmpty())
                query += "$it "
        }
        return query.trim()
    }
}