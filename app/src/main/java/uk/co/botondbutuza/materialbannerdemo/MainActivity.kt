package uk.co.botondbutuza.materialbannerdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_rv.view.*
import uk.co.botondbutuza.materialbanner.BannerNotification

class MainActivity : AppCompatActivity() {


    // Lifecycle.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = Adapter(createItems())

        val banner = BannerNotification.make(recycler, "This is a test message that is rather long.")
        Handler().postDelayed({ banner.show() }, 2500)
    }


    // Internal.

    private fun createItems(): List<String> {
        val items = mutableListOf<String>()

        for (i in 1..25) {
            items.add(i.toString())
        }

        return items
    }


    private class Adapter(private val items: List<String>) : RecyclerView.Adapter<Adapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
            return Holder(view)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(items[position])
        }


        class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(item: String) {
                itemView.text.text = "Recycler item $item"
            }
        }
    }
}
