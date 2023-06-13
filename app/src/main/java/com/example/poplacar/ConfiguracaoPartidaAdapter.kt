import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poplacar.ConfiguracaoPartida
import com.example.poplacar.R

class ConfiguracaoPartidaAdapter(private val configuracoesList: List<ConfiguracaoPartida>) :
    RecyclerView.Adapter<ConfiguracaoPartidaAdapter.ConfiguracaoPartidaViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    inner class ConfiguracaoPartidaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTime1: TextView = itemView.findViewById(R.id.nomeTime1TextView)
        val textTime2: TextView = itemView.findViewById(R.id.pontosTime1TextView)
        val pontosTime1: TextView = itemView.findViewById(R.id.nomeTime2TextView)
        val pontosTime2: TextView = itemView.findViewById(R.id.pontosTime2TextView)
        val tempo: TextView = itemView.findViewById(R.id.timerTextView)

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
        holder.textTime2.text = configuracao.time2
        holder.pontosTime1.text = configuracao.pontosTimeA.toString()
        holder.pontosTime2.text = configuracao.pontosTimeB.toString()
        holder.tempo.text = configuracao.tempo
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
