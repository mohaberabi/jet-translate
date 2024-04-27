package com.example.translatorapp.data.repository

import com.example.translatorapp.data.api.TranslateApi
import com.example.translatorapp.data.mapper.toWordItem
import com.example.translatorapp.domain.model.WordItem
import com.example.translatorapp.domain.repository.WordRepository
import com.example.translatorapp.util.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class WordRepositoryImpl @Inject constructor(
    private val api: TranslateApi
) : WordRepository {
    override suspend fun getWordResult(word: String):
            Flow<AppResult<WordItem>> {

        return flow {
            emit(AppResult.Loading(true))

            val res = try {
                api.getWordResult(word)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(AppResult.Error(e.toString()))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(AppResult.Error(e.toString()))
                return@flow

            } catch (e: Exception) {
                e.printStackTrace()
                emit(AppResult.Error(e.toString()))
                return@flow

            }
            res?.let { word ->
                emit(AppResult.Done(word[0].toWordItem()))
                return@flow
            }
            emit(AppResult.Error("Something Went Wrong"))

        }
    }

}