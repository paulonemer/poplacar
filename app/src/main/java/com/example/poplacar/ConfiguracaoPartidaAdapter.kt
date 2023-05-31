import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poplacar.ConfiguracaoPartida
import com.example.poplacar.Placar
import com.example.poplacar.R

class ConfiguracaoPartidaAdapter(private val configuracoesList: List<ConfiguracaoPartida>) : RecyclerView.Adapter<ConfiguracaoPartidaAdapter.ConfiguracaoPartidaViewHolder>() {

    inner class ConfiguracaoPartidaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTime1: TextView = itemView.findViewById(R.id.nomeTime1TextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfiguracaoPartidaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_configuracao_partida, parent, false)
        return ConfiguracaoPartidaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConfiguracaoPartidaViewHolder, position: Int) {
        val configuracao = configuracoesList[position]
        holder.textTime1.text = configuracao.time1
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Placar::class.java)
            intent.putExtra("position", position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return configuracoesList.size
    }
}
