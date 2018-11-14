package com.github.zackratos.ultimatebar.sample

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.github.zackratos.ultimatebar.ultimateBarBuilder
import kotlinx.android.synthetic.main.activity_fragment.*

/**
 *  @Author:  zackratos
 *  @Date:    2018/11/7
 *  @Time:    下午2:29
 *  @Email:   zhangwenchao@xiangwushuo.com
 */
class FragmentActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        ultimateBarBuilder().applyNavigation(false)
                .create()
                .immersionBar()
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, SampleFragment.newInstance(R.drawable.yurisa_5))
                    .commit()
        }

        fragment_1.setOnClickListener {
            ultimateBarBuilder().applyNavigation(false)
                    .statusDark(false)
                    .create()
                    .immersionBar()
            replaceFragment(R.drawable.yurisa_5)
        }

        fragment_2.setOnClickListener {
            ultimateBarBuilder().applyNavigation(false)
                    .statusDark(true)
                    .create()
                    .immersionBar()
            replaceFragment(R.drawable.yurisa_2)
        }

        fragment_3.setOnClickListener { replaceFragment(R.drawable.yurisa_3) }
    }

    private fun replaceFragment(@DrawableRes image: Int) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, SampleFragment.newInstance(image))
                .commit()
    }



}