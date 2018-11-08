@file:Suppress("NOTHING_TO_INLINE")

package com.github.zackratos.ultimatebar

import android.app.Activity

/**
 *  @Author:  zackratos
 *  @Date:    2018/11/7
 *  @Time:    下午12:05
 *  @Email:   zhangwenchao@xiangwushuo.com
 */
inline fun Activity.with(): UltimateBar.Builder = UltimateBar.with(this)
