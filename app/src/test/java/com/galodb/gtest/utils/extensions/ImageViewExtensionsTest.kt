package com.galodb.gtest.utils.extensions

import android.widget.ImageView
import com.galodb.gtest.R
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ImageViewExtensionsTest {

    private val imageView = mock(ImageView::class.java)

    @Test
    fun `test url empty`() {
        val url = ""
        imageView.loadImage(url)

        verify(imageView).setImageResource(R.mipmap.ic_launcher)
    }
}
