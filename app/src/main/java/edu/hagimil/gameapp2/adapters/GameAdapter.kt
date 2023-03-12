package edu.hagimil.gameapp2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.hagimil.gameapp2.R
import edu.hagimil.gameapp2.databinding.GameItemBinding
import edu.hagimil.gameapp2.models.GameItem


//boiler plate recyclerView adapter
class GameAdapter(val games: List<GameItem>, private val callback: (GameItem) -> Unit) :
    RecyclerView.Adapter<GameAdapter.VH>() {

    class VH(val binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GameItemBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    //use info collected and binding to view in RV
    override fun onBindViewHolder(holder: VH, position: Int) {
        val game = games[position]
        holder.binding.textTitle.text = game.gameName
        holder.binding.textScore.text = if (game.metacritic == null) {
            buildString { append("Metacritic : ").append("No score yet") }
        } else {
            buildString {
                append("Metacritic : ").append(game.metacritic.toString())
            }
        }
        Picasso.get().load(game.backgroundImage).into(holder.binding.imagePoster)
        holder.binding.root.setOnClickListener {
            callback(game)
        }
    }

    override fun getItemCount() = games.size

}