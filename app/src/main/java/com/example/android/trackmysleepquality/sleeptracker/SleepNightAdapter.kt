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
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

// Extend ListAdapter so that it can work with DiffUtil
// ListAdapter keeps track of list and notify its change when list is updated
// ListAdapter has a parameter which is a Callback and use it to figure out changes took placed in the list
class SleepNightAdapter(val clickListener: SleepNightListener)
    : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

    //Called by RecyclerView to display data with the position of view to be displayed
    // and ViewHolder of which data is to be set
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }


    //Called when RecyclerView needs a new ViewHolder of the given type
    //The method must inflate the required view and then wrap it with respective ViewHolder
    // and then return it
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         *  Binds the data from SleepNight instance to bind it on a List ItemView
         *  @param item Takes SleepNight instance for accessing and converting its data
         */
        fun bind(item: SleepNight, clickListener: SleepNightListener) {
            binding.sleep = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
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
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                //Wrapping the view with its respective holder
                return ViewHolder(binding)
            }
        }

    }

}

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {

    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }
}

class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}