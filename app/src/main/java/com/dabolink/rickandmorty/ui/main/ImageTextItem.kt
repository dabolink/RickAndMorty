package com.dabolink.rickandmorty.ui.main

import android.media.Image

interface ImageTextItem : TextItem {
    fun getImage(): Image
}
