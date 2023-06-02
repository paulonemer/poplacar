import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poplacar.ConfiguracaoPartida
import com.example.poplacar.Placar
import com.example.poplacar.R

class ConfiguracaoPartidaAdapter(private val configuracoesList: List<ConfiguracaoPartida>) :
    RecyclerView.Adapter<ConfiguracaoPartidaAdapter.ConfiguracaoPartidaViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    inner class ConfiguracaoPartidaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTime1: TextView = itemView.findViewById(R.id.nomeTime1TextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfiguracaoPartidaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_configuracao_partida, parent, false)
        return ConfiguracaoPartidaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConfiguracaoPartidaViewHolder, position: Int) {
        val configuracao = configuracoesList[position]
        holder.textTime1.text = configuracao.time1
    }

    override fun getItemCount(): Int {
        return configuracoesList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}
