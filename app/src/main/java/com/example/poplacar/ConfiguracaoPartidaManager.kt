import android.content.Context
import android.content.SharedPreferences
import com.example.poplacar.ConfiguracaoPartida
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ConfiguracaoPartidaManager {
    private const val PREFS_NAME = "configuracoes_partida_prefs"
    private const val KEY_CONFIGURACOES_PARTIDA_LIST = "configuracoes_partida_list"

    private var configuracoesPartidaList = mutableListOf<ConfiguracaoPartida>()

    fun getConfiguracoesPartidaList(): List<ConfiguracaoPartida> {
        return configuracoesPartidaList
    }

    fun setConfiguracoesPartidaList(context: Context, configuracoesPartidaList: MutableList<ConfiguracaoPartida>) {
        this.configuracoesPartidaList = configuracoesPartidaList
        saveConfiguracoesPartidaList(context)
    }

    fun addConfiguracaoPartida(context: Context, configuracaoPartida: ConfiguracaoPartida) {
        configuracoesPartidaList.add(configuracaoPartida)
        saveConfiguracoesPartidaList(context)
    }

    fun removeConfiguracaoPartida(context: Context, position: Int) {
        configuracoesPartidaList.removeAt(position)
        saveConfiguracoesPartidaList(context)
    }

    fun clearConfiguracoesPartidaList(context: Context) {
        configuracoesPartidaList.clear()
        saveConfiguracoesPartidaList(context)
    }

    fun saveConfiguracoesPartidaList(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val configuracoesPartidaJson = Gson().toJson(configuracoesPartidaList)
        editor.putString(KEY_CONFIGURACOES_PARTIDA_LIST, configuracoesPartidaJson)
        editor.apply()
    }

    fun loadConfiguracoesPartidaList(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val configuracoesPartidaJson = sharedPreferences.getString(KEY_CONFIGURACOES_PARTIDA_LIST, null)
        configuracoesPartidaList = if (configuracoesPartidaJson != null) {
            val type = object : TypeToken<List<ConfiguracaoPartida>>() {}.type
            val loadedList = Gson().fromJson<List<ConfiguracaoPartida>>(configuracoesPartidaJson, type)
            mutableListOf<ConfiguracaoPartida>().apply { addAll(loadedList) }
        } else {
            mutableListOf()
        }

    }
}
