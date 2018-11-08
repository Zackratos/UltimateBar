package com.github.zackratos.ultimatebar.sample

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sample.*

/**
 *  @Author:  zackratos
 *  @Date:    2018/11/7
 *  @Time:    下午4:51
 *  @Email:   zhangwenchao@xiangwushuo.com
 */
class SampleFragment: Fragment() {

    companion object {
        private const val IMAGE = "image"
        fun newInstance(@DrawableRes image: Int): SampleFragment {
            return SampleFragment().apply {
                arguments = Bundle().apply {
                    putInt(IMAGE, image)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(IMAGE)?.let { image_view.setImageResource(it) }
    }

}