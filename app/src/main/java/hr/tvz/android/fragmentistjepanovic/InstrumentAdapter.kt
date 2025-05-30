package hr.tvz.android.fragmentistjepanovic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import hr.tvz.android.fragmentistjepanovic.model.Instrument

class InstrumentAdapter(
    private val context: Context,
    private val instrumentsList: ArrayList<Instrument>,
    private val onInstrumentClick: (Instrument) -> Unit
): RecyclerView.Adapter<InstrumentAdapter.InstrumentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.instrument_item, parent, false)
        return InstrumentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return instrumentsList.size
    }

    override fun onBindViewHolder(holder: InstrumentViewHolder, position: Int) {
        val currentInstrument = instrumentsList[position]

        val resId = context.resources.getIdentifier(currentInstrument.image, "drawable", context.packageName)

        holder.instrumentImage.setImageResource(resId)
        holder.instrumentName.text = currentInstrument.name

        holder.itemView.setOnClickListener {
            onInstrumentClick(currentInstrument)
        }
    }

    class InstrumentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val instrumentImage: ShapeableImageView = itemView.findViewById(R.id.instrumentImage)
        val instrumentName: TextView = itemView.findViewById(R.id.instrumentName)
    }
}