package com.shaynek.jquiz.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaynek.jquiz.enums.Sort
import com.shaynek.jquiz.model.RedditPostData
import com.shaynek.jquiz.model.RedditResponse
import com.shaynek.jquiz.pref.Preferences
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Observable ViewModel for displaying the list of posts.
 * @constructor Initializes the data status and fetches data from the repository.
 */
class PostsViewModel(private val repository: AppRepository) : ViewModel() {

    val posts: MutableLiveData<List<RedditPostData>> = MutableLiveData()
    val dataStatus: MutableLiveData<DataStatus> = MutableLiveData()

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        dataStatus.postValue(DataStatus.LOADING)
        disposable.add(
            repository.fetchPosts(Preferences.getLastSort())
                .subscribe(this::onCluesFetched, this::onCluesFetchError))
    }

    /**
     * Clears disposables from API calls.
     */
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onSortSelected(sort: Sort?) {
        sort?.let {
            Preferences.setLastSort(it)
            dataStatus.postValue(DataStatus.LOADING)
            repository.fetchPosts(it)
                .subscribe(this::onCluesFetched, this::onCluesFetchError)
        }
    }

    private fun onCluesFetched(response: RedditResponse?) {
        posts.postValue(response?.data?.children?.map { it.data })
        dataStatus.postValue(DataStatus.SUCCESS)
    }

    private fun onCluesFetchError(e: Throwable?) {
        dataStatus.postValue(DataStatus.FAILED)
        e?.run { printStackTrace() }
    }
}