package com.andiez.suitmediatestone.ui.main

import com.andiez.suitmediatestone.ui.base.BasePresenter

class ChooseButtonPresenter private constructor() : BasePresenter() {
    private var chosenName: String = ""
    fun setName(name: String) {
        chosenName = name
    }
}