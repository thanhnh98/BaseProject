package com.thanh_nguyen.baseproject.di

import org.kodein.di.Kodein

/**
 * module for remote dependencies
 */

const val REMOTE_MODULE = "remote_module"

val remoteModule = Kodein.Module(REMOTE_MODULE, false){

}