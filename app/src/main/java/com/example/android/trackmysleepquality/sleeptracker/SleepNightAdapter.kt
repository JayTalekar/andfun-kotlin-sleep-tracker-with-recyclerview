/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.ViewHolder>(){

    var data = listOf<SleepNight>()
        //setter to notify whenever the data set changes
        set(value){
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = data.size

    //Called by RecyclerView to display data with the position of view to be displayed
    // and ViewHolder of which data is to be set
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


    //Called when RecyclerView needs a new ViewHolder of the given type
    //The method must inflate the required view and then wrap it with respective ViewHolder
    // and then return it
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(itemView : View): RecyclerView.ViewHolder(itemView){
        val sleepLength : TextView = itemView.findViewById(R.id.sleep_length)
        val quality : TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage : ImageView = itemView.findViewById(R.id.quality_image)

        /**
         *  Binds the data from SleepNight instance to bind it on a List ItemView
         *  @param item Takes SleepNight instance for accessing and converting its data
         */
        fun bind(item: SleepNight) {
            val res = itemView.context.resources
            sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)

            qualityImage.setImageResource(when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })
        }


        companion object {

            /**
             * Encapsulates the behaviour of Inflating the layout
             * @return Returns a ViewHolder with an inflated View
             */
            fun from(parent: ViewGroup): ViewHolder {
                //inflater to inflate the required view
                val layoutInflater = LayoutInflater.from(parent.context)
                //Inflating the required view by instantiating the XML File
                val view = layoutInflater.inflate(R.layout.list_item_sleep_night,
                        parent, false)
                //Wrapping the view with its respective holder
                return ViewHolder(view)
            }
        }

    }

}