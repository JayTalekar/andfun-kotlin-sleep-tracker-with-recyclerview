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

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<TextItemViewHolder>(){

    var data = listOf<SleepNight>()
        //setter to notify whenever the data set changes
        set(value){
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = data.size

    //Called by RecyclerView to display data with the position of view to be displayed
    // and ViewHolder of which data is to be set
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()
    }

    //Called when RecyclerView needs a new ViewHolder of the given type
    //The method must inflate the required view and then wrap it with respective ViewHolder
    // and then return it
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        //inflater to inflate the required view
        val layoutInflater = LayoutInflater.from(parent.context)
        //Inflating the required view by instantiating the XML File
        val view = layoutInflater.inflate(R.layout.text_item_view,
                                                        parent, false) as TextView
        //Wrapping the view with its respective holder
        return TextItemViewHolder(view)
    }

}