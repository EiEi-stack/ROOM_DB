package et.ad.roomdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import et.ad.roomdemo.db.UserEntity
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter(val listerner:RowClickListerner) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var items = ArrayList<UserEntity>()

    fun setListData(data: ArrayList<UserEntity>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater,listerner)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            listerner.onItemClickListerner(items[position])
        }
        holder.bing(items[position])
    }

   class MyViewHolder(view: View,val listerner: RowClickListerner) : RecyclerView.ViewHolder(view) {
        val tvName = view.tvName
        val tvEmail = view.tvEmail
        val deletedUserId = view.deleteUserId

        fun bing(data: UserEntity) {
            tvName.text = data.name
            tvEmail.text = data.email
            deletedUserId.setOnClickListener {
                listerner.onDeleteUserClickListener(data)
            }


        }
    }
    interface  RowClickListerner{
        fun onDeleteUserClickListener(user:UserEntity)
        fun onItemClickListerner(user:UserEntity)
    }
}