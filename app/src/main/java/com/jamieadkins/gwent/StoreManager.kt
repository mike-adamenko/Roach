package com.jamieadkins.gwent

import android.util.Log
import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.middleware.GsonParserFactory
import okio.BufferedSource
import com.jamieadkins.gwent.main.GwentApplication
import com.nytimes.android.external.fs3.SourcePersisterFactory
import com.nytimes.android.external.store3.base.Parser
import com.nytimes.android.external.store3.base.impl.MemoryPolicy
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.nytimes.android.external.store3.base.impl.Store
import io.reactivex.Single
import java.io.IOException
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Flowable
import okhttp3.ResponseBody

object StoreManager {
    private val storeMap = hashMapOf<BarCode, Store<*, BarCode>>()

    fun generateId(vararg args: Any): String {
        var id = ""
        for (obj in args) {
            id += obj
        }
        return id
    }

    fun <T> getData(barCode: BarCode, observable: Single<ResponseBody>, expirationTime: Long): Flowable<T> {
        var store = storeMap[barCode] as Store<T, BarCode>?
        if (store == null) {
            val storeBuilder = StoreBuilder.parsedWithKey<BarCode, BufferedSource, T>()
                    .fetcher { observable.map(ResponseBody::source) }
                    .memoryPolicy(MemoryPolicy
                            .builder()
                            .setExpireAfterWrite(expirationTime)
                            .setExpireAfterTimeUnit(TimeUnit.SECONDS)
                            .build())
                    .parser(GsonParserFactory.createSourceParser<T>(provideGson(), genericType<T>()) as Parser<BufferedSource, T>)
            try {
                storeBuilder.persister(SourcePersisterFactory.create(GwentApplication.INSTANCE.applicationContext.cacheDir))
            } catch (e: IOException) {
                Log.e(StoreManager.javaClass.simpleName, "Failed to get file store.", e)
            }

            store = storeBuilder.open()

            storeMap.put(barCode, store)

        }

        return store.get(barCode)
                .concatWith(store.fetch(barCode))
                .distinct()
    }

    fun provideGson(): Gson {
        return GsonBuilder()
                .create()
    }

    fun <T> genericType() = object : TypeToken<T>() {}.type
}