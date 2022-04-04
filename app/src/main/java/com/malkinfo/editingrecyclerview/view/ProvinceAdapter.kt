package com.malkinfo.editingrecyclerview.view

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.malkinfo.editingrecyclerview.R
import com.malkinfo.editingrecyclerview.model.ProvinceInfo

class ProvinceAdapter(val c: Context, val provinceList: ArrayList<ProvinceInfo>) :
    RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>() {


    inner class ProvinceViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        var name: TextView = v.findViewById(R.id.province_name_txt)
        var location: TextView = v.findViewById(R.id.location_txt)
        var provinceImageView: ImageView = v.findViewById(R.id.province_image_view)

        var mMenus: ImageView = v.findViewById(R.id.btn_more)

        init {
            mMenus.setOnClickListener { popupMenus(it) }
        }

        private fun popupMenus(v: View) {
            val position = provinceList[adapterPosition]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editText -> {
                        val v = LayoutInflater.from(c).inflate(R.layout.detail, null)

                        val provinceName = v.findViewById<TextView>(R.id.province_name_txt_edit)
                        val provinceDetail = v.findViewById<TextView>(R.id.province_detail_txt_edit)
                        val provincePopulation =
                            v.findViewById<TextView>(R.id.province_population_txt_edit)
                        val provinceImageView = v.findViewById<ImageView>(R.id.province_imageview_edit)

                        val cbImg2Edit = v.findViewById<CheckBox>(R.id.cb_img2_edit)
                        val cbImg3Edit = v.findViewById<CheckBox>(R.id.cb_img3_edit)
                        val cbImg4Edit = v.findViewById<CheckBox>(R.id.cb_img4_edit)


                        provinceName.text = position.name
                        provinceDetail.text = position.detail
                        provincePopulation.text = position.location
                        provinceImageView.setImageResource(position.imageResourceId)

                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok") { dialog, _ ->

                                Log.i("test", position.detail)

                                var imageViewResource: Int = position.imageResourceId

                                when {
                                    cbImg2Edit.isChecked -> {
                                        imageViewResource = R.drawable.img2
                                    }
                                    cbImg3Edit.isChecked -> {
                                        imageViewResource = R.drawable.img3
                                    }
                                    cbImg4Edit.isChecked -> {
                                        imageViewResource = R.drawable.img4
                                    }
                                }
                                position.imageResourceId = imageViewResource
                                notifyDataSetChanged()
                                Toast.makeText(c, "Shool Image is Edited", Toast.LENGTH_SHORT)
                                    .show()
                                dialog.dismiss()

                            }
                            .setNegativeButton("Cancel") { dialog, _ ->
                                dialog.dismiss()

                            }
                            .create()
                            .show()

                        true
                    }
                    R.id.delete -> {
                        /**set delete*/
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Are you sure delete this Information")
                            .setPositiveButton("Yes") { dialog, _ ->
                                provinceList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c, "Deleted this Information", Toast.LENGTH_SHORT)
                                    .show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else -> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }

        fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item, parent, false)
        return ProvinceViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val newList = provinceList[position]
        holder.name.text = newList.name
        holder.provinceImageView.setImageResource(newList.imageResourceId)
        holder.location.text = newList.location.toString()

    }

    override fun getItemCount(): Int {
        return provinceList.size
    }
}