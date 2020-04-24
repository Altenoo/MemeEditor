package com.alteno.memeeditor

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.memes_fragment.*

class memesFragment : Fragment() {
    lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.memes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = Adapter() // создаём адаптер
        recyclerView.adapter = adapter // подключаем адаптер к RecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        adapter.images = listOf(Meme("https://imgflip.com/s/meme/Mocking-Spongebob.jpg"))
    }
    inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

        var images: List<Meme>? = null // список игр, которые нужно отобразить адаптеру
            set(value) {
                field = value
                notifyDataSetChanged() // этот метод обновляет вёрстку (важно его вызывать каждый раз, когда меняются данные)
            }

        /* этот метод возвращает число элементов в списке */
        override fun getItemCount(): Int {
            return images?.size ?: 0
        }

        /* этот метод создаёт вьюшки для элементов списка */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(context) // получаем из контекста создаватель макетов inflater
            val view = inflater.inflate(R.layout.layout_meme_item, parent, false) // читаем макет и на его основе создаём view
            return ViewHolder(view) // сохраняем ссылки на все маленькие вью внутри view и возвращаем ViewHolder
        }

        /* а этот метод заполняет вьюшки содержимым */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val meme = images!![position]
            Picasso.get()
                .load(meme.imageAdress)
                .error(ColorDrawable(Color.RED))
                .placeholder(ColorDrawable(Color.GRAY))
                .fit().centerCrop()
                .into(holder.imageView) // загружаем картинку
            holder.itemView.setOnClickListener { // подписываемся на нажатие
            }
        }

        /* класс вью холдера - он содержит ссылки на нужные нам вьюшки */
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView = itemView.findViewById<ImageView>(R.id.imageView) // ImageView для постера
        }

    }
}