/*
 * ViewModelState.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 30/08/19 05:37
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.projectStructure

sealed class ViewModelState {
    object Error : ViewModelState()
    object Loading : ViewModelState()
    object Success : ViewModelState()
}
