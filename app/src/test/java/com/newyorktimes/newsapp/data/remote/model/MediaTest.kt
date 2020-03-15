package com.newyorktimes.newsapp.data.remote.model

import com.lavaira.checklistapp.data.remote.model.Media
import com.lavaira.checklistapp.data.remote.model.MediaMetadata
import org.junit.Test
import org.junit.Assert.*

/****
 * Unit test for Media Model class
 * Author: Lajesh Dineshkumar
 * Created on: 4/13/19
 * Modified on: 4/13/19
 *****/
class MediaTest {

    var metaDataList : ArrayList<MediaMetadata>? = null
    var media: Media? = null



    @Test
    fun testMediaObjectNull(){
        assertNull(metaDataList)
        assertNull(media)
    }

    @Test
    fun testMediaObjectNonNull(){
        metaDataList = ArrayList()
        media = Media(metaDataList!!)
        assertNotNull(metaDataList)
        assertNotNull(media)
    }

}